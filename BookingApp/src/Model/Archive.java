package Model;

import Model.Interfaces.Account;
import Model.Interfaces.Driver;
import Model.Interfaces.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J2FX on 01/05/2016.
 */
public final class Archive {
    private static Archive instance = new Archive();
    public static List<Account> allAccounts = new ArrayList<>();
    public static List<BookingImpl> allBookings = new ArrayList<>();
    public static List<BookingImpl> incompleteBookings = new ArrayList<>();
    public static List<Driver> allDrivers = new ArrayList<>();
    public static List<Vehicle> allVehicles = new ArrayList<>();

    public static Archive getInstance() {
        return instance;
    }

    public Archive() {
    }
}
