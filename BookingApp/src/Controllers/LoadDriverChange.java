package Controllers;

/**
 * Listens to driver changes and updates them locally.
 */
public class LoadDriverChange {
//    public void loadBookingChange(int bookingNumber){
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            URL url = new URL("https://amber-inferno-8546.firebaseio.com/Drivers.json");
//            JsonNode bookings = objectMapper.readValue(url, JsonNode.class);
//            JsonNode booking = bookings.get(bookingNumber);
//            BookingImpl b = getBooking(bookingNumber);
//            final long startTime = System.currentTimeMillis();
//
////            JsonNode bookingNo = booking.get("booking_number");
////            b.setBookingNumber(bookingNo.asInt());
//
//            JsonNode comment = booking.get("comment");
//            if (!b.getComments().equals(comment.asText())) {
//                b.setComments(comment.asText());
//                System.out.println("1a");
//            }
//
//            JsonNode dropoff = booking.get("dropoff");
//            if (!b.getDropOffAddress().equals(dropoff.asText())) {
//                b.setDropOffAddress(dropoff.asText());
//                System.out.println("1b");
//            }
//
//            JsonNode email = booking.get("email");
//            if (!b.getClientEmail().equals(email.asText())) {
//                b.setClientEmail(email.asText());
//                System.out.println("1c");
//            }
//
//            JsonNode name = booking.get("name");
//            if (!b.getClientName().equals(name.asText())) {
//                b.setClientName(name.asText());
//                System.out.println("1d");
//            }
//
//            JsonNode pickup = booking.get("pickup");
//            if (!b.getPickUpAddress().equals(pickup.asText())) {
//                b.setPickUpAddress(pickup.asText());
//                System.out.println("1e");
//            }
//
//            JsonNode tel = booking.get("tel");
//            if (!b.getClientTel().equals(tel.asText())) {
//                b.setClientTel(tel.asText());
//                System.out.println("1f");
//            }
//
//            JsonNode time = booking.get("time");
//            if (!b.getTime().equals(LocalTime.parse(time.asText()))) {
//                b.setTime(LocalTime.parse(time.asText()));
//                System.out.println("1g");
//            }
//
//            JsonNode vehicleType = booking.get("vehicle_type");
//            if (!b.getVehicleType().equals(vehicleType.asText())) {
//                b.setVehicleType(vehicleType.asText());
//                System.out.println("1h");
//            }
//
//            JsonNode noPassengers = booking.get("no_passengers");
//            if (!b.getNoPassengers().equals(noPassengers.asText())) {
//                b.setNoPassengers(noPassengers.asText());
//                System.out.println("1i");
//            }
//
//            JsonNode date = booking.get("date");
//            if (!b.getDate().equals(LocalDate.parse(date.asText()))) {
//                b.setDate(LocalDate.parse(date.asText()));
//                System.out.println("1j");
//            }
//
//            JsonNode price = booking.get("price");
//            if (b.getPrice() != (price.asDouble())) {
//                b.setPrice(price.asDouble());
//                System.out.println("1k");
//            }
//            final long endTime = System.currentTimeMillis();
//            System.out.println("Total execution time: " + (endTime - startTime) );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private BookingImpl getBooking(int number){
//        for(BookingImpl bookings : Archive.allBookings){
//            if(bookings.getBookingNumber() == number){
//                return bookings;
//            }
//        }
//        return null; // no booking exist - should not get to this stage
//    }
}
