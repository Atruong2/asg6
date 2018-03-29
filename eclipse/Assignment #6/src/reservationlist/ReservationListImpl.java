package reservationlist;

import java.util.GregorianCalendar;

import reservationlist.ReservationList;
import reservation.CarMake;
import reservation.RentalCar;
import reservation.Reservation;
import reservationlist.Node;

public class ReservationListImpl implements ReservationList
{
	private Node<Reservation> first;
	private int count;

	// default constructor
	public ReservationListImpl()
	{
		this.first = null;
		this.count = 0;
	}

	public String toString()
	{
		Node<Reservation> current = this.first;
		String retValue = "";
		while (current != null)
		{
			retValue += current.data + "\n";
			current = current.link;
		}
		return retValue;
	}

	//receives: nothing
	// returns: the number reservations in the list
	public int getSize()
	{
		return this.count;
	}

	// receives: nothing
	// returns:  a String of all reservations that have either start or end date on received date
	//      each reservation is separated by a newline character and specified by descriptor. 
	//      Returns an empty string if no reservations occurred on received date.
	public String getReservationListByDate (GregorianCalendar date)
	{
		Node<Reservation> curr = this.first;
		String retValue = "";
		while (curr != null)
		{
			if (curr.data.getStartDate().equals(date) || curr.data.getEndDate().equals(date))
			{
				retValue += curr.data + "\n";
			}
			curr = curr.link;
		}
		return retValue;
	}

	//receives: nothing
	// returns: true if the list contains given reservation false if not in list.
	// uses equals method and assumes it is overloaded for Reservation-derived instances
	public boolean contains (Reservation reservation)
	{
		if(this.find(reservation) != -1)
		{
			return true;
		}
		return false;
	}

	// receives: reservation to be added to this reservation list instance
	// returns: true if received reservation is added to list if not in list already returns true if added, false if not
	//       assumes equals is overloaded 
	//      list remains sorted by start date at all times (from earliest date to latest date)
	public boolean add (Reservation reservation)
	{
		if(this.getSize() == ReservationList.MAX_SIZE)
			return false;
		if(this.contains(reservation))
			return false;
		Node<Reservation> prev = null;
		Node<Reservation> curr = this.first;
		while(curr != null)
		{
			if(curr.data.getStartDate().compareTo(reservation.getStartDate()) > 0)
			{
				break;
			}
			prev = curr;
			curr = curr.link;
		}
		Node <Reservation> node = new Node<Reservation>(reservation);
		if(prev == null)
		{
			node.link = this.first;
			this.first = node;
		}
		else
		{
			node.link = prev.link;
			prev.link = node;
		}
		this.count++;
		return true;
	}

	// receives: a position in this list to retrieve a reservation from
	// returns: the reservation in the list at given position.
	//       uses zero-based positions, so 0 is the position of the first reservation in the list
	//       returns null if received position is out of range for this reservation list instance.
	//  Example use:
	//        ReservationList resList = new ReservationListImpl("reservations.txt");
	//        Reservation t1 = resList.get(0);
	//        assertTrue(t1!=null);
	public Reservation get (int position)
	{
		if (position < 0 || position >= this.getSize())
			return null;
		int pos = 0;
		Node<Reservation> current = this.first;
		while (current != null)
		{
			if (pos == position)
			{
				return current.data;
			}
			pos++;
			current = current.link;
		}
		return null;
	}

	// receives: reservation to find in this list instance
	// post: returns the position of received reservation in the list
	//       (uses equals method which is overloaded when matching)
	//       returns -1 if received reservation is not found in current list at any position
	//  Example use:
	//        ReservationList list = new ReservationListImpl("reservation.txt");
	//        HotelReservation hotelRes = new HotelReservation("aabb","3/15/2018", "4/12/2018", "Mariott", 2);
	//		  int position = list.find(hotelRes);
	//	      assertTrue(position != -1);
	public int find (Reservation reservation)
	{
		int position = 0;
		Node<Reservation> current = this.first;
		while (current != null)
		{
			if (current.data.equals(reservation))
				return position;
			current = current.link;
			position++;
		}
		return -1;
	}

	// receives: reservation to locate in this list
	// returns:  reservation  in list that matches given reservation, removes it from list
	//       list remains sorted by date after removal. (most recent to least recent)
	//  	    if reservation in not in the list, returns null
	public Reservation remove (Reservation reservation)
	{
		Node<Reservation> previous = null;
		Node<Reservation> current = this.first;
		while (current != null)
		{
			if (current.data.equals(reservation))
				break;
			previous = current;
			current = current.link;
		}
		if (current == null)
			return null;
		Reservation retValue = current.data;
		if (previous == null)
			first = first.link;
		else
			previous.link = current.link;
		this.count--;
		return retValue;
	}

	// receives: nothing
	// returns: nothing
	//  removes all reservations from this list, so it's size becomes 0.
	public void clear()
	{
		this.first = null;
		this.count = 0;
	}
}

