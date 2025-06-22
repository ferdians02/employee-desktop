    package Beranda;
 import Connect.ConnectDB;
        import Loginreg.*;
        import com.mysql.jdbc.Connection;
        import com.mysql.jdbc.Statement;
        import constant.Constants;
        import java.io.File;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import javax.swing.JOptionPane;
        import javax.swing.table.DefaultTableModel;
        import java.util.HashMap;
        import javax.swing.JOptionPane;
        import javax.swing.RowFilter;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableRowSorter;
        import net.sf.jasperreports.engine.JasperPrint;
        import net.sf.jasperreports.view.JasperViewer;
        import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
 * @author Dell
 */
public class Atur_resign extends javax.swing.JPanel {
        private Connection conn = (Connection) new ConnectDB().connect();
        private final Dashboard main;
        public static String jabatanLogin;   
        private String nama;
        private String nik;
        private String selectedIdResign;
        public Atur_resign(Dashboard main, String nik, String name) {
        initComponents();
        this.main = main;
        this.nama = name;
        this.nik = nik;
        loadData(nik);
        
        nik1.setEnabled(false);
        name1.setEnabled(false);
        tgl_resign.setEnabled(false);
        alasan.setEnabled(false);
        persetujuan.setText(name);
}

    private void loadData(String nikAtasan) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID RESIGN");
    model.addColumn("NIK");
    model.addColumn("NAMA KARYAWAN");
    model.addColumn("TANGGAL");
    model.addColumn("ALASAN");

    try {
        // Cek jabatan user login
        String sqlJabatan = """
            SELECT J.id_jabatan, J.nama_jabatan
            FROM tb_karyawan K
            INNER JOIN tb_jabatan J ON K.id_jabatan = J.id_jabatan
            WHERE K.nik = ?
        """;
        PreparedStatement psJabatan = conn.prepareStatement(sqlJabatan);
        psJabatan.setString(1, nikAtasan);
        ResultSet rsJabatan = psJabatan.executeQuery();

        int idJabatan = 0;
        String namaJabatan = "";
        if (rsJabatan.next()) {
            idJabatan = rsJabatan.getInt("id_jabatan");
            namaJabatan = rsJabatan.getString("nama_jabatan");
        }

        // Buat query utama
        String sql;
        if (namaJabatan.equalsIgnoreCase("DIREKTUR UTAMA")) {
            // Direktur bisa lihat semua data resign
            sql = """
                SELECT TR.id_resign, TK.nik, TK.nama_karyawan, TR.tanggal, TR.ket_resign
                FROM tb_resign TR
                INNER JOIN tb_karyawan TK ON TR.id_karyawan = TK.id_karyawan
                WHERE TR.record_flag = 'N'
                  AND TR.approval_spv_by1 IS NULL
                ORDER BY TR.id_resign DESC
            """;
        } else {
            // Atasan hanya bisa lihat bawahan satu divisi dan jabatan di bawahnya
            sql = """
                SELECT TR.id_resign, TK.nik, TK.nama_karyawan, TR.tanggal, TR.ket_resign
                FROM tb_resign TR
                INNER JOIN tb_karyawan TK ON TR.id_karyawan = TK.id_karyawan
                INNER JOIN tb_karyawan TKA ON TKA.id_divisi = TK.id_divisi
                                           AND TK.id_jabatan < TKA.id_jabatan
                WHERE TR.record_flag = 'N'
                  AND TR.approval_spv_by1 IS NULL
                  AND TKA.nik = ?
                ORDER BY TR.id_resign DESC
            """;
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        if (!namaJabatan.equalsIgnoreCase("DIREKTUR UTAMA")) {
            ps.setString(1, nikAtasan);
        }
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_resign"),
                rs.getString("nik"),
                rs.getString("nama_karyawan"),
                rs.getDate("tanggal"),
                rs.getString("ket_resign")
            });
        }

        tbl.setModel(model);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal load data resign: " + e.getMessage());
    }
}



    private void loadTableClickResign() {
    try {
        int row = tbl.getSelectedRow();
        if (row == -1) return;

        String idResign = tbl.getModel().getValueAt(row, 0).toString(); // ambil dari kolom ID
        this.selectedIdResign = idResign;

        String sql = """
            SELECT
                TR.id_resign,
                TK.nik,
                TK.nama_karyawan,
                TR.tanggal,
                TR.ket_resign
            FROM tb_resign TR
            INNER JOIN tb_karyawan TK ON TR.id_karyawan = TK.id_karyawan
            WHERE TR.id_resign = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idResign);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            nik1.setText(rs.getString("nik"));
            name1.setText(rs.getString("nama_karyawan"));
            tgl_resign.setDate(rs.getDate("tanggal"));
            alasan.setText(rs.getString("ket_resign"));
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal load data resign ke field: " + e.getMessage());
    }
}



    private void kosong() {
        selectedIdResign = null;
        nik1.setText("");
        name1.setText("");
        tgl_resign.setDate(null);
        alasan.setText("");
        ket.setText("");
        s.setSelected(false);
        td.setSelected(false);
        r.setSelected(false);
        persetujuan.setText(nama); // nama atasan login
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
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        name1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alasan = new javax.swing.JTextArea();
        clear = new javax.swing.JButton();
        tgl_resign = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        persetujuan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        s = new javax.swing.JCheckBox();
        td = new javax.swing.JCheckBox();
        r = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ket = new javax.swing.JTextField();
        nik1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 253, 246));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Approval Resign");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Nomor Induk Karyawan");

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
        jLabel4.setText("Tanggal Resign");

        name1.setBackground(new java.awt.Color(255, 253, 246));
        name1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        name1.setForeground(new java.awt.Color(30, 30, 30));
        name1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        name1.setPreferredSize(new java.awt.Dimension(64, 50));
        name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 30, 30));
        jLabel5.setText("Nama Karyawan");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 30, 30));
        jLabel7.setText("Alasan Resign");

        alasan.setBackground(new java.awt.Color(255, 253, 246));
        alasan.setColumns(20);
        alasan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        alasan.setForeground(new java.awt.Color(30, 30, 30));
        alasan.setRows(5);
        alasan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(alasan);

        clear.setBackground(new java.awt.Color(0, 0, 102));
        clear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear");
        clear.setBorderPainted(false);
        clear.setPreferredSize(new java.awt.Dimension(0, 50));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        tgl_resign.setBackground(new java.awt.Color(255, 253, 246));
        tgl_resign.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 30, 30));
        jLabel9.setText("Status");

        persetujuan.setBackground(new java.awt.Color(255, 253, 246));
        persetujuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        persetujuan.setForeground(new java.awt.Color(30, 30, 30));
        persetujuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        persetujuan.setPreferredSize(new java.awt.Dimension(64, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 30, 30));
        jLabel10.setText("Persetujuan");

        s.setBackground(new java.awt.Color(255, 253, 246));
        s.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        s.setForeground(new java.awt.Color(30, 30, 30));
        s.setText("Disetujui");

        td.setBackground(new java.awt.Color(255, 253, 246));
        td.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        td.setForeground(new java.awt.Color(30, 30, 30));
        td.setText("Tidak Disetujui");

        r.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        r.setForeground(new java.awt.Color(30, 30, 30));
        r.setText("Revisi");

        tbl.setBackground(new java.awt.Color(255, 253, 246));
        tbl.setForeground(new java.awt.Color(30, 30, 30));
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nomor Induk Karyawan", "Nama Karyawan", "Tanggal resign", "Persetujuan"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 30, 30));
        jLabel3.setText("Cari :");

        cari.setBackground(new java.awt.Color(255, 253, 246));
        cari.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        cari.setForeground(new java.awt.Color(30, 30, 30));
        cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cari.setPreferredSize(new java.awt.Dimension(64, 50));

        jButton3.setBackground(new java.awt.Color(0, 0, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cari");
        jButton3.setBorderPainted(false);
        jButton3.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(30, 30, 30));
        jLabel11.setText("Keterangan");

        ket.setBackground(new java.awt.Color(255, 253, 246));
        ket.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        ket.setForeground(new java.awt.Color(30, 30, 30));
        ket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ket.setPreferredSize(new java.awt.Dimension(64, 50));

        nik1.setBackground(new java.awt.Color(255, 253, 246));
        nik1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nik1.setForeground(new java.awt.Color(30, 30, 30));
        nik1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nik1.setPreferredSize(new java.awt.Dimension(64, 50));
        nik1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nik1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nik1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgl_resign, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(persetujuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(s)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(td)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(r))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(0, 69, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nik1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tgl_resign, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(persetujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s)
                    .addComponent(td)
                    .addComponent(r))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
        String sql = """
            UPDATE tb_resign
            SET 
                status_resign = ?, 
                approval_spv_desc1 = ?, 
                update_by = ?, 
                update_at = ?, 
                approval_spv_by1 = ?, 
                approval_spv_on1 = ?,
                record_flag = ?
            WHERE id_resign = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);

        // Tanggal saat ini
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        // Status dari radio button
        String status = null;
        if (s.isSelected()) {
            status = Constants.SPL_APPROVE;
        } else if (td.isSelected()) {
            status = Constants.SPL_REJECT;
        } else if (r.isSelected()) {
            status = Constants.SPL_REVISION;
        }

        String keterangan = ket.getText();
        String idResign = selectedIdResign;

        // Set parameter ke query
        ps.setString(1, status);         // status_resign
        ps.setString(2, keterangan);     // approval_spv_desc1
        ps.setString(3, loginn.jabatanLogin);            // update_by
        ps.setDate(4, sqlDate);          // update_at
        ps.setString(5, this.nama);            // approval_spv_by1
        ps.setDate(6, sqlDate);          // approval_spv_on1
        ps.setString(7, "U");            // record_flag = 'U'
        ps.setString(8, idResign);       // WHERE id_resign = ?

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data resign berhasil diperbarui!");
        kosong();
        loadData(nik);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal update resign: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        kosong();
    }//GEN-LAST:event_clearActionPerformed

    private void name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String keyword = cari.getText().trim(); // txtCari = field input cari nama

    DefaultTableModel model = (DefaultTableModel) tbl.getModel(); // tbl = JTable kamu
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tbl.setRowSorter(sorter);

    if (keyword.isEmpty()) {
        sorter.setRowFilter(null); // tampilkan semua jika kosong
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 2)); // 2 = kolom "Nama Karyawan"
    }

    cari.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
       loadTableClickResign();
    }//GEN-LAST:event_tblMouseClicked

    private void nik1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nik1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nik1ActionPerformed
                       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alasan;
    private javax.swing.JTextField cari;
    private javax.swing.JButton clear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField ket;
    private javax.swing.JTextField name1;
    private javax.swing.JTextField nik1;
    private javax.swing.JTextField persetujuan;
    private javax.swing.JCheckBox r;
    private javax.swing.JCheckBox s;
    private javax.swing.JTable tbl;
    private javax.swing.JCheckBox td;
    private com.toedter.calendar.JDateChooser tgl_resign;
    // End of variables declaration//GEN-END:variables

    private void JasperViewer(JasperPrint print, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
