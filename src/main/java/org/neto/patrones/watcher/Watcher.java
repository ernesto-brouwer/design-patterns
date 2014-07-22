package org.neto.patrones.watcher;

public interface Watcher<E extends Comparable>{
	
	void watch(E e); 
	
	void setAuditor();
}
