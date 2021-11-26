package org.example;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuStarter
{
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) {
        MenuStarter app = new MenuStarter();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }


        //   vehicleManager.displayAllVehicles();


        //   String registration = "172LH234106";
        //   Vehicle vehicle = vehicleManager.findVehicleByReg(registration);
        //   if (vehicle == null)
        //       System.out.println("No vehicle with registration " + registration + " was found.");
        //   else
        //       System.out.println("Found Vehicle: " + vehicle.toString());

        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVechmenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
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

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    private void displayVechmenu()
    {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Reg\n"
                + "3.Find Vehicle by Type\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by REG");
                        System.out.println("Enter REG: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findByReg(reg);
                        if (v == null)
                            System.out.println("No Reg  matching the name \"" + reg + "\"");
                        else
                            System.out.println("Found Booking: \n" + v.toString());
                        break;
                    case FIND_BY_TYPE:
                        System.out.println("Enter how you want to search for a vehicle");
                        String search = keyboard.nextLine();
                        Vehicle v1;
                        if(search.equalsIgnoreCase("type"))
                        {
                            System.out.println("Enter type");
                            String type = keyboard.nextLine();
                            v1 = vehicleManager.findByType(type);
                            //Collections.sort();
                        }
                        else if(search.equalsIgnoreCase("make"))
                        {
                            System.out.println("Enter Make");
                            String make = keyboard.nextLine();
                           v1 = vehicleManager.findByMake(make);
                        }
                        else
                        {
                            System.out.println("enter model");
                            String model = keyboard.nextLine();
                          v1 = vehicleManager.findByModel(model);
                        }
                        if (v1 == null)
                            System.out.println("No   matching the name");
                        else
                            System.out.println("Found: \n" + v1.toString());
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

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Delete Passenger\n"
                + "5.Modify Passenger Details\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DELETE_PASSENGER = 4;
        final int MOD_PASSENGER = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if (p == null)
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        else
                            System.out.println("Found passenger: \n" + p.toString());
                        break;
                    case ADD_PASSENGER:
                        System.out.println("ADD passenger");
                        System.out.println("Enter passenger name: ");
                        String addName = keyboard.nextLine();
                        System.out.println("Enter passenger Email: ");
                        String addEmail = keyboard.nextLine();
                        System.out.println("Enter passenger Phone: ");
                        String addPhone = keyboard.nextLine();
                        System.out.println("Enter passenger Latitude: ");
                        double addLatitude = keyboard.nextDouble();
                        System.out.println("Enter passenger Longtitude: ");
                        double addLongtitude = keyboard.nextDouble();

                        passengerStore.addPassenger(addName, addEmail, addPhone, addLatitude, addLongtitude);
                        break;
                    case DELETE_PASSENGER: //Not Working 100% yet!!
                        System.out.println("Delete passenger");
                        System.out.println("Enter passenger name: ");
                        String deleteName = keyboard.nextLine();
                        passengerStore.deletePassenger(deleteName);
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
    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** Booking MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Find Booking by Name\n"
                + "3. Exit\n"
                + "Enter Option [1,3]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int EXIT = 3;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Booking");
                        bookingManager.displayAllBookings();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Booking by Passenger Name");
                        System.out.println("Enter Passenger name: ");
                        String name = keyboard.nextLine();
                        Booking b = bookingManager.findByBookingName(name);
                        if (b == null)
                            System.out.println("No Bookings  matching the name \"" + name + "\"");
                        else
                            System.out.println("Found Booking: \n" + b.toString());
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
}

