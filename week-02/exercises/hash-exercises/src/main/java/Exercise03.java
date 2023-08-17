import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;
import java.util.Map;

public class Exercise03
{
    public static void main(String[] args)
    {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 2. Print `vehicleMap` using your "print all" method.
        printVehicles(vehicleMap);
    }

    // 1. Create a method to print all Vehicles in a HashMap<String, Vehicle>.
    // Consider making it `public` so you can use it in other exercises.
    public static void printVehicles(HashMap<String, Vehicle> vehicleMap)
    {
        // Reminder that this is one way to iterate through a hash map!
        for (Map.Entry<String, Vehicle> stringVehicleEntry : vehicleMap.entrySet())
        {
            Vehicle value = stringVehicleEntry.getValue();
            System.out.println(value);
        }
    }
}

