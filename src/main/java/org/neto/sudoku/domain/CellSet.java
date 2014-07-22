package org.neto.sudoku.domain;

import java.util.Map;

/**
 *
 * @author Emmanuel
 */
public interface CellSet extends Iterable<Cell>{
	
	int size();
	
	boolean isResolved();
	
	Cell getCell(int index);
	
	boolean removePossibility(byte digit);
	
	boolean isCorrect();
	
	CellSetType getType(); 
	
	void setType(CellSetType type, byte idx);

	Map<Byte, Cell[]> getSubsets(CellSetType type);

	Byte getBoxIndex(final Cell cell);
	
	Byte getColIndex(final Cell cell);
	
	Byte getRowIndex(final Cell cell);
}
