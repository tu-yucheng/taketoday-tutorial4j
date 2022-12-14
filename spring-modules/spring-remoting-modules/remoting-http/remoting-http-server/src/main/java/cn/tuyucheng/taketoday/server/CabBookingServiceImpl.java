package cn.tuyucheng.taketoday.server;

import cn.tuyucheng.taketoday.api.Booking;
import cn.tuyucheng.taketoday.api.BookingException;
import cn.tuyucheng.taketoday.api.CabBookingService;

import static java.lang.Math.random;
import static java.util.UUID.randomUUID;

public class CabBookingServiceImpl implements CabBookingService {

	@Override
	public Booking bookRide(String pickUpLocation) throws BookingException {
		if (random() < 0.3) throw new BookingException("Cab unavailable");
		return new Booking(randomUUID().toString());
	}
}
