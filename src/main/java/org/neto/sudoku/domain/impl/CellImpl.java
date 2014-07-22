package org.neto.sudoku.domain.impl;

import java.util.List;
import org.neto.sudoku.domain.Cell;

public class CellImpl extends AbstractCell{
	public CellImpl(){
		super();
		possibilities.addAll(DEFAULT_POSSIBILITIES);
	}
	
	public CellImpl(final byte value){
		super(value);
	}

	public CellImpl(final List<Byte> possibilities){
		super(possibilities);
	}
	
	public static Cell emptyCell(){
		return new CellImpl();
	}
	
	@Override
	public void setValue(byte digit){
		if(!isResolved()){
			value = digit;
			possibilities.clear();
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
}
