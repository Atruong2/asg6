package reservationlist;

import java.util.GregorianCalendar;

import reservation.Reservation;

public interface ReservationList {
	public static final int MAX_SIZE = 100000;

	//receives: nothing
	// returns: the number reservations in the list
	public int getSize();

	// receives: nothing
	// returns:  a String of all reservations that have either start or end date on received date
	//      each reservation is separated by a newline character and specified by descriptor. 
	//      Returns an empty string if no reservations occurred on received date.
	public String getReservationListByDate(GregorianCalendar date);
	
	//receives: nothing
	// returns: true if the list contains given reservation false if not in list.
	// uses equals method and assumes it is overloaded for Reservation-derived instances
	public boolean contains(Reservation reservation);

	// receives: reservation to be added to this reservation list instance
	// returns: true if received reservation is added to list if not in list already returns true if added, false if not
	//       assumes equals is overloaded 
	//      list remains sorted by start date at all times (from earliest date to latest date)
	public boolean add(Reservation reservation);

	// receives: a position in this list to retrieve a reservation from
	// returns: the reservation in the list at given position.
	//       uses zero-based positions, so 0 is the position of the first reservation in the list
	//       returns null if received position is out of range for this reservation list instance.
	//  Example use:
	//        ReservationList resList = new ReservationListImpl("reservations.txt");
	//        Reservation t1 = resList.get(0);
	//        assertTrue(t1!=null);
	public Reservation get(int position);

	// receives: reservation to find in this list instance
	// post: returns the position of received reservation in the list
	//       (uses equals method which is overloaded when matching)
	//       returns -1 if received reservation is not found in current list at any position
	//  Example use:
	//        ReservationList list = new ReservationListImpl("reservation.txt");
	//        HotelReservation hotelRes = new HotelReservation("aabb","3/15/2018", "4/12/2018", "Mariott", 2);
	//		  int position = list.find(hotelRes);
	//	      assertTrue(position != -1);
	public int find(Reservation reservation);

	// receives: reservation to locate in this list
	// returns:  reservation  in list that matches given reservation, removes it from list
	//       list remains sorted by date after removal. (most recent to least recent)
	//  	    if reservation in not in the list, returns null
	public Reservation remove(Reservation reservation);
	// receives: nothing
	// returns: nothing
	//  removes all reservations from this list, so it's size becomes 0.
	public void clear();


}
