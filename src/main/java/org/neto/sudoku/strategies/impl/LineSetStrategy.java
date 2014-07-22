package org.neto.sudoku.strategies.impl;

import java.util.List;
import org.neto.sudoku.services.Occurrence;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;

public class LineSetStrategy extends AbstractStrategy{
	
	@Override
	public String getName(){
		return "line";
	}

	/**
	 * Solamente puede ser aplicado sobre un CellSet tipo BOX.
	 * @param set
	 * @param occurrences
	 * @param board
	 * @return 
	 */
	@Override
	protected boolean applyOccurrences(final CellSet set,
									   final List<Occurrence> occurrences,
									   final Board board){
		boolean change = false;
		if(CellSetType.BOX.equals(set.getType())){
			for(Occurrence o : occurrences){
				change |= applyInRows(o, board);
				change |= applyInCols(o, board);
			}
		}
		
		return change;
	}
	
	private boolean applyInRows(final Occurrence o,
								final Board board){
		boolean change = false;
		if(o.getRows().size() == 1){
			final Byte rowKey = o.getRows().get(0);
			for(byte i = 0; i < board.size(); i++){
				if(board.getBoxIndex(rowKey, i) != o.getBoxes().get(0)){
					change |= board.getCell(rowKey, i).remove(o.getValue());
				}
			}
		}
		
		return change;
	}
	private boolean applyInCols(final Occurrence o,
								final Board board){
		boolean change = false;
		if(o.getColumns().size() == 1){
			byte colKey = o.getColumns().get(0);
			for(byte i = 0; i < board.size(); i++){
				if(board.getBoxIndex(i, colKey) != o.getBoxes().get(0)){
					change |= board.getCell(i, colKey).remove(o.getValue());
				}
			}
		}
		
		return change;
	}
}
