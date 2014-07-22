package org.neto.sudoku.strategies;

public interface CellSetResolver{
	
	/**
	 * <b>True</b> if the set is already solved, <b>False</b> otherwise.
	 * @return <b>True</b> if the set is already solved, <b>False</b> otherwise.
	 */
	boolean isResolved();

	/**
	 * <b>True</b>
	 * @return 
	 */
	boolean resolved();
}
