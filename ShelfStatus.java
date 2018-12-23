
package LbryClasses;

import java.io.Serializable;


public enum ShelfStatus implements Serializable{
    Reserved,
    CheckedOut,
    CheckedIn;
    
    public String toString(){
        switch(this){
            case Reserved:
                return "Reserved";
            case CheckedOut:
                return "CheckedOut";
            case CheckedIn:
                return  "CheckedIn";
             default:
                 return null;
        }           
    }
    
    public String[] getPossibleActivity(){
        switch(this){
            case Reserved:
                return new String[]{"CheckOut","CheckIn"};
            case CheckedOut:
                return new String[]{"CheckIn"};
            case CheckedIn:
                return  new String[]{"CheckOut","Reserve"};
             default:
                 return null;
        }           
    }
    
    public static ShelfStatus getValueOf(String value){
        if(value.equalsIgnoreCase(Reserved.toString())){
            return Reserved;
        }
        if (value.equalsIgnoreCase(CheckedOut.toString())) {
            return CheckedOut;            
        }
        if (value.equalsIgnoreCase(CheckedIn.toString())) {
            return CheckedIn;            
        }
        return null;
    }
    
        
}
    

