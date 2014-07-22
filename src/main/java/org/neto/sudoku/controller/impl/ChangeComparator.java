package org.neto.sudoku.controller.impl;

import org.neto.sudoku.controller.ChangeType;
import org.neto.sudoku.controller.Change;
import java.util.Comparator;

public class ChangeComparator implements Comparator<Change>{

	@Override
	public int compare(final Change c1, final Change c2){
		int comp;
		if(c1 == c2){
			comp = 0;
		}else if(c1.getType() == c2.getType()){
			comp = 0;
		}else  if(c1.getType() == null){
			comp = 1;
		}else if(c2.getType() == null){
			comp = -1;
		}else if(ChangeType.VALUE.equals(c1.getType())){
			comp = -1;
		}else{
			comp = 1;
		}
		return comp;
	}
	
}
