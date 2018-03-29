package reservationlist;

import java.util.Scanner;

import reservation.Reservation;
import reservation.ReservationUtilsImpl;

import java.io.PrintWriter;

public class ReservationListUtilsImpl 
{
	//receives: inFile, a Scanner already open and ready to read from
	//  reservationList, a list of reservations to add to from inFile if any reservations found
	//reads from inFile as many reservations as it can, puts each one into reservationList 
	//pre: inFile is open and ready to read from
	//all reservations found on inFile are added to reservationList using add method in ReservationList interface
	//returns: nothing
	public static void readFromScanner (Scanner inFile, ReservationList reservationList)
	{
		while (inFile.hasNext())
		{
			Reservation res = ReservationUtilsImpl.readFromScanner(inFile);

			if (res != null)
				reservationList.add(res);
		}
	}

	//receives: outFile, an open and ready to write to PrintWriter instance
	//  reservationList, a list to be written to outFile in data format (so it can be read back in later)
	//writes received reservation list to received outFile in same format as read in with descriptor 
	//on each line describing which type of reservation is on the line
	//pre: outFile is open and ready to print to
	//post: outFile contains entire contents of list written in same format
	//as input format
	public static void writeToFile (PrintWriter outFile, ReservationList reservationList)
	{
		for (int i = 0; i < reservationList.getSize(); i++)
		{
			Reservation res = reservationList.get(i);
			ReservationUtilsImpl.writeToFile (outFile, res);
		}
	}
}
