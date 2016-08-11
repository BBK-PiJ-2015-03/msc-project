package Controllers;

import Model.*;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by J2FX on 05/05/2016.
 */
public class LoadOnlineDatabase {

    public void loadOnlineDatabase(){
        loadBookings();
        loadDrivers();
    }

    public void loadBookings(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch.json");
            JsonNode bookings = objectMapper.readValue(url, JsonNode.class);
            bookings.forEach(booking -> {
                //double check below null
                if(!booking.toString().equals("null") && !bookingExists(booking.get("booking_number").asInt())) {
                    BookingImpl b = new BookingImpl(Cash.getInstance());
                    System.out.println("1");
                    System.out.println(booking.toString());

                    JsonNode bookingNo = booking.get("booking_number");
                    b.setBookingNumber(bookingNo.asInt());

                    JsonNode comment = booking.get("comment");
                    b.setComments(comment.asText());
                    System.out.println("1a");

                    JsonNode dropoff = booking.get("dropoff");
                    b.setDropOffAddress(dropoff.asText());
                    System.out.println("1b");

                    JsonNode email = booking.get("email");
                    b.setClientEmail(email.asText());
                    System.out.println("1c");

                    JsonNode name = booking.get("name");
                    b.setClientName(name.asText());
                    System.out.println("1d");

                    JsonNode pickup = booking.get("pickup");
                    b.setPickUpAddress(pickup.asText());
                    System.out.println("1e");

                    JsonNode tel = booking.get("tel");
                    b.setClientTel(tel.asText());
                    System.out.println("1f");

                    JsonNode time = booking.get("time");
                    b.setTime(LocalTime.parse(time.asText()));
                    System.out.println("1g");

                    JsonNode vehicleType = booking.get("vehicle_type");
                    b.setVehicleType(vehicleType.asText());
                    System.out.println("1h");

                    JsonNode noPassengers = booking.get("no_passengers");
                    b.setNoPassengers(noPassengers.asText());
                    System.out.println("1i");

                    JsonNode date = booking.get("date");
                    b.setDate(LocalDate.parse(date.asText()));
                    System.out.println("1j");

                    JsonNode price = booking.get("price");
                    b.setPrice(Double.parseDouble(price.asText()));
                    System.out.println("1k");

                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean bookingExists(int number){
        for(BookingImpl bookings : Archive.allBookings){
            if(bookings.getBookingNumber() == number){
                return true;
            }
        }
        return false;
    }

    public void loadDrivers(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://amber-inferno-8546.firebaseio.com/Drivers.json");
            JsonNode drivers = objectMapper.readValue(url, JsonNode.class);
            drivers.forEach(driver -> {
                //double check below null
                if(!driver.toString().equals("null") && !driverExists(driver.get("id").asInt())) {
                    DriverImpl d = new DriverImpl(driver.get("id").asText());

                    JsonNode driverName = driver.get("name");
                    d.setName(driverName.asText());

                    JsonNode driverPin = driver.get("pin");
                    d.setPinCode(driverPin.asText());

                    JsonNode driverNis = driver.get("nis");
                    d.setNationalInsurance(driverNis.asText());

                    JsonNode driverAddress = driver.get("address");
                    d.setAddress(driverAddress.asText());

                    JsonNode driverPco = driver.get("pco_no");
                    d.setPcoNumber(driverPco.asText());

                    JsonNode driverTel = driver.get("tel");
                    d.setTel(driverTel.asText());

                    JsonNode driverActive = driver.get("Active");
                    if (driverActive.asText().equals("True")){
                        JsonNode driverStatus = driver.get("status");
                        QueueBlockBuilder qb = new QueueBlockBuilder();
                        AnchorPane ap = qb.newBlock(d.getId());
                        if(driverStatus.asText().equals("Break")){
                            qb.makeBreak(ap);
                        } else if (driverStatus.asText().equals("Busy")){
                            qb.makeBusy(ap);
                        }
                        ObservableLists.driverQueue.add(ap);
                    }

//                    JsonNode driverPcoDate = driver.get("pco_date");
//                    d.setPcoExpiryDate(driverPcoDate.asText()); //Throws error (need to re-format)


                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean driverExists(int number){
        for(DriverImpl driver : Archive.allDrivers){
            if(driver.getId().equals(number)){
                return true;
            }
        }
        return false;
    }
}
