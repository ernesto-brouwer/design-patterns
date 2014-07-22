package org.neto.sudoku.domain;

public interface CellFactory{
	
	Class<?> getCellType();
	
	Cell emptyCell();
}
