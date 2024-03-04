/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 *
 * @author am23adf
 */
public class TemperatureHistory {
    ArrayList<TemperatureData> tempDataHistory = new ArrayList<TemperatureData>();
    PropertyChangeListener pcl = null;
//  Add temperature data to array
    public void addReading(TemperatureData td){
        tempDataHistory.add(td.duplicate());
        pcl.propertyChange(new PropertyChangeEvent(this, "history", null, toString()));
    }
//  convert temperature data to string
    public String toString(){
        String allHistory = "";
        for(int i = 0; i<tempDataHistory.size(); i++){
            allHistory += tempDataHistory.get(i).toString();
            allHistory += "\n";
        }
        return allHistory;
    }
    
    public void addPropertyChangeListner(PropertyChangeListener outsidePcl){
        this.pcl = outsidePcl;
    }
    
    

}


