package org.neto.sudoku.threads.impl;

import org.neto.sudoku.domain.Board;
import org.neto.sudoku.strategies.StrategyChain;
import org.neto.sudoku.threads.ResolverController;

public class ResolverControllerImpl implements ResolverController{
	
	private Board board;
	private StrategyChain chain;
	private ThreadGroup group;
	
	public ResolverControllerImpl(final Board board){
		this.board = board;
		this.group = new ThreadGroup("resolvedores");
	}

	@Override
	public void applyResolve(int row, int col){
		final ResolveApplier app = new ResolveApplier(
				board.getRow(row), board.getColumn(col), board.getBox(row, col), chain);
		app.run();
//		final Thread thread = new Thread(app);
//		thread.start();
//		group.
	}

	@Override
	public void setStrategyChain(final StrategyChain chain){
		this.chain = chain;
	}
}
