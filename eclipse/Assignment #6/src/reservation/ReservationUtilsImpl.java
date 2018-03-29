package reservation;

import java.io.PrintWriter;
import java.util.Scanner;
import reservation.HotelReservation;
import reservation.CarReservation;
import reservation.FlightReservation;
import reservation.RentalCar;
import reservation.CarMake;

public class ReservationUtilsImpl 
{
	//receives: inFile, a Scanner instance already open and ready for reading 
	//  tries to read one Reservation derived instance from file, 
	//if successful, creates Reservation-derived instance, populates it, returns it, assumes data in order expected
	//returns null if no data on inFile to read, returns populated Reservation-derived instance otherwise
	public static Reservation readFromScanner (Scanner inFile)
	{
		String resType = inFile.next();
		String confirmNum = inFile.next();
		String startDate = inFile.nextLine();
		String endDate = inFile.nextLine();

		if (resType.equals("HOTEL"))
		{
			int numPersons = inFile.nextInt();
			String hotel = inFile.nextLine();

			HotelReservation hotelR = new HotelReservation (confirmNum);
			hotelR.setStartDate (startDate);
			hotelR.setEndDate (endDate);
			hotelR.setNumPersons(numPersons);
			hotelR.setHotelName(hotel);
			return hotelR;
		}
		else if (resType.equals("CAR"))
		{
			String vin = inFile.next();
			int year = inFile.nextInt();
			String make = inFile.nextLine();

			CarReservation carR = new CarReservation (confirmNum);
			RentalCar car1 = new RentalCar (vin, year, CarMake.valueOf(make));
			carR.setCar(car1);
			carR.setStartDate (startDate);
			carR.setEndDate (endDate);
			return carR;
		}
		else if (resType.equals("FLIGHT"))
		{
			String depart = inFile.next();
			String arrive = inFile.nextLine();

			FlightReservation flightR = new FlightReservation (confirmNum);
			flightR.setStartDate (startDate);
			flightR.setEndDate (endDate);
			flightR.setDepartCode (depart);
			flightR.setArrivalCode (arrive);
			return flightR;
		}
		else
		{
			return null;
		}
	}

	//receives: reservation to write, outFile, already open and ready to be written to 
	//writes received reservation instance to file in same format as read in with 
	//including leading HOTEL or CAR or FLIGHT on first line of output
	//returns: nothing
	public static void writeToFile (PrintWriter outFile, Reservation reservation)
	{
		if (reservation instanceof HotelReservation)
		{
			HotelReservation hotelR = (HotelReservation) reservation;
			outFile.println ("HOTEL " + hotelR.getConfirmationNumber() + " " + hotelR.getStartDateAsString() + " " + hotelR.getEndDateAsString());
			outFile.println (hotelR.getNumPersons() + " " + hotelR.getHotelName());
		}
		else if (reservation instanceof CarReservation)
		{
			CarReservation carR = (CarReservation) reservation;
			outFile.println ("CAR " + carR.getConfirmationNumber() + " " + carR.getStartDateAsString() + " " + carR.getEndDateAsString());
			outFile.println (carR.getCar());
		}
		else if (reservation instanceof FlightReservation)
		{
			FlightReservation flightR = (FlightReservation) reservation;
			outFile.println ("FLIGHT " + flightR.getConfirmationNumber() + " " + flightR.getStartDateAsString() + " " + flightR.getEndDateAsString());
			outFile.println (flightR.getDepartCode() + " " + flightR.getArrivalCode());
		}
	}
}
