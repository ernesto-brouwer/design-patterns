package org.neto.sudoku.domain.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.LinkedCell;
import static org.neto.sudoku.domain.impl.AbstractCell.DEFAULT_POSSIBILITIES;

public class LinkedCellImpl extends AbstractCell implements LinkedCell{
	
	private Set<Cell> neighbors;
	
	public LinkedCellImpl(){
		super();
		possibilities.addAll(DEFAULT_POSSIBILITIES);
		neighbors = new LinkedHashSet<>(21);
	}
	
	public LinkedCellImpl(final byte value){
		super(value);
		neighbors = new LinkedHashSet<>(21);
	}

	public LinkedCellImpl(final List<Byte> possibilities){
		super(possibilities);
		neighbors = new LinkedHashSet<>(21);
	}
	

	@Override
	public void setValue(final byte digit){
		if(!isResolved()){
			value = digit;
			possibilities.clear();
			notifyNeighbors();
		}
	}

	@Override
	public boolean remove(byte digit){
		boolean resp = false;
		if(!isResolved() && !possibilities.isEmpty()){
			resp = possibilities.remove(new Byte(digit));
			if(possibilities.size() == 1){
				setValue(possibilities.get(0));
			}
		}
		
		return resp;
	}

	@Override
	public void addNeigbor(final Cell cell){
		if(this != cell && !neighbors.contains(cell)){
			neighbors.add(cell);
		}
	}

	@Override
	public List<Cell> getNeighbors(){
		return new ArrayList<>(neighbors);
	}

	@Override
	public int neighborCount(){
		return neighbors.size();
	}

	private void notifyNeighbors(){
		for(Cell cell : neighbors){
			cell.remove(value);
		}
	}

}
