package Maps;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by J2FX on 26/04/2016.
 */
public class MapGenerator {

    //These are final strings as they will never be changed
    final String first = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body style=\"margin:0\">\n" +
            "<iframe src=\"https://www.google.com/maps/embed/v1/directions?key=AIzaSyB2kViW5AL4Q1vGaik53tkw6Ve1ZXdT2vg&origin=";
    final String second = "&destination=";
    final String third = "\" style=\"border: 0;width: 100%;height: 100%;position: absolute;\">\n" +
            "</iframe>\n" +
            "</body>\n" +
            "</html>";

    /**
     * Constructor will take both origin and destination and pass them through
     * the relevant methods to update the map, this will avoid repeating code.
     *
     * @param o from location
     * @param d to location
     */
//    public MapGenerator(String o, String d) {
//        setRoute(o, d);
//    }

    /**
     * Opens deletes current map HTML file and creates a new one with updated routes
     * this will display the route on the Booking tab.
     *
     * @param origin from location
     * @param destination to location
     */
    public void write(String origin, String destination){
        String filePath = new File("").getAbsolutePath();
        String s = File.separator;
        filePath = filePath+s+"BookingApp"+s+"src"+s+"Maps"+s+"map.html";

        File oldMap=new File(filePath);
        oldMap.delete();
        File newMap=new File(filePath);

        try {
            PrintWriter fw = new PrintWriter(newMap);
            fw.print(first);
            fw.print(origin);
            fw.print(second);
            fw.print(destination);
            fw.print(third);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces string spaces with '+' to conform to URL format
     *
     * @param origin from location
     * @param destination to location
     */
    public void setRoute(String origin, String destination){
        origin = origin.replaceAll("\\s","+");
        destination = destination.replaceAll("\\s","+");
        write(origin,destination);
    }
}
