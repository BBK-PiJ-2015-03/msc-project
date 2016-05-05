package Controllers;

import Model.BookingImpl;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import javafx.application.Platform;

/**
 * Created by J2FX on 04/05/2016.
 */
public class BookingListener {
    private static Firebase bRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch");

    public static void start(){
        bRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.getChildren().forEach(c -> System.out.println((c) +"\n Child Added"));
                Platform.runLater(() -> {
                    LoadOnlineDatabase load = new LoadOnlineDatabase();
                    load.loadBookings();
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println(s);
                System.out.println("Change happened!");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("Remove happened!");


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public static void addBooking(BookingImpl b){
        Firebase bRefChild = bRef.child(b.getBookingNumber()+"");
        bRefChild.child("pickup").setValue(b.getPickUpAddress());
        bRefChild.child("dropoff").setValue(b.getDropOffAddress());
        bRefChild.child("name").setValue(b.getClientName());
        bRefChild.child("time").setValue(b.getTime().toString());
        bRefChild.child("comment").setValue(b.getComments());
        bRefChild.child("tel").setValue(b.getClientTel());
        bRefChild.child("email").setValue(b.getClientEmail());
        bRefChild.child("booking_number").setValue(b.getBookingNumber());
//        bRef.child("date").setValue(b.getDate());
    }
}
