package org.neto.sudoku.controller;

public interface ChangeController{
	
	void addChange(byte row, byte col, ChangeType type);
}
