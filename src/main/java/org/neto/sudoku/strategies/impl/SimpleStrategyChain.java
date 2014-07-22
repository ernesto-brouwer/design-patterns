package org.neto.sudoku.strategies.impl;

import java.util.ArrayList;
import java.util.List;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.services.Occurrence;
import org.neto.sudoku.services.OccurrenceCounter;
import org.neto.sudoku.strategies.Strategy;
import org.neto.sudoku.strategies.StrategyChain;

public class SimpleStrategyChain implements StrategyChain{

	private List<Strategy> strategies;
	
	protected OccurrenceCounter counter;

	public OccurrenceCounter getCounter(){
		return counter;
	}

	public void setCounter(final OccurrenceCounter counter){
		this.counter = counter;
	}

	public SimpleStrategyChain(){
		strategies = new ArrayList<>();
	}
	
	@Override
	public boolean apply(final CellSet set, final Board board){
		if(set.isResolved()){
			return false;
		}
		final List<Occurrence> occurrences = counter.findOccurrences(set);
		
		for(Strategy strategy : strategies){
			if(strategy.apply(set, occurrences, board)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void addStrategy(Strategy strategy){
		strategies.add(strategy);
	}

	@Override
	public void removeStrategy(Strategy strategy){
		strategies.remove(strategy);
	}
	
}
