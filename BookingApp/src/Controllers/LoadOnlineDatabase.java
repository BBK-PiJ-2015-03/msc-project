package Controllers;

import Model.Archive;
import Model.BookingImpl;
import Model.Cash;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by J2FX on 05/05/2016.
 */
public class LoadOnlineDatabase {
    public void loadBookings(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch.json");
            JsonNode bookings = objectMapper.readValue(url, JsonNode.class);
            bookings.forEach(booking -> {
                //double check below null
                if(!booking.toString().equals("null") && !exists(booking.get("booking_number").asInt())) {
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

    private boolean exists(int number){
        for(BookingImpl bookings : Archive.allBookings){
            if(bookings.getBookingNumber() == number){
                return true;
            }
        }
        return false;
    }
}
