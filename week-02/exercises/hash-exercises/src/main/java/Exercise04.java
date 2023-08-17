import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise04
{
    public static void main(String[] args)
    {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Create a new Vehicle. Use a VIN that's easy to remember.
        Vehicle myCar = new Vehicle();
        myCar.setVin("65ALLDAY");
        myCar.setColor("Blue");
        myCar.setMake("Nissan");
        myCar.setModel("Pathfinder");
        myCar.setYear(2019);

        // 2. Add the Vehicle to `vehicleMap` with the `put` method.
        vehicleMap.put(myCar.getVin(), myCar);

        // 3. Confirm the Vehicle was added by retrieving it with `get` and printing it to the console.
        System.out.println(vehicleMap.get("65ALLDAY"));
    }
}
