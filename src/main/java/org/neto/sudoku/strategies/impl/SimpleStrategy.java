package org.neto.sudoku.strategies.impl;

import java.util.List;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.services.Occurrence;

public class SimpleStrategy extends AbstractStrategy{

	@Override
	public String getName(){
		return "simple";
	}

	@Override
	protected boolean applyOccurrences(final CellSet set,
									   final List<Occurrence> occurrences,
									   final Board board){
		boolean change = false;
		for(Occurrence o : occurrences){
			if(o.getCells().size() == 1){
				o.getCells().get(0).setValue(o.getValue());
				change = true;
			}
		}
		
		return change;
	}
}
