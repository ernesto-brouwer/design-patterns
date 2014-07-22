package org.neto.sudoku.strategies.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;
import org.neto.sudoku.domain.impl.CellSetImpl;
import org.neto.sudoku.domain.impl.LinkedCellImpl;
import org.neto.sudoku.services.Occurrence;
import org.neto.sudoku.services.OccurrenceCounter;
import org.neto.sudoku.services.impl.CounterUtil;
import org.neto.sudoku.strategies.Strategy;

public class SetStrategyTest{
	
	private Strategy strategy;
	private OccurrenceCounter counter;
	
	public SetStrategyTest(){
		strategy = new SetStrategy();
		counter = new CounterUtil();
	}

	/**
	 * Test of getName method, of class SetStrategy.
	 */
	@Test
	public void testGetName(){
		System.out.println("getName");
		assertEquals("set", strategy.getName());
	}

	/**
	 * Test of apply method, of class SetStrategy.
	 */
	@Test
	public void testApply(){
		System.out.println("apply");
//		final CellSet set = hidenTwoSubset();
//		final CellSet set = getCellSet();
		final CellSet set = getThreeDigitSubset();
		System.out.println("CellSet: initial state");
		for(Cell cell : set){
			System.out.println(cell);
		}
		System.out.println("\n");
		
		final List<Occurrence> occurrences = counter.findOccurrences(set);
		System.out.println("Occurrences");
		for(Occurrence occ : occurrences){
			System.out.println(occ);
		}
		System.out.println("\n");
		
		strategy.apply(set, occurrences, null);

		System.out.println("CellSet: initial state");
		for(Cell cell : set){
			System.out.println(cell);
		}
		System.out.println("\n");
	}
	
	private CellSet hidenTwoSubset(){
		final Cell[] cells = new Cell[9];
		for(int i = 0; i < 4; i++){
			cells[i] = new LinkedCellImpl((byte)(i + 1));
		}
		final List<Byte> pos = Arrays.asList(
				new Byte[]{(byte)5, (byte)6, (byte)7});
		cells[4] = new LinkedCellImpl(pos);
		cells[5] = new LinkedCellImpl(pos);
		cells[6] = new LinkedCellImpl(pos);
//		cells[6] = new LinkedCellImpl(Arrays.asList(
//				new Byte[]{(byte)6, (byte)8, (byte)9}));
		
		final List<Byte> par = Arrays.asList(
				new Byte[]{(byte)5, (byte)6, (byte)7, (byte)8, (byte)9});
		cells[7] = new LinkedCellImpl(par);
		cells[8] = new LinkedCellImpl(par);
		
		final CellSet set = new CellSetImpl(cells);
		
		return set;
	}
	
	private CellSet getCellSet(){
		final Cell[] cells = getEmptyLinkedCellArray();
		//[4, 5, 7, 8];
		cells[0].remove((byte)6);
		cells[0].remove((byte)9);
		
		cells[1].setValue((byte)1);
		
		//[5, 7, 8, 9];
		cells[2].remove((byte)4);
		cells[2].remove((byte)6);
		
		cells[3].setValue((byte)2);
		
		//[6, 8];
		cells[4].remove((byte)4);
		cells[4].remove((byte)5);
		cells[4].remove((byte)7);
		cells[4].remove((byte)9);
		
		cells[5].setValue((byte)3);
		
		//[4, 8, 9];
		cells[6].remove((byte)5);
		cells[6].remove((byte)6);
		cells[6].remove((byte)7);
		
		//[6, 8];
		cells[7].remove((byte)4);
		cells[7].remove((byte)5);
		cells[7].remove((byte)7);
		cells[7].remove((byte)9);
		
		//[4, 6, 7];
		cells[8].remove((byte)5);
		cells[8].remove((byte)8);
		cells[8].remove((byte)9);
		
		final CellSet set = new CellSetImpl(cells);
		set.setType(CellSetType.ROW, (byte)3);
		
		return set;
	}
	
	private CellSet getThreeDigitSubset(){
		final Cell[] cells = getEmptyLinkedCellArray();
		
		cells[0].setValue((byte)5);
		//[1, 3, 8],
		cells[1].remove((byte)2);
		cells[1].remove((byte)6);
		cells[1].remove((byte)7);
		//[6, 8],
		cells[2].remove((byte)1);
		cells[2].remove((byte)2);
		cells[2].remove((byte)3);
		cells[2].remove((byte)7);

		cells[3].setValue((byte)4);
		//[1, 3, 7],
		cells[4].remove((byte)2);
		cells[4].remove((byte)6);
		cells[4].remove((byte)8);
		//[1, 2, 3, 7],
		cells[5].remove((byte)6);
		cells[5].remove((byte)8);

		cells[6].setValue((byte)9);
		//[2, 6, 8],
		cells[7].remove((byte)1);
		cells[7].remove((byte)3);
		cells[7].remove((byte)7);
		//[2, 6, 8],
		cells[8].remove((byte)1);
		cells[8].remove((byte)3);
		cells[8].remove((byte)7);
		
		final CellSet set = new CellSetImpl(cells);
		set.setType(CellSetType.COL, (byte)7);
		
		return set;
	}
	
	private Cell[] getEmptyLinkedCellArray(){
		final Cell[] cells = new Cell[9];
		for(int i = 0; i < cells.length; i++){
			cells[i] = new LinkedCellImpl();
			cells[i].setId(i+1);
		}
		for(int i = 0; i < cells.length; i++){
			LinkedCellImpl lcell = (LinkedCellImpl)cells[i];
			for(int j = 0; j < cells.length; j++){
				lcell.addNeigbor(cells[j]);
			}
		}
		
		return cells;
	}
	
	
//	@Test
	public void testRecurtion(){
		final List<String> lista = 
				Arrays.asList(new String[]{"a", "b", "c", "d", "e"});
		combinaciones(lista);
	}
	
	private void combinaciones(final List<String> lista){
		for(int i = 2; i < lista.size(); i++){
			combinacionesAux(lista, 0, i, new ArrayList<String>());
			System.out.println("\n");
		}
	}
	
	private void combinacionesAux(final List<String> lista, final int idx, final int size, final List<String> cola){
		if(size == 0){
			casoBase(cola);
		}else{
			recursion(lista, idx, size, cola);
		}
	}
	
	private void casoBase(final List<String> cola){
		System.out.println(Arrays.toString(new ArrayList<>(cola).toArray()));
	}
	
	private void recursion(final List<String> lista, final int idx, final int size, final List<String> cola){
		for(int i = idx; i <= lista.size() - size; i++){
			final List<String> recola = new ArrayList<>(cola);
			recola.add(lista.get(i));
			combinacionesAux(lista, i + 1, size - 1, recola);
		}
	}
	
	
}