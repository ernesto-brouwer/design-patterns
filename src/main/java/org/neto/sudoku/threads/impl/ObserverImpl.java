package org.neto.sudoku.threads.impl;

import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.threads.Observer;
import org.neto.sudoku.threads.ResolverController;

public class ObserverImpl implements Observer, Runnable{

	private ResolverController controller;
	private transient final Board board;
	private transient final int x;
	private transient final int y;
	private transient boolean unsolved;
	
	private CellState[][] shot;
	
	public ObserverImpl(final Board board,
						final int x,
						final int y,
						final int width,
						final int height){
		this.board = board;
		this.x = x;
		this.y = y;
		this.shot = new CellState[width][height];
		this.unsolved = true;
		initShot();
	}
	
	private void initShot(){
		for(int i = x; i < x + shot.length; i++){
			for(int j = y; j < y + shot[0].length; j++){
				final Cell cell = board.getCell(i, j);
				shot[i-x][j-y] = new CellState(cell.getValue(), cell.getPossibilitiesCount());
			}
		}
	}

	@Override
	public void reportTo(final ResolverController controller){
		this.controller = controller;
	}

	@Override
	public void run(){
		while(unsolved){
			updateShot();
		}
	}
	
	private void updateShot(){
		boolean b = true;
		for(int i = x; i < x + shot.length; i++){
			for(int j = y; j < y + shot[0].length; j++){
				final Cell cell = board.getCell(i, j);
				final CellState state = shot[i-x][j-y];
				if(cell.getValue() != state.value ||
				   cell.getPossibilitiesCount() != state.possibilities){
					controller.applyResolve(i, j);
					state.value = cell.getValue();
					state.possibilities = cell.getPossibilitiesCount();
				}
				b ^= state.value > 0;
			}
		}
		unsolved = !b;
	}
}
