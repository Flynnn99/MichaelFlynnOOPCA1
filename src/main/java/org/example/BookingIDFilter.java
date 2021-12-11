package org.example;

public class BookingIDFilter implements IFilter
{
    private  int bookId;

    public BookingIDFilter(int bookingId)
    {
        this.bookId = bookingId;
    }

    @Override
    public boolean matches(Object other)
    {
        Booking b = (Booking) other;
        // cast from Object to Product
        return b.getPassengerId() == bookId;
    }
}
