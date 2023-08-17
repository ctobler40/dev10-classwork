import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;
import java.util.Map;

public class Exercise06
{
    public static void main(String[] args)
    {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Loop over each vehicle in `vehicleMap` and print vehicles with a Dodge make.
        for (Map.Entry<String, Vehicle> stringVehicleEntry : vehicleMap.entrySet())
        {
            Vehicle vehicle = stringVehicleEntry.getValue();
            if (vehicle.getMake().equals("Dodge"))
                System.out.println(vehicle);
        }

        // 2. Loop three times with three different techniques: .values(), .entrySet(), and .keySet().
        // TODO: Implement other two ways of doing this!
    }
}
