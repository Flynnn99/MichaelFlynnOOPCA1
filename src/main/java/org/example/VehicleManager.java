package org.example;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName)
    {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    //Read and Write files
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

    //Display Vehicles
    public void displayAllVehicles()
    {

        for (Vehicle v : vehicleList)
        {
            if(v instanceof Van )
            {
                System.out.println(v.getId() + "\t\t " + v.getType() + "\t " + v.getMake() + "\t\t " + v.getModel() + "  \t\t\t\t"
                        + v.getMilesPerKm() + " \t\t " + v.getRegistration() + "  \t\t " + v.getCostPerMile() + "  \t\t " + v.getLastServicedDate() + "\t\t"
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
    public void displayVehicleMenu()
    {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Reg\n"
                + "3. Filter Vehicle by Type\n"
                + "4. Filter By Num of Seats\n"
                + "5. Add New Vehicle\n"
                + "6. Delete Vehicle\n"
                + "7. Edit Vehicle\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int FILTER_BY_NUM_OF_SEATS = 4;
        final int ADD_VEHICLE = 5;
        final int DELETE_VEHICLE = 6;
        final int EDIT_VEHICLE = 7;
        final int EXIT = 8;

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
                    case FILTER_BY_NUM_OF_SEATS:
                        filterVehicleByNumOfSeats();
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
    public void callArrayList(List<Vehicle> vehicleList)
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

    //Find Vehicles By
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
    private void findByVehicleType()
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Search Filter By Type");
        System.out.println("Enter type CAR VAN TRUCK 4X4");
        String type = keyboard.nextLine();

        List<Vehicle> types = findByType(new VehicleTypesFilter(type));
        VehicleRegComp regComp = new VehicleRegComp();
        Collections.sort(types, regComp);
        callArrayList(types);
    }
    private void filterVehicleByNumOfSeats()
    {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Search Filter By Num of Seats");
        System.out.println("Enter Num of Seats");
        int numOfSeats = keyboard.nextInt();

        List<Vehicle> types = filterByNumOfSeats(new VehicleNumOfSeatsFilter(numOfSeats));
        VehicleRegComp regComp = new VehicleRegComp();
        Collections.sort(types, regComp);
        if(types.isEmpty())
        {
            System.out.println("The number you have added is not in the list, " + numOfSeats );
        }
        else
        {
            System.out.println("Found\n");
            callArrayList(types);
        }
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

    //Filters
    public List<Vehicle> findByType(IFilter filter)
    {
        List<Vehicle> typeOfVehicle = new ArrayList<>();
        for (Vehicle v : vehicleList)
        {
            if (filter.matches(v))
            {
                typeOfVehicle.add(v);
            }
        }
        return typeOfVehicle;
    }
    public List<Vehicle> filterByNumOfSeats(IFilter filter)
    {
        List<Vehicle> numOfSeats = new ArrayList<>();
        for (Vehicle v : vehicleList)
        {
            if (filter.matches(v))
            {
                numOfSeats.add(v);
            }
        }
        return numOfSeats;
    }




    //Delete Vehicle
    private void deleteVehicleMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Delete Menu");
        System.out.println("Enter ID of booking you wish to delete");

        int delId = keyboard.nextInt();
        deleteVehicle(delId);

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

    //Add Vehicle
    public void addNewVehicle(String type, String make, String model, double milesPerKwH,
                              String registration, double costPerMile,
                              int year, int month, int day,
                              int mileage, double latitude, double longitude, int loadSpace)
    {

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

    //Edit Vehicles
    private void editVehicleMenu()
    {
        final String MENU_ITEMS = "\n*** Edit Vehicle MENU ***\n"
                + "1. Edit Vehicle Make\n"
                + "2. Edit Vehicle Model\n"
                + "3. Edit Vehicle MilesPerKwH\n"
                + "4. Edit Vehicle Registration\n"
                + "5. Edit Vehicle costPerMile\n"
                + "6. Edit Vehicle ServiceDate\n"
                + "7. Edit Vehicle Mileage\n"
                + "8. Edit Vehicle Depot Location\n"
                + "9. Exit\n"
                + "Enter Option [1,9]";
        final int EDIT_MAKE = 1;
        final int EDIT_MODEL = 2;
        final int EDIT_MILES_PER_KWH = 3;
        final int EDIT_REGISTRATION = 4;
        final int EDIT_COST_PER_MILE = 5;
        final int EDIT_SERVICE_DATE = 6;
        final int EDIT_MILEAGE = 7;
        final int EDIT_START_LOCATION = 8;
        final int EXIT = 9;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case EDIT_MAKE:
                        displayEditMake();
                        break;
                    case EDIT_MODEL:
                        displayEditModel();
                        break;
                    case EDIT_MILES_PER_KWH:
                        displayEditMilesPerKwH();
                        break;

                    case EDIT_REGISTRATION:
                        displayEditRegistration();
                        break;
                    case EDIT_COST_PER_MILE:
                        displayEditCostPerMile();
                        break;
                    case EDIT_SERVICE_DATE:
                        displayEditServiceDate();
                        break;
                    case EDIT_MILEAGE:
                        displayEditMileage();
                        break;
                    case EDIT_START_LOCATION:
                        displayEditDepotLocation();
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


    private void displayEditMake()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Make");

        System.out.println("Enter the Edited Make of the Vehicle");
        String make = keyboard.nextLine();
        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();

       Vehicle editVehicle = editVehicleMake(id, make);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Make updated to :  \n" + make);

        }


    }
    private void displayEditModel()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Model");

        System.out.println("Enter the Edited Model of the Vehicle");
        String model = keyboard.nextLine();
        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();

        Vehicle editVehicle = editVehicleModel(id, model);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Model updated to :  \n" + model);

        }


    }
    private void displayEditMilesPerKwH()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Miles Per KwH");

        System.out.println("Enter the Edited Miles Per KwH of the Vehicle");
        int milesPerKwH = keyboard.nextInt();

        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();


        Vehicle editVehicle = editVehicleMilesPerKwh(id, milesPerKwH);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Make updated to :  \n" + milesPerKwH);

        }


    }

    private void displayEditRegistration()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Reg");


        System.out.println("Enter the Edited reg of the Vehicle");
        String reg = keyboard.nextLine();

        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();

        Vehicle editVehicle = editVehicleRegistration(id, reg);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Registration updated to :  \n" + reg);

        }


    }
    private void displayEditCostPerMile()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Cost Per Mile");
        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();
        keyboard.nextInt();
        System.out.println("Enter the Edited Cost Per Mile of the Vehicle");
        double costPerMile = keyboard.nextDouble();

        Vehicle editVehicle = editVehicleCostPerMiles(id, costPerMile);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle CostPerMile updated to :  \n" + costPerMile);

        }


    }
    private void displayEditServiceDate()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Service Date");

        System.out.println("Enter the Edited Service of the Vehicle");
        System.out.println("Enter Year");
        int year = keyboard.nextInt();
        while(year<2019 || year>2021)
        {
            System.out.println("Invalid Year Selected Must be between 2019 & current date");
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
        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();

        Vehicle editVehicle = editVehicleServiceDate(id, year, month, day);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Service Date updated to :  \n" + year + "-"+ month +"-"+ day);

        }


    }

    private void displayEditMileage()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Edit Mileage");


        System.out.println("Enter the Edited Mileage of the Vehicle");
        int mileage = keyboard.nextInt();

        System.out.println("Enter the Vehicle Id you wish to edit");
        int id = keyboard.nextInt();

        Vehicle editVehicle = editVehicleMileage(id, mileage);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Make updated to :  \n" + mileage);

        }

    }
    private void displayEditDepotLocation()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Edit Vehicle Location");

        System.out.println("Enter the New Latitude for the DEPOT");
        double lat = keyboard.nextDouble();
        while(lat<-90 || lat>90)
        {
            System.out.println("Invalid Latitude MUST BE -90 TO 90");
            lat = keyboard.nextDouble();
        }
        System.out.println("Enter the New  Longitude for the DEPOT");
        double lng = keyboard.nextDouble();
        while(lng<-180 || lng>180)
        {
            System.out.println("Invalid Longitude MUST BE -180 TO 180");
            lng = keyboard.nextDouble();
        }

        System.out.println("Enter the ID of the Vehicle you to change");
        int id = keyboard.nextInt();


        Vehicle editVehicle = editVehicleDepotLocation(id, lat,lng);
        if (editVehicle == null)
            System.out.println("No Vehicle  matching the Id \"" + id + "\"");
        else {
            System.out.println("Vehicle Location updated to :  \n" + lat + "  " + lng);

        }


    }


    private Vehicle editVehicleMake(int id, String make)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setMake(make);
                return v;
            }
        }

        return null;
    }
    private Vehicle editVehicleModel(int id, String model)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setModel(model);
                return v;
            }
        }

        return null;
    }
    private Vehicle editVehicleMilesPerKwh(int id, int milesPerKwH)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setMilesPerKm(milesPerKwH);
                return v;
            }
        }

        return null;
    }

    private Vehicle editVehicleRegistration(int id, String reg)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setRegistration(reg);
                return v;
            }
        }

        return null;
    }
    private Vehicle editVehicleDepotLocation(int id, double lat, double lng)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setLocation(lat,lng);
                return v;
            }
        }
        return null;
    }
    private Vehicle editVehicleMileage(int id, int mileage)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setMileage(mileage);
                return v;
            }
        }

        return null;
    }
    private Vehicle editVehicleCostPerMiles(int id, double costPerMiles)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setCostPerMile(costPerMiles);
                return v;
            }
        }

        return null;
    }
    private Vehicle editVehicleServiceDate(int id, int year, int month, int day)
    {
        for(Vehicle v: vehicleList)
        {
            if(v.getId() == id)
            {
                v.setLastServicedDate(year, month, day);
                return v;
            }
        }

        return null;
    }




}
