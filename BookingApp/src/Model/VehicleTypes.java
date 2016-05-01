package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class - should be only one instance of this.
 * Contains a list of vehicle types, this can be modified via the UI.
 */
public class VehicleTypes {
    private static VehicleTypes instance = null;
    private List<String> types = new ArrayList<>();

    private VehicleTypes() {
        // Exists only to defeat instantiation.
    }
    public static VehicleTypes getInstance() {
        if(instance == null) {
            instance = new VehicleTypes();
        }
        return instance;
    }

    public void removeType(String type){
        types.remove(type);
    }

    public void addType(String type){
        types.add(type);
    }
}
