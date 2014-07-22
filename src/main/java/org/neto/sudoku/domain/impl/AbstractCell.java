package org.neto.sudoku.domain.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.neto.sudoku.domain.Cell;

public abstract class AbstractCell implements Cell{
	public static final List<Byte> DEFAULT_POSSIBILITIES;
	static{
		final List<Byte> list = new ArrayList<>();
		list.add((byte)1);
		list.add((byte)2);
		list.add((byte)3);
		list.add((byte)4);
		list.add((byte)5);
		list.add((byte)6);
		list.add((byte)7);
		list.add((byte)8);
		list.add((byte)9);
		DEFAULT_POSSIBILITIES = Collections.unmodifiableList(list);
	}
	
	protected int id;
	protected byte value;
	protected final List<Byte> possibilities;
	
	protected AbstractCell(){
		super();
		value = 0;
		possibilities = new ArrayList<>();
	}
	
	protected AbstractCell(final byte value){
		super();
		this.value = value;
		possibilities = new ArrayList<>();
	}
	
	protected AbstractCell(final List<Byte> possibilities){
		super();
		if(possibilities.size() == 1){
			this.value = possibilities.get(0);
			this.possibilities = new ArrayList<>();
		}else{
			this.value = 0;
			this.possibilities = new ArrayList<>(possibilities);
		}
	}

	@Override
	public int getId(){
		return id;
	}
	
	@Override
	public void setId(final int id){
		this.id = id;
	}
	
	@Override
	public byte getValue(){
		return value;
	}

	@Override
	public boolean isResolved(){
		return value > 0;
	}

	@Override
	public List<Byte> getPossibilities(){
		return new ArrayList<>(possibilities);
	}

	@Override
	public int getPossibilitiesCount(){
		return possibilities.size();
	}
	
	@Override
	public void add(byte digit){
		if(!isResolved() && !possibilities.contains(digit)){
			possibilities.add(digit);
		}
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("{");
		
		sb.append("value: ").append(value).append(", possibilities:[");
		for(Byte digit : possibilities){
			sb.append(digit).append(", ");
		}
		if(sb.charAt(sb.length() - 2) == ','){
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.append("]}").toString();
	}
}
