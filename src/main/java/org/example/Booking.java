package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

class Booking
{

    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDate bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");

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
    public Booking(int passengerId, int vehicleId, int year, int month, int day,
                   double startLatitude, double startLongitude, double endLatitude, double endLongitude, double cost)
    {
        this.bookingId = idGenerator.getNextId();
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDate.of(year, month,day);
        this.startLocation = new LocationGPS(startLatitude, startLongitude);
        this.endLocation = new LocationGPS(endLatitude, endLongitude);
        this.cost = cost;
    }

    public int getBookingId() {return bookingId;}

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getBookingDateTime() {
        return bookingDateTime;
    }
    public int getYearForPrint(){return getBookingDateTime().getYear();}
    public int getMonthForPrint(){return getBookingDateTime().getMonthValue();}
    public int getDayForPrint(){return getBookingDateTime().getDayOfMonth();}

    public void setBookingDateTime(int year, int month, int day){this.bookingDateTime = LocalDate.of(year, month, day);}

    public LocationGPS getStartLocation() {
        return startLocation;
    }


    public void setStartLocation(double latitude, double longitude) {this.startLocation = new LocationGPS(latitude, longitude);}
    public void setEndLocation(double latitude, double longitude) {this.endLocation = new LocationGPS(latitude, longitude);}



    public LocationGPS getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationGPS endLocation) {
        this.endLocation = endLocation;
    }
    public double getSLatitudeForPrint(){return getStartLocation().getLatitude();}
    public double getSLongitudeForPrint(){return getStartLocation().getLongitude();}
    public double getELatitudeForPrint(){return getEndLocation().getLatitude();}
    public double getELongitudeForPrint(){return getEndLocation().getLongitude();}

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return vehicleId == booking.vehicleId && Objects.equals(bookingDateTime, booking.bookingDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, bookingDateTime);
    }

}
