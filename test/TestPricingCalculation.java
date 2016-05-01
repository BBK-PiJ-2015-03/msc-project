import Model.Pricing;
import Model.PricingImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test for pricing calculation
 */
public class TestPricingCalculation {
    private Pricing priceCalc = PricingImpl.getInstance();


    @Before
    public void buildUp(){
        // Base fare at £2.50
        PricingImpl.getInstance().setBaseFare(2.50);

        //Every minute charged at 10p
        PricingImpl.getInstance().setPricePerUnitOfTime(0.15);
        //Every km/mile charged at £1.25
        PricingImpl.getInstance().setPricePerUnitOfDistance(1.25);
    }

    @Test
    public void testPriceCalculatingAccordingToSetPrices() {
        String distance = "9.0 km";
        String time = "25 mins";
        // £2.50 base fare
        // 9 * 1.25 = £11.25 - distance charge
        // 25 * 0.15 = £3.75 - time charge
        // £2.50 + £11.25 + £3.75 = £17.50 - total charge
        double expected = 17.50;
        double result = priceCalc.calculatePrice(distance, time);
        assertEquals(expected, result);

    }

    @Test
    public void testCorrectRoundingInPriceCalculation() {
        String distance = "5.86 km";
        String time = "19.30 mins";
        double expected = 12.72;
        double result = priceCalc.calculatePrice(distance, time);
        assertEquals(expected, result);

    }


}
