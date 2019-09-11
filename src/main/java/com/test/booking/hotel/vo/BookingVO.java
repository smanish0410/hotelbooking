package com.test.booking.hotel.vo;

import java.util.Date;

public class BookingVO 
{

private int startDate;
private int endDate;
private String bookingStatus;
private String bookingId;
private String bookingTime;

public int getStartDate() {
	return startDate;
}
public void setStartDate(int startDate) {
	this.startDate = startDate;
}
public int getEndDate() {
	return endDate;
}
public void setEndDate(int endDate) {
	this.endDate = endDate;
}
public String getBookingStatus() {
	return bookingStatus;
}
public void setBookingStatus(String bookingStatus) {
	this.bookingStatus = bookingStatus;
}
public String getBookingId() {
	return bookingId;
}
public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
}
public String getBookingTime() {
	return bookingTime;
}
public void setBookingTime(String bookingTime) {
	this.bookingTime = bookingTime;
}

}
