/**
 * Testing for TimeFieldValidator
 * Majority of test names will be self explanatory, if need be I will comment.
 */

import org.junit.Test;
import Model.TimeFieldValidator;

import static junit.framework.TestCase.assertTrue;

public class TimeValidatorTest {
    private TimeFieldValidator validator = new TimeFieldValidator();


    @Test
    public void test1000RandomTimes() {
        // Testing validator & format method of TimeFieldValidator
        for(int i=0; i <= 1000; i++) {
            String hour = ((int) (24 * Math.random())) + "";
            String minutes = ((int) (60 * Math.random())) + "";
            if(minutes.length() == 1){
                minutes = "0"+minutes;
            }
            assertTrue(validator.validate(validator.format(hour + ":" + minutes)));
        }

    }


}
