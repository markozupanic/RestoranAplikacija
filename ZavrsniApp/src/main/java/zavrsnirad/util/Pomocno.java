/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import zavrsnirad.model.Operater;

/**
 *
 * @author X
 */
public class Pomocno {
    public static final String FORMAT_DATUMA = "dd.MM.yyyy";
    public static final String FORMAT_DATUMA_VRIJEME = "dd.MM.yyyy HH:mm";
     public static final String NAZIV_APLIKACIJE = "ZAVRŠNI RAD";
    public static Operater operater;
    
    private static SimpleDateFormat format;
    public static String getFormat(Date d){
        if(format==null){
            format=new SimpleDateFormat(FORMAT_DATUMA_VRIJEME);
            
        }
        if(d==null){
        return "";
        }
        return format.format(d);
    }
    
    
    
    public static Integer randomKolicina(int min,int max){
          return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    
}
