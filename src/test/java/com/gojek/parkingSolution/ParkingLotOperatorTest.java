package com.gojek.parkingSolution;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author "Somveer"
 * 
 */
public class ParkingLotOperatorTest extends TestCase {
	
	private ParkingLotOperator parkingLotOperator;
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ParkingLotOperatorTest(String testName) {
		super(testName);
		parkingLotOperator = new ParkingLotOperator();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ParkingLotOperatorTest.class);
	}
	
	/**
	 * Test the "createParkingLot" function
	 */
	public void testCreateParkingLotFunc() {
		int size1 = 4;
		String expectedMsg1 = "Created a parking lot with " + size1 + " slots";
		String actualMsg1 = this.parkingLotOperator.createParkingLot(size1);
		assertTrue(actualMsg1.equals(expectedMsg1));
		
		int size2 = 0;
		String expectedMsg2 = "Error in creating parking lot. Invalid size.";
		String actualMsg2 = this.parkingLotOperator.createParkingLot(size2);
		assertTrue(actualMsg2.equals(expectedMsg2));
		
		int size3 = -1;
		String expectedMsg3 = "Error in creating parking lot. Invalid size.";
		String actualMsg3 = this.parkingLotOperator.createParkingLot(size3);
		assertTrue(actualMsg3.equals(expectedMsg3));
	}
	
	/**
	 * Test the "park" function
	 */
	public void testParkFunc() {
		int size = 3;
		this.parkingLotOperator.createParkingLot(size);
		
		Vehicle vehicle1 = new Vehicle("reg 1", "red");
		String expMsg1 = "Allocated slot number: "+1;
		String actMsg1 = this.parkingLotOperator.park(vehicle1);
		assertTrue(expMsg1.equals(actMsg1));
		
		Vehicle vehicle2 = new Vehicle("reg 2", "red");
		String expMsg2 = "Allocated slot number: "+2;
		String actMsg2 = this.parkingLotOperator.park(vehicle2);
		assertTrue(expMsg2.equals(actMsg2));
		
		Vehicle vehicle3 = new Vehicle("reg 3", "white");
		String expMsg3 = "Allocated slot number: "+3;
		String actMsg3 = this.parkingLotOperator.park(vehicle3);
		assertTrue(expMsg3.equals(actMsg3));
		
		Vehicle vehicle4 = new Vehicle("reg 4", "white");
		String expMsg4 = "Sorry, parking lot is full";
		String actMsg4 = this.parkingLotOperator.park(vehicle4);
		assertTrue(expMsg4.equals(actMsg4));
	}
	
	/**
	 * Test "leave" function
	 */
	public void testLeaveFunc() {
		int size = 2;
		this.parkingLotOperator.createParkingLot(size);
		
		Vehicle vehicle1 = new Vehicle("reg 1", "red");
		this.parkingLotOperator.park(vehicle1);
		Vehicle vehicle2 = new Vehicle("reg 2", "white");
		this.parkingLotOperator.park(vehicle2);
		
		int leaveSlot1 = 1;
		String expMsg1 = "Slot number "+leaveSlot1+" is free";
		String actMsg1 = this.parkingLotOperator.leave(leaveSlot1);
		assertTrue(expMsg1.equals(actMsg1));
		
		int leaveSlot2 = 1;
		String expMsg2 = "Slot number "+leaveSlot2+" is already free";
		String actMsg2 = this.parkingLotOperator.leave(leaveSlot2);
		assertTrue(expMsg2.equals(actMsg2));
		
		int leaveSlot3 = 3;
		String expMsg3 = "Invalid slot number. It should be in range ["+1+", "+this.parkingLotOperator.getParkingLot().getSize()+"]";
		String actMsg3 = this.parkingLotOperator.leave(leaveSlot3);
		assertTrue(expMsg3.equals(actMsg3));
	}
	
	/**
	 * Test "status" function
	 */
	public void testStatusFunc() {
		int size = 2;
		this.parkingLotOperator.createParkingLot(size);
		
		String expMsg1 = "Parking lot is empty";
		String actMsg1 = this.parkingLotOperator.getStatus();
		assertTrue(expMsg1.equals(actMsg1));
		
		Vehicle vehicle1 = new Vehicle("reg-1", "red");
		this.parkingLotOperator.park(vehicle1);
		Vehicle vehicle2 = new Vehicle("reg-2", "white");
		this.parkingLotOperator.park(vehicle2);
		
		String expMsg2 = "Slot No.     Registration No     Colour" + "\n"
				+ "1     reg-1     red" + "\n" 
				+ "2     reg-2     white";
		String actMsg2 = this.parkingLotOperator.getStatus();
		assertTrue(expMsg2.equals(actMsg2));
	}
	
	/**
	 * Test "getRegNosFromColor", "getSlotNosFromColor" and "getSlotNoFromRegNo" functions
	 */
	public void bulkTest() {
		int size = 3;
		this.parkingLotOperator.createParkingLot(size);
		
		Vehicle vehicle1 = new Vehicle("reg-1", "red");
		this.parkingLotOperator.park(vehicle1);
		Vehicle vehicle2 = new Vehicle("reg-2", "white");
		this.parkingLotOperator.park(vehicle2);
		Vehicle vehicle3 = new Vehicle("reg-3", "white");
		this.parkingLotOperator.park(vehicle3);
		
		String expMsg1 = "reg-1";
		String actMsg1 = this.parkingLotOperator.getRegNosFromColor("red");
		assertTrue(expMsg1.equals(actMsg1));
		
		String expMsg2 = "Not found";
		String actMsg2 = this.parkingLotOperator.getRegNosFromColor("black");
		assertTrue(expMsg2.equals(actMsg2));
		
		String expMsg3 = "2, 3";
		String actMsg3 = this.parkingLotOperator.getSlotNosFromColor("white");
		assertTrue(expMsg3.equals(actMsg3));
		
		String expMsg4 = "Not found";
		String actMsg4 = this.parkingLotOperator.getSlotNosFromColor("black");
		assertTrue(expMsg4.equals(actMsg4));
		
		String expMsg5 = "1";
		String actMsg5 = this.parkingLotOperator.getSlotNoFromRegNo("reg-1");
		assertTrue(expMsg5.equals(actMsg5));
		
		String expMsg6 = "3";
		String actMsg6 = this.parkingLotOperator.getSlotNoFromRegNo("reg-3");
		assertTrue(expMsg6.equals(actMsg6));
		
		String expMsg7 = "Not found";
		String actMsg7 = this.parkingLotOperator.getSlotNoFromRegNo("reg-4");
		assertTrue(expMsg7.equals(actMsg7));
	}

}
