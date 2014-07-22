package org.neto.sudoku.strategies;

import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;

public interface StrategyChain{
	
	boolean apply(CellSet set, Board board);
	
	void addStrategy(Strategy strategy);
	
	void removeStrategy(Strategy strategy);
}
