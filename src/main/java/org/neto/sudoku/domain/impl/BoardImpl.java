package org.neto.sudoku.domain.impl;

import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellFactory;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;
import org.neto.sudoku.domain.LinkedCell;

public class BoardImpl implements Board{
	
	private final Cell[][] board;
	
	private final CellSet[] rows;
	private final CellSet[] columns;
	private final CellSet[] boxes;
	
	
	public BoardImpl(final CellFactory factory){
		board = new Cell[9][9];//TODO parametrizar
		rows = new CellSet[board.length];
		columns = new CellSet[board.length];
		boxes = new CellSet[board.length];
		
		initBoard(factory);
		initRows();
		initColumns();
		initBoxes();
		
		if(LinkedCell.class.isAssignableFrom(factory.getCellType())){
			linkCells();
		}
		
		linkCellSets();
	}
	private void initBoard(final CellFactory factory){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = factory.emptyCell();
			}
		}
	}
	private void initRows(){
		for(int i = 0; i < board.length; i++){
			rows[i] = new CellSetImpl(board[i]);
		}
	}
	
	private void initColumns(){
		for(int j = 0; j < board.length; j++){
			final Cell[] cells = new Cell[board.length];
			for(int i = 0; i < board.length; i++){
				cells[i] = board[i][j];
			}
			columns[j] = new CellSetImpl(cells);
		}
	}
	
	private void initBoxes(){
		final int boxlenght = (int)Math.sqrt(board.length);
		for(int i = 0; i < board.length; i++){
			final Cell[] cells = new Cell[board.length];
			
			final int x = (i / boxlenght) * boxlenght;
			final int y = (i % boxlenght) * boxlenght;
			
			int count = 0;
			for(int m = x; m < x + boxlenght; m++){
				for(int n = y; n < y + boxlenght; n++){
					cells[count++] = board[m][n];
				}
			}
			
			boxes[i] = new CellSetImpl(cells);
		}
	}
	
	private void linkCells(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				final LinkedCell cell = (LinkedCell)getCell(i, j);
				linkCells(cell, getBox(i, j));
				linkCells(cell, getColumn(j));
				linkCells(cell, getRow(i));
			}
		}
	}
	
	private void linkCells(final LinkedCell cell, final CellSet set){
		for(Cell neighbor : set){
			cell.addNeigbor(neighbor);
		}
	}
	
	private void linkCellSets(){
		for(byte idx = 0; idx < board.length; idx++){
			boxes[idx].setType(CellSetType.BOX, idx);
			rows[idx].setType(CellSetType.ROW, idx);
			columns[idx].setType(CellSetType.COL, idx);
		}
	}

	@Override
	public Cell getCell(final int row, final int col){
		return board[row][col];
	}

	@Override
	public CellSet getRow(final int index){
		return rows[index];
	}

	@Override
	public CellSet getColumn(final int index){
		return columns[index];
	}

	@Override
	public CellSet getBox(final int index){
		return boxes[index];
	}
	@Override
	public CellSet getBox(final int row, final int col){
		return boxes[getBoxIndex(row, col)];
	}
	
	@Override
	public byte getBoxIndex(int row, int col){
		final byte sqrt = (byte)Math.sqrt(board.length);
		final byte[] rs = new byte[sqrt];
		final byte[] cs = new byte[sqrt];
		
		final int base = (row / sqrt) * sqrt;
		final int floor = col / sqrt;
		for(int i = 0; i < sqrt; i++){
			rs[i] = (byte)(i + base);
			cs[i] = (byte)((3*i) + floor);
		}
		
		int cc = 0;
		int rc = 0;
		while(rs[rc] != cs[cc]){
			if(rs[rc] > cs[cc]){
				cc++;
			}else{
				rc++;
			}
		}

		return rs[rc];
	}

	@Override
	public byte size(){
		return (byte)board.length;
	}

	@Override
	public boolean isResolved(){
		boolean resolved = true;
		
		for(CellSet row : rows){
			resolved &= row.isResolved();
		}
		
		return resolved;
	}
	
	@Override
	public StringBuilder toBoardString(){
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < board.length; i++){
			sb.append("|");
			for(int j = 0; j < board[i].length; j++){
				final byte value = board[i][j].getValue();
				sb.append(value == 0 ? " " : value).append("|");
			}
			sb.append("\n");
		}
		return sb;
	}
}
