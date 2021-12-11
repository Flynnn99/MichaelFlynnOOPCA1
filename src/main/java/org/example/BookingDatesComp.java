package org.example;
import java.util.Comparator;

public class BookingDatesComp implements Comparator<Booking>

{
    public int compare(Booking bk1, Booking bk2)
    {
        return bk1.getBookingDateTime().compareTo(bk2.getBookingDateTime());
    }
}
