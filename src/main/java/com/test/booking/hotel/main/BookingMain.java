package com.test.booking.hotel.main;

import java.io.Console;

import com.test.booking.hotel.services.BookingServicesImpl;

public class BookingMain {
	public static void main(String[] args) {
		while (true) {
			Console console = System.console();
			String startDate = console.readLine("Please input start date for booking: ");
			String endDate = console.readLine("Please input end date for booking: ");

			String[] inputArray = { startDate, endDate };

			new BookingServicesImpl().doBooking(inputArray);
			String choice = console.readLine("If you do not want to book more rooms, type N: ");

			if (choice.equalsIgnoreCase("N")) {
				System.out.println("Exiting now");
				System.exit(0);
			}
		}
	}
}
