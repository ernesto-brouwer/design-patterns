package org.neto.sudoku.controller;

public interface ChangeContainer{
	
	void addChange(Change change);
	
	Change pullChange();
}
