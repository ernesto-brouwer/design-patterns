package org.neto.sudoku;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellFactory;
import org.neto.sudoku.domain.impl.CellFactoryImpl;
import org.neto.sudoku.domain.impl.LinkedCellImpl;
import org.neto.sudoku.io.SudokuReader;
import org.neto.sudoku.io.impl.SudokuReaderImpl;
import org.neto.sudoku.services.OccurrenceCounter;
import org.neto.sudoku.services.impl.CounterUtil;
import org.neto.sudoku.strategies.impl.LineSetStrategy;
import org.neto.sudoku.strategies.impl.SetStrategy;
import org.neto.sudoku.strategies.impl.SimpleStrategy;
import org.neto.sudoku.strategies.impl.SimpleStrategyChain;

public class Sudoku{
	private static final Logger LOGGER = 
			Logger.getLogger(Sudoku.class.getName());
	
	private SudokuReader reader;
	private SimpleStrategyChain chain;
	
	public Sudoku(){
		final CellFactory factory = new CellFactoryImpl(LinkedCellImpl.class);
		reader = new SudokuReaderImpl();
		reader.setFactory(factory);
		
		final OccurrenceCounter counter = new CounterUtil();
		chain = new SimpleStrategyChain();
		chain.setCounter(counter);
		chain.addStrategy(new SimpleStrategy());
		chain.addStrategy(new LineSetStrategy());
		chain.addStrategy(new SetStrategy());
	}
	
	@Test
	public void completeTest(){
		final Board board = readBoard("very_hard_006.sdk");
		System.out.println(board.toBoardString());
		validateBoard(board);
		
		//Iteracion de solucion
		boolean change;
		do{
			change = false;
			for(int i = 0; i < 9; i++){
				change |= chain.apply(board.getRow(i), board);
				change |= chain.apply(board.getColumn(i), board);
				change |= chain.apply(board.getBox(i), board);
			}
		}while(change);
		System.out.println(board.toBoardString());
		
		validateBoard(board);
		
		printUnsolved(board);
	}
	
	private Board readBoard(final String file){
		Board board = null;
		final ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try(final InputStream input = cl.getResourceAsStream(file)){
			board = reader.read(input);
		}catch(IOException ex){
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		return board;
	}
	
	private void validateBoard(final Board board){
		for(int i = 0; i < 9; i++){
			Assert.assertTrue("El renglon " + i + " no es correcto", board.getRow(i).isCorrect());
		}
		for(int i = 0; i < 9; i++){
			Assert.assertTrue("La columna " + i + " no es correcto", board.getColumn(i).isCorrect());
		}
		for(int i = 0; i < 9; i++){
			Assert.assertTrue("La region " + i + " no es correcto", board.getBox(i).isCorrect());
		}
	}
	
	private void printUnsolved(final Board board){
		if(board.isResolved()){
			return;
		}
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				final Cell cell = board.getCell(i, j);
				if(cell.isResolved()){
					System.out.print(cell.getValue() + ", ");
				}else{
					System.out.print(cell.toString().replace("value: 0, possibilities:", "") + ", ");
				}
			}
			System.out.println();
		}
		
		Assert.assertTrue("No ha sido resulto por completo", board.isResolved());
	}
}
