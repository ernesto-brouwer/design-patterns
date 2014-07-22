package org.neto.sudoku.domain;

public interface Board{
	
	Cell getCell(int row, int col);
	
	CellSet getRow(int index);

	CellSet getColumn(int index);

	CellSet getBox(int row, int col);
	
	CellSet getBox(int index);
	
	byte getBoxIndex(int row, int col);
	
	StringBuilder toBoardString();
	
	byte size();
	
	boolean isResolved();
}
