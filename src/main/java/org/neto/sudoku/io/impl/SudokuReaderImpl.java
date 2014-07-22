package org.neto.sudoku.io.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellFactory;
import org.neto.sudoku.domain.impl.BoardImpl;
import org.neto.sudoku.io.SudokuReader;

public class SudokuReaderImpl implements SudokuReader{
	private static final Logger LOGGER =
			Logger.getLogger(SudokuReaderImpl.class.getName());

	private CellFactory factory;

	public CellFactory getFactory(){
		return factory;
	}

	@Override
	public void setFactory(final CellFactory factory){
		this.factory = factory;
	}
	
	@Override
	public Board read(final File file){
		Board board;
		try(final Scanner scanner = new Scanner(file)){
			board = process(scanner);
		}catch(FileNotFoundException ex){
			board = null;
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		return board;
	}

	@Override
	public Board read(final InputStream input){
		Board board;
		try(final Scanner scanner = new Scanner(input)){
			board = process(scanner);
		}
		return board;
	}
	
	private Board process(final Scanner scanner){
		final Board board = new BoardImpl(factory);
		String[] line;
		byte row = 0;
		while(scanner.hasNext()){
			line = scanner.nextLine().trim().split(",");
			String str;
			for(byte i = 0; i < line.length; i++){
				str = line[i].trim();
				if(!str.isEmpty() && !str.equals("0")){
					board.getCell(row, i).setValue(Byte.parseByte(str));
				}
			}
			row++;
		}
		
		return board;
	}
}
