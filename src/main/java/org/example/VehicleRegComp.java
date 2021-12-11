
package org.example;
import java.util.Comparator;

public class VehicleRegComp implements Comparator<Vehicle>

{
        public int compare(Vehicle vh1, Vehicle vh2)
        {
            return vh1.getRegistration().compareTo(vh2.getRegistration());
        }
}

