package reservation;

import utils.MyUtils;

public class RentalCar 
{
	private String vin;
	private int year;
	private CarMake make;

	public RentalCar()
	// default constructor
	{
		this.setVin("0000");
		this.setYear(2018);
		this.setMake (CarMake.TOYOTA);
	}

	// constructor receives VIN, Year, & Car Make
	// example use: RentalCar car = new car ("56A3", 2016, FORD)
	public RentalCar (String aVin, int aYear, CarMake aMake)
	{
		this.setVin(aVin);
		this.setYear(aYear);
		this.setMake (aMake);
	}

	// receives VIN & Year values
	// returns VIN & Year values within print statements
	public String toString()
	{
		String retValue = "";
		retValue += "VIN: " + this.getVin();
		retValue += " Year: " + this.getYear();
		retValue += " Make: " + this.getMake();
		return retValue;
	}

	// returns VIN of this RentalCar
	// example use: VIN = car1.getVin();
	public String getVin()
	{
		return this.vin;
	}

	// returns year of this RentalCar
	// example use: Year = car1.getYear();
	public int getYear()
	{
		return this.year;
	}

	// returns car make of this RentalCar
	// example use: CarMake car = car1.getMake();
	public CarMake getMake()
	{
		return this.make;
	}

	// example use: S1.setVin ("342A")
	public void setVin (String aVin)
	{
		if (aVin.length() != 4)
		{
			this.vin = "0000";
		}
		else
		{
			this.vin = aVin;

			for (int index = 0; index < aVin.length(); index++)
			{
				char ch = aVin.charAt(index);

				if (!Character.isLetterOrDigit(ch))
				{
					this.vin = "0000";
				}
			}
		}
	}

	// example use: S1.setYear ("2014")
	public void setYear (int aYear)
	{
		if (aYear >= 2013 && aYear <= 2018)
		{
			this.year = aYear;
		}
		else
		{
			this.year = 2018;
		}
	}

	// sets the make of this car to the received make
	// example use: CarMake car = CarMake.HONDA;
	public void setMake (CarMake aMake)
	{
		this.make = aMake;
	}

	// override of inherited equals 
	// checks each attribute of a rental car
	// if all match, return true
	// if any attribute does not match received obj's attribute, return false
	public boolean equals (Object obj)
	{
		if(this == obj) // are we the same thing?
			return true;
		if(! (obj instanceof RentalCar)) // is obj actually a RentalCar instance?
		{
			return false;
		}
		RentalCar car = (RentalCar) obj; // cast obj to RentalCar

		if(!(this.getVin().equals(car.getVin())))
			return false;
		if(!(this.getMake().equals(car.getMake())))
			return false;
		if(!(this.getYear() == car.getYear()))
			return false;

		return true; // if we get here vin, make, year all match, so return true.
	}

	//override of inherited hashCode
	// hashes each attribute
	// returns int value based on attributes (a typical hashCode override with equals override)
	public int hashCode()
	{
		int mult = 31;
		int result = 133;
		result = result * mult + this.getVin().hashCode();
		result = result * mult + this.getMake().hashCode();
		result = result * mult + this.getYear();
		return result; 
	}
}
