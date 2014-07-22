package org.neto.sudoku.services;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellFactory;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;
import org.neto.sudoku.domain.impl.BoardImpl;
import org.neto.sudoku.domain.impl.CellFactoryImpl;
import org.neto.sudoku.domain.impl.CellSetImpl;
import org.neto.sudoku.domain.impl.LinkedCellImpl;
import org.neto.sudoku.services.impl.CounterUtil;

public class OccurrenceCounterTest{
	private static final int[][] VERY_HARD = {
		{0, 2, 5}, {0, 4, 2}, {0, 6, 1},
		{1, 0, 7}, {1, 1, 2}, {1, 7, 8}, {1, 8, 3},
		{2, 0, 9}, {2, 8, 6},
		{3, 3, 1}, {3, 5, 3},
		{4, 0, 5}, {4, 3, 6}, {4, 5, 8}, {4, 8, 7},
		{5, 3, 4}, {5, 5, 2},
		{6, 0, 8}, {6, 8, 4},
		{7, 0, 1}, {7, 1, 3}, {7, 7, 6}, {7, 8, 2},
		{8, 2, 6}, {8, 4, 3}, {8, 6, 5}
	};
	
	private transient final OccurrenceCounter counter;
	private transient final Board board	;
	
	public OccurrenceCounterTest(){
		counter = new CounterUtil();
		final CellFactory factory = new CellFactoryImpl(LinkedCellImpl.class);
		board = new BoardImpl(factory);
		
		for(int[] val : VERY_HARD){
			board.getCell(val[0], val[1]).setValue((byte)val[2]);
		}
		
		board.getBox(0).setType(CellSetType.BOX, (byte)0);
		board.getBox(1).setType(CellSetType.BOX, (byte)1);
		
	}

	@Test
	public void testFindOccurrences(){
		System.out.println("findOccurrences");
		final CellSet set = board.getColumn(4);
		final List<Occurrence> occurrences = counter.findOccurrences(set);
		
		for(Occurrence oc : occurrences){
			System.out.println(oc);
		}
	}
}