package reservation;

public class HotelReservation extends Reservation
{
	private int numPersons;
	private String hotelName;
	
	public HotelReservation (String confirmNum)
	{
		super(confirmNum);
		this.numPersons = 1;
		this.hotelName = "$$$$";
	}
	
	public String toString()
	{
		String temp = "";
		temp += "HOTEL: " + super.toString();
		temp += "Number of Persons: " + this.getNumPersons() + " " + this.getHotelName();
		return temp;
	}
	
	public String getHotelName()
	{
		return this.hotelName;
	}
	
	public int getNumPersons()
	{
		return this.numPersons;
	}
	
	public void setHotelName (String aHotelName)
	{
		aHotelName = utils.MyUtils.properFormat (aHotelName);
		this.hotelName = aHotelName;
	}
	
	public void setNumPersons (int aNumPersons)
	{
		if (aNumPersons >= 1 && aNumPersons <= 100)
			this.numPersons = aNumPersons;
		else
			this.numPersons = 1;
	}
}
