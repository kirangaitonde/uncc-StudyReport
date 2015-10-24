package com.studyReport.db;

/**
 * Utility class for retrieving and inserting data from database
 *
 * @author K G
 *
 * References: 
 * http://www.datastax.com/ 
 * http://planetcassandra.org/
 *
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DBUtil {

    private static Cluster cluster;
    private static Session session;

    // method for 1st option, get venue by name 
    public static void getVenueByName(String name) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE name = ?";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(name)));
        cluster.close();
    }

    // method for 2st option, get venue by city
    public static void getVenueByCity(String city) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE city = ?";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(city)));
        cluster.close();
    }

    // method for 3rd option, get venue by zipcode 
    public static void getVenueByZipcode(String zip) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE zipcode = ?";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(zip)));
        cluster.close();
    }

    // method for 4th option, get venue by lat, lng, radius 
    public static void getVenueByLatLngRad(Double[] arr) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind());
        displayResultSetLatLng(rs, arr);
        cluster.close();
    }

    // method for 5th option, get venue by name and city 
    public static void getVenueByNameCity(String name, String city) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE name = ? AND city = ? ALLOW FILTERING";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(name, city)));
        cluster.close();
    }

    // method for 6th option, get venue by name and zipcode
    public static void getVenueByNameZipcode(String name, String zipcode) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE name = ? AND zipcode = ? ALLOW FILTERING";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(name, zipcode)));
        cluster.close();
    }

    // method for 7th option, get venue by name, lat, lng, radius 
    public static void getVenueByNameLatLngRad(String name, Double[] arr) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE name = ?";;
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(name));
        displayResultSetLatLng(rs, arr);
        cluster.close();
    }

    // method for 8th option, get venue by rating and city 
    public static void getVenueByRatingCity(Double rating, String city) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE rating >= ? AND city = ? ALLOW FILTERING";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(rating, city)));
        cluster.close();
    }

    // method for 9th option, get venue by rating and zipcode 
    public static void getVenueByRatingZipcode(Double rating, String zipcode) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE rating >= ? AND zipcode = ? ALLOW FILTERING";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        displayResultSet(session.execute(boundStatement.bind(rating, zipcode)));
        cluster.close();
    }

    // method for 10th option, get venue by rating, lat, lng, radius 
    public static void getVenueByRatingLatLngRad(Double rating, Double[] arr) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "SELECT * from venues WHERE rating >= ? ALLOW FILTERING";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(rating));
        displayResultSetLatLng(rs, arr);
        cluster.close();
    }

    // method to print the result set
    public static void displayResultSet(ResultSet rs) {
        if (rs.getAvailableWithoutFetching() == 0) {
            System.out.println("No venues found for the given criteria. Try again!");
        } else {
            System.out.println("Name " + "|" + "Address1 " + "|" + "City " + "|" + "State " + "|" + "Country " + "|" + "ZipCode " + "|" + "PhoneNumber " + "|" + "Rating ");
            for (Row row : rs) {
                System.out.println(row.getString("name") + "|" + row.getString("address1") + "|" + row.getString("city") + "|" + row.getString("state")
                        + "|" + row.getString("country") + "|" + row.getString("zipcode") + "|" + row.getString("phonenumber") + "|" + row.getDouble("rating"));
            }
        }
    }

    // method to print the result for lat lng options
    public static void displayResultSetLatLng(ResultSet rs, Double[] arr) {
        if (rs.getAvailableWithoutFetching() == 0) {
            System.out.println("No venues found for the given criteria. Try again!");
        } else {
            int flag = 0;
            for (Row row : rs) {
                Double lat = row.getDouble("lat");
                Double lng = row.getDouble("lng");
                if (lat >= arr[0] && lat <= arr[1] && lng >= arr[2] && lng <= arr[3]) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("Name " +"|" + "lat " +"|" + "lng " +"|" + "Address1 " + "|" + "City " + "|" + "State " + "|" + "Country " + "|" + "ZipCode " + "|" + "PhoneNumber " + "|" + "Rating ");
                    }
                    System.out.println(row.getString("name") + "|" + row.getDouble("lat") + "|" + row.getDouble("lng") + "|"
                            + row.getString("address1") + "|" + row.getString("city") + "|" + row.getString("state")
                            + "|" + row.getString("country") + "|" + row.getString("zipcode") + "|"
                            + row.getString("phonenumber") + "|" + row.getDouble("rating"));
                }

            }
            if (flag == 0) {
                System.out.println("No venues found for the given criteria. Try again!");
            }
        }
    }

    //method to insert data into venues table
    public static void insertRecord(String source_id, String source, String name, double lat, double lng, String address1, String address2, String city, String state, String country, String zipcode, String phonenumber, double rating) {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("urbanfttask");
        String query = "INSERT INTO venues(source_id, source, name, lat, lng, address1, address2, city, state, country, zipcode, phonenumber, rating) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = session.prepare(query);
        BoundStatement boundStatement = new BoundStatement(statement);
        session.execute(boundStatement.bind(source_id, source, name, lat, lng, address1, address2, city, state, country, zipcode, phonenumber, rating));
        cluster.close();
    }

}
