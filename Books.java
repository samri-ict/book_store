
package LbryClasses;

import java.io.Serializable;
import java.util.Date;


public class Books implements Serializable{
    public int BookId;
    public String Titel;
    public Authors Author;
    public Subjects Subject;
    public Date published;
    public String ISBN;
    public ShelfStatus shelfStat;
    
    public Books(int id, String tl, Authors atr, Subjects sbj, Date pbls,
            String _ISBN, ShelfStatus sStat){
        this.BookId = id;
        this.Titel = tl;
        this.Author = atr;
        this.Subject = sbj;
        this.published = pbls;
        this.ISBN = _ISBN;
        this.shelfStat = sStat;
    }
    
    
    
}
