package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;


    // Constructor
    public BookingManager(String filename, PassengerStore passengerStore, VehicleManager vehicleManager, BookingManager bookingManager)
    {

        this.bookingList = new ArrayList<>();
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;

        loadBookingDataFromFileName(filename);
    }

    //Read and Write Bookings
    private void loadBookingDataFromFileName(String filename)
    {
        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();

                int cost = sc.nextInt();

                bookingList.add(new Booking(bookingId, passengerId, vehicleId,year, month, day, startLatitude, startLongitude, endLatitude, endLongitude, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }

    }
    public void readBookingDataToFileName(String filename)
    {
        try
        {



            PrintWriter readBooking = new PrintWriter(filename);
            for (Booking b : bookingList) {
                if (b instanceof Booking)
                {
                    readBooking.write(
                            b.getBookingId()+ "," +
                                    b.getPassengerId() + "," +
                                    b.getVehicleId() + "," +
                                    b.getYearForPrint() + "," +
                                    b.getMonthForPrint() + "," +
                                    b.getDayForPrint() + ","+
                                    b.getSLatitudeForPrint()+ ","+
                                    b.getSLongitudeForPrint() + "," +
                                    b.getELatitudeForPrint()+ ","+
                                    b.getELongitudeForPrint() + "," +
                                    b.getCost() + "\n");
                }
            }
            readBooking.close();

        }catch(IOException e)
        {

        }
    }


    //Displays
    public  void displayBookingMenu()
    {
        final String MENU_ITEMS = "\n*** Booking MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Add Bookings\n"
                + "3. Find Booking by Name\n"
                + "4. Find Booking by Booking Id\n"
                + "5. Find Future Bookings\n"
                + "6. Delete Booking\n"
                + "7. Edit Bookings\n"
                + "8. Filter Booking By ID\n"
                + "9. Exit\n"
                + "Enter Option [1,9]";

        final int SHOW_ALL = 1;
        final int ADD_BOOKINGS = 2;
        final int FIND_BY_NAME = 3;
        final int FIND_BY_BOOKID = 4;
        final int FIND_BY_FUTURE_DATE = 5;
        final int DELETE_BOOKING = 6;
        final int EDIT_BOOKING = 7;
        final int FILTER_BOOK_BY_ID =8;
        final int EXIT = 9;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        displayAllBookings();
                        break;
                    case ADD_BOOKINGS:
                        addBookingMenu();
                        break;
                    case FIND_BY_NAME:
                        findByNameMenu();
                        break;

                    case FIND_BY_BOOKID:
                       findBookIdMenu();
                        break;
                    case FIND_BY_FUTURE_DATE:
                        findFutureDayMenu();
                        break;
                    case DELETE_BOOKING:
                        deleteBookingMenu();
                        break;
                    case EDIT_BOOKING:
                        editBookingMenu();
                        break;
                    case FILTER_BOOK_BY_ID:
                        filterByBookingIDMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }
    public void callBookArray(List<Booking> bookingList)
    {
        for(Booking b: bookingList)
        {
            System.out.println(b.getBookingId() + "\t\t" + b.getPassengerId() + "\t\t" + b.getVehicleId()
                    +"\t\t" + b.getBookingDateTime() +"\t\t" +  b.getStartLocation() + "\t\t" + b.getEndLocation()
                    +"\t\t" + b.getCost());
        }
    }
    public void displayAllBookings()
    {
        System.out.println("Booking\tPassenger\tVehicle\t\tBooking Date\tStart Depot\t\t\t\t\t\t\t\t\t\t End Depot\t\t\t\t\t\t\t\t\t\t\tCost   ");
        System.out.println("============================================================================================================================================================");
        for (Booking b : this.bookingList)
        {

            System.out.println(b.getBookingId() + "   \t\t" + b.getPassengerId() + " \t\t" + b.getVehicleId() + "\t\t" + b.getBookingDateTime() + "\t\t" + b.getStartLocation() + "\t" + b.getEndLocation() + "\t\t" + b.getCost() + "\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }


    //Add Booking
    public void addBooking(int passengerId, int vehicleId, int year, int month, int day,
                           double startLat, double startLong, double endLat, double endLong)
    {
        if(passengerStore.findPassengerById(passengerId) !=null)
        {
            if(vehicleManager.findVehicleById(vehicleId) !=null)
            {
                double cost = Haversine(startLat,startLong,endLat,endLong)/1.6;
                Booking B = new Booking(passengerId,vehicleId,year,month,day,startLat, startLong, endLat, endLong, cost);

                boolean bookingdup = false;
                String message = "";

                for(Booking b : bookingList)
                {
                    if (b.equals(B))
                    {
                        bookingdup = true;
                        message = "Already a booking with these details";
                        break;

                    }
                }
                if(!bookingdup)
                {
                    bookingList.add(B);
                    message = "Record successfully added";
                }
                System.out.println(message);
            }
        }
        else
        {
            System.out.println(passengerId + "  Does not Exist");
        }

    }
    private void addBookingMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Add a Booking");
        System.out.println("\n=====Passenger&VehicleInfo======");
        System.out.println("Enter Passenger Id");
        int addPassengerId = keyboard.nextInt();
        System.out.println("Enter Vehicle Id");
        int addVehicleId = keyboard.nextInt();


        System.out.println("\n=====Dates=====");
        System.out.println("Enter Year");
        int addYear = keyboard.nextInt();
        while(addYear <2021 || addYear >2025)
        {
            System.out.println("Bookings can be made up till 2025" + "\n Enter Year of Last Service");
            addYear = keyboard.nextInt();
        }
        System.out.println("Enter Month");
        int addMonth = keyboard.nextInt();
        while(addMonth<1 || addMonth > 12 )
        {
            System.out.println("You have entered an invalid month " + "\nEnter Month of Last Service");
            addMonth = keyboard.nextInt();
        }
        System.out.println("Enter Day");
        int addDay = keyboard.nextInt();
        while(addDay<1 || addDay>31)
        {
            System.out.println("Enter Day");
            addDay = keyboard.nextInt();
        }
        System.out.println("\n====TestLocation====");
        System.out.println("addStartLat");
        double addStartLat = keyboard.nextDouble();
        while(addStartLat <-90 || addStartLat > 90)
        {
            System.out.println("Invalid Latitude Coordinates must be between -90 & 90");
            System.out.println();
            System.out.println("Enter Latitude");
            addStartLat = keyboard.nextDouble();
        }
        System.out.println("addStartLong");
        double addStartLong = keyboard.nextDouble();
        while(addStartLong <-180 || addStartLong > 180)
        {
            System.out.println("Invalid Longitude Coordinates must be between -180 & 180");
            System.out.println();
            System.out.println("Enter Longitude");
            addStartLong = keyboard.nextDouble();
        }
        System.out.println("addEndLat");
        double addEndLat = keyboard.nextDouble();
        while(addEndLat <-90 || addEndLat > 90)
        {
            System.out.println("Invalid Latitude Coordinates must be between -90 & 90");
            System.out.println();
            System.out.println("Enter Latitude");
            addEndLat = keyboard.nextDouble();
        }
        System.out.println("addEndLong");
        double addEndLong = keyboard.nextDouble();
        while(addEndLong <-180 || addEndLong > 180)
        {
            System.out.println("Invalid Longitude Coordinates must be between -180 & 180");
            System.out.println();
            System.out.println("Enter Longitude");
            addEndLong = keyboard.nextDouble();
        }
        addBooking(addPassengerId,addVehicleId,addYear,addMonth,addDay,addStartLat, addStartLong, addEndLat, addEndLong);
    }


    //Find By
    public List<Booking> findBookingByPassengerName(String name)
    {
        List<Booking> bookName = new ArrayList();
        try {


            for (Booking b : bookingList)
            {
                if (b.getPassengerId() == passengerStore.findPassengerByName(name).getId()) {
                    bookName.add(b);
                }
            }

        }catch(NullPointerException exeception)
        {
            System.out.println("Error");
        }

        return bookName;
    }
    private List<Booking> findFutureDates()
    {
        ArrayList futureDays = new ArrayList();

        for(Booking b : bookingList)
        {
            if(b.getBookingDateTime().isAfter(LocalDate.now()))
            {
                futureDays.add(b);
            }
        }
        return futureDays;
    }
    public Booking findByBookingId(int bookId)
    {
        for(Booking b : bookingList)
        {
            if(b.getBookingId() == bookId)
            {
                System.out.println("Booking Found");
                return b;

            }
        }
        return null;
    }
    private void findByNameMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Find Booking By Passenger Name");
        System.out.println("Enter Passenger Name");
        String passName = keyboard.nextLine();
        List<Booking> passBook = findBookingByPassengerName(passName);
        BookingDatesComp bookComp = new BookingDatesComp();
        Collections.sort(passBook, bookComp);
        if(passBook.isEmpty())
        {
            System.out.println("No Booking for the passenger: " + passName + "  \n");
        }
        else
        {
            System.out.println("Booking for: " + passName + "  found: \n" );
            callBookArray(passBook);
        }
    }
    private void findBookIdMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Find Booking by Booking Id");
        System.out.println("Enter Booking Id: ");
        int bookId = keyboard.nextInt();
        Booking b = findByBookingId(bookId);
        if (b == null)
            System.out.println("No Bookings  matching the Id \"" + bookId + "\"");
        else
        {
            System.out.println("Booking found");
            System.out.println(b);
        }
    }
    private void findFutureDayMenu()
    {
        System.out.println("Find Future Bookings");
        List<Booking> futureDays = findFutureDates();
        BookingDatesComp bookComp = new BookingDatesComp();
        Collections.sort(futureDays, bookComp);
        if(futureDays.isEmpty())
        {
            System.out.println("Is there currently no future bookings, perhaps you would like to add them");
        }
        else
        {
            callBookArray(futureDays);
        }
    }


    //Delete Menus
    private void deleteBookingMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Delete Booking by ID");
        System.out.println("Enter Booking ID");
        int delId = keyboard.nextInt();
        deleteBooking(delId);
        
    }
    private void deleteBooking(int delId)
    {
        String message = " ";

     /*for(Booking b : bookingList)
     {
         if(b.getBookingId() == delId)
         {
             bookingList.remove(b);
             message = "Successfully removed booking : " + delId ;
         }
         else
         {
             message = "Booking:  " + delId + " Was not removed";
         }
         System.out.println(message);*/
        for(int i = 0; i< bookingList.size(); i++)
        {
            if(bookingList.get(i).getBookingId() == delId)
            {
                bookingList.remove(i);
                message = "Successfully removed booking : " + delId ;
            }
            else
            {
                message = "Booking:  " + delId + " Was not removed";
            }

        }
        System.out.println(message);
    }

    //Filters
    public List<Booking> filterById(IFilter filter)
    {
        List<Booking> filterId = new ArrayList<>();
        for(Booking b: bookingList)
        {
            if(filter.matches(b))
            {
                filterId.add(b);

            }
        }
        return filterId;
    }
    private void filterByBookingIDMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Filter Booking By Passenger Id");
        System.out.println("Enter the Passenger Id you wish to filter by");
        int passId = keyboard.nextInt();

        List<Booking> bookId = filterById(new BookingIDFilter(passId));
        callBookArray(bookId);
    }



    //Edits
    private void editBookingMenu()

    {
        final String MENU_ITEMS = "\n*** Edit PASSENGER MENU ***\n"
                + "1. Edit Booking Passenger ID\n"
                + "2. Edit Booking Vehicle ID\n"
                + "3. Edit Booking Date\n"
                + "4. Edit Booking Start Location\n"
                + "5. Edit Booking End Location\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";
        final int EDIT_PASSENGER_ID = 1;
        final int EDIT_VEHICLE_ID = 2;
        final int EDIT_BOOKING_DATE = 3;
        final int EDIT_START_LOCATION = 4;
        final int EDIT_END_LOCATION = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case EDIT_PASSENGER_ID:
                        editPassengerIdMenu();
                        break;
                    case EDIT_VEHICLE_ID:
                        editVehicleIdMenu();
                        break;
                    case EDIT_BOOKING_DATE:
                        editBookingDateMenu();
                        break;
                    case EDIT_START_LOCATION:
                        editStartLocationMenu();
                        break;
                    case EDIT_END_LOCATION:
                        editEndLocationMenu();
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | NullPointerException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void editPassengerIdMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Passenger Id");
        int passengerId = keyboard.nextInt();

        System.out.println("Enter the ID of the booking you wish to update");
        int id = keyboard.nextInt();

        Booking editBook = editPassengerId(id, passengerId);
        if (editBook == null)
            System.out.println("No Booking  matching the Id \"" + id + "\"");
        else {
            System.out.println("Passenger Id updated to :  \n" + passengerId);

        }
    }
    private void editVehicleIdMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Vehicle Id");
        int vehicleId = keyboard.nextInt();

        System.out.println("Enter the ID of the booking you wish to update");
        int id = keyboard.nextInt();

        Booking editBook = editVehicleId(id, vehicleId);
        if (editBook == null)
            System.out.println("No Booking  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Id updated to :  \n" + vehicleId);

        }
    }
    private void editBookingDateMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Booking Date");

        System.out.println("Enter Year");
        int year = keyboard.nextInt();
        while(year<2019 || year>2025)
        {
            System.out.println("Invalid Year Selected Must be between 2021 & 2025");
            System.out.println("Enter Year");
            year = keyboard.nextInt();
        }
        System.out.println("Enter Month");
        int month = keyboard.nextInt();
        while(month<1 || month >12)
        {
            System.out.println("Invalid Month Selected");
            System.out.println("Enter Month");
            month = keyboard.nextInt();
        }
        System.out.println("Enter Month");
        int day = keyboard.nextInt();
        while(month<1 || month >31)
        {
            System.out.println("Invalid Day Selected");
            System.out.println("Enter Day");
            day = keyboard.nextInt();
        }
        System.out.println("Enter the Booking Id you wish to edit");
        int id = keyboard.nextInt();

        Booking editBook = editBookingTime(id, year, month, day);
        if (editBook == null)
            System.out.println("No Booking  matching the Id \"" + id + "\"");
        else {
            System.out.println("Booking Date updated to :  \n" + year + "-"+ month +"-"+ day);

        }
    }
    private void editStartLocationMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Edit Start Location");

        System.out.println("Enter the New Latitude for the Start POS");
        double lat = keyboard.nextDouble();
        while(lat<-90 || lat>90)
        {
            System.out.println("Invalid Latitude MUST BE -90 TO 90");
            lat = keyboard.nextDouble();
        }
        System.out.println("Enter the New Longitude for the Start POS");
        double lng = keyboard.nextDouble();
        while(lng<-180 || lng>180)
        {
            System.out.println("Invalid Longitude MUST BE -180 TO 180");
            lng = keyboard.nextDouble();
        }

        System.out.println("Enter the ID of the Booking you to change");
        int id = keyboard.nextInt();

        Booking editBook = editStartLocation(id, lat,lng);
        if (editBook == null)
            System.out.println("No Booking  matching the Id \"" + id + "\"");
        else {
            System.out.println("Booking Location updated to :  \n" + lat + "  " + lng);

        }
    }
    private void editEndLocationMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Edit End Location");

        System.out.println("Enter the New Latitude for the End POS");
        double lat = keyboard.nextDouble();
        while(lat<-90 || lat>90)
        {
            System.out.println("Invalid Latitude MUST BE -90 TO 90");
            lat = keyboard.nextDouble();
        }
        System.out.println("Enter the New Longitude for the End POS");
        double lng = keyboard.nextDouble();
        while(lng<-180 || lng>180)
        {
            System.out.println("Invalid Longitude MUST BE -180 TO 180");
            lng = keyboard.nextDouble();
        }

        System.out.println("Enter the ID of the Booking you to change");
        int id = keyboard.nextInt();

        Booking editBook = editEndLocation(id, lat,lng);
        if (editBook == null)
            System.out.println("No Booking  matching the Id \"" + id + "\"");
        else {
            System.out.println("Booking Location updated to :  \n" + lat + "  " + lng);

        }
    }


    private Booking editBookingTime(int id, int year, int month, int day)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == id)
            {
                b.setBookingDateTime(year, month, day);
                return b;
            }
        }

        return null;
    }
    private Booking editPassengerId(int id, int passengerId)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == id)
            {
                b.setPassengerId(passengerId);
                return b;
            }

        }
        return null;
    }
    private Booking editVehicleId(int id, int vehicleId)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == id)
            {
                b.setVehicleId(vehicleId);
                return b;
            }

        }
        return null;
    }
    private Booking editStartLocation(int id, double lat, double lng)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == id)
            {
                b.setStartLocation(lat,lng);
                return b;
            }
        }
        return null;
    }
    private Booking editEndLocation(int id, double lat, double lng)
    {
        for(Booking b: bookingList)
        {
            if(b.getBookingId() == id)
            {
                b.setEndLocation(lat,lng);
                return b;
            }
        }
        return null;
    }


    //Method for finding the distance so for Costs
    private double Haversine(double starLat, double startLong, double endLat, double endLong)
    {
        double dLat = Math.toRadians(endLat - starLat);
        double dLon = Math.toRadians(endLong - startLong);

        // convert to radians
        starLat = Math.toRadians(starLat);
        endLat = Math.toRadians(endLat);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(starLat) *
                        Math.cos(endLat);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    
    }

}
