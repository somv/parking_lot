package com.gojek.parkingSolution.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParkingLotOperator {

	private ParkingLot parkingLot;
	
	// Registration number and slot mapping
	Map<String, Integer> regNoSlotMap = new HashMap<String, Integer>();
	
	// Slot and registration number mapping
	Map<Integer, Vehicle> slotRegNoMap = new HashMap<Integer, Vehicle>();
		
	// Color and registration numbers mapping
	Map<String, Set<String>> colorRegNoMap = new HashMap<String, Set<String>>();
	
	public void createParkingLot(int size) {
		if(size > 0) {
			this.parkingLot = new ParkingLot(size);
			System.out.println("Created a parking lot with " + size + " slots");
		} else {
			System.out.println("Error in creating parking lot. Check the size of lot.");
		}
	}
	
	public void park(Vehicle vehicle) {
		int slot = this.parkingLot.getMinSlot();
		
		String regNo = vehicle.getNumber();
		String color = vehicle.getColor();
		
		regNoSlotMap.put(regNo, slot);
		slotRegNoMap.put(slot, new Vehicle(regNo, color));
		Set<String> regNos = colorRegNoMap.get(color);
		
		if(regNos == null) regNos = new HashSet<String>();
		regNos.add(regNo);
		colorRegNoMap.put(color, regNos);
	}
	
	public void leave(int slotNo) {
		Vehicle vehicle = slotRegNoMap.remove(slotNo);
		String regNo = vehicle.getNumber();
		String color = vehicle.getColor();
		regNoSlotMap.remove(regNo);
		
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos != null) {
			regNos.remove(regNo);	
			if(regNos.isEmpty()) colorRegNoMap.remove(color);
		}
	}
	
	public void getStatus() {
		System.out.println("Slot No."+ "     " + "Registration No" + "     " + "Colour");
		for(int i=1; i < this.parkingLot.getSize(); i++) {
			Vehicle vehicle = slotRegNoMap.get(i);
			if(vehicle != null) {
				System.out.println((i) + "     " +vehicle.getNumber()+ "     " +vehicle.getColor());
			}
		}
	}
	
	public void getRegNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			System.out.println(String.join(", ", regNos));
		} else {
			System.out.println("Not found");
		}
	}
	
	public void getSlotNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			System.out.println(String.join(", ", regNos));
			List<Integer> list = new ArrayList<Integer>();
			for(String regNo : regNos) {
				int slot = regNoSlotMap.get(regNo);
				list.add(slot);
			}
			Collections.sort(list);
			StringBuilder strbul  = new StringBuilder();
			Iterator<Integer> iter = list.iterator();
		    while(iter.hasNext()) {
		    	strbul.append(iter.next());
		        if(iter.hasNext()) {
		        	strbul.append(",");
		        }
		     }
		    System.out.println(strbul.toString());
		} else {
			System.out.println("Not found");
		}
	}
	
	public void getSlotNoFromRegNo(String number) {
		Integer slot = regNoSlotMap.get(number);
		if(slot == null) {
			System.out.println("Not found");
		} else {
			System.out.println(slot);
		}
	}
	
}
