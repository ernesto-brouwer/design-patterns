package org.neto.sudoku.strategies.impl;

import java.util.List;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.services.Occurrence;
import org.neto.sudoku.services.OccurrenceCounter;
import org.neto.sudoku.strategies.Strategy;

public abstract class AbstractStrategy implements Strategy{
	
	protected OccurrenceCounter counter;

	public OccurrenceCounter getCounter(){
		return counter;
	}

	public void setCounter(final OccurrenceCounter counter){
		this.counter = counter;
	}

	@Override
	public boolean apply(final CellSet set, final List<Occurrence> occurrences, final Board board){
		return applyOccurrences(set, occurrences, board);
	}
	
//	@Override
//	public boolean apply(final CellSet set){
//		return set.isResolved() ? false : applyCellSet(set);
//	}
	
//	protected abstract boolean applyCellSet(final CellSet set);
	
	protected abstract boolean applyOccurrences(final CellSet set, final List<Occurrence> occurrences, final Board board);
}
