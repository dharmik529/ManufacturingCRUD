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
public class PartDAO implements DAO<Part>
{   
    public PartDAO() {
        
    }
    List<Part> parts;
    /**
     * Get a single part entity as an part object
     * @param id
     * @return 
     */
    @Override
    public Optional<Part> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM MP_Part WHERE Part_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Part part = null;
            while (rs.next()) {
                part = new Part(rs.getInt("Part_ID"), rs.getBoolean("Repaired"), rs.getInt("Station_ID"), rs.getString("Date_Time_Assembled"), rs.getString("Station_Color")); 
                        
            }
            return Optional.ofNullable(part);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all part entities as a List
     * @return 
     */
    @Override
    public List<Part> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        parts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MP_Part";
            rs = db.executeQuery(sql);
            Part part = null;
            while (rs.next()) {
                part = new Part(rs.getInt("Part_ID"), rs.getBoolean("Repaired"), rs.getInt("Station_ID"), rs.getString("Date_Time_Assembled"), rs.getString("Station_Color"));
                parts.add(part);
            }
            return parts;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert an part object into part table
     * @param part 
     */
    @Override
    public void insert(Part part)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO MP_Part(Part_ID, Repaired, Station_ID, Date_Time_Assembled, Station_Color) VALUES (?, ?, ?, ?,?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, part.getID());
            stmt.setBoolean(2, part.isRepaired());
            stmt.setInt(3, part.getStationID());
            stmt.setString(4, part.getDateTimeAssembled());
            stmt.setString(5, part.getStationColor());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new part was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update an part entity in database if it exists using an part object
     * @param part
     */
    @Override
    public void update(Part part) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE MP_Part SET Repaired=?, Station_ID=?, Date_Time_Assembled=? WHERE Part_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setBoolean(1, part.isRepaired());
            stmt.setInt(2, part.getStationID());
            stmt.setString(3, part.getDateTimeAssembled());      
            stmt.setInt(4, part.getStationID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing part was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete an part from part table if the entity exists
     * @param part 
     */
    @Override
    public void delete(Part part) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM MP_Part WHERE Part_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, part.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An part was deleted successfully!");
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
            String sql = "SELECT * FROM MP_Part WHERE Part_ID = -1";//We just need this sql query to get the column headers
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
