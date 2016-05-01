package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeFieldValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public TimeFieldValidator() {
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
    }

    /**
     * Validate time in 24 hours format with regular expression
     *
     * @param time time address for validation
     * @return true valid time fromat, false invalid time format
     */
    public boolean validate(final String time) {
        matcher = pattern.matcher(time);
        return matcher.matches();

    }

    public String format(String time) {
        TimeFieldValidator t = new TimeFieldValidator();
        if(!time.contains(":")) {
            if (time.length() == 4) {
                return time.substring(0, 2) + ":" + time.substring(2);
            } else if (time.length() == 3) {
                return "0" + time.substring(0, 1) + ":" + time.substring(1);
            }
            return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        }
        return time;
    }
}