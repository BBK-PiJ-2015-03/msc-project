package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class - should be only one instance of this.
 * Contains a list of vehicle types, this can be modified via the UI.
 */
public class VehicleTypes {
    private static VehicleTypes instance = null;
    public static List<String> types = new ArrayList<>();

    private VehicleTypes() {
        // Exists only to defeat instantiation.
    }
    public static VehicleTypes getInstance() {
        if(instance == null) {
            instance = new VehicleTypes();
        }
        return instance;
    }

}
