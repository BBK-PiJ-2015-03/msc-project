package Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Singleton implementation of Pricing interface,
 * There should only be one instance of this class.
 */
public final class PricingImpl implements Pricing {
    private static PricingImpl instance = new PricingImpl();
    private double baseFare;
    private double pricePerUnitOfDistance;
    private double pricePerUnitOfTime;
    DecimalFormat df = new DecimalFormat("#.####");


    public static synchronized PricingImpl getInstance() {
        return instance;
    }

    private PricingImpl() {
    }

    @Override
    public double calculatePrice(String distance, String time) {
        double price;
        String journeyTime[] = time.split(" ");
        String journeyDistance[] = distance.split(" ");
        double timeFare = Double.parseDouble(journeyTime[0])*pricePerUnitOfTime;
        double distanceFare = Double.parseDouble(journeyDistance[0])*pricePerUnitOfDistance;
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(baseFare+timeFare+distanceFare));
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getPricePerUnitOfDistance() {
        return pricePerUnitOfDistance;
    }

    public void setPricePerUnitOfDistance(double pricePerUnitOfDistance) {
        this.pricePerUnitOfDistance = pricePerUnitOfDistance;
    }

    public double getPricePerUnitOfTime() {
        return pricePerUnitOfTime;
    }

    public void setPricePerUnitOfTime(double pricePerUnitOfTime) {
        this.pricePerUnitOfTime = pricePerUnitOfTime;
    }
}
