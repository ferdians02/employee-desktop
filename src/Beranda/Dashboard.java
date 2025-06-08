package Beranda;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import javax.swing.SwingUtilities;



public class Dashboard extends javax.swing.JFrame {
   public static String jabatanLogin;
   public static String divisiLogin;
   static int idKaryawanLogin;
   private Absensi absen;
   private Lembur lembur;
   private Cuti cuti;
   private Gaji gaji;
   private Resign resign;
   private Tambah tambah;
   private Atur atur;
   private Cari cari;
   private Atur_cuti atcut;
   private Atur_resign ares;
   private Atur_lembur alem;
   private String nik;
   private String name;
   
   public void setAbsensi(){
       this.nik = noTxt.getText();
       this.name = namaTxt.getText();
        if(absen == null){
            absen = new Absensi(nik, name);
        }
        jPanel2.removeAll();
        jPanel2.add(absen);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setLembur(){
        if(lembur == null){
            lembur = new Lembur(this,nik, name);
        }
        jPanel2.removeAll();
        jPanel2.add(lembur);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
    public void setResign() {
        this.nik = noTxt.getText();
        this.name = namaTxt.getText();
        if(resign == null){
            resign = new Resign(nik, name);
    }
        jPanel2.removeAll();
        jPanel2.add(resign);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
     public void setGaji(){
        this.nik = noTxt.getText();
        this.name = namaTxt.getText();
        if(gaji == null){
            gaji = new Gaji(nik, name);
    }
        jPanel2.removeAll();
        jPanel2.add(gaji);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
    
   public void setCuti(){
       this.nik = noTxt.getText();
       this.name = noTxt.getText();
        if(cuti == null){
            cuti = new Cuti(nik, name);
        }
        jPanel2.removeAll();
        jPanel2.add(cuti);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setTambah(){
        if(tambah == null){
            tambah = new Tambah(this);
        }
        jPanel2.removeAll();
        jPanel2.add(tambah);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setAtur(){
        if(atur == null){
            atur = new Atur(this);
        }
        jPanel2.removeAll();
        jPanel2.add(atur);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setCari(){
        if(cari == null){
            cari = new Cari(this);
        }
        jPanel2.removeAll();
        jPanel2.add(cari);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setAtur_cuti(){
        if(atcut == null){
            atcut = new Atur_cuti(this);
        }
        jPanel2.removeAll();
        jPanel2.add(atcut);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setAtur_resign(){
        if(ares == null){
            ares = new Atur_resign(this);
        }
        jPanel2.removeAll();
        jPanel2.add(ares);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
   public void setAtur_lembur(){
        if(alem == null){
            alem = new Atur_lembur(this, nik, name);
        }
        jPanel2.removeAll();
        jPanel2.add(alem);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
   
    public Dashboard(String nomor, String nama) {
        initComponents();
        
        this.nik = nomor;
        this.name = nama;
        
        noTxt.setText(nik);
        namaTxt.setText(name);
        
        noTxt.setEnabled(false);
        namaTxt.setEnabled(false);
        setAbsensi();
    }
    
    public void aksesAdmin() {
        babs.setVisible(true);
        blem.setVisible(true);
        bcut.setVisible(true);
        bres.setVisible(true);
        bCariAbsen.setVisible(true);
        bTambah.setVisible(true);
        bgaj.setVisible(true);
        bAturGaji.setVisible(true);
        bApvCuti.setVisible(true);
        bApvLembur.setVisible(true);
        bApvResign.setVisible(true);
}

    public void aksesStaff() {
        babs.setVisible(true);
        blem.setVisible(true);
        bcut.setVisible(true);
        bres.setVisible(true);
        bgaj.setVisible(true);
        bCariAbsen.setVisible(false);
        bTambah.setVisible(false);
        bAturGaji.setVisible(false);
        bApvCuti.setVisible(false);
        bApvLembur.setVisible(false);
        bApvResign.setVisible(false);
    }

    public void aksesManager() {
        aksesStaff(); // dapat akses seperti staff
        bCariAbsen.setVisible(true); // tambahan
        bApvCuti.setVisible(true);
        bApvLembur.setVisible(true);
        bApvResign.setVisible(true);
    }

    public void aksesHRD() {
        aksesStaff(); // dapat akses seperti manager
        bTambah.setVisible(true); // tambahan
    }


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        babs = new javax.swing.JButton();
        blem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        namaTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        noTxt = new javax.swing.JTextField();
        bres = new javax.swing.JButton();
        bcut = new javax.swing.JButton();
        bgaj = new javax.swing.JButton();
        bTambah = new javax.swing.JButton();
        bAturGaji = new javax.swing.JButton();
        bApvLembur = new javax.swing.JButton();
        bApvResign = new javax.swing.JButton();
        bApvCuti = new javax.swing.JButton();
        bCariAbsen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1243, 834));

        jPanel1.setBackground(new java.awt.Color(255, 253, 246));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SelfTracking");
        jLabel1.setOpaque(true);

        babs.setBackground(new java.awt.Color(77, 120, 204));
        babs.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        babs.setForeground(new java.awt.Color(255, 255, 255));
        babs.setText("Absensi");
        babs.setBorderPainted(false);
        babs.setPreferredSize(new java.awt.Dimension(0, 50));
        babs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                babsActionPerformed(evt);
            }
        });

        blem.setBackground(new java.awt.Color(44, 44, 44));
        blem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        blem.setForeground(new java.awt.Color(255, 255, 255));
        blem.setText("Lembur");
        blem.setBorderPainted(false);
        blem.setPreferredSize(new java.awt.Dimension(0, 50));
        blem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(77, 120, 204));
        jLabel4.setText("Nama");

        namaTxt.setBackground(new java.awt.Color(255, 253, 246));
        namaTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        namaTxt.setForeground(new java.awt.Color(30, 30, 30));
        namaTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 209, 202), 2));
        namaTxt.setPreferredSize(new java.awt.Dimension(0, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(77, 120, 204));
        jLabel5.setText("Nomor Induk Karyawan ");

        noTxt.setBackground(new java.awt.Color(255, 253, 246));
        noTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        noTxt.setForeground(new java.awt.Color(30, 30, 30));
        noTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 209, 202), 2));
        noTxt.setPreferredSize(new java.awt.Dimension(0, 50));

        bres.setBackground(new java.awt.Color(44, 44, 44));
        bres.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bres.setForeground(new java.awt.Color(255, 255, 255));
        bres.setText("Resign");
        bres.setBorderPainted(false);
        bres.setPreferredSize(new java.awt.Dimension(0, 50));
        bres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bresActionPerformed(evt);
            }
        });

        bcut.setBackground(new java.awt.Color(44, 44, 44));
        bcut.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bcut.setForeground(new java.awt.Color(255, 255, 255));
        bcut.setText("Cuti");
        bcut.setBorderPainted(false);
        bcut.setPreferredSize(new java.awt.Dimension(0, 50));
        bcut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcutActionPerformed(evt);
            }
        });

        bgaj.setBackground(new java.awt.Color(44, 44, 44));
        bgaj.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bgaj.setForeground(new java.awt.Color(255, 255, 255));
        bgaj.setText("Gaji");
        bgaj.setBorderPainted(false);
        bgaj.setPreferredSize(new java.awt.Dimension(0, 50));
        bgaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgajActionPerformed(evt);
            }
        });

        bTambah.setBackground(new java.awt.Color(44, 44, 44));
        bTambah.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bTambah.setForeground(new java.awt.Color(255, 255, 255));
        bTambah.setText("Tambah Karyawan");
        bTambah.setBorderPainted(false);
        bTambah.setPreferredSize(new java.awt.Dimension(0, 50));
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        bAturGaji.setBackground(new java.awt.Color(44, 44, 44));
        bAturGaji.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bAturGaji.setForeground(new java.awt.Color(255, 255, 255));
        bAturGaji.setText("Atur Gaji");
        bAturGaji.setBorderPainted(false);
        bAturGaji.setPreferredSize(new java.awt.Dimension(0, 50));
        bAturGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAturGajiActionPerformed(evt);
            }
        });

        bApvLembur.setBackground(new java.awt.Color(44, 44, 44));
        bApvLembur.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bApvLembur.setForeground(new java.awt.Color(255, 255, 255));
        bApvLembur.setText("Approval lembur");
        bApvLembur.setBorderPainted(false);
        bApvLembur.setPreferredSize(new java.awt.Dimension(0, 50));
        bApvLembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApvLemburActionPerformed(evt);
            }
        });

        bApvResign.setBackground(new java.awt.Color(44, 44, 44));
        bApvResign.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bApvResign.setForeground(new java.awt.Color(255, 255, 255));
        bApvResign.setText("Approval Resign");
        bApvResign.setBorderPainted(false);
        bApvResign.setPreferredSize(new java.awt.Dimension(0, 50));
        bApvResign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApvResignActionPerformed(evt);
            }
        });

        bApvCuti.setBackground(new java.awt.Color(44, 44, 44));
        bApvCuti.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bApvCuti.setForeground(new java.awt.Color(255, 255, 255));
        bApvCuti.setText("Approval Cuti");
        bApvCuti.setBorderPainted(false);
        bApvCuti.setPreferredSize(new java.awt.Dimension(0, 50));
        bApvCuti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApvCutiActionPerformed(evt);
            }
        });

        bCariAbsen.setBackground(new java.awt.Color(44, 44, 44));
        bCariAbsen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        bCariAbsen.setForeground(new java.awt.Color(255, 255, 255));
        bCariAbsen.setText("Cari Aja");
        bCariAbsen.setBorderPainted(false);
        bCariAbsen.setPreferredSize(new java.awt.Dimension(0, 50));
        bCariAbsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariAbsenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(babs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bcut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bgaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bAturGaji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bApvLembur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bApvResign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bApvCuti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bCariAbsen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(babs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bcut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bgaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAturGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCariAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bApvLembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bApvCuti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bApvResign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void babsActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        babs.setBackground(new Color(77,120,204));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setAbsensi();
    }                                    

    private void blemActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        blem.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setLembur();
    }                                    

    private void bresActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        bres.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setResign();
    }                                    

    private void bcutActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        bcut.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setCuti();
    }                                    

    private void bgajActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
        bgaj.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setGaji();
    }                                    

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        bTambah.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setTambah();
    }                                       

    private void bAturGajiActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        bAturGaji.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setAtur();
    }                                         

    private void bApvLemburActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        bApvLembur.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        setAtur_lembur();
    }                                          

    private void bApvResignActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        bApvResign.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setAtur_resign();
    }                                          

    private void bApvCutiActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        bApvCuti.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bCariAbsen.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setAtur_cuti();
    }                                        

    private void bCariAbsenActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        bCariAbsen.setBackground(new Color(77,120,204));
        babs.setBackground(new Color(44,44,44));
        blem.setBackground(new Color(44,44,44));
        bres.setBackground(new Color(44,44,44));
        bcut.setBackground(new Color(44,44,44));
        bgaj.setBackground(new Color(44,44,44));
        bTambah.setBackground(new Color(44,44,44));
        bAturGaji.setBackground(new Color(44,44,44));
        bApvResign.setBackground(new Color(44,44,44));
        bApvCuti.setBackground(new Color(44,44,44));
        bApvLembur.setBackground(new Color(44,44,44));
        setCari();
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton bApvCuti;
    private javax.swing.JButton bApvLembur;
    private javax.swing.JButton bApvResign;
    private javax.swing.JButton bAturGaji;
    private javax.swing.JButton bCariAbsen;
    private javax.swing.JButton bTambah;
    private javax.swing.JButton babs;
    private javax.swing.JButton bcut;
    private javax.swing.JButton bgaj;
    private javax.swing.JButton blem;
    private javax.swing.JButton bres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField namaTxt;
    private javax.swing.JTextField noTxt;
    // End of variables declaration                   
}
