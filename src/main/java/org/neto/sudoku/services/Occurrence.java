package org.neto.sudoku.services;

import java.util.ArrayList;
import java.util.List;
import org.neto.sudoku.domain.Cell;

public class Occurrence{
	
	private transient final byte value;
	//TODO mantener ordenadas
	private transient final List<Cell> cells;
	private transient final List<Byte> boxes;
	private transient final List<Byte> columns;
	private transient final List<Byte> rows;

	public Occurrence(final byte value){
		this.value = value;
		this.cells = new ArrayList<>();
		this.boxes = new ArrayList<>();
		this.columns = new ArrayList<>();
		this.rows = new ArrayList<>();
	}

	public byte getValue(){
		return value;
	}
	
	public List<Cell> getCells(){
		return cells;
	}
	
	public List<Byte> getBoxes(){
		return boxes;
	}

	public List<Byte> getColumns(){
		return columns;
	}

	public List<Byte> getRows(){
		return rows;
	}

	
	public void addCellOccurrence(final Cell cell){
		if(!cells.contains(cell)){
			cells.add(cell);
		}
	}
	
	public void addBoxOccurrence(final Byte boxId){
		if(!boxes.contains(boxId)){
			boxes.add(boxId);
		}
	}

	public void addColOccurrence(final Byte colId){
		if(!columns.contains(colId)){
			columns.add(colId);
		}
	}

	public void addRowOccurrence(final Byte rowId){
		if(!rows.contains(rowId)){
			rows.add(rowId);
		}
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("{");
		
		sb.append("value: ").append(value).append(", ");
		sb.append("cells: [");
		for(Cell cell : cells){
			sb.append(cell.getId()).append(", ");
		}
		if(sb.charAt(sb.length() - 1) == ' '){
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("], ");
		
		byteListToString("boxes", boxes, sb);
		sb.append(", ");
		byteListToString("columns", columns, sb);
		sb.append(", ");
		byteListToString("rows", rows, sb);
		sb.append("}");
		
		return sb.toString();
	}
	
	private void byteListToString(final String name,
								  final List<Byte> list,
								  final StringBuilder sb){
		
		sb.append(name).append(": [");
		for(Byte id : list){
			sb.append(id).append(", ");
		}
		if(sb.charAt(sb.length() - 1) == ' '){
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("]");
	}
}
