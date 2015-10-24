package com.studyReport.main;

import com.studyReport.db.DBUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to select the options and display results
 *
 * @author K G
 */
public class MainClass {
    
    // method to display search options
    public static void displayOptions() {
        System.out.println("Search options:");
        System.out.println("1. Search venue by name.");
        System.out.println("2. Search venue by city.");
        System.out.println("3. Search venue by zipcode.");
        System.out.println("4. Search venue by lat, lng, radius.");
        System.out.println("5. Search venue by name and city.");
        System.out.println("6. Search venue by name and zipcode.");
        System.out.println("7. Search venue by name, lat, lng, radius.");
        System.out.println("8. Search venue by rating and city");
        System.out.println("9. Search venue by rating and zipcode");
        System.out.println("10. Search venue by rating, lat, lng, radius");
    }
    
    /*for a given lat, lng and radius the mehtod will return 
    end lattitudes and end longitudes */ 
    public static Double[] getLatsLngs(Double lat, Double lng, int radius){
        Double[] arr = new Double[4];
        Double latdist = (Double)(radius/69.0);
        Double lngdist = (Double)(radius/52.5);
        arr[0]= (double)Math.round((lat-latdist) * 10000) / 10000;
        arr[1]= (double)Math.round((lat + latdist) * 10000) / 10000;
        arr[2]= (double)Math.round((lng - lngdist) * 10000) / 10000;
        arr[3]= (double)Math.round((lng + lngdist) * 10000) / 10000;
        return arr;
    }   

    public static void main(String args[]) {
        while (true) {
            MainClass.displayOptions();
            try {
                System.out.println("Select option using number and press enter");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String option = br.readLine();
                switch (option) {
                    
                    case "1": // search by name
                        System.out.println("Enter name:");
                        String name = br.readLine();
                        DBUtil.getVenueByName(name);
                        break;
                    
                    case "2":// search by city
                        System.out.println("Enter City:");
                        String city = br.readLine();
                        DBUtil.getVenueByCity(city);
                        break;
                    
                    case "3":// search by zipcode
                        System.out.println("Enter Zipcode:");
                        String zip = br.readLine();
                        DBUtil.getVenueByZipcode(zip);
                        break;
                    
                    case "4": // search by lat, lng, radius
                        System.out.println("Enter Lattitude:");
                        String lat = br.readLine();
                        System.out.println("Enter Longitude:");
                        String lng = br.readLine();
                        System.out.println("Enter Radius in miles:");
                        String radius = br.readLine();
                        DBUtil.getVenueByLatLngRad(getLatsLngs(Double.parseDouble(lat),Double.parseDouble(lng),Integer.parseInt(radius)));
                        break;
                    
                    case "5": //search by name and city
                        System.out.println("Enter Name:");
                        String name1 = br.readLine();
                        System.out.println("Enter City:");
                        String city1 = br.readLine();
                        DBUtil.getVenueByNameCity(name1,city1);
                        break;
                    
                    case "6": // search by name and zipcode
                        System.out.println("Enter Name:");
                        String name2 = br.readLine();
                        System.out.println("Enter Zipcode:");
                        String zip2 = br.readLine();
                        DBUtil.getVenueByNameZipcode(name2,zip2);
                        break;
                    
                    case "7": // search by name, lat, lng, radius
                        System.out.println("Enter Name:");
                        String nameLatLng = br.readLine();
                        System.out.println("Enter Lattitude:");
                        String lat1 = br.readLine();
                        System.out.println("Enter Longitude:");
                        String lng1 = br.readLine();
                        System.out.println("Enter Radius in miles:");
                        String radius1 = br.readLine();
                        DBUtil.getVenueByNameLatLngRad(nameLatLng, getLatsLngs(Double.parseDouble(lat1),Double.parseDouble(lng1),Integer.parseInt(radius1)));
                        break;
                    
                    case "8": // search by rating and city
                        System.out.println("Enter Rating:");
                        String rating2 = br.readLine();
                        System.out.println("Enter City:");
                        String city2 = br.readLine();
                        DBUtil.getVenueByRatingCity(Double.parseDouble(rating2),city2);
                        break;
                    
                    case "9": // search by rating and zipcode
                        System.out.println("Enter Rating:");
                        String rating3 = br.readLine();
                        System.out.println("Enter Zipcode:");
                        String zip3 = br.readLine();
                        DBUtil.getVenueByRatingZipcode(Double.parseDouble(rating3),zip3);
                        break;
                    
                    case "10": // search by rating, lat, lng, radius
                        System.out.println("Enter Rating:");
                        String ratingLatLng = br.readLine();
                        System.out.println("Enter Lattitude:");
                        String lat2 = br.readLine();
                        System.out.println("Enter Longitude:");
                        String lng2 = br.readLine();
                        System.out.println("Enter Radius in miles:");
                        String radius2 = br.readLine();
                        DBUtil.getVenueByRatingLatLngRad(Double.parseDouble(ratingLatLng), getLatsLngs(Double.parseDouble(lat2),Double.parseDouble(lng2),Integer.parseInt(radius2)));
                        break;
                    
                    default:
                        System.out.println("You din't select the correct option");                        
                        break;
                }
            System.out.println("Do u want to search again? press y or n");
            String inp = br.readLine();
            if(inp.equals("y")){
               continue; 
            }else if(inp.equals("n")){
               break; 
            }else{
                break;
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
