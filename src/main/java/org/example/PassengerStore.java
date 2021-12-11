package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class PassengerStore
{

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName)
    {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers()
    {
        System.out.println("ID\t\tName\t\t\t\t Email\t\t\t\t\t\t  Phone\t\t\t\t\t\t\tHome");
        System.out.println("=================================================================================================================");
        for (Passenger p : this.passengerList)
        {
            System.out.println(p.getId() + "\t\t" + p.getName() + "  \t\t  " + p.getEmail() + "   \t\t " + p.getPhone() + "  \t\t    " + p.getLocation() + "\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }

    public void addPassenger(String name, String email, String phone, double latitude, double longtitude) {
        Passenger P = new Passenger(name, email, phone, latitude, longtitude);

        boolean dup = false;


            for (Passenger p : passengerList)
            {


                if (p.equals(P))
                {
                    dup = true;
                    System.out.println("Passenger Already Exists");
                    break;

                }
            }
            if (!dup) {
                passengerList.add(P);
                System.out.println("Passenger was Successfully Added");
            }
    }
    public void deletePassenger(int id)
    {
        String message = " ";

        for (int i=0; i< passengerList.size(); i++)
        {

            if (passengerList.get(i).getId() == id)
            {
                passengerList.remove(i);
                message = "Passenger  " + id + "  removed Successfully";
            }
            else
            {
            message = "Passenger  " + id + "  was not removed";
            }
        }
        System.out.println(message);



    }

    public Passenger findPassengerByName(String findname)
    {
        //ArrayList<Passenger> pass = new ArrayList();
        for(Passenger p : passengerList)
        {
            if(p.getName().equalsIgnoreCase(findname))
            {

                return p;
            }
        }
        return null;
    }


    public ArrayList<Passenger> findPassengerById(int id)
    {
        ArrayList<Passenger> pass = new ArrayList();
        for(Passenger p: passengerList)
        {
            if(p.getId() ==id)
            {
                System.out.println("ID Found");
                pass.add(p);
            }
        }
        return pass;
    }


    public Passenger editPassengerName(String name, String newName)
    {
        for(Passenger p: passengerList)
        {
            if(p.getName().equalsIgnoreCase(name))
            {

            }
        }
        return null;
    }
    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }
    public void readPassengerToFile(String filename)
    {
        try
        {



                PrintWriter readPassenger = new PrintWriter(filename);
                for (Passenger p : passengerList) {
                    if (p instanceof Passenger) {
                        readPassenger.write(
                                p.getId() + "," +
                                        p.getName() + "," +
                                        p.getEmail() + "," +
                                        p.getPhone() + "," +
                                        p.getLatitudeForPrint()+ ","+
                                        p.getLongitudeForPrint() + "\n");
                    }
                }
                readPassenger.close();

        }catch(IOException e)
        {

        }

    }


    // TODO - see functional spec for details of code to add
    public void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Delete Passenger\n"
                + "5.Edit Passenger\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DELETE_PASSENGER = 4;
        final int EDIT_PASSENGER = 5;
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
                        showAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger Name: ");
                        String Findname = keyboard.nextLine();

                        Passenger pass = findPassengerByName(Findname);
                        if (pass == null)
                            System.out.println("No Passenger  matching the Name \"" + Findname + "\"");
                        else
                        {
                            System.out.println("Passenger found :  \n" + pass);

                        }

                        break;
                    case ADD_PASSENGER:
                        AddPassenger();


                        break;
                    case DELETE_PASSENGER: //Not Working 100% yet!!

                        deletePassenger();
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | FileNotFoundException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void AddPassenger()throws FileNotFoundException {
        Scanner kb = new Scanner(System.in);


        System.out.println("ADD passenger");
        System.out.println("Enter passenger name: ");
        String addName = kb.nextLine();
        System.out.println("Enter passenger Email: ");
        String addEmail = kb.nextLine();
        System.out.println("Enter passenger Phone: ");
        String addPhone = kb.nextLine();
        System.out.println("Enter passenger Latitude: ");
        double addLatitude = kb.nextDouble();
        System.out.println("Enter passenger Longtitude: ");
        double addLongtitude = kb.nextDouble();

        addPassenger(addName, addEmail, addPhone, addLatitude, addLongtitude);

    }

    private void showAllPassengers()
    {
        System.out.println("Display ALL Passengers");
        displayAllPassengers();
    }

    private void deletePassenger()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Delete passenger");
        System.out.println("Enter passenger Id: ");
        int deleteId = keyboard.nextInt();
        deletePassenger(deleteId);
    }
} // end class
