package Controllers;

import Model.BookingImpl;
import Model.Cash;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;

/**
 * Created by J2FX on 04/05/2016.
 */
public class BookingListener {
    private static Firebase bRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch");
    private static Firebase dRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Dispatched");

    /**
     * Load online database, this takes all bookings that are awaiting dispatch and downloads them locally
     * run on a the main thread.
     */
    public static void start(){
        bRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Platform.runLater(() -> {
                    LoadOnlineDatabase load = new LoadOnlineDatabase();
                    load.loadBookings();
                    MainController m = new MainController();
                    m.refreshBookingNo();
                });
                // Must run on FX thread - otherwise throws exception but still functions well either way ? weird

            }

            /**
             * Whenever a booking has values changed on the database this method is run,
             * the booking will be updated locally to match its values on the database.
             * @param dataSnapshot the booking that has changed
             * @param s
             */
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Platform.runLater(() -> {
                    LoadBookingChange load = new LoadBookingChange();
//                    System.out.println("UPDATING BOOKING: "+Integer.parseInt(dataSnapshot.getKey()));
                    load.loadBookingChange(Integer.parseInt(dataSnapshot.getKey()));
                });
            }

            /**
             * When a booking is removed from the online database this method will remove it locally,
             *
             * ConcurrentModificationException may be thrown if this is run straight after
             * application has been launched
             * @param dataSnapshot
             */
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int removedBooking = Integer.parseInt(dataSnapshot.getKey());
                try {
                    ObservableLists.bookingsList.forEach(b -> {
                        if(b.getBookingNumber() == removedBooking){
                            ObservableLists.bookingsList.remove(b);
                        }
                    });
                } catch (ConcurrentModificationException ex){
                    //This occurs because I am removing an from a list while I am iterating through that list
                    //functions properly and works well however if I have time I will look at alternative ways
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //Do nothing
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //If internet connection cut - maybe wait for 1 minute and try again?
            }
        });
    }

    /**
     * Adds a booking to the database
     * @param b the booking to add
     * @param status status to assign the booking (either Awaiting_Dispatch, Dispatched, Completed)
     */
    public static void addBooking(BookingImpl b, String status){
        Firebase bRefChild;
        if(!status.equals("new")){
            //Dispatched Booking
            //Removes from Awaiting_Dispatch and puts in Dispatched
            bRefChild = dRef.child(status);
            bRef.child(b.getBookingNumber()+"").removeValue();
        } else {
            bRefChild = bRef.child(b.getBookingNumber() + "");
        }
        bRefChild.child("pickup").setValue(b.getPickUpAddress());//
        bRefChild.child("dropoff").setValue(b.getDropOffAddress());//
        bRefChild.child("name").setValue(b.getClientName());//
        bRefChild.child("time").setValue(b.getTime().toString());
        bRefChild.child("comment").setValue(b.getComments());//
        bRefChild.child("tel").setValue(b.getClientTel());//
        bRefChild.child("email").setValue(b.getClientEmail());//
        bRefChild.child("booking_number").setValue(b.getBookingNumber());//
        bRefChild.child("account").setValue(Cash.getInstance().getId());
        bRefChild.child("vehicle_type").setValue(b.getVehicleType());
        bRefChild.child("no_passengers").setValue(b.getNoPassengers());
        bRefChild.child("date").setValue(b.getDate());
        try {
            bRefChild.child("time").setValue(new SimpleDateFormat("HH:mm").format(b.getTime()));
        } catch (IllegalArgumentException e){
            // Always throws this exception - unsure why as it saves correctly.
            // Do nothing as it still writes to database as expected
            // Maybe need to look at different formats for time?
        }
        bRefChild.child("price").setValue(b.getPrice());

    }


}
