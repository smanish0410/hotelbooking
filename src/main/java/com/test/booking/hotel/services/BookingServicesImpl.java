package com.test.booking.hotel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import com.test.booking.hotel.vo.BookingVO;

public class BookingServicesImpl implements BookingServices{
	
	private static final int NUMBER_OF_ROOMS = 3;
	
	public void doBooking(String[] inputArray) {

		Map<Integer, List<BookingVO>> currentBookingMap = BookingServicesHelper.getRoomBookingMap();

		if (BookingServicesHelper.validateInputs(inputArray)) {
			int startDate = Integer.parseInt(inputArray[0]);
			int endDate = Integer.parseInt(inputArray[1]);
			int roomNumber = findAvailableRoom(currentBookingMap, startDate, endDate);
			createBooking(roomNumber, startDate, endDate);
		} else {
			System.out.println("Booking DECLINED for requested time slot " + inputArray[0]+" and " +inputArray[1]);
		}
		 
	}

	private int findAvailableRoom(Map<Integer, List<BookingVO>> currentBookingMap, int startDate, int endDate) {

		boolean bookingExists = false;
		int roomNumber = 0;

		if (currentBookingMap != null && !currentBookingMap.isEmpty()) {

			for (Map.Entry<Integer, List<BookingVO>> entryElement : currentBookingMap.entrySet()) {

				List<BookingVO> bookingList = entryElement.getValue();
				roomNumber = entryElement.getKey();

				if (CollectionUtils.isEmpty(bookingList)) {
					return roomNumber++;
				} else {
					bookingExists = checkRoomBooking(bookingList, startDate, endDate);
					if (bookingExists) {
						continue;
					} else {
						return roomNumber;
					}
				}
			}
			if (roomNumber < NUMBER_OF_ROOMS) {
				roomNumber++;
			} else {
				roomNumber = -1;
			}
		} else {
			roomNumber = 1;
		}

		return roomNumber;
	}

	private boolean checkRoomBooking(List<BookingVO> bookingList, int startDate, int endDate) {
		return bookingList.stream()
				.filter(bookingVO -> ((bookingVO.getStartDate() <= startDate && bookingVO.getEndDate() >= startDate)
						|| (bookingVO.getStartDate() <= endDate && bookingVO.getEndDate() >= endDate)
						|| (bookingVO.getStartDate() >= startDate && endDate >= bookingVO.getEndDate())))
				.findAny().isPresent();

	}

	private void createBooking(int roomNumber, int startDate, int endDate) {

		BookingVO roomBooking = new BookingVO();
		roomBooking.setEndDate(endDate);
		roomBooking.setStartDate(startDate);
		roomBooking.setBookingId(BookingServicesHelper.generateBookingId(startDate, endDate));
		roomBooking.setBookingTime(BookingServicesHelper.getCurrentDateAsString());

		if (roomNumber < 1) {
			roomBooking.setBookingStatus(DECLINED_STATUS);
			saveDeclinedBooking(roomBooking);
		} else {
			roomBooking.setBookingStatus(ACCEPTED_STATUS);
			saveAcceptedBooking(roomNumber, roomBooking);
		}

	}

	private void saveAcceptedBooking(int roomNumber, BookingVO roomBooking) {

		Map<Integer, List<BookingVO>> currentBookingMap = BookingServicesHelper.getRoomBookingMap();
		List<BookingVO> updatedBookingList = currentBookingMap.get(roomNumber);
		if (CollectionUtils.isEmpty(updatedBookingList))
			updatedBookingList = new ArrayList<>();

		updatedBookingList.add(roomBooking);
		currentBookingMap.put(roomNumber, updatedBookingList);

		System.out.println("Booking successfully ACCEPTED at time: " + roomBooking.getBookingTime() + " start date as :" + roomBooking.getStartDate() + " end date as :"
				+ roomBooking.getEndDate() + " having booking id as :" + roomBooking.getBookingId());
		System.out.println("Room Booked is : " + roomNumber);
	}

	private void saveDeclinedBooking(BookingVO roomBooking) {
		System.out.println("Booking DECLINED at time: " + roomBooking.getBookingTime() + " start date as :" + roomBooking.getStartDate() + " end date as :"
				+ roomBooking.getEndDate() + " having booking id as :" + roomBooking.getBookingId());
	}

}
