package org.neto.sudoku.controller;

public class Change{
	private byte row;
	private byte col;
	private ChangeType type;

	public Change(){
	}

	public Change(final byte row, final byte col, final ChangeType type){
		this.row = row;
		this.col = col;
		this.type = type;
	}

	public byte getRow(){
		return row;
	}

	public void setRow(final byte row){
		this.row = row;
	}

	public byte getCol(){
		return col;
	}

	public void setCol(final byte col){
		this.col = col;
	}

	public ChangeType getType(){
		return type;
	}

	public void setType(final ChangeType type){
		this.type = type;
	}
}
