package org.neto.sudoku.domain.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.neto.sudoku.domain.Cell;

public class CellImplTest{
	
//	@Test
	public void testEmptyCell(){
		System.out.println("emptyCell");
		Cell expResult = null;
		Cell result = CellImpl.emptyCell();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setValue method, of class CellImpl.
	 */
	@Test
	public void testSetValue(){
		System.out.println("setValue");
		final byte digit = 7;
		final Cell cell = CellImpl.emptyCell();
		cell.setValue(digit);
		assertTrue("La celda ya esta resuelta", cell.isResolved());
		assertEquals("La celda no tiene el valor establecido", digit, cell.getValue());
	}

	/**
	 * Test of isResolved method, of class CellImpl.
	 */
	@Test
	public void testIsResolved(){
		System.out.println("isResolved");
		final Cell resolved = new CellImpl((byte)5);
		final Cell unsolved = CellImpl.emptyCell();
		
		assertTrue("La celda si esta resuelta", resolved.isResolved());
		assertFalse("La celda no esta resuelta", unsolved.isResolved());
	}

	/**
	 * Test of getPossibilities method, of class CellImpl.
	 */
	@Test
	public void testGetPossibilities(){
		System.out.println("getPossibilities");
		final Cell cell = CellImpl.emptyCell();
		assertEquals("No tiene la posibilidades por default",
					 CellImpl.DEFAULT_POSSIBILITIES,
					 cell.getPossibilities());
	}

	/**
	 * Test of remove method, of class CellImpl.
	 */
	@Test
	public void testRemove(){
		System.out.println("remove");
		final byte digit = 5;
		final Cell cell = CellImpl.emptyCell();
		cell.remove(digit);
		assertFalse("La posibilidades aun contienen " + digit,
					cell.getPossibilities().contains(digit));
	}

	/**
	 * Test of add method, of class CellImpl.
	 */
	@Test
	public void testAdd(){
		System.out.println("add");
		final byte digit = 6;
		final List<Byte> pos = new ArrayList<>(CellImpl.DEFAULT_POSSIBILITIES);
		pos.remove(digit);
		final Cell cell = new CellImpl(pos);
		cell.add(digit);
		assertTrue("La posibilidades no contienen " + digit,
				   cell.getPossibilities().contains(digit));
	}

	/**
	 * Test of toString method, of class CellImpl.
	 */
//	@Test
	public void testToString(){
		System.out.println("toString");
		CellImpl instance = new CellImpl();
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}