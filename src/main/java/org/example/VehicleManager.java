package org.example;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName)
    {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles()
    {

        for (Vehicle v : vehicleList)
        {
            if(v instanceof Van )
            {
                System.out.println(v.getId() + "\t\t " + v.getType() + "\t " + v.getMake() + "\t\t " + v.getModel() + "  \t\t "
                        + v.getMilesPerKm() + "  \t\t " + v.getRegistration() + "  \t\t " + v.getCostPerMile() + "  \t\t " + v.getLastServicedDate() + "\t\t"
                        + v.getMileage() + "  \t\t" + v.getDepotGPSLocation() + "  \t\t" + ((Van) v).getLoadSpace() + "\n");
                System.out.println("-------------------------------------------------------------------------" +
                        "--------------------------------------------------------------------------------------------");
            }
            else if (v instanceof Car)
            {
                System.out.println(v.getId() + "\t\t " + v.getType() + "\t " + v.getMake() + "\t\t " + v.getModel() + "  \t\t "
                        + v.getMilesPerKm() + "  \t\t " + v.getRegistration() + "  \t\t " + v.getCostPerMile() + "  \t\t " + v.getLastServicedDate() + "\t\t"
                        + v.getMileage() + "  \t\t" + v.getDepotGPSLocation() + "  \t\t" + ((Car) v).getNumOfSeats() + "\n");
                System.out.println("-------------------------------------------------------------------------" +
                        "--------------------------------------------------------------------------------------------");

            }
            }
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                double loadSpace = sc.nextInt();// would read a car as num of seats

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if (type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4"))
                {
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            (int) loadSpace));
                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }
    public void readVehicleToFile(String filename)
    {
        try {


            PrintWriter readVehicle = new PrintWriter(filename);
            for (Vehicle v : vehicleList) {
                if (v instanceof Van) {
                    readVehicle.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + "," +
                                    v.getMilesPerKm() + "," +
                                    v.getRegistration() + "," +
                                    v.getCostPerMile() + "," +
                                    v.getYearForPrint() + "," +
                                    v.getMonthForPrint() + "," +
                                    v.getDayForPrint() + ","+
                                    v.getMileage() + "," +
                                    v.getLatitudeForPrint() + ","+
                                    v.getLongitudeForPrint()+","
                                    +((Van) v).getLoadSpace() + "\n");
                }
                else if (v instanceof Car)
                {
                    readVehicle.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + "," +
                                    v.getMilesPerKm() + "," +
                                    v.getRegistration() + "," +
                                    v.getCostPerMile() + "," +
                                    v.getYearForPrint() + "," +
                                    v.getMonthForPrint() + "," +
                                    v.getDayForPrint() + ","+
                                    v.getMileage() + "," +
                                    v.getLatitudeForPrint() + ","+
                                    v.getLongitudeForPrint()+","
                                    +((Car) v).getNumOfSeats() + "\n");
                }
            }
            readVehicle.close();

        } catch (IOException e) {

        }
    }

    //TODO add more functionality as per spec.
    public Vehicle findByReg(String reg)
    {
          for(Vehicle v : vehicleList)
          {
              if(v.getRegistration().equalsIgnoreCase(reg))
              {
                  System.out.println("Vehicle Found");
                  return v;
              }
          }

        return null;
    }
    public Vehicle findVehicleById(int id)
    {
        for(Vehicle v : vehicleList)
        {
            if(v.getId() == id)
            {
                System.out.println("Vehicle Found");
                return v;
            }
        }

        return null;
    }

    /*public Vehicle findByMake(String make)
    {
        for (Vehicle v : vehicleList) {
            if (v.getMake().equalsIgnoreCase(make)) {
                System.out.println("Vehicle Found");
                return v;
            }
        }
        return null;
    }
    public Vehicle findByModel(String model)
    {
        for (Vehicle v : vehicleList) {
            if (v.getMake().equalsIgnoreCase(model)) {
                System.out.println("Vehicle Found");
                return v;
            }
        }
        return null;
    }*/


    public void addNewVehicle(String type, String make, String model, double milesPerKwH,
                           String registration, double costPerMile,
                           int year, int month, int day,
                           int mileage, double latitude, double longitude, int loadSpace) {

            if (type.equalsIgnoreCase("van") || type.equalsIgnoreCase("truck"))
            {
                vehicleList.add(new Van(type, make, model, milesPerKwH, registration, costPerMile, year, month, day,
                        mileage, latitude, longitude, loadSpace));

            }
            else if (type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4"))
            {
                vehicleList.add(new Car(type, make, model, milesPerKwH, registration, costPerMile, year, month, day,
                        mileage, latitude, longitude, loadSpace));
            }
    }

    public void deleteVehicle(int vehicleId)
    {
        String message = "";
        for(int i =0; i < vehicleList.size(); i++)
        {
            if(vehicleList.get(i).getId() == vehicleId)
            {
                vehicleList.remove(i);
                i--;

                message = "Successfully removed Vehicle: " + vehicleId;

            }
            else
            {
                message = "Unable to remove Vehicle: " + vehicleId;
            }

        }
        System.out.println(message);
    }
    public void displayVehicleMenu()
    {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Reg\n"
                + "3.Find Vehicle by Type\n"
                + "4.Add New Vehicle\n"
                + "5.Delete Vehicle\n"
                + "6.Edit Vehicle\n"
                + "7. Exit\n"
                + "Enter Option [1,7]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int ADD_VEHICLE = 4;
        final int DELETE_VEHICLE = 5;
        final int EDIT_VEHICLE = 6;
        final int EXIT = 7;

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
                        displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        findVehicleReg();
                        break;
                    case FIND_BY_TYPE:
                        findByVehicleType();
                        break;
                    case ADD_VEHICLE:
                        addVehicle();
                        break;
                    case DELETE_VEHICLE:
                        deleteVehicleMenu();
                        break;
                    case EDIT_VEHICLE:
                        editVehicleMenu();
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

    private void findByVehicleType()
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Search Find By Type");
        System.out.println("Enter type CAR VAN TRUCK 4X4");
        String type = keyboard.nextLine();

        ArrayList<Vehicle> types = findByType(type);
        VehicleRegComp regComp = new VehicleRegComp();
        Collections.sort(types, regComp);
        callArrayList(types);
    }
    private void findVehicleReg()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Find Vehicle by REG");
        System.out.println("Enter REG: ");
        String reg = keyboard.nextLine();
        Vehicle v = findByReg(reg);
        if (v == null)
            System.out.println("No Reg  matching the name \"" + reg + "\"");
        else
            System.out.println("Found Booking: \n" + v);
    }
    private ArrayList<Vehicle> findByType(String type)
    {
        ArrayList<Vehicle> typeOfVehicle = new ArrayList<>();
        for (Vehicle v : vehicleList)
        {
            if (v.getType().equalsIgnoreCase(type))
            {
                typeOfVehicle.add(v);
            }
        }
        return typeOfVehicle;
    }
    public void addVehicle()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Add New Vehicle");
        System.out.println("======Type======");
        System.out.println("Enter type of vehicle");
        String addtype = keyboard.nextLine();

        System.out.println("Enter make of vehicle");
        String make = keyboard.nextLine();
        System.out.println("Enter model of vehicle");
        String model = keyboard.nextLine();
        System.out.println("Enter reg of vehicle");
        String addReg = keyboard.nextLine();

        System.out.println("\n======Vehicle Details======");
        System.out.println("Enter milesPerKwH of vehicle");
        double milesPerKwH = keyboard.nextDouble();
        while(milesPerKwH <1 & milesPerKwH >4)
        {
            System.out.println("Invalid milesPerKwh");
            System.out.println("Enter milesPerKwH of vehicle");
            milesPerKwH = keyboard.nextDouble();
        }
        System.out.println("Enter cost per mile of vehicle");
        double costPerMile = keyboard.nextDouble();
        System.out.println("enter mileage of the booking");
        int mileage = keyboard.nextInt();
        while(mileage < 100 || mileage > 150000)
        {
            System.out.println("Invalid Mileage Selected");
            System.out.println("Enter mileage of the booking");
            System.out.println("Must be Greater then 100 & less then 150000");
            mileage = keyboard.nextInt();
        }

        System.out.println("\n======Vehicle Service Info======");
        System.out.println("Enter Year of Last of Service");
        int year = keyboard.nextInt();
        while(year <2019 || year >2021)
        {
            System.out.println("Cars Must be Serviced between 2019 and current Year" + "\n Enter Year of Last Service");
            year = keyboard.nextInt();
        }
        System.out.println("Enter Month of Last Service");
        int month = keyboard.nextInt();
        while(month<1 || month > 12 )
        {
            System.out.println("You have entered an invalid month " + "\nEnter Month of Last Service");
            month = keyboard.nextInt();
        }
        System.out.println("enter Day of last Service");
        int day = keyboard.nextInt();
        while(day<1 || day>31)
        {
            System.out.println("Invalid Date");
            System.out.println("enter Day of last Service");
            day = keyboard.nextInt();
        }

        System.out.println("\n======Depot======");
        System.out.println("Enter Latitude");
        double latitude = keyboard.nextDouble();
        while(latitude <-90 || latitude > 90)
        {
            System.out.println("Invalid Latitude Coordinates must be between -90 & 90");
            System.out.println();
            System.out.println("Enter Latitude");
            latitude = keyboard.nextDouble();
        }
        System.out.println("Enter Longitude");
        double longitude = keyboard.nextDouble();
        while(longitude <-180 || longitude > 180)
        {
            System.out.println("Invalid Longitude Coordinates must be between -180 & 180");
            System.out.println();
            System.out.println("Enter Longitude");
            longitude = keyboard.nextDouble();
        }

        System.out.println("\n======Additional Info======");
        System.out.println("enter loadSpace or Num of Seats ");
        int additional = keyboard.nextInt();
        while(additional <1)
        {
            System.out.println("Invalid LoadSpace of Number of Seats");
            System.out.println("enter loadSpace or Num of Seats ");
            additional = keyboard.nextInt();
        }

        addNewVehicle(addtype, make, model, milesPerKwH, addReg, costPerMile, year, month, day, mileage, latitude, longitude, additional);

    }
    public void callArrayList(ArrayList<Vehicle> vehicleList)
    {

        for (Vehicle vehicle:vehicleList)
        {
            if(vehicle instanceof Van) {
                System.out.println(vehicle.getId()
                        + "\t\t" + vehicle.getType()
                        + "\t" + vehicle.getMake()
                        + "\t\t" + vehicle.getModel()
                        + "\t" + vehicle.getMilesPerKm()
                        + "\t\t" + vehicle.getRegistration()
                        + "\t" + vehicle.getCostPerMile()
                        + "\t\t" + vehicle.getLastServicedDate()
                        + "\t" + vehicle.getMileage()
                        + "\t\t" + vehicle.getDepotGPSLocation()
                        + "\t\t" + ((Van) vehicle).getLoadSpace());
            }
            else if(vehicle instanceof Car)
            {
                System.out.println(vehicle.getId()
                        + "\t\t" + vehicle.getType()
                        + "\t" + vehicle.getMake()
                        + "\t\t" + vehicle.getModel()
                        + "\t" + vehicle.getMilesPerKm()
                        + "\t\t" + vehicle.getRegistration()
                        + "\t" + vehicle.getCostPerMile()
                        + "\t\t" + vehicle.getLastServicedDate()
                        + "\t" + vehicle.getMileage()
                        + "\t\t" + vehicle.getDepotGPSLocation()
                        + "\t\t" + ((Car) vehicle).getNumOfSeats());
            }

        }

    }
    private void deleteVehicleMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Delete Menu");
        System.out.println("Enter ID of booking you wish to delete");

        int delId = keyboard.nextInt();
        deleteVehicle(delId);

    }
    private void editVehicleMenu()
    {
        System.out.println("Still Working on it");
    }



}
