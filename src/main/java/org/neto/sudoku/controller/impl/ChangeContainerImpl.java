package org.neto.sudoku.controller.impl;

import org.neto.sudoku.controller.Change;
import java.util.PriorityQueue;
import org.neto.sudoku.controller.ChangeContainer;

public class ChangeContainerImpl implements ChangeContainer{
	
	private PriorityQueue<Change> changes;

	public ChangeContainerImpl(){
		changes = new PriorityQueue<>(20, new ChangeComparator());
	}

	@Override
	public synchronized void addChange(final Change change){
		changes.add(change);
	}

	@Override
	public synchronized Change pullChange(){
		return changes.poll();
	}
}
