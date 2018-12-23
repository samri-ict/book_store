
import LbryClasses.ActivityType;
import LbryClasses.Authors;
import LbryClasses.Books;
import LbryClasses.Counter;
import LbryClasses.Gender;
import LbryClasses.Members;
import LbryClasses.ShelfStatus;
import LbryClasses.Subjects;
import LbryClasses.Users;
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

public class jfrmCounter extends javax.swing.JFrame {

    DefaultTableModel dtCounterActivity;
    DefaultTableModel dtBooks;
    DefaultTableModel dtMembers;     
     
     
     int activityID = 0;
     public int userID = 1;
     
     Users user;
     
     lbrRMI _rmi;
     
     Registry reg;
    

    /**
     * Creates new form jfrmCounter
     */
    public jfrmCounter() {
        initComponents();
        
        
        this.setLocationRelativeTo(null);
        
        this.dtCounterActivity = new DefaultTableModel();
        this.dtCounterActivity.addColumn("activityID");
        this.dtCounterActivity.addColumn("userID");
        this.dtCounterActivity.addColumn("Member");
        this.dtCounterActivity.addColumn("Book");
        this.dtCounterActivity.addColumn("Counter");
        this.dtCounterActivity.addColumn("Counter Date");
        
       this.jtblActivities.setModel(dtCounterActivity);
       
       this.jtblActivities.getColumnModel().getColumn(0).setWidth(0);
       this.jtblActivities.getColumnModel().getColumn(0).setMinWidth(0);
       this.jtblActivities.getColumnModel().getColumn(0).setMaxWidth(0);
       
       this.jtblActivities.getColumnModel().getColumn(1).setWidth(0);
       this.jtblActivities.getColumnModel().getColumn(1).setMinWidth(0);
       this.jtblActivities.getColumnModel().getColumn(1).setMaxWidth(0);
              
        try {
            reg=LocateRegistry.getRegistry("127.0.0.1", 1099);
            _rmi=(lbrRMI) reg.lookup("myBind");
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        this.dtBooks = new DefaultTableModel();
        this.dtBooks.getDataVector().removeAllElements();
        ArrayList<Books> allBooks;
        Object[] ob;
                
        this.dtBooks.addColumn("bookID");
        this.dtBooks.addColumn("Titel");
        this.dtBooks.addColumn("Author");
        this.dtBooks.addColumn("Subject");
        this.dtBooks.addColumn("Year Published");
        this.dtBooks.addColumn("ISBN");
        this.dtBooks.addColumn("Status");
        
        try {
            allBooks =  _rmi.fetchAllBooks();
            for (int i = 0; i < allBooks.size(); i++) {
                Books bk = allBooks.get(i);
                
                ob=new Object[7];
                ob[0] = bk.BookId;
                ob[1] = bk.Titel;
                if (bk.Author != null) {
                    ob[2] = bk.Author.AuthorID;
                } else {
                    ob[2] = "";
                }
                
                if (bk.Subject != null) {
                    ob[3] = bk.Subject.SubjectID;
                } else {
                    ob[3] = "";
                }
                
                ob[4] = bk.published;
                ob[5] = bk.ISBN;
                ob[6] = bk.shelfStat;
                dtBooks.addRow(ob);
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        for (int i = 0; i <= (this.dtBooks.getRowCount()-1); i++) {            
                                
            this.jcbxBooks.addItem(this.dtBooks.getValueAt(i, 1).toString());
        }
               
        
        
        this.dtMembers = new DefaultTableModel();
        this.dtMembers.getDataVector().removeAllElements();
        ArrayList<Members> allMembers;
                
        dtMembers.addColumn("memberID");
        dtMembers.addColumn("First Name");
        dtMembers.addColumn("Middel Name");
        dtMembers.addColumn("Last Name");
        dtMembers.addColumn("Gender");
        dtMembers.addColumn("Date Join");
        
        try {
            allMembers =  _rmi.fetchAllMembers();
            for (int i = 0; i < allMembers.size(); i++) {
                Members mbr = allMembers.get(i);
                
                ob=new Object[6];
                ob[0] = mbr.MemberId;
                ob[1] = mbr.FirstName;
                ob[2] = mbr.MiddelName;
                ob[3] = mbr.LastName;
                ob[4] = mbr.Gender;
                ob[5] = mbr.DateSubscribed;
                dtMembers.addRow(ob);
            }
            
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String mbrName;
        for (int i = 0; i <= (dtMembers.getRowCount()-1); i++) {            
            mbrName = dtMembers.getValueAt(i, 1).toString()+ " " +
                    dtMembers.getValueAt(i, 2).toString()+ " " +
                    dtMembers.getValueAt(i, 3).toString();
                    
            this.jcbxMembers.addItem(mbrName);
        }
        
        this.user = null;
        
        try {
                this.user = _rmi.fetchAUser(this.userID);            
            } catch (RemoteException ex) {
                Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        Calendar cal = Calendar.getInstance(); 
        this.jdtPActivityDate.setDate(cal.getTime());
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblActivities = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbxMembers = new javax.swing.JComboBox<>();
        jbtnSave = new javax.swing.JButton();
        jbtnSearch = new javax.swing.JButton();
        jbtnClearFrame = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jdtPActivityDate = new org.jdesktop.swingx.JXDatePicker();
        jcbxBooks = new javax.swing.JComboBox<>();
        jcbxActivity = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtblActivities.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblActivities.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblActivities.setRowHeight(20);
        jtblActivities.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblActivitiesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblActivities);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Member");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Book");

        jcbxMembers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Others -" }));

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
        jLabel5.setText("Date");

        jcbxBooks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Others -" }));
        jcbxBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxBooksActionPerformed(evt);
            }
        });

        jcbxActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select One --" }));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Action");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbxMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbxBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdtPActivityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClearFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdtPActivityDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxActivity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jbtnClearFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Library Counter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblActivitiesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblActivitiesMouseClicked
        int selectedRowIndex = this.jtblActivities.getSelectedRow();

        if(selectedRowIndex == -1){
            return;
        }

        this.activityID = (int) this.jtblActivities.getValueAt(selectedRowIndex, 0);
        //this.userID = (int) this.jtblActivities.getValueAt(selectedRowIndex, 1);

        if (!"".equals(this.jtblActivities.getValueAt(selectedRowIndex, 2).toString())) {
            this.jcbxMembers.setSelectedItem(this.jtblActivities.getValueAt(selectedRowIndex, 2).toString());
        } else {
            this.jcbxMembers.setSelectedIndex(0);
        }

        if (!"".equals(this.jtblActivities.getValueAt(selectedRowIndex, 3).toString())) {
            this.jcbxBooks.setSelectedItem(this.jtblActivities.getValueAt(selectedRowIndex, 3).toString());
        } else {
            this.jcbxBooks.setSelectedIndex(0);
        }
        
        this.jcbxActivity.setSelectedItem(this.jtblActivities.getValueAt(selectedRowIndex, 4).toString());
        this.jdtPActivityDate.setDate(Date.valueOf(this.jtblActivities.getValueAt(selectedRowIndex, 5).toString()));
    }//GEN-LAST:event_jtblActivitiesMouseClicked

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed

        if(this.jcbxBooks.getSelectedIndex() <= 0 ||
           this.jcbxMembers.getSelectedIndex() <= 0 ||
           this.jcbxActivity.getSelectedIndex() <= 0)
        {
            JOptionPane.showMessageDialog(this, "Please Select Book, Member and Action to take before you proceed.");
            return;
        }

        Members mbr;
        if (this.jcbxMembers.getSelectedIndex() != 0) {
            int selectedMbrIndex = this.jcbxMembers.getSelectedIndex()-1;
            mbr=new Members(Integer.valueOf(dtMembers.getValueAt(selectedMbrIndex,0).toString()), 
                            dtMembers.getValueAt(selectedMbrIndex,1).toString(), 
                            dtMembers.getValueAt(selectedMbrIndex,2).toString(), 
                            dtMembers.getValueAt(selectedMbrIndex,3).toString(), 
                            Gender.getValueOf(dtMembers.getValueAt(selectedMbrIndex,4).toString()), 
                            Date.valueOf(dtMembers.getValueAt(selectedMbrIndex,5).toString()));
            
        } else {
            mbr = null;
        }
        
        Authors autr;
        autr = null;
        
        
        Subjects sbj;
        sbj = null;

        Books bk;
        int selectedBookIndex = 0;
        
        if (this.jcbxBooks.getSelectedIndex() != 0) {
            
            selectedBookIndex = this.jcbxBooks.getSelectedIndex()-1;
            
            int authrID = 0;
            if(!"".equals(this.dtBooks.getValueAt(selectedBookIndex,2).toString())){
                authrID = Integer.valueOf(this.dtBooks.getValueAt(selectedBookIndex,2).toString());
            }
             
            try {
                    autr = 
                            _rmi.fetchAnAuthor(Integer.valueOf(authrID));  
                    
            } catch (RemoteException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int subjID = 0;
            if(!"".equals(this.dtBooks.getValueAt(selectedBookIndex,3).toString())){
                subjID = Integer.valueOf(this.dtBooks.getValueAt(selectedBookIndex,3).toString());
            }
        
            try {
                sbj = _rmi.fetchASubject(subjID);            
            } catch (RemoteException ex) {
                Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            bk=new Books(Integer.valueOf(this.dtBooks.getValueAt(selectedBookIndex,0).toString()),
                      this.dtBooks.getValueAt(selectedBookIndex,1).toString(),
                      autr,
                      sbj,
                      Date.valueOf(this.dtBooks.getValueAt(selectedBookIndex,4).toString()),
                    this.dtBooks.getValueAt(selectedBookIndex,5).toString(),
                    ShelfStatus.getValueOf(this.dtBooks.getValueAt(selectedBookIndex,6).toString())
            );

        } else {
            bk = null;
        }

        Counter cntr;
        cntr = new Counter(this.activityID, this.user, mbr, bk,
                            ActivityType.getValueOf(this.jcbxActivity.getSelectedItem().toString()),
                            this.jdtPActivityDate.getDate()
                    );
        
        try {
            boolean recordSaved = _rmi.AddCounterActivity(cntr);
            if(recordSaved){
                JOptionPane.showMessageDialog(this, "Record Saved Successfully");
                this.jbtnSearchActionPerformed(evt);
            }
            else{
                JOptionPane.showMessageDialog(this, "Sorry, Unable to save Record");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dtBooks.setValueAt(ActivityType.getValueOf(this.jcbxActivity.getSelectedItem().toString()).getShelfStatus(),
                selectedBookIndex, 6);
        this.jbtnClearFrameActionPerformed(evt);
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed

        this.dtCounterActivity.getDataVector().removeAllElements();
        ArrayList<Counter> allCounterActivitys;
        Object[] ob;

        try {
            allCounterActivitys =  _rmi.fetchAllCounterActivity();
            for (int i = 0; i < allCounterActivitys.size(); i++) {
                Counter cnt = allCounterActivitys.get(i);

                ob=new Object[6];
                ob[0] = cnt.activityID;
                //ob[1] = cnt.user.UserId;
                if (cnt.member != null) {
                    ob[2] = cnt.member.FirstName + " " +
                    cnt.member.MiddelName + " "+
                    cnt.member.LastName;

                } else {
                    ob[2] = "";
                }

                if (cnt.book != null) {
                    ob[3] = cnt.book.Titel;

                } else {
                    ob[3] = "";
                }

                ob[4] = cnt.activity;
                ob[5] = cnt.activityDate;
                this.dtCounterActivity.addRow(ob);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(jfrmCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnClearFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearFrameActionPerformed

        this.activityID = 0;
        this.jcbxMembers.setSelectedIndex(0);
        this.jcbxBooks.setSelectedIndex(0);
        this.jcbxActivity.setSelectedIndex(0);

        Calendar cal = Calendar.getInstance();
        this.jdtPActivityDate.setDate(cal.getTime());
    }//GEN-LAST:event_jbtnClearFrameActionPerformed

    private void jcbxBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxBooksActionPerformed
        
        this.jcbxActivity.removeAllItems();
        this.jcbxActivity.addItem("-- Select One --");
        
        if (this.jcbxBooks.getSelectedIndex() != 0) {
            
            int selectedBookIndex = this.jcbxBooks.getSelectedIndex()-1;
            
            String[] actions = 
                    ShelfStatus.getValueOf(this.dtBooks.getValueAt(selectedBookIndex,6).toString()).getPossibleActivity();
            
            for (int i = 0; i < actions.length; i++) {
                this.jcbxActivity.addItem(actions[i]);                
            }
            
        }
        
    }//GEN-LAST:event_jcbxBooksActionPerformed

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
            java.util.logging.Logger.getLogger(jfrmCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmCounter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnClearFrame;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcbxActivity;
    private javax.swing.JComboBox<String> jcbxBooks;
    private javax.swing.JComboBox<String> jcbxMembers;
    private org.jdesktop.swingx.JXDatePicker jdtPActivityDate;
    private javax.swing.JTable jtblActivities;
    // End of variables declaration//GEN-END:variables
}
