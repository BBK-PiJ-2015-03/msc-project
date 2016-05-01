package Model;

import java.util.List;

/**
 * Account Interface.
 **/
public interface Account {
    /**
     * @return list of jobs for the account
     */
    List<Booking> getJobs();

    /**
     * Remove a job
     */
    void deleteJob(Booking booking);

    /**
     * Add a job
     */
    void newJob(Booking booking);

}
