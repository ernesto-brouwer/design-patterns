package org.neto.sudoku.domain.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;

public class BoardImplTest{
	
	private final Board board;
	
	public BoardImplTest(){
		board = new BoardImpl(new CellFactoryImpl(LinkedCellImpl.class));
	}

	@Test
	public void testSetType(){
		for(byte idx = 0; idx < 9; idx++){
			board.getBox(idx).setType(CellSetType.BOX, idx);
			board.getRow(idx).setType(CellSetType.ROW, idx);
			board.getColumn(idx).setType(CellSetType.COL, idx);
		}
	}
	
//	@Test
	public void testGetCell(){
		System.out.println("getCell");
		int row = 0;
		int col = 0;
		Cell expResult = null;
		Cell result = board.getCell(row, col);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRow method, of class BoardImpl.
	 */
//	@Test
	public void testGetRow(){
		System.out.println("getRow");
		int index = 0;
		CellSet expResult = null;
		CellSet result = board.getRow(index);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getColumn method, of class BoardImpl.
	 */
//	@Test
	public void testGetColumn(){
		System.out.println("getColumn");
		int index = 0;
		CellSet expResult = null;
		CellSet result = board.getColumn(index);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getBox method, of class BoardImpl.
	 */
//	@Test
	public void testGetBox(){
		System.out.println("getBox");
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				board.getBox(i, j);
			}
		}
	}
}