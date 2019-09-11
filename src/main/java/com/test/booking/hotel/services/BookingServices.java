package com.test.booking.hotel.services;

public interface BookingServices {
	
	public static final String ACCEPTED_STATUS = "ACCEPTED";
	public static final String DECLINED_STATUS = "DECLINED";

	public void doBooking(String[] inputArray);
}
