package reservation;

public class CarReservation extends Reservation
{
	private RentalCar car;
	
	public CarReservation (String confirmNum)
	{
		super(confirmNum);
		this.car = new RentalCar();
	}
	
	public String toString()
	{
		String temp = "";
		temp += "CAR: " + super.toString();
		temp += "Car Info: " + this.getCar();
		return temp;
	}
	
	public RentalCar getCar()
	{
		return this.car;
	}
	
	public void setCar(RentalCar aCar)
	{
		this.car = aCar;
	}
}
