package com.gojek.parkingSolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Somveer
 *
 * Main entry point of the program.
 */
public class App 
{
	public static void main(String[] args) {
		CommandParser commandParser = new CommandParser();
		
		// if the user is going to use command line to pass arguments to the program.
		if(args.length == 0) {
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				String line = sc.nextLine();
				if(line.trim().equals("exit")) break;
				if(line.trim().length() > 0) commandParser.parseCommand(line);
			}
			
			sc.close();
			
		// if the user is using file to pass the arguments to the program.
		} else {
			
			try {
				String path = args[0];
				File f = new File(path.trim());
	            BufferedReader b = new BufferedReader(new FileReader(f));
	            String readLine = "";
	            while ((readLine = b.readLine()) != null) {
	            	if(readLine.trim().equals("exit")) break;
	            	commandParser.parseCommand(readLine.trim());
	            }
	            b.close();
	        } catch (IOException e) {
	            System.out.println("Exception occurred while reading file. " + e.getMessage());
	        }
			
		}
		
    }
}
