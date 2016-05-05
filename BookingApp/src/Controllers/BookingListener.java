package Controllers;

import Model.BookingImpl;
import Model.Cash;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import javafx.application.Platform;

import java.text.SimpleDateFormat;

/**
 * Created by J2FX on 04/05/2016.
 */
public class BookingListener {
    private static Firebase bRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch");

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

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println(s);
                System.out.println("Change happened!");
                //Have to work out what change happened and apply it to local object

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("Remove happened!");
                // delete local object of child removed


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

    public static void addBooking(BookingImpl b){
        Firebase bRefChild = bRef.child(b.getBookingNumber()+"");
        System.out.println(1);
        bRefChild.child("pickup").setValue(b.getPickUpAddress());//
        System.out.println(2);
        bRefChild.child("dropoff").setValue(b.getDropOffAddress());//
        System.out.println(3);
        bRefChild.child("name").setValue(b.getClientName());//
        System.out.println(4);
        bRefChild.child("time").setValue(b.getTime().toString());
        System.out.println(5);
        bRefChild.child("comment").setValue(b.getComments());//
        System.out.println(6);
        bRefChild.child("tel").setValue(b.getClientTel());//
        System.out.println(7);
        bRefChild.child("email").setValue(b.getClientEmail());//
        System.out.println(8);
        bRefChild.child("booking_number").setValue(b.getBookingNumber());//
        System.out.println(9);
        bRefChild.child("account").setValue(Cash.getInstance().getId());
        System.out.println(10);
        bRefChild.child("vehicle_type").setValue(b.getVehicleType());
        System.out.println(11);
        bRefChild.child("no_passengers").setValue(b.getNoPassengers());
        System.out.println(12);
        bRefChild.child("date").setValue(b.getDate());
        System.out.println(13);
        try {
            bRefChild.child("time").setValue(new SimpleDateFormat("HH:mm").format(b.getTime()));
        } catch (IllegalArgumentException e){
            // Always throws this exception - unsure why as it saves correctly.
            // Do nothing as it still writes to database as expected
        }
        System.out.println(14);
        bRefChild.child("price").setValue(b.getPrice());
        System.out.println(15);
    }
}
