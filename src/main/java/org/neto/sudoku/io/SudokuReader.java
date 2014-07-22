package org.neto.sudoku.io;

import java.io.File;
import java.io.InputStream;
import org.neto.sudoku.domain.Board;
import org.neto.sudoku.domain.CellFactory;

public interface SudokuReader{
	
	void setFactory(CellFactory factory);
	
	Board read(File file);
	
	Board read(InputStream input);
}
