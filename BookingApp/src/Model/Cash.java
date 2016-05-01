package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Cash account, all cash booking to be stored here.
 */
public class Cash implements Account {
    List<Booking> cashBookings = new ArrayList<>();

    @Override
    public List<Booking> getJobs() {
        return cashBookings;
    }

    @Override
    public void deleteJob(Booking booking) {
        cashBookings.remove(booking);
    }

    @Override
    public void newJob(Booking booking) {
        cashBookings.add(booking);
    }
}
