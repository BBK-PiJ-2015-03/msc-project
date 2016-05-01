package Model;

import Model.Interfaces.Account;
import Model.Interfaces.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J2FX on 01/05/2016.
 */
public class AccountImpl implements Account{
    String name;
    String id;
    String address;
    String tel;
    List<Booking> allBookings = new ArrayList<>();

    public AccountImpl(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
        Archive.allAccounts.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public List<Booking> getBookings() {
        return allBookings;
    }

    @Override
    public void deleteBooking(Booking booking) {
        allBookings.remove(booking);
    }

    @Override
    public void newBooking(Booking booking) {
        allBookings.add(booking);
    }
}
