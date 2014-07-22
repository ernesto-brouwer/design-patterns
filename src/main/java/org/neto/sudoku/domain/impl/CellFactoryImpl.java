package org.neto.sudoku.domain.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.neto.sudoku.domain.Cell;
import org.neto.sudoku.domain.CellFactory;

public class CellFactoryImpl implements CellFactory{
	private static final Logger LOGGER =
			Logger.getLogger(CellFactoryImpl.class.getName());
	
	private final Class<? extends Cell> cellType;
	
	private static int count = 0;
	
	public CellFactoryImpl(final Class<? extends Cell> cellType){
		this.cellType = cellType;
	}

	@Override
	public Class<?> getCellType(){
		return cellType;
	}
	
	@Override
	public Cell emptyCell(){
		Cell cell;
		try{
			cell = cellType.newInstance();
			cell.setId(++count);
		}catch(IllegalAccessException | InstantiationException ex){
			cell = null;
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		return cell;
	}
}
