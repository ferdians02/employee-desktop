package Beranda;
        
import Beranda.Dashboard;
import Connect.ConnectDB;
import com.mysql.jdbc.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
//import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author rakha
 */
public class Atur extends javax.swing.JPanel {
    private final Connection conn = (Connection) new ConnectDB().connect();

    public Atur(Dashboard main) {
        initComponents();
        loadKaryawanByDivisi(); // memuat data karyawan berdasarkan divisi login
        loadTableGaji();        // memuat data gaji dari database

        
        nik.setEnabled(false);
        name.setEnabled(false);
        jabatan.setEnabled(false);
        divisi.setEnabled(false);
        gaji.setEnabled(false);

        // Table Karyawan diklik
        tableKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableKaryawan.getSelectedRow();
                if (row != -1) {
                    nik.setText(tableKaryawan.getValueAt(row, 0).toString());
                    name.setText(tableKaryawan.getValueAt(row, 1).toString());
                    jabatan.setText(tableKaryawan.getValueAt(row, 2).toString());

                    // Ambil gapok dari TB_JABATAN
                    try {
                        String sql = """
                            SELECT J.GAPOK, D.NAMA_DIVISI
                            FROM TB_KARYAWAN K
                            JOIN TB_JABATAN J ON K.ID_JABATAN = J.ID_JABATAN
                            JOIN TB_DIVISI D ON K.ID_DIVISI = D.ID_DIVISI
                            WHERE K.NIK = ?
                        """;
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, nik.getText());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            gaji.setText(rs.getString("GAPOK"));
                            divisi.setText(rs.getString("NAMA_DIVISI"));
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal ambil gaji: " + e.getMessage());
                    }
                }
            }
        });

        // Table Gaji diklik
        tableGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableGaji.getSelectedRow();
                if (row != -1) {
                    nik.setText(tableGaji.getValueAt(row, 0).toString());
                    name.setText(tableGaji.getValueAt(row, 1).toString());
                    divisi.setText(tableGaji.getValueAt(row, 2).toString());
                    jabatan.setText(tableGaji.getValueAt(row, 3).toString());
                    tunjangan.setText(tableGaji.getValueAt(row, 4).toString());
                    gaji.setText(tableGaji.getValueAt(row, 5).toString());
                }
            }
        });

    }

     private void kosong() {
        nik.setText("");
        name.setText("");
        jabatan.setText("");
        divisi.setText("");
        gaji.setText("");
        tunjangan.setText("");
    }

    private void loadKaryawanByDivisi() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"NIK", "Nama Karyawan", "Jabatan"}, 0
        );

        try {
            String sql = """
                SELECT K.NIK, K.NAMA_KARYAWAN, J.NAMA_JABATAN
                FROM TB_KARYAWAN K
                JOIN TB_JABATAN J ON K.ID_JABATAN = J.ID_JABATAN
                JOIN TB_DIVISI D ON K.ID_DIVISI = D.ID_DIVISI
                WHERE D.NAMA_DIVISI = ?
            """;

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, Dashboard.divisiLogin); // pastikan Dashboard.divisiLogin sudah terisi saat login
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("NIK"),
                    rs.getString("NAMA_KARYAWAN"),
                    rs.getString("NAMA_JABATAN")
                });
            }

            tableKaryawan.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load karyawan: " + e.getMessage());
        }
    }



        private void loadTableGaji() {
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"NIK", "Nama", "Divisi", "Jabatan", "Tunjangan", "Gaji"}, 0
            );

            try {
                String sql = """
                    SELECT K.NIK, K.NAMA_KARYAWAN, D.NAMA_DIVISI, J.NAMA_JABATAN, G.TUNJANGAN, J.GAPOK
                    FROM TB_GAJI G
                    JOIN TB_KARYAWAN K ON G.ID_KARYAWAN = K.ID_KARYAWAN
                    JOIN TB_JABATAN J ON K.ID_JABATAN = J.ID_JABATAN
                    JOIN TB_DIVISI D ON K.ID_DIVISI = D.ID_DIVISI
                """;

                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("NIK"),
                        rs.getString("NAMA_KARYAWAN"),
                        rs.getString("NAMA_DIVISI"),
                        rs.getString("NAMA_JABATAN"),
                        rs.getString("TUNJANGAN"),
                        rs.getString("GAPOK")
                    });
                }

                tableGaji.setModel(model);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal load gaji: " + e.getMessage());
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGaji = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        nik = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        divisi = new javax.swing.JTextField();
        jabatan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableKaryawan = new javax.swing.JTable();
        name = new javax.swing.JTextField();
        gaji = new javax.swing.JTextField();
        tunjangan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 253, 246));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Atur Gaji");

        tableGaji.setBackground(new java.awt.Color(255, 253, 246));
        tableGaji.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        tableGaji.setForeground(new java.awt.Color(30, 30, 30));
        tableGaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nomor Induk Karyawa", "Nama", "Divisi", "Jabatan", "tunjangan", "Gaji"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableGaji);
        if (tableGaji.getColumnModel().getColumnCount() > 0) {
            tableGaji.getColumnModel().getColumn(2).setHeaderValue("Divisi");
            tableGaji.getColumnModel().getColumn(4).setHeaderValue("tunjangan");
            tableGaji.getColumnModel().getColumn(5).setHeaderValue("Gaji");
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 30, 30));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Jabatan :");

        nik.setBackground(new java.awt.Color(255, 253, 246));
        nik.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nik.setForeground(new java.awt.Color(30, 30, 30));
        nik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 30, 30));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nama Karyawan :");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 30, 30));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Gaji :");

        cari.setBackground(new java.awt.Color(255, 253, 246));
        cari.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        cari.setForeground(new java.awt.Color(30, 30, 30));
        cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cari.setPreferredSize(new java.awt.Dimension(64, 50));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 30, 30));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tunjangan :");

        divisi.setBackground(new java.awt.Color(255, 253, 246));
        divisi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        divisi.setForeground(new java.awt.Color(30, 30, 30));
        divisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jabatan.setBackground(new java.awt.Color(255, 253, 246));
        jabatan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jabatan.setForeground(new java.awt.Color(30, 30, 30));
        jabatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 30, 30));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Divisi :");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 30, 30));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("NIK:");

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cari");
        jButton2.setBorderPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.setBorderPainted(false);
        jButton3.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Edit");
        jButton4.setBorderPainted(false);
        jButton4.setPreferredSize(new java.awt.Dimension(0, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tableKaryawan.setBackground(new java.awt.Color(255, 253, 246));
        tableKaryawan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        tableKaryawan.setForeground(new java.awt.Color(30, 30, 30));
        tableKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nomor Induk Karyawa", "Nama", "Jabatan"
            }
        ));
        jScrollPane3.setViewportView(tableKaryawan);

        name.setBackground(new java.awt.Color(255, 253, 246));
        name.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        name.setForeground(new java.awt.Color(30, 30, 30));
        name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gaji.setBackground(new java.awt.Color(255, 253, 246));
        gaji.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        gaji.setForeground(new java.awt.Color(30, 30, 30));
        gaji.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tunjangan.setBackground(new java.awt.Color(255, 253, 246));
        tunjangan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        tunjangan.setForeground(new java.awt.Color(30, 30, 30));
        tunjangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(30, 30, 30));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Cari :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(gaji)
                    .addComponent(tunjangan)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nik, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jabatan)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(divisi)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 324, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nikValue = nik.getText().trim();
        String tunjanganValue = tunjangan.getText().trim();
        String jabatanValue = jabatan.getText().trim();

        if (nikValue.isEmpty() || tunjanganValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi data NIK dan Tunjangan.");
            return;
        }

        try {
            // Ambil ID_KARYAWAN dari NIK
            String getIdSql = "SELECT ID_KARYAWAN FROM TB_KARYAWAN WHERE NIK = ?";
            PreparedStatement getIdPs = conn.prepareStatement(getIdSql);
            getIdPs.setString(1, nikValue);
            ResultSet rs = getIdPs.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Karyawan tidak ditemukan.");
                return;
            }

            int idKaryawan = rs.getInt("ID_KARYAWAN");

            // Siapkan data waktu
            java.sql.Date createAt = new java.sql.Date(System.currentTimeMillis());
            LocalDate localDate = createAt.toLocalDate();
            String bulan = localDate.getMonth().toString(); // misal: JUNE
            String tahun = String.valueOf(localDate.getYear());

            // Insert ke TB_GAJI
            String insertSql = """
                INSERT INTO TB_GAJI (ID_KARYAWAN, TUNJANGAN, BULAN, TAHUN, CREATE_BY, CREATE_AT, RECORD_FLAG)
                VALUES (?, ?, ?, ?, ?, ?, 'N')
            """;

            PreparedStatement insertPs = conn.prepareStatement(insertSql);
            insertPs.setInt(1, idKaryawan);
            insertPs.setInt(2, Integer.parseInt(tunjanganValue));
            insertPs.setString(3, bulan);
            insertPs.setString(4, tahun);
            insertPs.setString(5, jabatanValue); // create_by
            insertPs.setDate(6, createAt);

            int result = insertPs.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data gaji berhasil disimpan.");
                loadTableGaji(); // refresh table gaji
                kosong();        // clear input
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data gaji.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat insert gaji: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String keyword = cari.getText().trim();

    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Silakan masukkan nama karyawan untuk dicari.");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tableKaryawan.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tableKaryawan.setRowSorter(sorter);

    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 1)); // kolom ke-1: nama

    if (sorter.getViewRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Maaf, nama yang Anda cari tidak ditemukan.");
        tableKaryawan.setRowSorter(null); // tampilkan semua kembali
    }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        kosong();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nikKaryawan = nik.getText();
        String namaKaryawan = name.getText();
        String jabatanLogin = Dashboard.jabatanLogin; // disimpan saat login
        String tunjanganBaru = tunjangan.getText();

        if (nikKaryawan.isEmpty() || tunjanganBaru.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIK dan Tunjangan tidak boleh kosong.");
            return;
        }

        try {
            // Ambil ID karyawan berdasarkan NIK
            String getIdKaryawanSQL = "SELECT ID_KARYAWAN FROM TB_KARYAWAN WHERE NIK = ?";
            PreparedStatement pst = conn.prepareStatement(getIdKaryawanSQL);
            pst.setString(1, nikKaryawan);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Karyawan dengan NIK ini tidak ditemukan.");
                return;
            }

            int idKaryawan = rs.getInt("ID_KARYAWAN");

            // Update tunjangan + otomatis update_by dan update_at
            String updateSQL = """
                UPDATE TB_GAJI 
                SET TUNJANGAN = ?, 
                    UPDATE_BY = ?, 
                    UPDATE_AT = CURRENT_DATE()
                WHERE ID_KARYAWAN = ?
            """;

            PreparedStatement updatePst = conn.prepareStatement(updateSQL);
            updatePst.setInt(1, Integer.parseInt(tunjanganBaru));
            updatePst.setString(2, jabatanLogin);
            updatePst.setInt(3, idKaryawan);

            int affectedRows = updatePst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Data gaji berhasil diperbarui.");
                loadTableGaji(); // refresh tabel gaji
                kosong(); // kosongkan field
            } else {
                JOptionPane.showMessageDialog(this, "Data gaji gagal diperbarui.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat update: " + e.getMessage());
        }       
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari;
    private javax.swing.JTextField divisi;
    private javax.swing.JTextField gaji;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jabatan;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nik;
    private javax.swing.JTable tableGaji;
    private javax.swing.JTable tableKaryawan;
    private javax.swing.JTextField tunjangan;
    // End of variables declaration//GEN-END:variables
}
