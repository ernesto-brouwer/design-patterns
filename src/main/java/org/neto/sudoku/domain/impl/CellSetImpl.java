package org.neto.sudoku.domain.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.domain.CellSetType;

public class CellSetImpl implements CellSet{
	
	private CellSetType type;
	private Cell[] cells;
	private boolean resolved;
	
	private Map<Byte, Cell[]> boxSet;
	private Map<Byte, Cell[]> rowSet;
	private Map<Byte, Cell[]> colSet;
	
	//Cache
	private Map<Cell, Byte> cellBox;
	private Map<Cell, Byte> cellRow;
	private Map<Cell, Byte> cellCol;
	
	private transient final byte sqrt;
	
	public CellSetImpl(final Cell[] cells){
		this.sqrt = (byte)Math.sqrt(cells.length);

		this.resolved = false;
		this.cells = new Cell[cells.length];
		System.arraycopy(cells, 0, this.cells, 0, cells.length);
		
		this.boxSet = new LinkedHashMap<>();
		this.rowSet = new LinkedHashMap<>();
		this.colSet = new LinkedHashMap<>();
		this.type = CellSetType.UNDEFINED;
		
		this.cellBox = new LinkedHashMap<>();
		this.cellCol = new LinkedHashMap<>();
		this.cellRow = new LinkedHashMap<>();
	}

	@Override
	public Map<Byte, Cell[]> getSubsets(final CellSetType type){
		this.type = type;
		final Map<Byte, Cell[]> subset = new LinkedHashMap<>();
		if(CellSetType.BOX.equals(type)){
			subset.putAll(boxSet);
		}else if(CellSetType.COL.equals(type)){
			subset.putAll(colSet);
		}else if(CellSetType.ROW.equals(type)){
			subset.putAll(rowSet);
		}
		return subset;
	}

	@Override
	public CellSetType getType(){
		return type;
	}
	
	@Override
	public void setType(final CellSetType type,
						final byte idx){
		this.type = type;
		if(CellSetType.BOX.equals(type)){
			setBoxIdxs(idx);
		}else if(CellSetType.COL.equals(type)){
			setColIdxs(idx);
		}else if(CellSetType.ROW.equals(type)){
			setRowIdxs(idx);
		}
	}

	private void setBoxIdxs(final byte idx){
		boxSet.put(idx, cells);

		final byte colBase = (byte)(Math.floor(idx / sqrt) * sqrt);
		final byte rowBase = (byte)(Math.floor(idx % sqrt) * sqrt);
		Cell[] rowSubset;
		Cell[] colSubset;
		for(byte i = 0; i < sqrt; i++){
			rowSubset = new Cell[sqrt];
			colSubset = new Cell[sqrt];
			for(byte j = 0; j < sqrt; j++){
				rowSubset[j] = cells[i * sqrt + j];
				colSubset[j] = cells[j * sqrt + i];
			}
			rowSet.put((byte)(colBase + i), rowSubset);
			colSet.put((byte)(rowBase + i), colSubset);
		}
	}
	
	private void setRowIdxs(final byte idx){
		rowSet.put(idx, cells);

		for(byte i = 0; i < cells.length; i++){
			colSet.put(i, new Cell[]{cells[i]});
		}
		
		final byte rowBase = (byte)(Math.floor(idx / sqrt) * sqrt);
		Cell[] boxSubset;
		for(byte i = 0; i < sqrt; i++){
			boxSubset = new Cell[sqrt];
			for(byte j = 0; j < sqrt; j++){
				boxSubset[j] = cells[i * sqrt + j];
			}
			boxSet.put((byte)(rowBase + i), boxSubset);
		}
	}
	
	private void setColIdxs(final byte idx){
		colSet.put(idx, cells);

		for(byte i = 0; i < cells.length; i++){
			rowSet.put(i, new Cell[]{cells[i]});
		}
		
		final byte colBase = (byte)Math.floor(idx / sqrt);
		Cell[] boxSubset;
		for(byte i = 0; i < sqrt; i++){
			boxSubset = new Cell[sqrt];
			for(byte j = 0; j < sqrt; j++){
				boxSubset[j] = cells[i * sqrt + j];
			}
			boxSet.put((byte)(colBase + (i * sqrt)), boxSubset);
		}
	}
	

	@Override
	public Byte getBoxIndex(final Cell cell){
		return subsetIdx(cell, cellBox, boxSet);
	}
	
	@Override
	public Byte getColIndex(final Cell cell){
		return subsetIdx(cell, cellCol, colSet);
	}

	@Override
	public Byte getRowIndex(final Cell cell){
		return subsetIdx(cell, cellRow, rowSet);
	}

	//Puede ser optimizado
	private Byte subsetIdx(final Cell cell, final Map<Cell, Byte> cache, final Map<Byte, Cell[]> subset){
		Byte boxIdx;
		if(cache.containsKey(cell)){
			boxIdx = cache.get(cell);
		}else{
			boxIdx = findSubsetIdx(cell, subset);
			cache.put(cell, boxIdx);
		}
		return boxIdx;
	}
	
	private Byte findSubsetIdx(final Cell cell, final Map<Byte, Cell[]> subset){
		for(Entry<Byte, Cell[]> entry : subset.entrySet()){
			for(Cell c : entry.getValue()){
				if(c == cell){
					return entry.getKey();
				}
			}
		}
		return 0;
	}
	
	
	
	@Override
	public int size(){
		return cells.length;
	}

	@Override
	public boolean isResolved(){
		if(!resolved){
			for(Cell cell : cells){
				if(!cell.isResolved()){
					return false;
				}
			}
		}
		resolved = true;
		return resolved;
	}

	@Override
	public boolean isCorrect(){
		final int[] count = new int[cells.length];
		for(Cell cell : cells){
			if(cell.isResolved()){
				count[cell.getValue() - 1]++;
			}
		}
		
		for(int i : count){
			if(i > 1){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Cell getCell(final int index){
		Cell cell;
		if(0 <= index && index < cells.length){
			cell = cells[index];
		}else{
			cell = null;
		}
		
		return cell;
	}

	@Override
	public boolean removePossibility(byte digit){
		boolean resp = false;
		for(Cell cell : cells){
			if(!cell.isResolved()){
				resp |= cell.remove(digit);
			}
		}
		
		return resp;
	}
	
	@Override
	public Iterator<Cell> iterator(){
		return new CellSetIterator();
	}
	
	private class CellSetIterator implements Iterator<Cell>{
		private int index;
		
		@Override
		public boolean hasNext(){
			return index < cells.length;
		}

		@Override
		public Cell next(){
			return cells[index++];
		}

		@Override
		public void remove(){
		}
	}
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("[");
		for(Cell cell : cells){
			sb.append(cell).append(", ");
		}
		if(sb.length() > 1){
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.append("]").toString();
	}
}
