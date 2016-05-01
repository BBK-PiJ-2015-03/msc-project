package Model;

import Model.Interfaces.Account;
import Model.Interfaces.Booking;
import Model.Interfaces.Driver;

/**
 * Implementation of Booking interface, will contain all relevant booking details of a job.
 */
public class BookingImpl implements Booking {
    private static int bookingNumberCounter = 1001;
    private final int bookingNumber;
    private Account account;
    private String vehicleType;
    private int noPassengers;
    private String date;
    private String time;
    private String pickUpAddress;
    private String dropOffAddress;
    private String clientName;
    private String clientTel;
    private String clientEmail;
    private String comments;
    private double price;
    private boolean completed = false;
    private Driver driver;

    public BookingImpl(){
        this.bookingNumber = bookingNumberCounter;
        bookingNumberCounter++;
        Archive.allBookings.add(this);
    }


    @Override
    public void deleteBooking() {
        account.deleteBooking(this);
        Archive.allBookings.remove(this);
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getNoPassengers() {
        return noPassengers;
    }

    public void setNoPassengers(int noPassengers) {
        this.noPassengers = noPassengers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(String dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientTel() {
        return clientTel;
    }

    public void setClientTel(String clientTel) {
        this.clientTel = clientTel;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
