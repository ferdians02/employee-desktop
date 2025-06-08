        /*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
         */
        package Beranda;

        import Connect.ConnectDB;
        import Loginreg.*;
        import com.mysql.jdbc.Connection;
        import com.mysql.jdbc.Statement;
        import constant.Constants;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import javax.swing.JOptionPane;
        import javax.swing.table.DefaultTableModel;

        /**
         *
         * @author Dell
         */
        public class Cuti extends javax.swing.JPanel {
            private Connection conn = (Connection) new ConnectDB().connect();
                public Cuti(String nomor, String nama) {
                    initComponents();
                    tampilData();
                    akhir.addPropertyChangeListener("date", evt -> {
                        if (awal.getDate() != null && akhir.getDate() != null) {
                            long beda = akhir.getDate().getTime() - awal.getDate().getTime();
                            long jumlahHari = (beda / (1000 * 60 * 60 * 24)) + 1; // +1 agar inklusif
                            if (jumlahHari >= 0) {
                                lama.setText(String.valueOf(jumlahHari));
                            } else {
                                lama.setText("");
                                JOptionPane.showMessageDialog(this, "Tanggal akhir tidak boleh sebelum tanggal awal!");
                            }
                        }
                    });

                    nik.setText(nomor);
                    name.setText(nama);

                    nik.setEnabled(false);
                    name.setEnabled(false);
                    persetujuan.setEnabled(false);
                    ket.setEnabled(false);
                    lama.setEnabled(false);
                    awal.setDate(new java.util.Date());
            }
                
                
                
                
                void kosong(){
                    awal.setDate(new java.util.Date());
                    akhir.setDate(new java.util.Date());
                    lama.setText("");
                    alasan.setText("");
                }
                
                private Integer cariId(String namaKaryawan) {
                    Integer id = null;
                    try {
                        String sql = "SELECT ID_KARYAWAN FROM TB_KARYAWAN WHERE NAMA_KARYAWAN = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, namaKaryawan);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            id = rs.getInt("ID_KARYAWAN");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal mendapatkan ID Karyawan: " + e.getMessage());
                    }
                    return id;
                }
                
                private void tampilData() {
                        DefaultTableModel model = new DefaultTableModel(
                            new String[]{"ID", "Nama Karyawan", "Tgl Awal", "Tgl Akhir", "Alasan", "Lama"}, 0
                        );

                        try {
                            String sql = """
                                SELECT c.id_cuti, k.nama_karyawan, c.tgl_awal, c.tgl_akhir, c.alasan, c.lama_cuti
                                FROM tb_cuti c
                                JOIN tb_karyawan k ON c.id_karyawan = k.id_karyawan
                                WHERE c.record_flag = 'N' AND c.id_karyawan = ?
                            """;
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, loginn.idKaryawanLogin);
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                model.addRow(new Object[]{
                                    rs.getInt("id_cuti"),
                                    rs.getString("nama_karyawan"),
                                    rs.getDate("tgl_awal"),
                                    rs.getDate("tgl_akhir"),
                                    rs.getString("alasan"),
                                    rs.getInt("lama_cuti")
                                });
                            }

                            table_cuti.setModel(model);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
                        }
                    }






                    /**
             * This method is called from within the constructor to initialize the form.
             * WARNING: Do NOT modify this code. The content of this method is always
             * regenerated by the Form Editor.
             */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nik = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alasan = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        lama = new javax.swing.JTextField();
        awal = new com.toedter.calendar.JDateChooser();
        akhir = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        persetujuan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_cuti = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        ket = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 253, 246));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pengajuan Cuti");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Nomor Induk Karyawan");

        nik.setBackground(new java.awt.Color(255, 253, 246));
        nik.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nik.setForeground(new java.awt.Color(30, 30, 30));
        nik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nik.setPreferredSize(new java.awt.Dimension(64, 50));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Simpan");
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 30, 30));
        jLabel4.setText("Tanggal Mulai");

        name.setBackground(new java.awt.Color(255, 253, 246));
        name.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        name.setForeground(new java.awt.Color(30, 30, 30));
        name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        name.setPreferredSize(new java.awt.Dimension(64, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 30, 30));
        jLabel5.setText("Nama Karyawan");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(30, 30, 30));
        jLabel6.setText("Lama Cuti");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 30, 30));
        jLabel7.setText("Alasan Cuti");

        alasan.setBackground(new java.awt.Color(255, 253, 246));
        alasan.setColumns(20);
        alasan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        alasan.setForeground(new java.awt.Color(30, 30, 30));
        alasan.setRows(5);
        alasan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(alasan);

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.setBorderPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lama.setBackground(new java.awt.Color(255, 253, 246));
        lama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lama.setForeground(new java.awt.Color(30, 30, 30));
        lama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lama.setPreferredSize(new java.awt.Dimension(64, 50));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(30, 30, 30));
        jLabel11.setText("Tanggal Akhir");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 30, 30));
        jLabel10.setText("Persetujuan");

        persetujuan.setBackground(new java.awt.Color(255, 253, 246));
        persetujuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        persetujuan.setForeground(new java.awt.Color(30, 30, 30));
        persetujuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        persetujuan.setPreferredSize(new java.awt.Dimension(64, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 30, 30));
        jLabel9.setText("Keterangan");

        table_cuti.setBackground(new java.awt.Color(255, 253, 246));
        table_cuti.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        table_cuti.setForeground(new java.awt.Color(30, 30, 30));
        table_cuti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nomor Induk Karyawan", "Nama Karyawan", "Tanggal", "Lama Cuti", "Alasan", "Keterangan"
            }
        ));
        table_cuti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cutiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_cuti);

        jTextField4.setBackground(new java.awt.Color(255, 253, 246));
        jTextField4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(30, 30, 30));
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField4.setPreferredSize(new java.awt.Dimension(64, 50));

        ket.setBackground(new java.awt.Color(255, 253, 246));
        ket.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        ket.setForeground(new java.awt.Color(30, 30, 30));
        ket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ket.setPreferredSize(new java.awt.Dimension(64, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(351, 351, 351))
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(lama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(persetujuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(156, 156, 156)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(persetujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(402, 402, 402)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(402, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

            private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    // Ambil input
                    String nikInput = nik.getText();
                    java.util.Date tglAwal = awal.getDate();
                    java.util.Date tglAkhir = akhir.getDate();
                    String alasanCuti = alasan.getText();

                    // Validasi input
                    if (nikInput.isEmpty() || tglAwal == null || tglAkhir == null || alasanCuti.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Harap lengkapi semua isian!");
                        return;
                    }

                    // Hitung lama cuti
                    long beda = tglAkhir.getTime() - tglAwal.getTime();
                    long jumlahHari = (beda / (1000 * 60 * 60 * 24)) + 1;
                    if (jumlahHari <= 0) {
                        JOptionPane.showMessageDialog(this, "Tanggal akhir harus setelah tanggal awal.");
                        return;
                    }

                    // Ambil ID karyawan & Jabatan berdasarkan NIK
                    String sqlKaryawan = """
                        SELECT k.id_karyawan, j.nama_jabatan
                        FROM tb_karyawan k
                        JOIN tb_jabatan j ON k.id_jabatan = j.id_jabatan
                        WHERE k.nik = ?
                    """;
                    PreparedStatement ps1 = conn.prepareStatement(sqlKaryawan);
                    ps1.setString(1, nikInput);
                    ResultSet rs = ps1.executeQuery();

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this, "Data karyawan tidak ditemukan.");
                        return;
                    }

                    int idKaryawan = rs.getInt("id_karyawan");
                    String namaJabatan = rs.getString("nama_jabatan"); // akan digunakan sebagai create_by

                    // Simpan ke tb_cuti
                    String sqlInsert = """
                        INSERT INTO tb_cuti 
                        (id_karyawan, tgl_awal, tgl_akhir, alasan, lama_cuti, create_by, create_at, record_flag)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
                    PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
                    ps2.setInt(1, idKaryawan);
                    ps2.setDate(2, new java.sql.Date(tglAwal.getTime()));
                    ps2.setDate(3, new java.sql.Date(tglAkhir.getTime()));
                    ps2.setString(4, alasanCuti);
                    ps2.setInt(5, (int) jumlahHari);
                    ps2.setString(6, namaJabatan); // ini akan disimpan ke kolom create_by
                    ps2.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
                    ps2.setString(8, "N");

                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Pengajuan cuti berhasil disimpan.");
                    kosong();     // Reset form
                    tampilData(); // Refresh tabel (jika kamu punya method ini)
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan data cuti: " + e.getMessage());
                    e.printStackTrace();
                }
            }                                        
            

            private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                  kosong();
            }//GEN-LAST:event_jButton2ActionPerformed

    private void table_cutiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cutiMouseClicked
        int row = table_cuti.getSelectedRow();
        if (row >= 0) {
            // Ambil nilai dari tabel
            String tglAwal = table_cuti.getValueAt(row, 2).toString();
            String tglAkhir = table_cuti.getValueAt(row, 3).toString();
            String alasanCuti = table_cuti.getValueAt(row, 4).toString();

            try {
                // Konversi String ke Date
                java.util.Date dateAwal = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tglAwal);
                java.util.Date dateAkhir = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tglAkhir);

                // Isi ke field
                awal.setDate(dateAwal);
                akhir.setDate(dateAkhir);
                alasan.setText(alasanCuti);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_table_cutiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser akhir;
    private javax.swing.JTextArea alasan;
    private com.toedter.calendar.JDateChooser awal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField ket;
    private javax.swing.JTextField lama;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nik;
    private javax.swing.JTextField persetujuan;
    private javax.swing.JTable table_cuti;
    // End of variables declaration//GEN-END:variables

            private static class upstream {

                public upstream() {
                }
            }
        }
