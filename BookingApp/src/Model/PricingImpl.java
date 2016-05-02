package Model;

import Model.Interfaces.Pricing;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Singleton implementation of Pricing interface,
 * There should only be one instance of this class.
 */
public final class PricingImpl implements Pricing {
    private final static PricingImpl instance = new PricingImpl();
    private double baseFare = 2.50;
    private double pricePerUnitOfDistance = 1.25;
    private double pricePerUnitOfTime = 0.15;
    DecimalFormat df = new DecimalFormat("#.00");


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
        price = Double.parseDouble(df.format(baseFare+timeFare+distanceFare));
        return Math.round(price * 20.0) / 20.0; //Rounding to nearest £0.05
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
