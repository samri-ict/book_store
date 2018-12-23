
import LbryClasses.Authors;
import LbryClasses.Books;
import LbryClasses.Counter;
import LbryClasses.Members;
import LbryClasses.Subjects;
import LbryClasses.Users;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface lbrRMI extends Remote{
    
    public boolean AddUsers(Users newUser) throws RemoteException;
    public boolean AddMembers(Members newMember) throws RemoteException;    
    public boolean AddAuthors(Authors newAuthor) throws RemoteException;
    public boolean AddSubjects(Subjects newSubject) throws RemoteException;
    public boolean AddBooks(Books newBook) throws RemoteException;
    public boolean AddCounterActivity(Counter newCounterActivity) throws RemoteException;
    
    
    
    public ArrayList<Users> fetchAllUsers() throws RemoteException;
    public ArrayList<Members> fetchAllMembers() throws RemoteException;    
    public ArrayList<Authors> fetchAllAuthors() throws RemoteException;
    public ArrayList<Subjects> fetchAllSubjects() throws RemoteException;
    public ArrayList<Books> fetchAllBooks() throws RemoteException;
    public ArrayList<Counter> fetchAllCounterActivity() throws RemoteException;
    
    public Users fetchAUser(int id) throws RemoteException;
    public Members fetchAMember(int id) throws RemoteException;    
    public Authors fetchAnAuthor(int id) throws RemoteException;
    public Subjects fetchASubject(int id) throws RemoteException;
    public Books fetchABook(int id) throws RemoteException;
    public Counter fetchACounterActivity(int id) throws RemoteException;
    
    public Users getUser(String userName, String passWord) throws RemoteException;
        
}
