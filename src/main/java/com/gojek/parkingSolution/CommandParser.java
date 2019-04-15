package com.gojek.parkingSolution;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

	private ParkingLotOperator parkingLotOperator;
	
	/*
	 *  Map to contains all of the valid commands and their argument lengths after splitting the command string at space.
	 */
	private Map<String, Integer> commands;
	
	/*
	 * Map to contain correct command. 
	 * It will be used to give an example of its usage if the user enters wrong command.
	 */
	private Map<String, String> commandUsage;
	
	public CommandParser() {
		commands = new HashMap<String, Integer>();
		commands.put("create_parking_lot", 2); // create_parking_lot command will have a length of 2 after splitting the string at space i.e. `create_parking_lot 4`
		commands.put("park", 3);
		commands.put("leave", 2);
		commands.put("status", 1);
		commands.put("registration_numbers_for_cars_with_colour", 2);
		commands.put("slot_numbers_for_cars_with_colour", 2);
		commands.put("slot_number_for_registration_number", 2);
		commands.put("exit", 1);
		
		commandUsage = new HashMap<String, String>();
		commandUsage.put("create_parking_lot", "create_parking_lot 4");
		commandUsage.put("park", "park KA-01-HH-1234 White");
		commandUsage.put("leave", "leave 2");
		commandUsage.put("status", "status");
		commandUsage.put("registration_numbers_for_cars_with_colour", "registration_numbers_for_cars_with_colour White");
		commandUsage.put("slot_numbers_for_cars_with_colour", "slot_numbers_for_cars_with_colour White");
		commandUsage.put("slot_number_for_registration_number", "slot_number_for_registration_number KA-01-HH-3141");
	}
	
	public void parseCommand(String line) {
		
		// split the input line on spaces
		String[] lineArgs = line.split(" ");
		
		// the first element of the split string will be the method, like 'park' or 'leave'
		String lineMethod = lineArgs[0].trim().toLowerCase();
		
		// Some validations on the entered command, if the command is wrong then it won't execute the further steps.
		boolean isValidCommand = checkCommand(lineArgs);
		if(!isValidCommand) return;
		
		
		switch(lineMethod) {
		
			case "create_parking_lot":
				try {
					int size = Integer.parseInt(lineArgs[1].trim());
					if(size <= 0) {
						System.out.println("Invalid size parameter");
						break;
					}
					if(this.parkingLotOperator == null) {
						this.parkingLotOperator = new ParkingLotOperator();
						String msg = this.parkingLotOperator.createParkingLot(size);
						System.out.println(msg);
					} else {
						System.out.println("Parking lot has been already created");
					}
				} catch(Exception ex) {
					System.out.println("Invalid size parameter");
				}
				break;
				
			case "park":
				String number = lineArgs[1].trim();
				String color = lineArgs[2].trim();
				String parkMsg = this.parkingLotOperator.park(new Vehicle(number, color));
				System.out.println(parkMsg);
				break;
				
			case "leave":
				try {
					int slotNumber = Integer.parseInt(lineArgs[1].trim());
					String leaveMsg = this.parkingLotOperator.leave(slotNumber);
					System.out.println(leaveMsg);
				} catch(Exception ex) {
					System.out.println("Invalid slot number.");
				}
				break;
				
			case "status":
				String statusMsg = this.parkingLotOperator.getStatus();
				System.out.println(statusMsg);
				break;
			
			case "registration_numbers_for_cars_with_colour":
				String findColor = lineArgs[1].trim();
				String regNos = this.parkingLotOperator.getRegNosFromColor(findColor);
				System.out.println(regNos);
				break;
				
			case "slot_numbers_for_cars_with_colour":
				String slotColor = lineArgs[1].trim();
				String slotsMsg = this.parkingLotOperator.getSlotNosFromColor(slotColor);
				System.out.println(slotsMsg);
				break;
				
			case "slot_number_for_registration_number":
				String regNumber = lineArgs[1].trim();
				String slots = this.parkingLotOperator.getSlotNoFromRegNo(regNumber);
				System.out.println(slots);
				break;
				
			case "exit":
				this.parkingLotOperator = null;
				break;
				
			default:
				System.out.println("Unsupported command. Choose from [create, leave, park, status, registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number]");
				break;
				
		}
	}
	
	public boolean checkCommand(String[] lineArgs) {
		String lineMethod = lineArgs[0].trim();
		
		if(lineMethod != null) {
			Integer numArgs = commands.get(lineMethod);
			
			if(numArgs == null) {
				System.out.println("Unsupported command. Choose from [create, leave, park, status, registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number]");
				return false;
			}
			
			if(numArgs != lineArgs.length) {
				String usage = commandUsage.get(lineMethod);
				System.out.println("Please provide valid parameters. Usage example <"+usage+">");
				return false;
			}
			
			if(!lineMethod.equals("create_parking_lot") && this.parkingLotOperator == null) {
				System.out.println("Error occurred. Create the parking lot first.");
				return false;
			}
			
			return true;
		}
		
		return true;
	}
	
}
