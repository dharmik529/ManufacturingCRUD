/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;

/**
 *
 * @author Gokhan
 */
public class Part 
{
    private int ID;
    private boolean repaired;
    private int stationID;
    private String dateTimeAssembled;
    private String StationColor;
    
    public Part(int ID, boolean repaired, int stationID, String dateTimeAssembled, String StationColor)
    {
        this.ID = ID;
        this.repaired = repaired;
        this.stationID = stationID;
        this.dateTimeAssembled = dateTimeAssembled;
        this.StationColor = StationColor;
    }

    public int getID() {
        return ID;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public int getStationID() {
        return stationID;
    }

    public String getDateTimeAssembled() {
        return dateTimeAssembled;
    }
    public String getStationColor(){
        return StationColor;
    }

    @Override
    public String toString() {
        return "Part{" + "ID=" + ID + ", repaired=" + repaired + ", stationID=" + 
                stationID + ", dateTimeAssembled=" + dateTimeAssembled + "StationColor=" + StationColor + '}';
    }





    

    
}
