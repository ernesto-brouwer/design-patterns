package org.neto.sudoku.services;

import java.util.List;
import org.neto.sudoku.domain.CellSet;

public interface OccurrenceCounter{
	List<Occurrence> findOccurrences(CellSet set);
}
