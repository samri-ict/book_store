
import LbryClasses.ActivityType;
import LbryClasses.Authors;
import LbryClasses.Books;
import LbryClasses.Counter;
import LbryClasses.Gender;
import LbryClasses.Members;
import LbryClasses.ShelfStatus;
import LbryClasses.Subjects;
import LbryClasses.Users;
import com.sun.rowset.CachedRowSetImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import sun.security.rsa.RSACore;


public class server extends UnicastRemoteObject implements lbrRMI {

     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     
    public server() throws RemoteException{
        super();
    }
    
    @Override
    public boolean AddUsers(LbryClasses.Users newUser) throws RemoteException {
        try {
           
            String newUserModCmd;
            String memberID = "NULL";
                    if(newUser.Member != null) {
                       memberID =  String.valueOf(newUser.Member.MemberId);
                    }
                    
                            
            if (newUser.UserId == 0) {
                newUserModCmd = 
                    "INSERT INTO users (USERNAME, PASSWORD, " +
                    "MEMBER_ID, ISACTIVE, CANCREATEUSER, CANREGISTERMEMBER, " +
                    "CANREGISTERAUTHOR, CANREGISTERSUBJECT, CANREGISTERBOOK, "+
                    "CANRESERVEBOOK, CANCHECKINBOOK, CANCHECKOUTBOOK) "+ 
                    "VALUES ('" +  
                        newUser.UserName + "','" +
                        newUser.Password + "'," + 
                        memberID + ",'" +
                        (newUser.IsActive ? "Y":"N") + "','" +
                        (newUser.CanCreateUser ? "Y":"N") + "','" +
                        (newUser.CanRegisterMember ? "Y":"N") + "','" +
                        (newUser.CanRegisterAuthor ? "Y":"N") + "','" +
                        (newUser.CanRegistersubject ? "Y":"N") + "','" +
                        (newUser.CanRegisterBook ? "Y":"N") + "','" +
                        (newUser.CanReserveBook ? "Y":"N") + "','" +
                        (newUser.CanCheckInBook ? "Y":"N") + "','" +
                        (newUser.CanCheckOutBook ? "Y":"N") + "'" +                    
                    ")";
                
            } else {
                newUserModCmd = 
                    "UPDATE users " +
                    "SET USERNAME = '"+ newUser.UserName +"', " +
                       "PASSWORD = '"+ newUser.Password +"', " +
                       "MEMBER_ID = "+ memberID +", " +
                       "ISACTIVE = '"+ (newUser.IsActive ? "Y":"N") +"', " +
                       "CANCREATEUSER = '"+ (newUser.CanCreateUser ? "Y":"N") +"', " +
                       "CANREGISTERMEMBER = '"+ (newUser.CanRegisterMember ? "Y":"N") +"', " +
                       "CANREGISTERAUTHOR = '"+ (newUser.CanRegisterAuthor ? "Y":"N") +"', " +
                       "CANREGISTERSUBJECT = '"+ (newUser.CanRegistersubject ? "Y":"N") +"', " +
                       "CANREGISTERBOOK = '"+ (newUser.CanRegisterBook ? "Y":"N") +"', " +
                       "CANRESERVEBOOK = '"+ (newUser.CanReserveBook ? "Y":"N") +"', " +
                       "CANCHECKINBOOK = '"+ (newUser.CanCheckInBook ? "Y":"N") +"', " +
                       "CANCHECKOUTBOOK = '"+ (newUser.CanCheckOutBook ? "Y":"N") +"' " +
                       "WHERE USER_ID = '" + String.valueOf(newUser.UserId) + "'";
            }
            
            return handleMyDB.updateDB(newUserModCmd);
            
        } catch (Exception e) {
            System.out.println("Error On Adding Users: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean AddMembers(Members newMember) throws RemoteException {
         try {
             String newMemberModCmd = "";
             
             if(newMember.MemberId == 0){
                newMemberModCmd = 
                    "INSERT INTO members (FIRSTNAME, MIDDELNAME, " +
                    "LASTNAME, GENDER, DATESUBCRIBED) "+ 
                    "VALUES ('" +  
                        newMember.FirstName + "','" +
                        newMember.MiddelName + "','" + 
                        newMember.LastName + "','" +
                        newMember.Gender.name() + "','" +                        
                        dateFormat.format(newMember.DateSubscribed) + "'" +
                    ")";
                 
             }
             else{
                 newMemberModCmd = 
                    "UPDATE members " +
                    "SET FIRSTNAME = '"+ newMember.FirstName + "', " +
                      "MIDDELNAME = '" + newMember.MiddelName + "', " +
                      "LASTNAME = '" + newMember.LastName + "', " +
                      "GENDER = '" + newMember.Gender.name() + "', " +
                      "DATESUBCRIBED = '" + dateFormat.format(newMember.DateSubscribed) + "' " +
                    "WHERE MEMBER_ID = " + String.valueOf(newMember.MemberId);                   
             }
           
            return handleMyDB.updateDB(newMemberModCmd);
            
        } catch (Exception e) {
            System.out.println("Error On Adding Members: "+e.getMessage());
            return false;
        }
    }

    
    @Override
    public boolean AddAuthors(Authors newAuthor) throws RemoteException {
        try {
            
            String newAuthorModCmd;
            
            if (newAuthor.AuthorID == 0) {
                
                newAuthorModCmd = 
                    "INSERT INTO authors (FIRSTNAME, MIDDELNAME, " +
                    "LASTNAME, GENDER, NATIONALITY) "+ 
                    "VALUES ('" +  
                        newAuthor.FirstName + "','" +
                        newAuthor.MiddelName + "','" + 
                        newAuthor.LastName + "','" +
                        newAuthor.Gender.toString() + "','" + 
                        newAuthor.Nationality + "'" + 
                    ")";
                
            } else {
                 newAuthorModCmd = 
                    "UPDATE authors " +
                    "SET FIRSTNAME = '"+ newAuthor.FirstName + "', " +
                      "MIDDELNAME = '" + newAuthor.MiddelName + "', " +
                      "LASTNAME = '" + newAuthor.LastName + "', " +
                      "GENDER = '" + newAuthor.Gender.toString()+ "', " +
                      "NATIONALITY = '" + newAuthor.Nationality + "' " +
                    "WHERE AUTHOR_ID = " + String.valueOf(newAuthor.AuthorID);
            }
            
            return handleMyDB.updateDB(newAuthorModCmd);
            
        } catch (Exception e) {
            System.out.println("Error On Adding Author: "+e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean AddSubjects(Subjects newSubject) throws RemoteException {
        try {
            String newSubjectModCmd;
            
            if (newSubject.SubjectID == 0) {
                
                newSubjectModCmd = 
                    "INSERT INTO subject (NAME, DESCRIPTION) "+ 
                    "VALUES ('" +  
                        newSubject.Name + "','" + 
                        newSubject.Description + "'" + 
                    ")";
                
            } else {
                 newSubjectModCmd = 
                    "UPDATE subject " +
                    "SET NAME = '"+ newSubject.Name + "', " +
                      "DESCRIPTION = '" + newSubject.Description + "' " +
                    "WHERE SUBJECT_ID = " + String.valueOf(newSubject.SubjectID);
            }
            
            return handleMyDB.updateDB(newSubjectModCmd);
            
        } catch (Exception e) {
            System.out.println("Error On Adding Subjects: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean AddBooks(Books newBook) throws RemoteException {
        try {
            
            String newBookModCmd;
            String authorID = "NULL";
            if(newBook.Author != null) {
               authorID =  String.valueOf(newBook.Author.AuthorID);
            }
                    
            String subjectID = "NULL";
            if(newBook.Subject != null) {
               subjectID =  String.valueOf(newBook.Subject.SubjectID);
            }
            
            if (newBook.BookId == 0) {
                
                newBookModCmd = 
                    "INSERT INTO books (TITLE, AUTHOR_ID, " +
                    "SUBJECT_ID, YEARPUBLISHED, ISBN, SHELFSTATUS) "+ 
                    "VALUES ('" +  
                        newBook.Titel + "'," +
                        authorID + "," + 
                        subjectID + ",'" +
                        dateFormat.format(newBook.published) + "','" + 
                        newBook.ISBN + "','" +  
                        newBook.shelfStat.toString() + "'" +
                    ")";
                
            } else {
                 newBookModCmd = 
                    "UPDATE books " +
                    "SET TITLE = '"+ newBook.Titel + "', " +
                      "AUTHOR_ID = " + authorID + ", " +
                      "SUBJECT_ID = " + subjectID + ", " +
                      "YEARPUBLISHED = '" + dateFormat.format(newBook.published) + "', " +
                      "ISBN = '" + newBook.ISBN + "', " +
                      "SHELFSTATUS = '" + newBook.shelfStat.toString() + "' " +
                    "WHERE BOOK_ID = " + String.valueOf(newBook.BookId);
            }
            
            return handleMyDB.updateDB(newBookModCmd);
                        
        } catch (Exception e) {
            System.out.println("Error On Adding Books: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean AddCounterActivity(Counter newCounterActivity) throws RemoteException {
        try {
            String newActivityModCmd;
            
            String userID = "NULL";
            if(newCounterActivity.user != null) {
               userID =  String.valueOf(newCounterActivity.user.UserId);
            }
            
            String memberID = "NULL";
            if(newCounterActivity.member != null) {
               memberID =  String.valueOf(newCounterActivity.member.MemberId);
            }
                    
            String bookID = "NULL";
            if(newCounterActivity.book != null) {
               bookID =  String.valueOf(newCounterActivity.book.BookId);
               newCounterActivity.book.shelfStat =
                       newCounterActivity.activity.getShelfStatus();
            }
            
            
            if (newCounterActivity.activityID == 0) {
                
                newActivityModCmd = 
                    "INSERT INTO counteractivity (USERID, MEMBERID, " +
                    "BOOKID, ACTIVITYTYPE, ACTIVITYDATE) "+ 
                    "VALUES (" +  
                        userID + "," +
                        memberID + "," + 
                        bookID + ",'" +
                        newCounterActivity.activity.toString() + "','" +
                        dateFormat.format(newCounterActivity.activityDate) + "'" +
                    ")";
                
            } else {
                 newActivityModCmd = 
                    "UPDATE counteractivity " +
                    "SET USERID = "+ userID + ", " +
                      "MEMBERID = " + memberID + ", " +
                      "BOOKID = " + bookID + ", " +
                      "ACTIVITYTYPE = '" + newCounterActivity.activity.toString() + "', " +
                      "ACTIVITYDATE = '" + dateFormat.format(newCounterActivity.activityDate) + "' " +
                    "WHERE ACTIVITYID = " + String.valueOf(newCounterActivity.activityID);
            }
            System.out.println(newActivityModCmd);
            boolean recordSaved = handleMyDB.updateDB(newActivityModCmd);
            
            if (recordSaved) {
                if(newCounterActivity.book != null) {
                    this.AddBooks(newCounterActivity.book);
                }
            }
            return recordSaved;
                        
        } catch (Exception e) {
            System.out.println("Error On Adding Counter Activity: "+e.getMessage());
            return false;
        }
    }

    
    
    
    @Override
    public ArrayList<LbryClasses.Users> fetchAllUsers() throws RemoteException {
        
        ArrayList<Users> userLst=new ArrayList<>();
        Users user;
        try {
            String getUserList = "SELECT * FROM users";
            CachedRowSetImpl userList;
            userList = handleMyDB.queryDB(getUserList);
            int memberID = 0;
            
            try {
                
                while (userList.next()) {
                    
                   memberID = userList.getInt("MEMBER_ID");
                    
                    user=new Users(userList.getInt("USER_ID"),
                                    userList.getString("USERNAME"),
                                    userList.getString("PASSWORD"),
                                    this.fetchAMember(memberID),                            
                                   ("Y".equals(userList.getString("ISACTIVE"))),
                                   ("Y".equals(userList.getString("CANCREATEUSER"))),
                                   ("Y".equals(userList.getString("CANREGISTERMEMBER"))),
                                   ("Y".equals(userList.getString("CANREGISTERAUTHOR"))),
                                   ("Y".equals(userList.getString("CANREGISTERSUBJECT"))),
                                   ("Y".equals(userList.getString("CANREGISTERBOOK"))),
                                   ("Y".equals(userList.getString("CANRESERVEBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKINBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKOUTBOOK")))
                            );
                    userLst.add(user);                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Users: " + e.getMessage());
            }
            
            return userLst;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllUsers()" + e.getMessage());
            return userLst;
        }
    }

    @Override
    public ArrayList<LbryClasses.Members> fetchAllMembers() throws RemoteException {
        
        ArrayList<Members> memberLst=new ArrayList<>();
        Members member;
        try {
            String getMemberList = "SELECT * FROM members";
            CachedRowSetImpl memberList;
            memberList = handleMyDB.queryDB(getMemberList);
            
            try {
                
                while (memberList.next()) {
                    member=new Members(memberList.getInt("MEMBER_ID"),
                                    memberList.getString("FIRSTNAME"),
                                    memberList.getString("MIDDELNAME"),
                                    memberList.getString("LASTNAME"),
                                   Gender.getValueOf(memberList.getString("GENDER")),
                                   Date.valueOf(memberList.getString("DATESUBCRIBED")));
                    memberLst.add(member);                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
            }
            
            return memberLst;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return memberLst;
        }        
    }

   
    @Override
    public ArrayList<Authors> fetchAllAuthors() throws RemoteException {
        
        ArrayList<Authors> authorLst=new ArrayList<>();
        Authors author;
        try {
            String getAuthorList = "SELECT * FROM authors";
            CachedRowSetImpl authorList;
            authorList = handleMyDB.queryDB(getAuthorList);
            
            try {
                
                while (authorList.next()) {
                    author= new Authors(authorList.getInt("AUTHOR_ID"),
                                authorList.getString("FIRSTNAME"),
                                authorList.getString("MIDDELNAME"),
                                authorList.getString("LASTNAME"),
                                Gender.getValueOf(authorList.getString("GENDER")),
                                authorList.getString("NATIONALITY"));

                    authorLst.add(author);                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
            }
            
            return authorLst;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return authorLst;
        }
    }

    @Override
    public ArrayList<Subjects> fetchAllSubjects() throws RemoteException {
        
        ArrayList<Subjects> subjectLst=new ArrayList<>();
        Subjects subject;
        try {
            String getSubjectList = "SELECT * FROM subject";
            CachedRowSetImpl subjectList;
            subjectList = handleMyDB.queryDB(getSubjectList);
            
            try {
                
                while (subjectList.next()) {
                    subject= new Subjects(subjectList.getInt("SUBJECT_ID"), 
                                          subjectList.getString("NAME"), 
                                          subjectList.getString("DESCRIPTION"));
                    
                    subjectLst.add(subject);               
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
            }
            
            return subjectLst;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return subjectLst;
        }
    }

    @Override
    public ArrayList<Books> fetchAllBooks() throws RemoteException {
        ArrayList<Books> bookLst=new ArrayList<>();
        Books book;
        try {
            String getBookList = "SELECT * FROM books";
            CachedRowSetImpl bookList;
            bookList = handleMyDB.queryDB(getBookList);
            int authorID = 0;
            int subjectID = 0;
            
            try {
                
                while (bookList.next()) {
                    
                    authorID = bookList.getInt("AUTHOR_ID");
                    subjectID = bookList.getInt("SUBJECT_ID");
                    
                    book= new Books(bookList.getInt("BOOK_ID"),
                                    bookList.getString("TITLE"),
                                    this.fetchAnAuthor(authorID),
                                    this.fetchASubject(subjectID),
                                    Date.valueOf(bookList.getString("YEARPUBLISHED")), 
                                    bookList.getString("ISBN"),
                                    ShelfStatus.getValueOf(bookList.getString("SHELFSTATUS")));

                    bookLst.add(book);                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
            }
            
            return bookLst;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return bookLst;
        }
    }

    @Override
    public ArrayList<Counter> fetchAllCounterActivity() throws RemoteException {
        
        ArrayList<Counter> counterList=new ArrayList<>();
        Counter counterObj;
        
        try {
            
            String getAllCounterActivity = "SELECT * FROM counteractivity";
            CachedRowSetImpl counterLst;
            counterLst = handleMyDB.queryDB(getAllCounterActivity);
            int userID = 0;
            int memberID = 0;
            int bookID = 0;
            
            while (counterLst.next()) {
                
                userID = counterLst.getInt("USERID");
                memberID = counterLst.getInt("MEMBERID");
                bookID = counterLst.getInt("BOOKID");
                    
                counterObj = new Counter(counterLst.getInt("ACTIVITYID"),
                                        this.fetchAUser(userID),
                                        this.fetchAMember(memberID),
                                        this.fetchABook(bookID),                        
                                        ActivityType.getValueOf(counterLst.getString("ACTIVITYTYPE")),
                                        counterLst.getDate("ACTIVITYDATE"));
                
                counterList.add(counterObj);
            }
            
            return counterList;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllCounterActivity()" + e.getMessage());
            return counterList;
        }
    }

    
    
    
    
    
    @Override
    public Users fetchAUser(int id) throws RemoteException {
        
       
        Users user;
        user =null;
        try {
            String getUserList = "SELECT * FROM users " +
                                 "WHERE USER_ID = "+ String.valueOf(id);
            CachedRowSetImpl userList;
            userList = handleMyDB.queryDB(getUserList);
            int memberID;
            
            try {
                
                while (userList.next()) {
                    
                   memberID = userList.getInt("MEMBER_ID");
                    
                    user=new Users(userList.getInt("USER_ID"),
                                    userList.getString("USERNAME"),
                                    userList.getString("PASSWORD"),
                                    this.fetchAMember(memberID),                            
                                   ("Y".equals(userList.getString("ISACTIVE"))),
                                   ("Y".equals(userList.getString("CANCREATEUSER"))),
                                   ("Y".equals(userList.getString("CANREGISTERMEMBER"))),
                                   ("Y".equals(userList.getString("CANREGISTERAUTHOR"))),
                                   ("Y".equals(userList.getString("CANREGISTERSUBJECT"))),
                                   ("Y".equals(userList.getString("CANREGISTERBOOK"))),
                                   ("Y".equals(userList.getString("CANRESERVEBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKINBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKOUTBOOK")))
                            ); 
                    return user;                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Users: " + e.getMessage());
                return user;
            }
            
            return user;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllUsers()" + e.getMessage());
            return user;
        }
    }

    @Override
    public Members fetchAMember(int id) throws RemoteException {
        
        
        Members member;
        member = null;
        
        if (id <= 0) {
            return member;
        }
        
        try {
            String getMemberList = "SELECT * FROM members " +
                                   "WHERE MEMBER_ID = " + String.valueOf(id);
            CachedRowSetImpl memberList;
            memberList = handleMyDB.queryDB(getMemberList);
            
            try {
                
                while (memberList.next()) {
                    member=new Members(memberList.getInt("MEMBER_ID"),
                                    memberList.getString("FIRSTNAME"),
                                    memberList.getString("MIDDELNAME"),
                                    memberList.getString("LASTNAME"),
                                   Gender.getValueOf(memberList.getString("GENDER")),
                                   Date.valueOf(memberList.getString("DATESUBCRIBED")));
                    return member;
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
            }
            
            return member;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return member;
        }        
    }

    @Override
    public Authors fetchAnAuthor(int id) throws RemoteException {
        
        Authors author;
        author=null;
        try {
            String getAuthorList = "SELECT * FROM authors " + 
                                   "WHERE AUTHOR_ID = " + String.valueOf(id);
            CachedRowSetImpl authorList;
            authorList = handleMyDB.queryDB(getAuthorList);
            
            try {
                
                while (authorList.next()) {
                    author= new Authors(authorList.getInt("AUTHOR_ID"),
                                authorList.getString("FIRSTNAME"),
                                authorList.getString("MIDDELNAME"),
                                authorList.getString("LASTNAME"),
                                Gender.getValueOf(authorList.getString("GENDER")),
                                authorList.getString("NATIONALITY"));

                    return author;                  
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
                return author;
            }
            
            return author;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return author;
        }
    }

    @Override
    public Subjects fetchASubject(int id) throws RemoteException {
        
        Subjects subject;
        subject = null;
        try {
            String getSubjectList = "SELECT * FROM subject " +
                                    "WHERE SUBJECT_ID = " + String.valueOf(id);
            CachedRowSetImpl subjectList;
            subjectList = handleMyDB.queryDB(getSubjectList);
            
            try {
                
                while (subjectList.next()) {
                    subject= new Subjects(subjectList.getInt("SUBJECT_ID"), 
                                          subjectList.getString("NAME"), 
                                          subjectList.getString("DESCRIPTION"));
                    
                    return subject;               
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
                return subject;
            }
            
            return subject;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return subject;
        }
    }

    @Override
    public Books fetchABook(int id) throws RemoteException {
        
        Books book;
        book = null;
        try {
            String getBookList = "SELECT * FROM books " +
                                 "WHERE BOOK_ID = " + String.valueOf(id);
            CachedRowSetImpl bookList;
            bookList = handleMyDB.queryDB(getBookList);
            int authorID = 0;
            int subjectID = 0;
            
            try {
                
                while (bookList.next()) {
                    
                    authorID = bookList.getInt("AUTHOR_ID");
                    subjectID = bookList.getInt("SUBJECT_ID");
                    
                    book= new Books(bookList.getInt("BOOK_ID"),
                                    bookList.getString("TITLE"),
                                    this.fetchAnAuthor(authorID),
                                    this.fetchASubject(subjectID),
                                    Date.valueOf(bookList.getString("YEARPUBLISHED")), 
                                    bookList.getString("ISBN"),
                                    ShelfStatus.getValueOf(bookList.getString("SHELFSTATUS")));

                    return book;                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Members: " + e.getMessage());
                return book;
            }
            
            return book;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllMembers()" + e.getMessage());
            return book;
        }
    }

    @Override
    public Counter fetchACounterActivity(int id) throws RemoteException {
        
        Counter counterObj;
        counterObj = null;
        
        try {
            
            String getAllCounterActivity = "SELECT * FROM counteractivity";
            CachedRowSetImpl counterLst;
            counterLst = handleMyDB.queryDB(getAllCounterActivity);
            int userID;
            int memberID;
            int bookID;
            
            while (counterLst.next()) {
                
                userID = counterLst.getInt("USERID");
                memberID = counterLst.getInt("MEMBERID");
                bookID = counterLst.getInt("BOOKID");
                    
                counterObj = new Counter(counterLst.getInt("ACTIVITYID"),
                                        this.fetchAUser(userID),
                                        this.fetchAMember(memberID),
                                        this.fetchABook(bookID),                        
                                        ActivityType.getValueOf(counterLst.getString("ACTIVITYTYPE")),
                                        counterLst.getDate("ACTIVITYDATE"));
                
                return counterObj;
            }
            
            return counterObj;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllCounterActivity()" + e.getMessage());
            return counterObj;
        }
    }
    
    @Override
    public Users getUser(String userName, String passWord) throws RemoteException {
        
        Users user;
        user =null;
        try {
            String getUserList = "SELECT * FROM users " +
                                 "WHERE USERNAME = '"+ userName +"' AND PASSWORD = '" + passWord + "'";
            CachedRowSetImpl userList;
            userList = handleMyDB.queryDB(getUserList);
            int memberID;
            
            try {
                
                while (userList.next()) {
                    
                   memberID = userList.getInt("MEMBER_ID");
                    
                    user=new Users(userList.getInt("USER_ID"),
                                    userList.getString("USERNAME"),
                                    userList.getString("PASSWORD"),
                                    this.fetchAMember(memberID),                            
                                   ("Y".equals(userList.getString("ISACTIVE"))),
                                   ("Y".equals(userList.getString("CANCREATEUSER"))),
                                   ("Y".equals(userList.getString("CANREGISTERMEMBER"))),
                                   ("Y".equals(userList.getString("CANREGISTERAUTHOR"))),
                                   ("Y".equals(userList.getString("CANREGISTERSUBJECT"))),
                                   ("Y".equals(userList.getString("CANREGISTERBOOK"))),
                                   ("Y".equals(userList.getString("CANRESERVEBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKINBOOK"))),
                                   ("Y".equals(userList.getString("CANCHECKOUTBOOK")))
                            ); 
                    return user;                    
                }                
            } catch (SQLException e) {
                System.out.println("Result Set Users: " + e.getMessage());
                return user;
            }
            
            return user;
            
        } catch (Exception e) {
            System.out.println("server.fetchAllUsers()" + e.getMessage());
            return user;
        }
    }

    
    
    public static void main(String[] args) {
        try {
            
            Registry reg=LocateRegistry.createRegistry(1099);
            reg.rebind("myBind", new server());
            
        } catch (Exception e) {
            System.out.println("Skeleton Failed: "+ e.getMessage());
        }
    }

    
    

}
