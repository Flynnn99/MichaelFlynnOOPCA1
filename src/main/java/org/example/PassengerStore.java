package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    public void addPassenger(String name, String email, String phone, double latitude, double longtitude) {
        Passenger P = new Passenger(name, email, phone, latitude, longtitude);

        boolean dup = false;

        for (Passenger p : passengerList)
        {


            if (p.equals(P)) {
                dup = true;
                break;
            }
        }
        if (!dup ) {
            passengerList.add(P);
        }

    }
    public void deletePassenger(String name)
    {

        for (Passenger p : passengerList)
        {

            if (p.getName().equalsIgnoreCase(name))
            {
                passengerList.remove(name);
            }

        }


    }

    public void editPassengerName(String name, String email, String phone, double latitude, double longtitude) {
        String newName = " ";
        Passenger P = new Passenger(name, email, phone, latitude, longtitude);

        boolean dup = false;

        for (Passenger p : passengerList)
        {


            if (p.equals(P)) {
                dup = true;
                break;
            }
        }
        if (dup )
        {
            for(int i = 0; i< passengerList.size(); i++)
            {
                if(passengerList.get(i).getName().equalsIgnoreCase(name))
                {
                    passengerList.get(i).setName(newName);
                }
            }
        }

    }

    public Passenger findPassengerByName(String name)
    {
        for(Passenger p : passengerList)
        {
            if(p.getName().equalsIgnoreCase(name))
            {
                System.out.println("Passenger Found");
                return p;
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

    // TODO - see functional spec for details of code to add

} // end class
