import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise05
{
    public static void main(String[] args)
    {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Instantiate a new HashMap<String, Vehicle>.
        HashMap<String, Vehicle> vehicleMap2 = new HashMap<>();

        // 2. Add two vehicles to the new map.
        // Normally, we would declare these as well, or just make a constructor
        vehicleMap2.put("I8BY3IHKACFHB29", new Vehicle());
        vehicleMap2.put("SDRC37GAO9CFNAE", new Vehicle());

        // 3. Add items from the new map to `vehicleMap` using the `putAll` method.
        vehicleMap.putAll(vehicleMap2);

        // 4. Confirm the vehicles were added by retrieving on with its VIN and printing it to the console.
        System.out.println(vehicleMap.get("I8BY3IHKACFHB29"));
        System.out.println(vehicleMap.get("SDRC37GAO9CFNAE"));
    }
}
