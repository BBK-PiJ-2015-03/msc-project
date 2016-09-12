package Controllers;

import Model.Archive;
import Model.BookingImpl;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class handles booking data changes within the json database.
 */
public class LoadBookingChange {

    /**
     * Takes a booking number and compares that booking to the json database,
     * any changes to that booking will be updated locally.
     * @param bookingNumber
     */
    public void loadBookingChange(int bookingNumber){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch.json");
            JsonNode bookings = objectMapper.readValue(url, JsonNode.class);
            JsonNode booking = bookings.get(bookingNumber+"");

            BookingImpl b = getBooking(bookingNumber);

            JsonNode comment = booking.get("comment");
            if (!b.getComments().equals(comment.asText())) {
                b.setComments(comment.asText());
            }

            JsonNode dropoff = booking.get("dropoff");
            if (!b.getDropOffAddress().equals(dropoff.asText())) {
                b.setDropOffAddress(dropoff.asText());
            }

            JsonNode email = booking.get("email");
            if (!b.getClientEmail().equals(email.asText())) {
                b.setClientEmail(email.asText());
            }

            JsonNode name = booking.get("name");
            if (!b.getClientName().equals(name.asText())) {
                b.setClientName(name.asText());
            }

            JsonNode pickup = booking.get("pickup");
            if (!b.getPickUpAddress().equals(pickup.asText())) {
                b.setPickUpAddress(pickup.asText());
            }

            JsonNode tel = booking.get("tel");
            if (!b.getClientTel().equals(tel.asText())) {
                b.setClientTel(tel.asText());
            }

            JsonNode time = booking.get("time");
            if (!b.getTime().equals(LocalTime.parse(time.asText()))) {
                b.setTime(LocalTime.parse(time.asText()));
            }

            JsonNode vehicleType = booking.get("vehicle_type");
            if (!b.getVehicleType().equals(vehicleType.asText())) {
                b.setVehicleType(vehicleType.asText());
            }

            JsonNode noPassengers = booking.get("no_passengers");
            if (!b.getNoPassengers().equals(noPassengers.asText())) {
                b.setNoPassengers(noPassengers.asText());
            }

            JsonNode date = booking.get("date");
            if (!b.getDate().equals(LocalDate.parse(date.asText()))) {
                b.setDate(LocalDate.parse(date.asText()));
            }

            JsonNode price = booking.get("price");
            if (b.getPrice() != (price.asDouble())) {
                b.setPrice(price.asDouble());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a booking exists, if so return that booking.
     * @param number of booking
     * @return booking
     */
    private BookingImpl getBooking(int number){
        for(BookingImpl bookings : Archive.allBookings){
            if(bookings.getBookingNumber() == number){
                return bookings;
            }
        }
        return null; // no booking exist - should not get to this stage
    }
}
