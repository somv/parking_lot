package com.gojek.parkingSolution;

import java.util.PriorityQueue;

/**
 * 
 * @author Somveer
 * A PriorityQueue based implementation of parking lot class.
 */
public class ParkingLot {

	/*
	 * priority queue will be used to get the nearest slot, slots will be numbered from 1 to the size of 
	 * parking lot and we can get the minimum number in O(1) time. 
	 * So this is a good choice for these kind of operations.
	 */
	private PriorityQueue<Integer> slots = new PriorityQueue<Integer>();
	
	/*
	 * This is the total size of parking lot.
	 */
    private int size; 
  
    public ParkingLot(int size) {
    	this.size = size;
    	for(int i=1; i<=size; i++) this.slots.add(i);
    }

    public Integer getSlot() {
    	return this.slots.poll();
    }
    
    public boolean leaveSlot(int slot) {
    	return (slot > 0 && slot <= this.size) ? this.slots.add(slot) : false;
    }
    
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
