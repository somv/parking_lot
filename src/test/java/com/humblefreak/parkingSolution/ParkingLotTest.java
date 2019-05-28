package com.humblefreak.parkingSolution;

import com.humblefreak.parkingSolution.ParkingLot;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParkingLotTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ParkingLotTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ParkingLotTest.class);
	}
	
	public void testParkingLot() {
		// created parkingLot class instance with a parking lot of size 4.
		ParkingLot parkingLot = new ParkingLot(4);
		
		// the parking lot should have a size of 4
		Integer size = parkingLot.getSize();
		assertTrue(size == 4);
		
		// if we ask for the slot, then it should return the minimum possible slot, which is 1 as 1, 2, 3, 4 are left.
		Integer slot1 = parkingLot.getSlot();
		assertTrue(slot1 == 1);
		
		// if we ask for the slot again, then it should return the minimum possible slot, which is 2 as only 2, 3, 4 are left.
		Integer slot2 = parkingLot.getSlot();
		assertTrue(slot2 == 2);
		
		// if we ask for the slot again, then it should return the minimum possible slot, which is 3 as only 3, 4 are left.
		Integer slot3 = parkingLot.getSlot();
		assertTrue(slot3 == 3);
		
		// if we ask for the slot again, then it should return the minimum possible slot, which is 4 as only 4 is left.
		Integer slot4 = parkingLot.getSlot();
		assertTrue(slot4 == 4);
		
		// if we ask for the slot again, then it should return null, as the parking lot is full.
		Integer slot5 = parkingLot.getSlot();
		assertTrue(slot5 == null);
		
		// if we ask for leaving the slot 2, then it should return true.
		boolean leftSlot2 = parkingLot.leaveSlot(2);
		assertTrue(leftSlot2 == true);
		
		// if we ask for the slot again, then it should return 2, as slot 2 has just been freed.
		Integer slot6 = parkingLot.getSlot();
		assertTrue(slot6 == 2);
		
		// if we ask for leaving the slot 3, then it should return true.
		boolean leftSlot3 = parkingLot.leaveSlot(3);
		assertTrue(leftSlot3 == true);
		
		// if we ask for leaving the slot 4, then it should return true.
		boolean leftSlot4 = parkingLot.leaveSlot(4);
		assertTrue(leftSlot4 == true);
		
		// if we ask for leaving the slot 5, then it should return false, because there are only 4 lots.
		boolean leftSlot5 = parkingLot.leaveSlot(5);
		assertTrue(leftSlot5 == false);
		
		// if we ask for the slot again, then it should return 3, as 3 and 4 has been freed.
		Integer slot7 = parkingLot.getSlot();
		assertTrue(slot7 == 3);
		
		// if we ask for the slot again, then it should return 4, as only 4 is available now.
		Integer slot8 = parkingLot.getSlot();
		assertTrue(slot8 == 4);
		
		// if we ask for the slot again, then it should return null, as the parking lot is full.
		Integer slot9 = parkingLot.getSlot();
		assertTrue(slot9 == null);
	}
	
}
