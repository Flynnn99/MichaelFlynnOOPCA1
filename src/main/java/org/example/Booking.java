package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

class Booking
{
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDate bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification

    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day,
                   double startLatitude, double startLongitude, double endLatitude, double endLongitude, double cost)
    {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDate.of(year, month,day);
        this.startLocation = new LocationGPS(startLatitude, startLongitude);
        this.endLocation = new LocationGPS(endLatitude, endLongitude);
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDateTime +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }
}
