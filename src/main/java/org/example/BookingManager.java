package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    //TODO implement functionality as per specification
    public void displayAllBookings() {
        for (Booking b : this.bookingList) {
            System.out.println(b.toString());
        }
    }

    public void addBooking(int bookingId, int passengerId, int vehicleId, int year, int month, int day,
                           double startLat, double startLong, double endLat, double endLong, double cost)
    {
        Booking B = new Booking(bookingId,passengerId,vehicleId,year,month,day,startLat, startLong, endLat, endLong, cost);

        boolean bookingdup = false;

        for(Booking b : bookingList)
        {
            if (b.equals(B)) {
                bookingdup = true;
                break;
            }
        }
        if(!bookingdup)
        {
           bookingList.add(B);
        }
    }

    public Booking findByBookingName(String name)
    {

        return null;
    }
}
