package org.neto.sudoku.controller.impl;

import org.neto.sudoku.controller.Change;
import org.neto.sudoku.controller.ChangeContainer;
import org.neto.sudoku.controller.ChangeType;

public class ChangeControllerImpl{
	
	private transient final ChangeContainer container;

	public ChangeControllerImpl(final ChangeContainer container){
		this.container = container;
	}
	
	public void addChange(final byte row, final byte col, final ChangeType type){
		container.addChange(new Change(row, col, type));
	}
}
