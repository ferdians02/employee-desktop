/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author wot1
 */
public class ValidateUtil {

    public static void validationKaryawan(String nik, String nama, String no, String alamat, String divisi, String jabatan, String jk) {
        if (nik.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nomor induk karyawan tidak boleh kosong");
        } else if (nik.length() > 32 && nik.length() < 0) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Induk Karyawan tidak boleh [%d] atau [%]", nik.length()));
        }

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Karyawan tidak boleh kosong");
        } else if (nama.length() > 128 && nama.length() < 0) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nama tidak boleh [%d] atau [%d]", nama.length()));
        }

        if (no.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Handphone tidak boleh kosong");
        } else if (no.length() > 12) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Handphone tidak boleh lebih dari [%d]", no));
        }

        if (alamat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alamat tidak boleh kosong");
        }

        if (divisi.equalsIgnoreCase("Pilih") || divisi == null) {
            JOptionPane.showMessageDialog(null, "Divisi harus dipilih");
        }

        if (jabatan.equals("Pilih") || jabatan == null) {
            JOptionPane.showMessageDialog(null, "Jabatan harus dipilih");
        }

        if (jk.isEmpty() && jk.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Jenis kelamin harus dipilih");

        }
    }

    public static ValidationOption validationLembur(Date dt, String start, String end, String desc) {

        StringBuilder errors = new StringBuilder();

        if (dt == null) {
            errors.append("Tanggal tidak boleh kosong");
        }

        if (start.isEmpty()) {   
            errors.append("Jam mulai tidak boleh kosong");
        }

        if (end.isEmpty()) {
            errors.append("Jam selesai tidak boleh kosong");
        }

        if (desc.isEmpty()) {
           errors.append("Keterangan tidak boleh kosong");
        }
        
        boolean isValid = errors.length() == 0;
        return new ValidationOption(isValid, errors.toString());

    }
}
