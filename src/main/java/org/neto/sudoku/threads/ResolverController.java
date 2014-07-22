package org.neto.sudoku.threads;

import org.neto.sudoku.strategies.StrategyChain;

public interface ResolverController{
	
	void applyResolve(int row, int col);
	
	void setStrategyChain(StrategyChain chain);
}
