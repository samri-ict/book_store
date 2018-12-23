
package LbryClasses;

import java.io.Serializable;


public enum ActivityType implements Serializable{
    Reserve,
    CheckOut,
    CheckIn;
    
    public String toString(){
        switch(this){
            case Reserve:
                return "Reserve";
            case CheckOut:
                return "CheckOut";
            case CheckIn:
                return "CheckIn";
            default:
                 return null;
        }
    }
    
     public ShelfStatus getShelfStatus(){
        switch(this){
            case Reserve:
                return ShelfStatus.Reserved;
            case CheckOut:
                return ShelfStatus.CheckedOut;
            case CheckIn:
                return ShelfStatus.CheckedIn;
            default:
                 return null;
        }
    }
    
    public static ActivityType getValueOf(String value){
        if (value.equalsIgnoreCase(Reserve.toString())) {
            return Reserve;
        }else if (value.equalsIgnoreCase(CheckOut.toString())) {
            return CheckOut;
        }else if (value.equalsIgnoreCase(CheckIn.toString())) {
            return CheckIn;
        }else {
            return null;
        }
    }
    
}
