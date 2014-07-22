package org.neto.sudoku.services.impl;

import org.neto.sudoku.services.Occurrence;
import org.neto.sudoku.services.OccurrenceComparator;

public class PossibilitiesComparator implements OccurrenceComparator{

	@Override
	public int compare(final Occurrence occ1, final Occurrence occ2){
		if(occ1.getCells().size() == occ2.getCells().size()){
			
		}else{
			return occ1.getCells().size() - occ2.getCells().size();
		}
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
