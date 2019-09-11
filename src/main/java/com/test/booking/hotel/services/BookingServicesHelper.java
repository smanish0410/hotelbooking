package com.test.booking.hotel.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.test.booking.hotel.util.InputValidationCodeEnum;
import com.test.booking.hotel.vo.BookingVO;

public class BookingServicesHelper {

	private static Map<Integer, List<BookingVO>> roomBookingMap = new HashMap<>();
	
	private static final String DATE_TIME_FORMAT="dd-mm-yyyy hh:mm:ss";

	public static Map<Integer, List<BookingVO>> getRoomBookingMap() {
		return roomBookingMap;
	}

	public static boolean validateInputs(String[] inputArray) {

		if (inputArray == null || inputArray.length != 2) {
			BookingServicesHelper.printValidationStatements(InputValidationCodeEnum.LESS_INPUTS_ENTERED.getValue());
			return false;
		} else {
			return validateInputDateNumber(inputArray[0], inputArray[1]);
		}

	}

	private static boolean validateInputDateNumber(String startDateStr, String endDateStr) {
		Integer startDate = null;
		Integer endDate = null;

		try {
			startDate = Integer.parseInt(startDateStr);
			endDate = Integer.parseInt(endDateStr);
		} catch (NumberFormatException exc) {
			printValidationStatements(InputValidationCodeEnum.NUMBERS_NOT_ENTERED.getValue());
			return false;
		}
		if (startDate < 0 || endDate > 365) {
			printValidationStatements(InputValidationCodeEnum.DATES_OUT_OF_RANGE.getValue());
			return false;
		} else {
			if (startDate > endDate) {
				printValidationStatements(InputValidationCodeEnum.DATES_NOT_IN_ORDER.getValue());
				return false;
			}
		}
		return true;
	}

	private static void printValidationStatements(String validationCode) {

		switch (validationCode) {
		case "0001":
			System.out.println("Input are less than 2. Kindly enter start date and end date ");
			break;
		case "0002":
			System.out.println("Kindly pass start date and end date in numbers");
			break;
		case "0003":
			System.out.println("Kindly enter start date and end date between 0 and 365");
			break;
		case "0004":
			System.out.println("Kindly enter start date lower than end date");
			break;
		default:
			break;
		}
	}

	
	public static String generateBookingId( int startDate, int endDate) {
		return new StringBuffer(String.valueOf(startDate)).append(String.valueOf(endDate)).append(new Random().nextInt(10000)).toString();
	}
	
	public static String getCurrentDateAsString() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		return dateFormat.format(date);
	}
}
