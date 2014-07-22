package org.neto.sudoku.domain;

import java.util.List;

public interface Cell{
	
	void setValue(byte digit);
	
	byte getValue();
	
	boolean isResolved();
	
	boolean remove(byte digit);
	
	void add(byte digit);
	
	int getPossibilitiesCount();
	
	List<Byte> getPossibilities();
	
	int getId();
	
	void setId(int id);
}
