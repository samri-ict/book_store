
package LbryClasses;

import java.io.Serializable;


public class Subjects implements Serializable{
    public int SubjectID;
    public String Name;
    public String Description;
    
    public Subjects(int Id, String Nm, String Des){
        this.SubjectID = Id;
        this.Name = Nm;
        this.Description = Des;        
    }
    
}
