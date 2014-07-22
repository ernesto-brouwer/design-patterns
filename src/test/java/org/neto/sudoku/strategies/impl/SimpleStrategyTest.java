package org.neto.sudoku.strategies.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.impl.CellSetImpl;
import org.neto.sudoku.domain.impl.LinkedCellImpl;

public class SimpleStrategyTest{
	
	private static SimpleStrategy strategy;
	
	@BeforeClass
	public static void setup(){
		strategy = new SimpleStrategy();
	}
	
	@Test
	public void testGetName(){
		System.out.println("getName");
		final String expResult = "simple";
		final String result = strategy.getName();
		assertEquals("El nombre de la estrategia no es el esperado", expResult, result);
	}

	@Test
	public void testApply(){
		System.out.println("apply");
		final CellSet set = new CellSetImpl(getScalon((byte)9));
		System.out.println("Antes");
		System.out.println(set);
		System.out.println();
		final SimpleStrategy instance = new SimpleStrategy();
		instance.apply(set, null, null);
		System.out.println("Despues");
		System.out.println(set);
		assertTrue("El set no es correcto", set.isCorrect());
		assertTrue("No ha sido resuelto", set.isResolved());
	}
	
	private Cell[] getScalon(final byte max){
		
		final Cell[] cells = new Cell[max];
		final List<Byte> pos = new ArrayList<>(max);
		for(byte i = 1; i <= max; i++){
			pos.add(new Byte(i));
			cells[i-1] = new LinkedCellImpl(pos);
		}
		
		for(Cell cell : cells){
			for(Cell neighnor : cells){
				((LinkedCellImpl)cell).addNeigbor(neighnor);
			}
		}
		return cells;
	}
}