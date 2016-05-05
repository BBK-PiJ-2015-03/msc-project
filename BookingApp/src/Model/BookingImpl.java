package Model;

import Controllers.ObservableLists;
import Model.Interfaces.Account;
import Model.Interfaces.Booking;
import Model.Interfaces.Driver;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Implementation of Booking interface, will contain all relevant booking details of a job.
 */
public class BookingImpl implements Booking {
    public static ObjectProperty<String> bookingNumberCounter = new SimpleObjectProperty<>("1001");
    private int bookingNumber;
    private Account account;
    private String vehicleType;
    private String noPassengers;
    private LocalDate date;
    private ObjectProperty<LocalTime> time = new SimpleObjectProperty<>();
    private StringProperty pickUpAddress = new SimpleStringProperty();
    private StringProperty dropOffAddress = new SimpleStringProperty();
    private StringProperty clientName = new SimpleStringProperty();
    private StringProperty formattedPrice = new SimpleStringProperty();
    private String clientTel;
    private String clientEmail;
    private StringProperty comments = new SimpleStringProperty();
    private ObjectProperty<Double> price = new SimpleObjectProperty<>();
    private boolean completed = false;
    private Driver driver;

    public BookingImpl(Account account){
        this.bookingNumber = Integer.parseInt(bookingNumberCounter.getValue());
        bookingNumberCounter.setValue((this.bookingNumber+1)+"");
        Archive.allBookings.add(this);
        Archive.incompleteBookings.add(this);
        ObservableLists.bookingsList.add(this);
        account.newBooking(this);
    }


    @Override
    public void deleteBooking() {
        account.deleteBooking(this);
        Archive.allBookings.remove(this);
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber){ this.bookingNumber = bookingNumber; }

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

    public String getNoPassengers() {
        return noPassengers;
    }

    public void setNoPassengers(String noPassengers) {
        this.noPassengers = noPassengers;
    }

    public String getDate() {
//        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.valueOf(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ObjectProperty<LocalTime> getTimeProperty() {
        return time;
    }

    public LocalTime getTime() {
        return this.getTimeProperty().get();
    }

    public void setTime(LocalTime time) {this.time.set(time);}

    public String getPickUpAddress() {
        return pickUpAddress.get();
    }

    public StringProperty getPickUpAddressProperty() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress.set(pickUpAddress);
    }

    public String getDropOffAddress() {
        return dropOffAddress.get();
    }

    public StringProperty getDropOffAddressProperty() {
        return dropOffAddress;
    }

    public void setDropOffAddress(String dropOffAddress) {
        this.dropOffAddress.set(dropOffAddress);
    }

    public String getClientName() {
        return clientName.get();
    }

    public StringProperty getClientNameProperty() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
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
        return comments.get();
    }

    public StringProperty getCommentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public double getPrice() {
        return price.get();
    }

    public StringProperty getPriceProperty() {
        return formattedPrice;
    }

    public void setPrice(Double price) {
        this.price.set(price);
        DecimalFormat df = new DecimalFormat("#.00");
        this.formattedPrice.setValue("£"+df.format(this.price.get()));

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
