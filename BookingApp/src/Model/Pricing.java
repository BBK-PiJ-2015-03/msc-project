package Model;

/**
 * Created by J2FX on 01/05/2016.
 */
public interface Pricing {

    /**
     * Calculate price for unit of distance
     * @param distance in Kilometers or Miles
     * @return calculated price
     */
    double calculatePrice(String distance, String time);

}
