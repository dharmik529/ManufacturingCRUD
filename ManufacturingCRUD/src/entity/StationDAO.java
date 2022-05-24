/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Gokhan
 */
public class StationDAO implements DAO<Station>
{   
    public StationDAO() {
        
    }
    List<Station> stations;
    /**
     * Get a single station entity as a station object
     * @param id
     * @return 
     */
    @Override
    public Optional<Station> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM MP_Station WHERE Station_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Station station = null;
            while (rs.next()) {
                station = new Station(rs.getInt("Station_ID"), rs.getString("Station_Color"));
            }
            return Optional.ofNullable(station);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all station entities as a List
     * @return 
     */
    @Override
    public List<Station> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        stations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MP_Station";
            rs = db.executeQuery(sql);
            Station station = null;
            while (rs.next()) {
                station = new Station(rs.getInt("Station_ID"), rs.getString("Station_Color"));
                stations.add(station);
            }
            return stations;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a station object into station table
     * @param station 
     */
    @Override
    public void insert(Station station)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO MP_Station(Station_ID, Station_Color) VALUES (?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, station.getID());
            stmt.setString(2, station.getStationColor());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new check in location was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a station entity in database if it exists using a station object
     * @param station
     */
    @Override
    public void update(Station station) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE MP_Station SET Station_Color=? WHERE Station_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, station.getStationColor());
            stmt.setInt(2, station.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing check in location was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a station from station table if the entity exists
     * @param station 
     */
    @Override
    public void delete(Station station) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM MP_Station WHERE Station_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, station.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A check in location was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MP_Station WHERE Station_ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
