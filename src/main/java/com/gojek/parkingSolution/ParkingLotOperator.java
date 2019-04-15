package com.gojek.parkingSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Somveer
 * This class has all of the business logic that is required to run the parking lot.
 * 
 */
public class ParkingLotOperator {

	private ParkingLot parkingLot;
	
	/*
	 *  Registration number and slot mapping
	 *  This will be used to get the slot of vehicles from their registration number in O(1) time.
	 */
	Map<String, Integer> regNoSlotMap = new HashMap<String, Integer>();
	
	/*
	 *  Slot and vehicle mapping
	 *  This will be used to get the vehicle parked at a particular slot in O(1) time.
	 */
	Map<Integer, Vehicle> slotVehicleMap = new HashMap<Integer, Vehicle>();
		
	/*
	 *  Color and registration numbers mapping
	 *  This will be used to get the information about all of the vehicles from their color.
	 */
	Map<String, Set<String>> colorRegNoMap = new HashMap<String, Set<String>>();
	
	/**
	 * 
	 * @param size: The size of the parking lot
	 * It will create a priority queue with the given size
	 * 
	 */
	public String createParkingLot(int size) {
		if(size > 0) {
			this.parkingLot = new ParkingLot(size);
			return "Created a parking lot with " + size + " slots";
		}
		return "Error in creating parking lot. Invalid size.";
	}
	
	/**
	 * 
	 * @param vehicle: Vehicle object to be parked
	 * 
	 * This function will make entries into the three maps of this vehicle, if and
	 * only if there is some space left in the parking lot.
	 */
	public String park(Vehicle vehicle) {
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
			return "Allocated slot number: "+slot;
		}
		return "Sorry, parking lot is full";
	}
	
	/**
	 * 
	 * @param slotNo: slot number to be freed
	 * 
	 * This function will check if the slot is valid, if the slot is valid then it will remove the entries of that
	 * vehicle from the three maps.
	 */
	public String leave(int slotNo) {
		if(slotNo > 0 && slotNo <= this.parkingLot.getSize()) {
			Vehicle vehicle = slotVehicleMap.remove(slotNo);
			if(vehicle == null) {
				return "Slot number "+slotNo+" is already free";
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
			return "Slot number "+slotNo+" is free";
		}
		return "Invalid slot number. It should be in range ["+1+", "+this.parkingLot.getSize()+"]";
	}
	
	/**
	 * This function prints information about the status of the parking lot.
	 * It will print all of the vehicles parked at that time in ascending order of their slot number.
	 */
	public String getStatus() {
		Set<Integer> slots = slotVehicleMap.keySet();
		int currentSize = slots.size();
		if(currentSize == 0) {
			return "Parking lot is empty";
		}
		StringBuilder sb = new StringBuilder("Slot No."+ "     " + "Registration No" + "     " + "Colour\n");
		Iterator<Integer> it = slots.iterator();
		while(it.hasNext()) {
			int slot = it.next();
			Vehicle vehicle = slotVehicleMap.get(slot);
			if(vehicle != null) {
				String str = slot + "     "  + vehicle.getNumber() + "     "  + vehicle.getColor();
				sb.append(str);
				if(it.hasNext()) sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param color: Color of the vehicle
	 * 
	 * This function will return color from the registration number of the vehicle.
	 */
	public String getRegNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			return "Not found";
		}
		return String.join(", ", regNos);
	}
	
	/**
	 * 
	 * @param color: Color of the vehicle
	 * 
	 * This function will return slot numbers of all the vehicles of that particular color.
	 */
	public String getSlotNosFromColor(String color) {
		Set<String> regNos = colorRegNoMap.get(color);
		if(regNos == null || regNos.size() == 0) {
			return "Not found";
		}
		
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
	    return strbul.toString();
	}
	
	/**
	 * 
	 * @param number: Registration number of vehicle
	 * 
	 * This function will return the slot number of the vehicle if it has been parked into the parking lot.
	 */
	public String getSlotNoFromRegNo(String number) {
		Integer slot = regNoSlotMap.get(number);
		if(slot == null) {
			return "Not found";
		} else {
			return slot+"";
		}
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
}
