package com.test.booking.hotel.util;

public enum InputValidationCodeEnum 
{
    
	LESS_INPUTS_ENTERED("0001"),
	NUMBERS_NOT_ENTERED("0002"),
	DATES_OUT_OF_RANGE("0003"),
	DATES_NOT_IN_ORDER("0004");

    private final String value;

    InputValidationCodeEnum (String value) {
        this.value = value;
    }
    
    public String getValue() {
    	
    	return value;
    }

}
