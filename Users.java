
package LbryClasses;

import java.io.Serializable;



public class Users  implements Serializable{
    
     public int UserId;
     public String UserName;
     public String Password;
     public Members Member;
     public boolean IsActive;
     public boolean CanCreateUser;
     public boolean CanRegisterMember;
     public boolean CanRegisterAuthor;
     public boolean CanRegistersubject;
     public boolean CanRegisterBook;
     public boolean CanReserveBook;
     public boolean CanCheckInBook;
     public boolean CanCheckOutBook;
     
     public Users(int Id, String Un, String Pass, Members Mbr, boolean Active, boolean createUser,
                   boolean regMember, boolean regAuthor, boolean regSubject,
                   boolean regBook, boolean resvBook, boolean ckIn, boolean ckOut){
         this.UserId = Id;
         this.UserName = Un;
         this.Password = Pass;
         this.Member = Mbr;
         this.IsActive = Active;
         this.CanCreateUser = createUser;
         this.CanRegisterMember = regMember;
         this.CanRegisterAuthor = regAuthor;
         this.CanRegistersubject = regSubject;
         this.CanRegisterBook = regBook;
         this.CanReserveBook = resvBook;
         this.CanCheckInBook = ckIn;
         this.CanCheckOutBook = ckOut;

     }
    
    
}
