package org.neto.sudoku.threads.impl;

import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.strategies.StrategyChain;

class ResolveApplier implements Runnable{

	CellSet row;
	CellSet col;
	CellSet box;
	StrategyChain chain;

	public ResolveApplier(){
		super();
	}

	public ResolveApplier(final CellSet row,
						  final CellSet col,
						  final CellSet box,
						  final StrategyChain chain){
		this.row = row;
		this.col = col;
		this.box = box;
		this.chain = chain;
	}

	@Override
	public void run(){
		chain.apply(row, null);
		chain.apply(col, null);
		chain.apply(box, null);
	}
}
