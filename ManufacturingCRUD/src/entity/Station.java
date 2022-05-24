/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */
public class Station 
{
    private int ID;
    private String color;
    
    public Station(int ID, String color)
    {
        this.ID = ID;
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public String getStationColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Station{" + "ID=" + ID + ", color=" + color + '}';
    }

   
}
