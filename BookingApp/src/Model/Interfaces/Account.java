package Model.Interfaces;

import java.util.List;

/**
 * Account Interface.
 **/
public interface Account {
    /**
     * @return list of jobs for the account
     */
    List<Booking> getBookings();

    /**
     * Remove a job
     */
    void deleteBooking(Booking booking);

    /**
     * Add a job
     */
    void newBooking(Booking booking);

    String getId();

}
