
package LbryClasses;

import java.io.Serializable;
import java.util.Date;


public class Members implements Serializable{
    //The individual clients of a Library
    
    public int MemberId;
    public String FirstName;
    public String MiddelName;
    public String LastName;
    public Gender Gender;
    public Date DateSubscribed;
    
    public Members(int Id, String Fn, String Mn, String Ln, Gender Gm, Date dSc){
        this.MemberId = Id;
        this.FirstName = Fn;
        this.MiddelName = Mn;
        this.LastName = Ln;
        this.Gender = Gm;
        this.DateSubscribed = dSc;
    }    
       
}

