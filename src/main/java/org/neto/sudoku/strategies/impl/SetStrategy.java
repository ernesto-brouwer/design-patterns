package org.neto.sudoku.strategies.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import org.neto.sudoku.services.Occurrence;
import java.util.List;
import java.util.Set;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;

public class SetStrategy extends AbstractStrategy{
	@Override
	public String getName(){
		return "set";
	}

	@Override
	protected boolean applyOccurrences(final CellSet set,
									   final List<Occurrence> occurrences,
									   final Board board){
		final List<Cell> unsolved = new ArrayList<>();
		for(Cell cell : set){
			if(!cell.isResolved()){
				unsolved.add(cell);
			}
		}
		
		boolean change = false;
		for(int i = 2; i < unsolved.size(); i++){
			change = compareCellsAux(unsolved, 0, i, new ArrayList<Cell>(), set);
		}
		return change;
	}
	
	private boolean compareCellsAux(final List<Cell> cells, final int idx, final int size, final List<Cell> stack, final CellSet set){
		boolean change;
		if(size == 0){
			change = compareCellBase(set, stack);
		}else{
			change = compareCellRec(cells, idx, size, stack, set);
		}
		
		return change;
	}

	private boolean compareCellBase(final CellSet set, final List<Cell> cells){
		final Set<Byte> pos = eqPossibilities(cells);
		boolean change;
		if(pos.isEmpty()){
			return false;
		}else{
			change = false;
			for(Cell cell : set){
				if(!cells.contains(cell) && !cell.isResolved()){
					for(Byte digit : pos){
						change |= cell.remove(digit);
					}
				}
			}
		}
		return change;
	}
	
	private boolean compareCellRec(final List<Cell> cells,
								   final int idx,
								   final int size,
								   final List<Cell> stack,
								   final CellSet set){
		boolean change = false;
		for(int i = idx; i <= cells.size() - size; i++){
			final List<Cell> recl = new ArrayList<>(stack);
			recl.add(cells.get(i));
			change |= compareCellsAux(cells, i+1, size - 1, recl, set);
		}
		return change;
	}
	
	private Set<Byte> eqPossibilities(final List<Cell> cells){
		final Set<Byte> all = new LinkedHashSet<>(cells.size());
		for(Cell cell : cells){
			final List<Byte> l = cell.getPossibilities();
			if(l.size() > cells.size()){
				return Collections.EMPTY_SET;
			}
			all.addAll(l);
		}
		
		return all.size() > cells.size() ? Collections.EMPTY_SET : all;
	}
}
