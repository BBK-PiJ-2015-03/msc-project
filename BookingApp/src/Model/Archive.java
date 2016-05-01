package Model;

import Model.Interfaces.Account;
import Model.Interfaces.Booking;
import Model.Interfaces.Driver;
import Model.Interfaces.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J2FX on 01/05/2016.
 */
public final class Archive {
    private static Archive instance = new Archive();
    static List<Account> allAccounts = new ArrayList<>();
    static List<Booking> allBookings = new ArrayList<>();
    static List<Driver> allDrivers = new ArrayList<>();
    static List<Vehicle> allVehicles = new ArrayList<>();

    public static Archive getInstance() {
        return instance;
    }

    public Archive() {
    }
}
