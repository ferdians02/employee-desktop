    package Beranda;
    import Connect.ConnectDB;
    import Loginreg.*;
    import com.mysql.jdbc.Connection;
    import constant.Constants;
    import java.sql.Date;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Statement;
    import java.text.SimpleDateFormat;
    import javax.swing.JOptionPane;
import javax.swing.RowFilter;
    import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
    /**
     *
     * @author Dell
     */
    public class Atur_cuti extends javax.swing.JPanel {
        private Connection conn = (Connection) new ConnectDB().connect();
        private final Dashboard main;
        private String nama;
        private String nik;
        private String selectedIdCuti;
        public Atur_cuti(Dashboard main, String nik, String name) {
        initComponents();
        this.main = main;
        this.nama = name;
        this.nik = nik;
        loadData(nik);
        
        nik1.setEnabled(false);
        name1.setEnabled(false);
        awal.setEnabled(false);
        akhir.setEnabled(false);
        lama.setEnabled(false);
        alasan.setEnabled(false);
        persetujuan.setText(name);
    }

    private void loadData(String nikAtasan) {
    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID CUTI");
    model.addColumn("NIK");
    model.addColumn("NAMA KARYAWAN");
    model.addColumn("TGL AWAL");
    model.addColumn("TGL AKHIR");
    model.addColumn("ALASAN");
    model.addColumn("LAMA CUTI");
    model.addColumn("ATASAN");

    try {
        String sql = """
            SELECT
                C.id_cuti,
                K.nik,
                K.nama_karyawan,
                C.tgl_awal,
                C.tgl_akhir,
                C.alasan,
                C.lama_cuti,
                KA.nama_karyawan AS nama_atasan
            FROM tb_cuti C
            INNER JOIN tb_karyawan K ON C.id_karyawan = K.id_karyawan
            INNER JOIN tb_karyawan KA ON KA.id_divisi = K.id_divisi AND KA.id_jabatan > K.id_jabatan
            WHERE C.record_flag = 'N'
              AND C.approval_spv_by1 IS NULL
              AND KA.nik = ?
            ORDER BY C.id_cuti DESC
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nikAtasan);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_cuti"),
                rs.getString("nik"),
                rs.getString("nama_karyawan"),
                rs.getDate("tgl_awal"),
                rs.getDate("tgl_akhir"),
                rs.getString("alasan"),
                rs.getInt("lama_cuti"),
                rs.getString("nama_atasan")
            });
        }

        tbl.setModel(model); // tbl adalah JTable kamu

        // Sembunyikan kolom ID CUTI (kolom ke-0)
        tbl.getColumnModel().getColumn(0).setMinWidth(0);
        tbl.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl.getColumnModel().getColumn(0).setWidth(0);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal memuat data cuti: " + e.getMessage());
    }
}

    private void loadTableClickCuti() {
    try {
        int row = tbl.getSelectedRow();
        String idCuti = tbl.getModel().getValueAt(row, 0).toString();
        this.selectedIdCuti = idCuti; // simpan ID ke variabel biasa

        String sql = """
            SELECT
                C.id_cuti,
                K.nik,
                K.nama_karyawan,
                C.tgl_awal,
                C.tgl_akhir,
                C.lama_cuti,
                C.alasan
            FROM tb_cuti C
            INNER JOIN tb_karyawan K ON C.id_karyawan = K.id_karyawan
            WHERE C.id_cuti = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idCuti);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            nik1.setText(rs.getString("nik"));
            name1.setText(rs.getString("nama_karyawan"));
            awal.setDate(rs.getDate("tgl_awal"));
            akhir.setDate(rs.getDate("tgl_akhir"));
            lama.setText(String.valueOf(rs.getInt("lama_cuti")));
            alasan.setText(rs.getString("alasan"));
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal tampilkan data ke field: " + e.getMessage());
    }
}

    private void kosong() {
        selectedIdCuti = null;
        nik1.setText("");
        name1.setText("");
        awal.setDate(null);
        akhir.setDate(null);
        alasan.setText("");
        lama.setText("");
        desc.setText("");
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
        nik1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        name1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alasan = new javax.swing.JTextArea();
        clear = new javax.swing.JButton();
        lama = new javax.swing.JTextField();
        awal = new com.toedter.calendar.JDateChooser();
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
        simpan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        akhir = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        desc = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 253, 246));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Approval Cuti");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Nomor Induk Karyawan");

        nik1.setBackground(new java.awt.Color(255, 253, 246));
        nik1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        nik1.setForeground(new java.awt.Color(30, 30, 30));
        nik1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nik1.setPreferredSize(new java.awt.Dimension(64, 50));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cari");
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

        name1.setBackground(new java.awt.Color(255, 253, 246));
        name1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        name1.setForeground(new java.awt.Color(30, 30, 30));
        name1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        name1.setPreferredSize(new java.awt.Dimension(64, 50));

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

        lama.setBackground(new java.awt.Color(255, 253, 246));
        lama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lama.setForeground(new java.awt.Color(30, 30, 30));
        lama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lama.setPreferredSize(new java.awt.Dimension(64, 50));

        awal.setBackground(new java.awt.Color(255, 253, 246));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 30, 30));
        jLabel9.setText("Persetujuan");

        persetujuan.setBackground(new java.awt.Color(255, 253, 246));
        persetujuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        persetujuan.setForeground(new java.awt.Color(30, 30, 30));
        persetujuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        persetujuan.setPreferredSize(new java.awt.Dimension(64, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 30, 30));
        jLabel10.setText("Keterangan");

        s.setBackground(new java.awt.Color(255, 253, 246));
        s.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        s.setForeground(new java.awt.Color(30, 30, 30));
        s.setText("Disetujui");
        s.setAutoscrolls(true);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        td.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        td.setForeground(new java.awt.Color(30, 30, 30));
        td.setText("Tidak Disetujui");
        td.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdActionPerformed(evt);
            }
        });

        r.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        r.setForeground(new java.awt.Color(30, 30, 30));
        r.setText("Revisi");

        tbl.setBackground(new java.awt.Color(255, 253, 246));
        tbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        tbl.setForeground(new java.awt.Color(30, 30, 30));
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nomor Induk Karyawan", "Nama Karyawan", "Tanggal", "Divisi", "Keterangan"
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

        simpan.setBackground(new java.awt.Color(0, 0, 102));
        simpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        simpan.setForeground(new java.awt.Color(255, 255, 255));
        simpan.setText("Simpan");
        simpan.setBorderPainted(false);
        simpan.setPreferredSize(new java.awt.Dimension(0, 50));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 30, 30));
        jLabel8.setText("Tanggal Akhir");

        akhir.setBackground(new java.awt.Color(255, 253, 246));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(30, 30, 30));
        jLabel11.setText("Keterangan");

        desc.setBackground(new java.awt.Color(255, 253, 246));
        desc.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        desc.setForeground(new java.awt.Color(30, 30, 30));
        desc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        desc.setPreferredSize(new java.awt.Dimension(64, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(s)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(td)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(simpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nik1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(awal, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(akhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(name1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(persetujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nik1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lama, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(persetujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s)
                            .addComponent(td)
                            .addComponent(r))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            String keyword = cari.getText().trim();

            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tbl.setRowSorter(sorter);
            
            if (keyword.isEmpty()) {
                sorter.setRowFilter(null); // tampilkan semua
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 2)); // 2 = kolom NAMA KARYAWAN
//                cari.setText("");
//                tbl.setRowSorter(null);
            }
        }//GEN-LAST:event_jButton1ActionPerformed

        private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
           kosong();
        }//GEN-LAST:event_clearActionPerformed

        private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_sActionPerformed

        private void tdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_tdActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
            try {
            String sql = """
                UPDATE tb_cuti
                SET 
                    approval_spv_desc1 = ?,
                    update_by = ?,
                    update_at = ?,
                    approval_spv_by1 = ?,
                    approval_spv_on1 = ? 
                WHERE id_cuti = ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);

            String status = null;
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            if (s.isSelected()) {
                status = Constants.SPL_APPROVE; // contoh: "Approve"
            } else if (td.isSelected()) {
                status = Constants.SPL_REJECT; // contoh: "Reject"
            } else if (r.isSelected()) {
                status = Constants.SPL_REVISION; // contoh: "Revision"
            }

            // isi parameter
            ps.setString(1, status);         // approval_spv_desc1
            ps.setString(2, nik);            // update_by
            ps.setDate(3, sqlDate);          // update_at
            ps.setString(4, nik);            // approval_spv_by1
            ps.setDate(5, sqlDate);          // approval_spv_on1
            ps.setString(6, selectedIdCuti); // ID cuti (dari klik tabel)

            ps.executeUpdate();
            loadData(nik); // refresh data
            JOptionPane.showMessageDialog(null, "Data cuti berhasil disubmit");
            kosong();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        loadTableClickCuti();
    }//GEN-LAST:event_tblMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser akhir;
    private javax.swing.JTextArea alasan;
    private com.toedter.calendar.JDateChooser awal;
    private javax.swing.JTextField cari;
    private javax.swing.JButton clear;
    private javax.swing.JTextField desc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lama;
    private javax.swing.JTextField name1;
    private javax.swing.JTextField nik1;
    private javax.swing.JTextField persetujuan;
    private javax.swing.JCheckBox r;
    private javax.swing.JCheckBox s;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tbl;
    private javax.swing.JCheckBox td;
    // End of variables declaration//GEN-END:variables

        private static class upstream {

            public upstream() {
            }
        }
    }
