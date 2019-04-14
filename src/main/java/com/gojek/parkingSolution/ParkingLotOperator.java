package com.gojek.parkingSolution;

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
	Map<Integer, Vehicle> slotVehicleMap = new HashMap<Integer, Vehicle>();
		
	// Color and registration numbers mapping
	Map<String, Set<String>> colorRegNoMap = new HashMap<String, Set<String>>();
	
	public void createParkingLot(int size) {
		if(size > 0) {
			this.parkingLot = new ParkingLot(size);
			System.out.println("Created a parking lot with " + size + " slots");
		} else {
			System.out.println("Error in creating parking lot. Invalid size.");
		}
	}
	
	public void park(Vehicle vehicle) {
		Integer slot = this.parkingLot.getSlot();
		if(slot != null) {
			String regNo = vehicle.getNumber();
			String color = vehicle.getColor();
			
			regNoSlotMap.put(regNo, slot);
			slotVehicleMap.put(slot, new Vehicle(regNo, color));
			Set<String> regNos = colorRegNoMap.get(color);
			
			if(regNos == null) regNos = new HashSet<String>();
			regNos.add(regNo);
			colorRegNoMap.put(color, regNos);
			System.out.println("Allocated slot number: "+slot);
		} else {
			System.out.println("Sorry, parking lot is full");
		}
		
	}
	
	public void leave(int slotNo) {
		if(slotNo > 0 && slotNo <= this.parkingLot.getSize()) {
			Vehicle vehicle = slotVehicleMap.remove(slotNo);
			if(vehicle == null) {
				System.out.println("Slot number "+slotNo+" is already free");
				return;
			}
			this.parkingLot.leaveSlot(slotNo);
			String regNo = vehicle.getNumber();
			String color = vehicle.getColor();
			regNoSlotMap.remove(regNo);
			
			Set<String> regNos = colorRegNoMap.get(color);
			if(regNos != null) {
				regNos.remove(regNo);	
				if(regNos.isEmpty()) colorRegNoMap.remove(color);
			}
			System.out.println("Slot number "+slotNo+" is free");
		} else {
			System.out.println("Invalid slot number. It should be in range ["+1+", "+this.parkingLot.getSize()+"]");
		}
	}
	
	public void getStatus() {
		Set<Integer> slots = slotVehicleMap.keySet();
		int currentSize = slots.size();
		if(currentSize == 0) {
			System.out.println("Parking lot is empty");
			return;
		}
		System.out.println("Slot No."+ "     " + "Registration No" + "     " + "Colour");
		Iterator<Integer> it = slots.iterator();
		while(it.hasNext()) {
			int slot = it.next();
			Vehicle vehicle = slotVehicleMap.get(slot);
			if(vehicle != null) {
				System.out.println(slot + "     " +vehicle.getNumber()+ "     " +vehicle.getColor());
			}
		}
	}
	
	public void getRegNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			System.out.println("Not found");
			return;
		}
		System.out.println(String.join(", ", regNos));
	}
	
	public void getSlotNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			System.out.println("Not found");
			return;
		}
		
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
	        	strbul.append(", ");
	        }
	     }
	    System.out.println(strbul.toString());
	}
	
	public void getSlotNoFromRegNo(String number) {
		Integer slot = regNoSlotMap.get(number);
		if(slot == null) {
			System.out.println("Not found");
		} else {
			System.out.println(slot);
		}
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
}
