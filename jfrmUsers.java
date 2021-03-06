
import LbryClasses.Gender;
import LbryClasses.Members;
import LbryClasses.Users;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Arrays;


public class jfrmUsers extends javax.swing.JFrame {

    DefaultTableModel dtUsers;
    DefaultTableModel dtMembers;
    
    int userID = 0;
    
    lbrRMI _rmi;
    Registry reg;
    
    /**
     * Creates new form jfrmUsers
     */
    public jfrmUsers() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        dtUsers = new DefaultTableModel();
        this.dtUsers.addColumn("userID");
        this.dtUsers.addColumn("User Name");
        this.dtUsers.addColumn("PassWord");
        this.dtUsers.addColumn("Member Name");
        this.dtUsers.addColumn("Active");
        this.dtUsers.addColumn("Create User");
        this.dtUsers.addColumn("Create Member");
        this.dtUsers.addColumn("Register Author");
        this.dtUsers.addColumn("Maintain Subject");
        this.dtUsers.addColumn("Register Book");
        this.dtUsers.addColumn("Reserve Book");
        this.dtUsers.addColumn("CheckIn Book");
        this.dtUsers.addColumn("CheckOut Book");
        
       this.jtblUSers.setModel(dtUsers);
       
       this.jtblUSers.getColumnModel().getColumn(0).setWidth(0);
       this.jtblUSers.getColumnModel().getColumn(0).setMinWidth(0);
       this.jtblUSers.getColumnModel().getColumn(0).setMaxWidth(0);
       
       this.jtblUSers.getColumnModel().getColumn(2).setWidth(0);
       this.jtblUSers.getColumnModel().getColumn(2).setMinWidth(0);
       this.jtblUSers.getColumnModel().getColumn(2).setMaxWidth(0);
       
        try {
            reg=LocateRegistry.getRegistry("127.0.0.1", 1099);
            _rmi=(lbrRMI) reg.lookup("myBind");
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dtMembers = new DefaultTableModel();
        this.dtMembers.getDataVector().removeAllElements();
        ArrayList<Members> allMembers;
        Object[] ob;
        
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
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String mbrName;
        for (int i = 0; i <= (dtMembers.getRowCount()-1); i++) {            
            mbrName = dtMembers.getValueAt(i, 1).toString()+ " " +
                    dtMembers.getValueAt(i, 2).toString()+ " " +
                    dtMembers.getValueAt(i, 3).toString();
                    
            this.jcbxMember.addItem(mbrName);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jckActive = new javax.swing.JCheckBox();
        jckCreateUser = new javax.swing.JCheckBox();
        jckCreateMember = new javax.swing.JCheckBox();
        jckCreateAuthor = new javax.swing.JCheckBox();
        jckCreateSubject = new javax.swing.JCheckBox();
        jckCreateBook = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtUserName = new javax.swing.JTextField();
        jPfPassWord = new javax.swing.JPasswordField();
        jcbxMember = new javax.swing.JComboBox<>();
        jckCheckIn = new javax.swing.JCheckBox();
        jckCheckOut = new javax.swing.JCheckBox();
        jbtnSave = new javax.swing.JButton();
        jbtnSearch = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jckReserveBook = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUSers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jckActive.setText("Active");

        jckCreateUser.setText("Create User");

        jckCreateMember.setText("Create Member");

        jckCreateAuthor.setText("Create Author");

        jckCreateSubject.setText("Create Subject");

        jckCreateBook.setText("Create Book");

        jLabel1.setText("User Name");

        jLabel2.setText("Pass Word");

        jLabel3.setText("Member");

        jcbxMember.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select One --" }));

        jckCheckIn.setText("Check In Book");

        jckCheckOut.setText("Check Out Book");

        jbtnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnSearch.setText("Search");
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        jbtnClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        jckReserveBook.setText("Reserve Book");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPfPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxMember, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jckCreateMember, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckCreateAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckCreateSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckCreateBook, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jbtnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addComponent(jckActive, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jckReserveBook, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jckCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jckCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPfPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jckActive)
                .addGap(2, 2, 2)
                .addComponent(jckCreateUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckCreateMember)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckCreateAuthor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckCreateSubject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckCreateBook)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckReserveBook)
                    .addComponent(jckCheckOut)
                    .addComponent(jckCheckIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("System Users Management");

        jPanel1.setAutoscrolls(true);

        jtblUSers.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblUSers.setRowHeight(20);
        jtblUSers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblUSersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblUSers);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        
        if("".equals(this.jtxtUserName.getText()) ||
                "".equals(Arrays.toString(this.jPfPassWord.getPassword())))
        {
            JOptionPane.showMessageDialog(this, "Please Insert User Name and Password before you proceed.");
            return;
        } 
        
        Members mbr;
        if (this.jcbxMember.getSelectedIndex() != 0) {
            int selectedMbrIndex = this.jcbxMember.getSelectedIndex()-1;
            mbr=new Members(Integer.valueOf(dtMembers.getValueAt(selectedMbrIndex,0).toString()), 
                            dtMembers.getValueAt(selectedMbrIndex,1).toString(), 
                            dtMembers.getValueAt(selectedMbrIndex,2).toString(), 
                            dtMembers.getValueAt(selectedMbrIndex,3).toString(), 
                            Gender.getValueOf(dtMembers.getValueAt(selectedMbrIndex,4).toString()), 
                            Date.valueOf(dtMembers.getValueAt(selectedMbrIndex,5).toString()));
            
        } else {
            mbr = null;
        }
        
        
        Users user;
        user =new Users(this.userID, this.jtxtUserName.getText(), this.jPfPassWord.getText(),
                mbr,
                this.jckActive.isSelected(), this.jckCreateUser.isSelected(),
                this.jckCreateMember.isSelected(), this.jckCreateAuthor.isSelected(),
                this.jckCreateSubject.isSelected(), this.jckCreateBook.isSelected(),
                this.jckReserveBook.isSelected(), this.jckCheckIn.isSelected(),
                this.jckCheckOut.isSelected());
        
        try {
            boolean recordSaved = _rmi.AddUsers(user);
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
        this.jbtnClearActionPerformed(evt);
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
        
        this.dtUsers.getDataVector().removeAllElements();
        ArrayList<Users> allUsers;
        Object[] ob;
        
        try {
            allUsers =  _rmi.fetchAllUsers();
            for (int i = 0; i < allUsers.size(); i++) {
                Users usr = allUsers.get(i);
                
                ob=new Object[13];
                ob[0] = usr.UserId;
                ob[1] = usr.UserName;
                ob[2] = usr.Password;
                if (usr.Member != null) {
                  ob[3] = usr.Member.FirstName + " " + usr.Member.MiddelName + 
                        " " + usr.Member.LastName;
                }
                else{
                    ob[3] = "";                
                }
                    
                
                ob[4] = String.valueOf(usr.IsActive);
                ob[5] = String.valueOf(usr.CanCreateUser);
                ob[6] = String.valueOf(usr.CanRegisterMember);
                ob[7] = String.valueOf(usr.CanRegisterAuthor);
                ob[8] = String.valueOf(usr.CanRegistersubject);
                ob[9] = String.valueOf(usr.CanRegisterBook);
                ob[10] = String.valueOf(usr.CanReserveBook);
                ob[11] = String.valueOf(usr.CanCheckInBook);
                ob[12] = String.valueOf(usr.CanCheckOutBook);
                
                this.dtUsers.addRow(ob);
                
                System.out.println(usr.Member);
            }
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(jfrmMembers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        
        this.userID = 0;
        this.jtxtUserName.setText("");
        this.jPfPassWord.setText("");
        this.jcbxMember.setSelectedIndex(0);
        
        this.jckActive.setSelected(true);
        this.jckCreateUser.setSelected(false);
        this.jckCreateMember.setSelected(false);
        this.jckCreateAuthor.setSelected(false);
        this.jckCreateSubject.setSelected(false);
        this.jckCreateBook.setSelected(false);
        this.jckReserveBook.setSelected(false);
        this.jckCheckIn.setSelected(false);
        this.jckCheckOut.setSelected(false);
        
    }//GEN-LAST:event_jbtnClearActionPerformed

    private void jtblUSersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblUSersMouseClicked
        
        int selectedRowIndex = this.jtblUSers.getSelectedRow();
        
        if(selectedRowIndex == -1){
            return;
        }
        
        this.userID = (int) this.jtblUSers.getValueAt(selectedRowIndex, 0);
        this.jtxtUserName.setText(this.jtblUSers.getValueAt(selectedRowIndex, 1).toString());
        this.jPfPassWord.setText(this.jtblUSers.getValueAt(selectedRowIndex, 2).toString());
        if (!"".equals(this.jtblUSers.getValueAt(selectedRowIndex, 3).toString())) {
            this.jcbxMember.setSelectedItem(this.jtblUSers.getValueAt(selectedRowIndex, 3).toString());
        } else {
            this.jcbxMember.setSelectedIndex(0);
        }
        
        
        
        this.jckActive.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 4).toString()));
        this.jckCreateUser.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 5).toString()));
        this.jckCreateMember.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 6).toString()));
        this.jckCreateAuthor.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 7).toString()));
        this.jckCreateSubject.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 8).toString()));
        this.jckCreateBook.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 9).toString()));
        this.jckReserveBook.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 10).toString()));
        this.jckCheckIn.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 11).toString()));
        this.jckCheckOut.setSelected(Boolean.valueOf(this.jtblUSers.getValueAt(selectedRowIndex, 12).toString()));
        
        
    }//GEN-LAST:event_jtblUSersMouseClicked

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
            java.util.logging.Logger.getLogger(jfrmUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPfPassWord;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcbxMember;
    private javax.swing.JCheckBox jckActive;
    private javax.swing.JCheckBox jckCheckIn;
    private javax.swing.JCheckBox jckCheckOut;
    private javax.swing.JCheckBox jckCreateAuthor;
    private javax.swing.JCheckBox jckCreateBook;
    private javax.swing.JCheckBox jckCreateMember;
    private javax.swing.JCheckBox jckCreateSubject;
    private javax.swing.JCheckBox jckCreateUser;
    private javax.swing.JCheckBox jckReserveBook;
    private javax.swing.JTable jtblUSers;
    private javax.swing.JTextField jtxtUserName;
    // End of variables declaration//GEN-END:variables
}
