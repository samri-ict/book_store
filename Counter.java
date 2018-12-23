
package LbryClasses;

import java.io.Serializable;
import java.util.Date;


public class Counter implements Serializable{
    public int activityID;
    public Users user;
    public Members member;
    public Books book;
    public ActivityType activity;
    public Date activityDate;
    
    public Counter(int id, Users us, Members mb, Books bk, ActivityType actT,
            Date aDt){
        this.activityID = id;
        this.user = us;
        this.member = mb;
        this.book = bk;
        this.activity = actT;
        this.activityDate = aDt;        
    }
    
    
}
