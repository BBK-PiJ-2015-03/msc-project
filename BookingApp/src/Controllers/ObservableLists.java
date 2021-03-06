package Controllers;

import Model.*;
import Model.Interfaces.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to hold all observable lists displayed in the GUI
 */
public class ObservableLists {
    public static ObservableList<Account> accountList = FXCollections.observableArrayList();
    public static ObservableList<Account> accountListNoCash = FXCollections.observableArrayList();
    public static ObservableList<DriverImpl> driverList = FXCollections.observableArrayList();
    public static ObservableList<String> vehicleTypeList = FXCollections.observableArrayList();
    public static ObservableList<BookingImpl> bookingsList = FXCollections.observableArrayList();

    /**
     * Populates the observable lists.
     */
    public static void initialise(){
        accountList.add(Cash.getInstance());
        accountList.addAll(Archive.allAccounts);
        driverList.addAll(Archive.allDrivers);
        vehicleTypeList.addAll(VehicleTypes.types);
        bookingsList.clear();
        bookingsList.addAll(Archive.incompleteBookings);
    }

    public static void refreshAccountList(){
        accountList.clear();
        accountListNoCash.clear();
        accountList.add(Cash.getInstance());
        accountList.addAll(Archive.allAccounts);
        accountListNoCash.addAll(Archive.allAccounts);
    }

    public static void refreshDriverList(){  //Added Now
        driverList.clear();
        driverList.addAll(Archive.allDrivers);
    }
}
