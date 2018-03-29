package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.Test;


import reservation.CarMake;
import reservation.CarReservation;
import reservation.FlightReservation;
import reservation.HotelReservation;
import reservation.RentalCar;
import reservation.Reservation;
import reservation.ReservationUtilsImpl;
import reservationlist.ReservationList;
import reservationlist.ReservationListImpl;
import reservationlist.ReservationListUtilsImpl;

public class Asg6Test {
	private String sName="Some Student";
	@Test
	public void test() {
		sName=utils.MyUtils.getNameFromStudent();
		System.out.println("********BEGIN TESTING FOR " + sName + " ***********");
		reservationTestHotel();
		reservationTestCar();
		reservationTestFlight();
		reservationListTestGetFind();
		reservationListTestSizeContainsAdd();
		reservationListFindByDate();
		reservationListTestRemoveClear();
		reservationTestFileIO();
		System.out.println("********END OF TESTING FOR " + sName + " ***********");
		
	}
	
	
	public void reservationTestFlight()
	{
		utils.MyUtils.printTimeStamp(sName + " -- BEGINS reservationTestFlight()");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		FlightReservation fres1 = new FlightReservation("F222");
		FlightReservation fres2 = new FlightReservation("F333");
		fres1.setArrivalCode("CLE");
		assertTrue(fres1.getArrivalCode().equals("CLE"));
		fres1.setDepartCode("kkkccc333");
		assertTrue(fres1.getDepartCode().equals("AUS"));
		fres1.setArrivalCode("CLEVELAND");
		assertTrue(fres1.getArrivalCode().equals("AUS"));
		fres1.setDepartCode("ATL");
		assertTrue(fres1.getDepartCode().equals("ATL"));
		assertTrue(fres1.getStartDate().equals(date3));
		assertTrue(fres1.getEndDate().equals(date3));
		fres1.setEndDate("4/10/2019");
		fres1.setStartDate("4/1/2019");
		assertTrue(fres1.getStartDate().equals(utils.MyUtils.stringToDate("4/1/2019")));
		assertTrue(fres1.getEndDate().equals(utils.MyUtils.stringToDate("4/10/2019")));
		
		String out = fres1.toString();
		System.out.println("Flight reservation: " + out);
		assertTrue(out.contains("4/1/2019"));
		assertTrue(out.contains("ATL"));
		assertTrue(out.contains("AUS"));
		assertTrue(out.contains("FLIGHT"));
		
		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestFlight()");
	}
	public void reservationTestCar()
	{
		utils.MyUtils.printTimeStamp(sName + " -- BEGINS reservationTestCar()");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		
		RentalCar car1 = new RentalCar(); // default car
		RentalCar car2 = new RentalCar("8899", 2018, CarMake.NISSAN);
		
		CarReservation carRes1 = new CarReservation("XXYY");
		CarReservation carRes2 = new CarReservation("[]86");
	
		
		assertTrue(carRes2.getConfirmationNumber().equals("AAAA"));
		assertTrue(carRes1.getConfirmationNumber().equals("XXYY"));
		carRes1.setEndDate("3/21/2019");
		carRes1.setStartDate("3/15/2019");
		assertTrue(carRes1.getStartDate().equals(date1));
		assertTrue(carRes2.getEndDate().equals(date3));
	
		assertTrue(carRes1.getStartDate().equals(date1));
		assertTrue(carRes1.getEndDate().equals(date2));
		assertTrue(carRes2.getEndDate().equals(date3));
		carRes1.setEndDate("3/3/2019");
		assertTrue(carRes1.getEndDate().equals(date2));
		carRes1.setStartDate("3/22/2019");
		assertTrue(carRes1.getStartDate().equals(date1));
		carRes1.setStartDate("3/21/2019");
		assertTrue(carRes1.getStartDate().equals(date2));
		carRes1.setStartDate("3/15/2019");
		assertTrue(carRes1.getStartDate().equals(date1));
		carRes1.setEndDate("3/14/2019");
		assertTrue(carRes1.getEndDate().equals(date2));
		carRes1.setEndDate("3/15/2019");
		assertTrue(carRes1.getEndDate().equals(date1));
		
		RentalCar car3 = carRes1.getCar();
		assertTrue(car3.equals(car1));
		assertTrue(car3.getVin().equals("0000"));
		assertTrue(car3.getYear()== 2018);
		assertTrue(car3.getMake().equals(CarMake.TOYOTA));
		carRes1.setCar(car2);
	    car3 = carRes1.getCar();
		assertTrue(car3.equals(car2));
		assertTrue(car3.getVin().equals("8899"));
		assertTrue(car3.getYear()== 2018);
		assertTrue(car3.getMake().equals(CarMake.NISSAN));
		
		String out = carRes1.toString();
		System.out.println("Car Reservation: " + out);
		assertTrue(out.contains("2018"));
		assertTrue(out.contains("8899"));
		assertTrue(out.contains("NISSAN"));
		assertTrue(out.contains("CAR"));
		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestCar()");
	}
	public void reservationTestHotel()
	{
		utils.MyUtils.printTimeStamp(sName + " -- BEGINS reservationTestHotel()");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		System.out.println("Today is: " + utils.MyUtils.dateToString(today));
		HotelReservation hotel1 = new HotelReservation("1111");
		assertTrue(hotel1.getConfirmationNumber().equals("1111"));
		assertTrue(hotel1.getStartDate().equals(date3));
		assertTrue(hotel1.getEndDate().equals(date3));
		assertTrue(hotel1.getHotelName().equals("$$$$"));
		hotel1.setHotelName("     ");
		assertTrue(hotel1.getHotelName().equals("$$$$"));
		hotel1.setHotelName("hyatt hill country   reSorT");
		assertTrue(hotel1.getHotelName().equals("Hyatt Hill Country Resort"));
		assertTrue(hotel1.getNumPersons()==1);
		HotelReservation hotel2 = new HotelReservation("abcd");
		assertTrue(hotel2.getConfirmationNumber().equals("ABCD"));
		hotel2.setConfirmationNumber(");39");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("445566");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("  ");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel2.setConfirmationNumber("ab;;");
		assertTrue(hotel2.getConfirmationNumber().equals("AAAA"));
		hotel1.setEndDate("3/21/2019");
		hotel1.setStartDate("3/15/2019");
		assertTrue(hotel1.getStartDate().equals(date1));
		assertTrue(hotel1.getEndDate().equals(date2));
		
		assertTrue(hotel1.getStartDate().equals(date1));
		assertTrue(hotel1.getEndDate().equals(date2));
		assertTrue(hotel1.getNumPersons()==1);
		assertTrue(hotel2.getNumPersons()==1);
		hotel1.setNumPersons(0);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(100);
		assertTrue(hotel1.getNumPersons()==100);
		hotel1.setNumPersons(101);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(1);
		assertTrue(hotel1.getNumPersons()==1);
		hotel1.setNumPersons(-65);
		assertTrue(hotel1.getNumPersons()==1);
		
		String out1 = hotel1.toString();
		String out2 = hotel2.toString();
		System.out.println("hotel1: " + hotel1);
		System.out.println("hotel2: " + hotel2);
		assertTrue(out1.contains("Hyatt Hill Country Resort"));
		assertTrue(out1.contains("1111"));
		assertTrue(out1.contains("HOTEL"));
		assertTrue(out1.contains("3/15/2019"));
		assertTrue(out1.contains("3/21/2019"));
		assertTrue(out2.contains("AAAA"));
		assertTrue(out2.contains("$$$$"));
		assertTrue(out2.contains("HOTEL"));
		
		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestHotel()");
	}
	public void reservationListTestGetFind()
	{

		utils.MyUtils.printTimeStamp(sName + " BEGINS reservationListTestGetFind Test");
		RentalCar car1 = new RentalCar("1211", 2018, CarMake.TOYOTA);
		Reservation res = null;
		ReservationList list1 = new ReservationListImpl();
		assertTrue(list1.getSize() == 0);
		assertTrue(list1.get(0) == null);
		assertTrue(list1.get(-55) == null);
		assertTrue(list1.get(600) == null);
		assertTrue(list1.get(1) == null);
		HotelReservation hres1 = new HotelReservation("H111");
		FlightReservation fres1 = new FlightReservation("F111");
		CarReservation cres1 = new CarReservation("C111");
		hres1.setEndDate("4/21/2019");
		hres1.setStartDate("4/12/2019");
		hres1.setNumPersons(2);
		hres1.setHotelName("HYatt AusTIN   ");
		
		fres1.setEndDate("4/08/2019");
		fres1.setStartDate("4/06/2019");
		fres1.setArrivalCode("SAT");
		fres1.setDepartCode("DAL");
		
		cres1.setEndDate("3/30/2019");
		cres1.setStartDate("3/28/2019");
		cres1.setCar(car1);
		
		assertTrue(list1.add(hres1));
		assertTrue(list1.getSize() == 1);
		assertTrue(list1.get(0).equals(hres1));
		assertTrue(list1.add(fres1));
		assertTrue(list1.getSize() == 2);
		System.out.println("Should see 2 Reservations:\n" + list1);
		assertTrue(list1.get(0).equals(fres1));
		assertTrue(list1.get(1).equals(hres1));
		assertTrue(list1.find(fres1) == 0);
		assertTrue(list1.find(hres1) == 1);
		
		assertTrue(list1.add(cres1));
		System.out.println("Should see 3 Reservations:\n" + list1);
		
		utils.MyUtils.printTimeStamp(sName + " END reservationListTestGetFind Test");
	}
	public void reservationListTestSizeContainsAdd()
	{
		utils.MyUtils.printTimeStamp(sName + " BEGINS reservationListTestSizeContainsAdd Test");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		RentalCar car1 = new RentalCar("1211", 2018, CarMake.TOYOTA);
		Reservation res = null;
		ReservationList list1 = new ReservationListImpl();
		
		HotelReservation hres1 = new HotelReservation("H111");
		FlightReservation fres1 = new FlightReservation("F111");
		CarReservation cres1 = new CarReservation("C111");
		hres1.setEndDate("4/12/2019");
		hres1.setStartDate("4/21/2019");
		hres1.setNumPersons(2);
		hres1.setHotelName("HYatt AusTIN   ");
		
		fres1.setEndDate("4/08/2019");
		fres1.setStartDate("4/06/2019");
		fres1.setArrivalCode("SAT");
		fres1.setDepartCode("DAL");
		
		cres1.setEndDate("3/30/2019");
		cres1.setStartDate("3/28/2019");
		cres1.setCar(car1);
		
		HotelReservation hres2 = new HotelReservation("H222");
		FlightReservation fres2 = new FlightReservation("F222");
		CarReservation cres2 = new CarReservation("C222");
		hres2.setEndDate("4/13/2019");
		hres2.setStartDate("4/22/2019");
		hres2.setNumPersons(2);
		hres2.setHotelName("HYatt DallAs   ");
		
		fres2.setEndDate("4/09/2019");
		fres2.setStartDate("4/06/2019");
		fres2.setArrivalCode("AUS");
		fres2.setDepartCode("DAL");
		
		cres2.setEndDate("3/30/2019");
		cres2.setStartDate("3/28/2019");
		cres2.setCar(car1);
		assertTrue(list1.getSize() == 0);
		assertTrue(list1.add(cres2));
		assertTrue(list1.find(cres2) == 0);
		assertTrue(list1.contains(cres2));
		assertTrue(list1.getSize() == 1);
		assertTrue(list1.add(fres2));
		assertTrue(list1.find(fres2) == 1);
		assertTrue(list1.contains(fres2));
		assertTrue(list1.getSize() ==2);
		assertTrue(list1.add(hres2));
		assertTrue(list1.find(hres2) == 0);
		assertTrue(list1.contains(hres2));
		assertTrue(list1.getSize() == 3);
		assertTrue(list1.add(fres1));
		System.out.println("Here is current list:\n" + list1);
		assertTrue(list1.find(fres1) == 3);
		assertTrue(list1.contains(fres1));
		assertTrue(list1.getSize() ==4);
		

		utils.MyUtils.printTimeStamp(sName + " ENDS reservationListTestSizeContainsAdd Test");


	}

	public void reservationListFindByDate()
	{
		utils.MyUtils.printTimeStamp(sName + " BEGINS Test reservationListFindByDate()");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		RentalCar car1 = new RentalCar("1111", 2018, CarMake.TOYOTA);
		RentalCar car2 = new RentalCar("2222", 2017, CarMake.FORD);
		Reservation res = null;
		ReservationList list1 = new ReservationListImpl();
		
		HotelReservation hres1 = new HotelReservation("H111");
		FlightReservation fres1 = new FlightReservation("F111");
		CarReservation cres1 = new CarReservation("C111");
		hres1.setEndDate("4/12/2019");
		hres1.setStartDate("4/08/2019");
		hres1.setNumPersons(2);
		hres1.setHotelName("HYatt AusTIN   ");
		
		fres1.setEndDate("4/08/2019");
		fres1.setStartDate("4/06/2019");
		fres1.setArrivalCode("SAT");
		fres1.setDepartCode("DAL");
		
		cres1.setEndDate("3/30/2019");
		cres1.setStartDate("3/28/2019");
		cres1.setCar(car1);
		
		HotelReservation hres2 = new HotelReservation("H222");
		FlightReservation fres2 = new FlightReservation("F222");
		CarReservation cres2 = new CarReservation("C222");
		hres2.setEndDate("4/12/2019");
		hres2.setStartDate("4/07/2019");
		hres2.setNumPersons(4);
		hres2.setHotelName("HYatt DallAs   ");
		
		fres2.setEndDate("4/07/2019");
		fres2.setStartDate("4/3/2019");
		fres2.setArrivalCode("AUS");
		fres2.setDepartCode("DAL");
		
		cres2.setEndDate("3/31/2019");
		cres2.setStartDate("3/27/2019");
		cres2.setCar(car2);
		assertTrue(list1.add(hres2));
		assertTrue(list1.add(cres2));
		assertTrue(list1.add(fres2));
		assertTrue(list1.add(hres1));
		assertTrue(list1.add(cres1));
		assertTrue(list1.add(fres1));
		System.out.println("\nHere is the list after adding " + list1.getSize() + " reservations \n" + list1);
		GregorianCalendar date = null;
		String aDate = "4/03/2019";
		date=utils.MyUtils.stringToDate(aDate);
		String out = list1.getReservationListByDate(date);
		int numLines = utils.MyUtils.numberLines(out);
		assertTrue(numLines == 1);
		System.out.println("num lines for date: " + aDate + " = " + numLines + "\n" + out);
		aDate = "4/12/2019";
		date=utils.MyUtils.stringToDate(aDate);
		out = list1.getReservationListByDate(date);
		numLines = utils.MyUtils.numberLines(out);
		
		System.out.println("num lines for date: " + aDate + " = " + numLines + "\n" + out);
		assertTrue(numLines == 2);
		aDate = "4/06/2019";
		date=utils.MyUtils.stringToDate(aDate);
		out = list1.getReservationListByDate(date);
		numLines = utils.MyUtils.numberLines(out);
		System.out.println("num lines for date: " + aDate + " = " + numLines + "\n" + out);
		assertTrue(numLines == 1);
		aDate = "4/30/2019";
		date=utils.MyUtils.stringToDate(aDate);
		out = list1.getReservationListByDate(date);
		numLines = utils.MyUtils.numberLines(out);
		System.out.println("num lines for date: " + aDate + " = " + numLines);
		assertTrue(numLines == 0);
		aDate = "3/27/2019";
		date=utils.MyUtils.stringToDate(aDate);
		out = list1.getReservationListByDate(date);
		numLines = utils.MyUtils.numberLines(out);
		System.out.println("num lines for date: " + aDate + " = " + numLines);
		assertTrue(numLines == 1);
	
		utils.MyUtils.printTimeStamp(sName + " ENDS Test reservationListFindByDate()");
	}
	public void reservationListTestRemoveClear()
	{
		utils.MyUtils.printTimeStamp(sName + " BEGINS Test reservationListFindByDate()");
		GregorianCalendar date1 = new GregorianCalendar(2019, 2, 15);
		GregorianCalendar date2 = new GregorianCalendar(2019, 2, 21);
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH);
		int day = today.get(Calendar.DAY_OF_MONTH);
		int year = today.get(Calendar.YEAR);
		GregorianCalendar date3 = new GregorianCalendar(year, month, day);
		RentalCar car1 = new RentalCar("1211", 2018, CarMake.TOYOTA);
		Reservation res = null;
		ReservationList list1 = new ReservationListImpl();
		
		HotelReservation hres1 = new HotelReservation("H111");
		FlightReservation fres1 = new FlightReservation("F111");
		CarReservation cres1 = new CarReservation("C111");
		hres1.setEndDate("4/12/2019");
		hres1.setStartDate("4/21/2019");
		hres1.setNumPersons(2);
		hres1.setHotelName("HYatt AusTIN   ");
		
		fres1.setEndDate("4/08/2019");
		fres1.setStartDate("4/06/2019");
		fres1.setArrivalCode("SAT");
		fres1.setDepartCode("DAL");
		
		cres1.setEndDate("3/30/2019");
		cres1.setStartDate("3/28/2019");
		cres1.setCar(car1);
		
		HotelReservation hres2 = new HotelReservation("H222");
		FlightReservation fres2 = new FlightReservation("F222");
		CarReservation cres2 = new CarReservation("C222");
		hres2.setEndDate("4/13/2019");
		hres2.setStartDate("4/22/2019");
		hres2.setNumPersons(2);
		hres2.setHotelName("HYatt DallAs   ");
		
		fres2.setEndDate("4/09/2019");
		fres2.setStartDate("4/06/2019");
		fres2.setArrivalCode("AUS");
		fres2.setDepartCode("DAL");
		
		cres2.setEndDate("3/30/2019");
		cres2.setStartDate("3/28/2019");
		cres2.setCar(car1);
		assertTrue(list1.add(hres2));
		assertTrue(list1.add(cres2));
		assertTrue(list1.add(fres2));
		assertTrue(list1.add(hres1));
		assertTrue(list1.add(cres1));
		assertTrue(list1.add(fres1));
		
		System.out.println(sName + " Here is the list of " + list1.getSize() + " reservation(s)\n" + list1);

		FlightReservation fRem = (FlightReservation) list1.remove(fres1);
		assertTrue(fRem.equals(fres1));
		assertFalse(list1.contains(fres1));
		fRem = (FlightReservation) list1.remove(fres2);
		assertTrue(fRem.equals(fres2));
		assertFalse(list1.contains(fres2));
		assertTrue(list1.getSize() == 4);
		
		

		CarReservation cRem = (CarReservation) list1.remove(cres1);
		assertTrue(cRem.equals(cres1));
		assertFalse(list1.contains(cres1));
		cRem = (CarReservation) list1.remove(cres2);
		assertTrue(cRem.equals(cres2));
		assertFalse(list1.contains(cres2));
		assertTrue(list1.getSize() == 2);
		
		HotelReservation hRem = (HotelReservation) list1.remove(hres1);
		assertTrue(hRem.equals(hres1));
		assertFalse(list1.contains(hres1));
		hRem = (HotelReservation) list1.remove(hres2);
		assertTrue(hRem.equals(hres2));
		assertFalse(list1.contains(hres2));
		assertTrue(list1.getSize() == 0);
		
		assertTrue(list1.remove(hres2) == null);
		assertTrue(list1.add(hres2));
		assertTrue(list1.getSize() == 1);
		assertTrue(list1.add(cres2));
		assertTrue(list1.getSize() ==2);
		list1.clear();
		assertTrue(list1.add(fres2));
		assertTrue(list1.getSize() == 1);
		assertTrue(list1.add(hres2));
		assertTrue(list1.getSize() ==2);
		list1.clear();
		assertTrue(list1.getSize() == 0);
		assertTrue(list1.remove(fres2) == null);
		System.out.println(sName + " here is the UGLY toString() for GregorianCalendar date1: " + date1);
		utils.MyUtils.printTimeStamp(sName + " ENDS Test reservationListTestSortRemoveClear");
	}
	
	public void reservationTestFileIO()
	{
		utils.MyUtils.printTimeStamp(sName + " -- BEGINS reservationTestFileIO()");
		System.out.println("********Now testing methods in ReservationUtilsImpl");

		Scanner inputFile = null;
		PrintWriter outputFile = null;
		String filename = "reservation6.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		Reservation res1 = null;
		int resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			resCount ++;
			System.out.println("Just read: " + res1);
		}
		inputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + resCount + " reservations from " + filename);

		System.out.println(sName + " ********Now Testing writeToFile...");
		filename = "reservationEmpty.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		outputFile.close();
		filename = "reservation6.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		filename = "reservation6Out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		res1 = null;
		resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			resCount ++;
			System.out.println("Just read: " + res1);
			ReservationUtilsImpl.writeToFile(outputFile, res1);
		}
		inputFile.close();
		outputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + resCount + " reservations from " + filename);

		filename = "reservation6Out.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		 res1 = null;
		 resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			resCount ++;
			System.out.println("Just read: " + res1);
		}
		inputFile.close();
		if(resCount == 6)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + 6 + " reservations from " + filename);
		
		filename = "reservation1.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		filename = "reservation1Out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		res1 = null;
		resCount = 0;
		while(inputFile.hasNext()){
			res1 = ReservationUtilsImpl.readFromScanner(inputFile);
			resCount ++;
			System.out.println("Just read: " + res1);
			ReservationUtilsImpl.writeToFile(outputFile, res1);
		}
		inputFile.close();
		outputFile.close();
		if(resCount == 1)
			System.out.println("SUCCESS read " + resCount + " reservations from " + filename);
		else
			System.out.println("FAILURE should have read " + 1 + " reservations from " + filename);
		System.out.println("********Now testing methods in ReservationListUtilsImpl*******");
		
		
		filename = "reservation6.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
		ReservationList list1 = new ReservationListImpl();
		ReservationListUtilsImpl.readFromScanner(inputFile, list1);
		System.out.println("After reading list from " + filename );
		System.out.println(list1);
		assertTrue(list1.getSize() == 6);
		filename = "reservation6out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		ReservationListUtilsImpl.writeToFile(outputFile, list1);
		outputFile.close();
		System.out.println("Just wrote list to file: " + filename);
		filename = "reservation6out.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
	    list1.clear();
		ReservationListUtilsImpl.readFromScanner(inputFile, list1);
		System.out.println("After reading list from " + filename );
		System.out.println(list1);
		assertTrue(list1.getSize() == 6);
		
		filename = "reservation1.txt";
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
	    list1 = new ReservationListImpl();
		ReservationListUtilsImpl.readFromScanner(inputFile, list1);
		inputFile.close();
		System.out.println("After reading list from " + filename );
		System.out.println(list1);
		assertTrue(list1.getSize() == 1);
		filename = "reservation1out.txt";
		try {
			outputFile = new PrintWriter(new FileWriter(new File(filename)));

		}catch (IOException e) {

			System.out.println("FAILURE, cannot open file " + filename + " for output, EXITING on FAILURE");
			System.exit(0);	
		}
		ReservationListUtilsImpl.writeToFile(outputFile, list1);
		outputFile.close();
		System.out.println("Just wrote list to file: " + filename);
		try {
			inputFile = new Scanner(new File(filename));

			System.out.println("SUCCESS opened file: " + filename + " for input");
		}catch(FileNotFoundException e) {
			System.out.println("FAILURE cannot open file: " + filename + " for input" +
					" EXIT ON FAILURE TO OPEN FILE.");
			System.exit(0);
		}
	    list1.clear();
		ReservationListUtilsImpl.readFromScanner(inputFile, list1);
		inputFile.close();
		System.out.println("After reading list from " + filename );
		System.out.println(list1);
		assertTrue(list1.getSize() == 1);
		
		System.out.println("********COMPLETE testing methods in ReservationListUtilsImpl******");
		utils.MyUtils.printTimeStamp(sName + " -- ENDS reservationTestFileIO()");
	}
}
