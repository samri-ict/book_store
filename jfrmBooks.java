
import LbryClasses.Authors;
import LbryClasses.Books;
import LbryClasses.Gender;
import LbryClasses.Members;
import LbryClasses.ShelfStatus;
import LbryClasses.Subjects;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class jfrmBooks extends javax.swing.JFrame {

     DefaultTableModel dtBooks;
     DefaultTableModel dtAuthors;     
     DefaultTableModel dtSubjects;
     
     int bookID = 0;
     
     lbrRMI _rmi;
     
     Registry reg;
    
    /**
     * Creates new form jfrmBooks
     */
    public jfrmBooks() {
        initComponents();
        
        
        this.setLocationRelativeTo(null);
        
        this.dtBooks = new DefaultTableModel();
        this.dtBooks.addColumn("bookID");
        this.dtBooks.addColumn("Titel");
        this.dtBooks.addColumn("Author");
        this.dtBooks.addColumn("Subject");
        this.dtBooks.addColumn("Year Published");
        this.dtBooks.addColumn("ISBN");
        this.dtBooks.addColumn("Status");
        
       this.jtblBooks.setModel(dtBooks);
       
       this.jtblBooks.getColumnModel().getColumn(0).setWidth(0);
       this.jtblBooks.getColumnModel().getColumn(0).setMinWidth(0);
       this.jtblBooks.getColumnModel().getColumn(0).setMaxWidth(0);
              
        try {
            reg=LocateRegistry.getRegistry("127.0.0.1", 1099);
            _rmi=(lbrRMI) reg.lookup("myBind");
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dtAuthors = new DefaultTableModel();
        this.dtAuthors.getDataVector().removeAllElements();
        ArrayList<Authors> allAuthors;
        Object[] ob;
        
        this.dtAuthors.addColumn("authorID");
        this.dtAuthors.addColumn("First Name");
        this.dtAuthors.addColumn("Middle Name");
        this.dtAuthors.addColumn("Last Name");
        this.dtAuthors.addColumn("Gender");
        this.dtAuthors.addColumn("Nationality");
        
        try {
            allAuthors =  _rmi.fetchAllAuthors();
            for (int i = 0; i < allAuthors.size(); i++) {
                Authors authr = allAuthors.get(i);
                
                ob=new Object[6];
                ob[0] = authr.AuthorID;
                ob[1] = authr.FirstName;
                ob[2] = authr.MiddelName;
                ob[3] = authr.LastName;
                ob[4] = authr.Gender;
                ob[5] = authr.Nationality;
                this.dtAuthors.addRow(ob);
            }
            
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String authrName;
        for (int i = 0; i <= (this.dtAuthors.getRowCount()-1); i++) {            
            authrName = this.dtAuthors.getValueAt(i, 1).toString()+ " " +
                    this.dtAuthors.getValueAt(i, 2).toString()+ " " +
                    this.dtAuthors.getValueAt(i, 3).toString();
                    
            this.jcbxAuthors.addItem(authrName);
        }
        
        
        
        
        this.dtSubjects = new DefaultTableModel();
        this.dtSubjects.getDataVector().removeAllElements();
        ArrayList<Subjects> allSubjects;
                
        this.dtSubjects.addColumn("subjectID");
        this.dtSubjects.addColumn("Name");
        this.dtSubjects.addColumn("Description");
        
        try {
            allSubjects =  _rmi.fetchAllSubjects();
            for (int i = 0; i < allSubjects.size(); i++) {
                Subjects subj = allSubjects.get(i);
                
                ob=new Object[3];
                ob[0] = subj.SubjectID;
                ob[1] = subj.Name;
                ob[2] = subj.Description;
                this.dtSubjects.addRow(ob);
            }
            
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i <= (this.dtSubjects.getRowCount()-1); i++) {            
           this.jcbxSubjects.addItem(this.dtSubjects.getValueAt(i, 1).toString());
        }
        
        
        Calendar cal = Calendar.getInstance(); 
        this.jdtPPublicationDate.setDate(cal.getTime());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtTitel = new javax.swing.JTextField();
        jtxtISBN = new javax.swing.JTextField();
        jcbxAuthors = new javax.swing.JComboBox<>();
        jbtnSave = new javax.swing.JButton();
        jbtnSearch = new javax.swing.JButton();
        jbtnClearFrame = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jdtPPublicationDate = new org.jdesktop.swingx.JXDatePicker();
        jcbxSubjects = new javax.swing.JComboBox<>();
        jcbxStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblBooks = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Book Information Entry Form");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Titel");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ISBN");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Author");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Subject");

        jcbxAuthors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Others -" }));

        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jbtnSearch.setText("Search");
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        jbtnClearFrame.setText("Clear Form");
        jbtnClearFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearFrameActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Publication Date");

        jcbxSubjects.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Others -" }));

        jcbxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CheckedIn", "Reserved", "CheckedOut" }));
        jcbxStatus.setEnabled(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Shelf Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdtPPublicationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(379, 379, 379))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnClearFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbxAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtTitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdtPPublicationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClearFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        jtblBooks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblBooks.setRowHeight(20);
        jtblBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblBooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblBooks);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 216, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblBooksMouseClicked
        int selectedRowIndex = this.jtblBooks.getSelectedRow();

        if(selectedRowIndex == -1){
            return;
        }

        this.bookID = (int) this.jtblBooks.getValueAt(selectedRowIndex, 0);
        this.jtxtTitel.setText(this.jtblBooks.getValueAt(selectedRowIndex, 1).toString());
        
        if (!"".equals(this.jtblBooks.getValueAt(selectedRowIndex, 2).toString())) {
            this.jcbxAuthors.setSelectedItem(this.jtblBooks.getValueAt(selectedRowIndex, 2).toString());
        } else {
            this.jcbxAuthors.setSelectedIndex(0);
        } 
        
        if (!"".equals(this.jtblBooks.getValueAt(selectedRowIndex, 3).toString())) {
            this.jcbxSubjects.setSelectedItem(this.jtblBooks.getValueAt(selectedRowIndex, 3).toString());
        } else {
            this.jcbxSubjects.setSelectedIndex(0);
        }
                 
        this.jdtPPublicationDate.setDate(Date.valueOf(this.jtblBooks.getValueAt(selectedRowIndex, 4).toString()));        
        this.jtxtISBN.setText(this.jtblBooks.getValueAt(selectedRowIndex, 5).toString());
        this.jcbxStatus.setSelectedItem(this.jtblBooks.getValueAt(selectedRowIndex, 6).toString());
        
    }//GEN-LAST:event_jtblBooksMouseClicked

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed

        if("".equals(this.jtxtTitel.getText()) ||
            ("".equals(this.jtxtISBN.getText())))
        {
            JOptionPane.showMessageDialog(this, "Please Insert Book Titel and ID before you proceed.");
            return;
        }
        
        if(this.jtxtISBN.getText().toCharArray().length != 13){
        
            JOptionPane.showMessageDialog(this, "ISBN should be exactly 13 Character wide.");
            return;
        }
        
        
        Authors authr;
        if (this.jcbxAuthors.getSelectedIndex() != 0) {
            int selectedAuthorIndex = this.jcbxAuthors.getSelectedIndex()-1;
            authr=new Authors(Integer.valueOf(this.dtAuthors.getValueAt(selectedAuthorIndex,0).toString()), 
                            this.dtAuthors.getValueAt(selectedAuthorIndex,1).toString(), 
                            this.dtAuthors.getValueAt(selectedAuthorIndex,2).toString(), 
                            this.dtAuthors.getValueAt(selectedAuthorIndex,3).toString(), 
                            Gender.getValueOf(this.dtAuthors.getValueAt(selectedAuthorIndex,4).toString()), 
                            this.dtAuthors.getValueAt(selectedAuthorIndex,5).toString());
            
        } else {
            authr = null;
        }
        
        
        
        Subjects subj;
        if (this.jcbxSubjects.getSelectedIndex() != 0) {
            int selectedSubjectIndex = this.jcbxSubjects.getSelectedIndex()-1;
            subj=new Subjects(Integer.valueOf(this.dtSubjects.getValueAt(selectedSubjectIndex,0).toString()), 
                            this.dtSubjects.getValueAt(selectedSubjectIndex,1).toString(), 
                            this.dtSubjects.getValueAt(selectedSubjectIndex,2).toString());
            
        } else {
            subj = null;
        }
        
        
        

        Books book;
        book = new Books(this.bookID, this.jtxtTitel.getText(), authr, subj,
                         this.jdtPPublicationDate.getDate(),
                         this.jtxtISBN.getText(),
                         ShelfStatus.getValueOf(this.jcbxStatus.getSelectedItem().toString()));
        try {
            boolean recordSaved = _rmi.AddBooks(book);
            if(recordSaved){
                JOptionPane.showMessageDialog(this, "Record Saved Successfully");
                this.jbtnSearchActionPerformed(evt);
            }
            else{
                JOptionPane.showMessageDialog(this, "Sorry, Unable to save Record");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jbtnClearFrameActionPerformed(evt);
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed

        this.dtBooks.getDataVector().removeAllElements();
        ArrayList<Books> allBooks;
        Object[] ob;

        try {
            allBooks =  _rmi.fetchAllBooks();
            for (int i = 0; i < allBooks.size(); i++) {
                Books bk = allBooks.get(i);

                ob=new Object[7];
                ob[0] = bk.BookId;
                ob[1] = bk.Titel;
                if (bk.Author != null) {
                    ob[2] = bk.Author.FirstName + " " +
                             bk.Author.MiddelName + " "+
                             bk.Author.LastName;
                    
                } else {
                    ob[2] = "";
                }
                
                if (bk.Subject != null) {
                    ob[3] = bk.Subject.Name;
                    
                } else {
                    ob[3] = "";
                }                
                
                ob[4] = bk.published;
                ob[5] = bk.ISBN;
                ob[6] = bk.shelfStat;
                dtBooks.addRow(ob);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnClearFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearFrameActionPerformed

        this.bookID = 0;
        this.jtxtTitel.setText("");
        this.jtxtISBN.setText("");
        this.jcbxAuthors.setSelectedIndex(0);
        this.jcbxSubjects.setSelectedIndex(0);
        this.jcbxStatus.setSelectedIndex(0);

        Calendar cal = Calendar.getInstance();
        this.jdtPPublicationDate.setDate(cal.getTime());
    }//GEN-LAST:event_jbtnClearFrameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jfrmBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnClearFrame;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcbxAuthors;
    private javax.swing.JComboBox<String> jcbxStatus;
    private javax.swing.JComboBox<String> jcbxSubjects;
    private org.jdesktop.swingx.JXDatePicker jdtPPublicationDate;
    private javax.swing.JTable jtblBooks;
    private javax.swing.JTextField jtxtISBN;
    private javax.swing.JTextField jtxtTitel;
    // End of variables declaration//GEN-END:variables
}
