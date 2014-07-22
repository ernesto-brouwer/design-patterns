package org.neto.sudoku.threads.impl;

class CellState{
	byte value;
	int possibilities;

	public CellState(){
		super();
	}

	public CellState(final byte value, final int possibilities){
		this.value = value;
		this.possibilities = possibilities;
	}
}
