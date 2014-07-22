package org.neto.sudoku.domain;

import java.util.List;

public interface LinkedCell extends Cell{
	
	void addNeigbor(Cell cell);
	
	int neighborCount();
	
	List<Cell> getNeighbors();
}
