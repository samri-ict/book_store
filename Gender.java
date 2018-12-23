
package LbryClasses;

import java.io.Serializable;

public enum Gender implements Serializable{
    Male,
    Female;
    
    public String toString(){
        switch(this){
        case Male :
            return "Male";
        case Female :
            return "Female";        
        }
        return null;
    }

   
    public static Gender getValueOf(String value){
        if(value.equalsIgnoreCase(Male.toString()))
            return Gender.Male;
        else if(value.equalsIgnoreCase(Female.toString()))
            return Gender.Female;
        else
            return null;
    }
}
