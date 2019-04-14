package com.gojek.parkingSolution;

import java.util.PriorityQueue;

public class ParkingLot {

	private PriorityQueue<Integer> slots = new PriorityQueue<Integer>();
    private int size; 
  
    public ParkingLot(int size) {
    	this.size = size;
    	for(int i=1; i<=size; i++) this.slots.add(i);
    }

    public Integer getSlot() {
    	return this.slots.poll();
    }
    
    public boolean leaveSlot(int slot) {
    	return this.slots.add(slot);
    }
    
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
