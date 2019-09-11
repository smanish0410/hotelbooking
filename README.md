# hotelbooking
test program for hotel booking
Prerequisite : Java 8 and Maven
Build the project with maven, 
with successful build /target contains hotelBooking-jar-with-dependencies.jar
run this jar using following command, after setting PATH and JAVA_HOME
java -jar hotelBooking-jar-with-dependencies.jar

provide, start date and end date and keep following results

At this moment, BookingService class has number of rooms hardcoded as 3. For any other test, change the same and build/run the jar again.

Improvements to be done,
1. Take number of rooms from properties file.
2. With Current scenario, ie running program with one JVM, synchronized list, map and thread safety (locking the booking) is not considered.


P.S :- All the test cases in exercise are working. Test cases are missing due to lack of time and lack of understanding.
