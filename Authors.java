
package LbryClasses;

import java.io.Serializable;


public class Authors implements Serializable{
    public int AuthorID;
    public String FirstName;
    public String MiddelName;
    public String LastName;
    public Gender Gender;
    public String Nationality;
    
    public Authors(int Id, String Fn, String Mn, String Ln, Gender Gm, String Nt){
        this.AuthorID = Id;
        this.FirstName = Fn;
        this.MiddelName = Mn;
        this.LastName = Ln;
        this.Gender = Gm;
        this.Nationality = Nt;
    }
    
    
    //public Authors getAutor(int Id);
    
       
    
}
