package Model;

import Model.Interfaces.Account;
import Model.Interfaces.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Cash account, all cash booking to be stored here.
 */
public final class Cash implements Account {
    private final static Cash instance = new Cash();
    List<Booking> cashBookings = new ArrayList<>();

    public static synchronized Cash getInstance() {
        return instance;
    }

    private Cash() {
    }

    @Override
    public List<Booking> getBookings() {
        return cashBookings;
    }

    @Override
    public void deleteBooking(Booking booking) {
        cashBookings.remove(booking);
    }

    @Override
    public void newBooking(Booking booking) {
        cashBookings.add(booking);
    }
}
