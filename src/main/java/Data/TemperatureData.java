/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author comqsjb
 */
public class TemperatureData {

   
    
    PropertyChangeListener pcl = null;
    public float getTemperature() {
        return temperature;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener outsidePcl)
    {
        this.pcl = outsidePcl;
    }

    /**
     * @param temperature the temperature to set
     */
    public float convertToCelsius(float temperature){
        return ( (temperature - 32) * 5/9 );
    }
    
//    public void setTemperature(String temperature, boolean isCelsius)  {
    public void setTemperature(String temperature)  {
        
        System.out.println("Temperature is " + temperature);
        float temp, oldTemp;
        oldTemp = this.temperature;
        
        try 
        {
            this.temperature = Float.parseFloat(temperature);
            if (pcl == null) return;
            if (!celsius){
                this.temperature = this.convertToCelsius(this.temperature);
                System.out.println("converted to celsius: " + this.temperature);
            }
            
            pcl.propertyChange(new PropertyChangeEvent(this, "temperature", oldTemp, this.temperature));
            pcl.propertyChange(new PropertyChangeEvent(this, "feedback", "", toString()));
  
        }
        catch (Exception e)
                {
                   pcl.propertyChange(new PropertyChangeEvent(this, "feedback", 0, "Invalid entry"));
                   return;
                }

          }
//    move time.now to setTemperature
//    function to prepare the output string
    public String toString() {
        String shownTemperature = String.valueOf(this.temperature);
//        String dateAndTime = getDateAndTime();

        this.localDateTime = now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        String dateAndTime = localDateTime.format(format);
        if (this.temperature > 37.9) return  "Date: " + dateAndTime + " - Temperature Reading: "+ this.temperature + " (High)";
        else {
            return "Date: " + dateAndTime + " - Temperature Reading: " + this.temperature + " (Normal)";
        }
    }
    
    
//    Function to get current date and time from the system
//    public String getDateAndTime(){
//        localDateTime = now();
//        return localDateTime
        
//    }
    
    public TemperatureData duplicate(){
        TemperatureData clone = new TemperatureData();
        clone.celsius = this.celsius;
        clone.localDateTime = this.localDateTime;
        clone.temperature = this.temperature;
        return clone;
    }
    
    public void setCelciusScale(){
        this.celsius = true;
    }
    
    public void setFahrenheitScale(){
        this.celsius = false;
    }
    
    private float temperature;
    private boolean celsius = true;
    private LocalDateTime localDateTime;
    
}
