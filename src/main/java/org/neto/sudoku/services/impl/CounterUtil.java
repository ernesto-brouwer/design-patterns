package org.neto.sudoku.services.impl;

import java.util.ArrayList;
import org.neto.sudoku.services.Occurrence;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellSet;
import org.neto.sudoku.services.OccurrenceCounter;

public class CounterUtil implements OccurrenceCounter{
	
	@Override
	public List<Occurrence> findOccurrences(final CellSet set){
		final Map<Byte, Occurrence> occurrences = new LinkedHashMap<>();
		for(Cell cell : set){
			for(Byte digit : cell.getPossibilities()){
				addOccurrence(digit, cell, set, occurrences);
			}
		}
		
		return new ArrayList<>(occurrences.values());
	}
	
	private void addOccurrence(final Byte digit,
							   final Cell cell,
							   final CellSet set,
							   final Map<Byte, Occurrence> occurrences){
		if(!occurrences.containsKey(digit)){
			occurrences.put(digit, new Occurrence(digit));
		}
		final Occurrence oc = occurrences.get(digit);
		oc.addCellOccurrence(cell);
		oc.addBoxOccurrence(set.getBoxIndex(cell));
		oc.addColOccurrence(set.getColIndex(cell));
		oc.addRowOccurrence(set.getRowIndex(cell));
	}

	//debug
	private void printOcurrenciasMap(final Map<Byte, SortedSet<Integer>> ocurrencias){
		for(Entry<Byte, SortedSet<Integer>> entry : ocurrencias.entrySet()){
			final StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey()).append(" -> ");
			for(Integer entero : entry.getValue()){
				sb.append(entero).append(" ");
			}
			System.out.println(sb);
		}
	}
	
	private void printEntryList(final List<Entry<Byte, SortedSet<Integer>>> list){
		for(Entry<Byte, SortedSet<Integer>> entry : list){
			final StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey()).append(" -> ");
			for(Integer entero : entry.getValue()){
				sb.append(entero).append(" ");
			}
			System.out.println(sb);
		}
	}
	//debug
}
