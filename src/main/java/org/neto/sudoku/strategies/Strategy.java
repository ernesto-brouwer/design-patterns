package org.neto.sudoku.strategies;

import java.util.List;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.services.Occurrence;

public interface Strategy{
	
	String getName();
	
//	boolean apply(CellSet set);
	
	boolean apply(CellSet set, List<Occurrence> occurrences, Board board);
}
