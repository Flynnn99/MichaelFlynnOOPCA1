package org.example;


import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class Tests
{
    @Test
    public void createBooking()
    {
        System.out.println("Add Booking Test");
        Booking book = new Booking(102,120,2021,4,4,
                53.2543,-6.4444,53.2543,-6.4444, 50);
        assertEquals(102, book.getPassengerId());
        assertEquals(120, book.getVehicleId());
        assertEquals(2021, book.getBookingDateTime().getYear());
        assertEquals(4, book.getBookingDateTime().getMonthValue());
        assertEquals(4, book.getBookingDateTime().getDayOfMonth());
        assertEquals(53.2543, book.getStartLocation().getLatitude(), 0.00005);
        assertEquals(-6.4444, book.getStartLocation().getLongitude(),0.00005);
        assertEquals(53.2543, book.getEndLocation().getLatitude(),0.00005);
        assertEquals(-6.4444, book.getEndLocation().getLongitude(),0.00005);
        assertEquals(50,book.getCost(),0.00005);

    }
    @Test
    public void createPassenger()
    {
        System.out.println("Add Booking Test");
        Passenger pass = new Passenger("Michael", "michael@gmail.com", "23456666", 54.55, 54.46);
        assertEquals("Michael", pass.getName());
        assertEquals("michael@gmail.com", pass.getEmail());
        assertEquals("23456666", pass.getPhone());
        assertEquals(54.55, pass.getLocation().getLatitude(), 0.00005);
        assertEquals(54.46, pass.getLocation().getLongitude(), 0.00005);

    }
    @Test
    public void createVan()
    {
        Van van = new Van("Truck","Nissan","Urvan",4,"181MN6538107",
                6.00,2021,05,24,126000,53.2543,-6.4444,240);
        assertEquals("Nissan", van.getMake());
        assertEquals("Urvan", van.getModel());
        assertEquals("181MN6538107", van.getRegistration());
        assertEquals(6.00, van.getCostPerMile(),0.005);
        assertEquals(2021, van.getLastServicedDate().getYear());
        assertEquals(5, van.getLastServicedDate().getMonthValue());
        assertEquals(24, van.getLastServicedDate().getDayOfMonth());
        assertEquals(126000,van.getMileage());
        assertEquals(53.2543, van.getDepotGPSLocation().getLatitude(),0.00005);
        assertEquals(-6.4444, van.getDepotGPSLocation().getLongitude(),0.00005);
        assertEquals(240,van.getLoadSpace(),0.05);
    }
    @Test
    public void createCar()
    {
        Car van = new Car("Truck","Nissan","Urvan",4,"181MN6538107",
                6.00,2021,05,24,126000,53.2543,-6.4444,5);
        assertEquals("Nissan", van.getMake());
        assertEquals("Urvan", van.getModel());
        assertEquals("181MN6538107", van.getRegistration());
        assertEquals(6.00, van.getCostPerMile(),0.005);
        assertEquals(2021, van.getLastServicedDate().getYear());
        assertEquals(5, van.getLastServicedDate().getMonthValue());
        assertEquals(24, van.getLastServicedDate().getDayOfMonth());
        assertEquals(126000,van.getMileage());
        assertEquals(53.2543, van.getDepotGPSLocation().getLatitude(),0.00005);
        assertEquals(-6.4444, van.getDepotGPSLocation().getLongitude(),0.00005);
        assertEquals(5,van.getNumOfSeats(),0.05);
    }


}