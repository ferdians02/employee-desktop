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
        
    public static boolean validationKaryawan(String nik, String nama, String no, String alamat, String divisi, String jabatan, String jenis) {
        if (nik.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nomor induk karyawan tidak boleh kosong");
            return false;
        } else if (nik.length() > 32 && nik.length() < 0) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Induk Karyawan tidak boleh [%d] atau [%]", nik.length()));
            return false;
        }

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Karyawan tidak boleh kosong");
            return false;
        } else if (nama.length() > 128 && nama.length() < 0) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nama tidak boleh [%d] atau [%d]", nama.length()));
            return false;
        }

        if (no.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Handphone tidak boleh kosong");
            return false;
        } else if (no.length() > 12) {
            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Handphone tidak boleh lebih dari [%d]", no));
            return false;
        }

        if (alamat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alamat tidak boleh kosong");
            return false;
        }

        if (divisi.equalsIgnoreCase("Pilih") || divisi == null) {
            JOptionPane.showMessageDialog(null, "Divisi harus dipilih");
            return false;
        }

        if (jabatan.equals("Pilih") || jabatan == null) {
            JOptionPane.showMessageDialog(null, "Jabatan harus dipilih");
            return false;
        }

        if (jenis.equalsIgnoreCase("Pilih")) {
            JOptionPane.showMessageDialog(null, "Jenis kelamin harus dipilih");
            return false;
        }
        
        return true;
    }

//    public static void validationKaryawan(String nik, String nama, String no, String alamat, String divisi, String jabatan, String jenis) {
//        if (nik.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Nomor induk karyawan tidak boleh kosong");
//            return;
//        } else if (nik.length() > 32 && nik.length() < 0) {
//            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Induk Karyawan tidak boleh [%d] atau [%]", nik.length()));
//            return;
//        }
//
//        if (nama.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Nama Karyawan tidak boleh kosong");
//            return;
//        } else if (nama.length() > 128 && nama.length() < 0) {
//            JOptionPane.showMessageDialog(null, String.format("Panjang Nama tidak boleh [%d] atau [%d]", nama.length()));
//            return;
//        }
//
//        if (no.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "No Handphone tidak boleh kosong");
//            return;
//        } else if (no.length() > 12) {
//            JOptionPane.showMessageDialog(null, String.format("Panjang Nomor Handphone tidak boleh lebih dari [%d]", no));
//            return;
//        }
//
//        if (alamat.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Alamat tidak boleh kosong");
//            return;
//        }
//
//        if (divisi.equalsIgnoreCase("Pilih") || divisi == null) {
//            JOptionPane.showMessageDialog(null, "Divisi harus dipilih");
//            return;
//        }
//
//        if (jabatan.equals("Pilih") || jabatan == null) {
//            JOptionPane.showMessageDialog(null, "Jabatan harus dipilih");
//            return;
//        }
//
//        if (jenis.equalsIgnoreCase("Pilih")) {
//            JOptionPane.showMessageDialog(null, "Jenis kelamin harus dipilih");
//            return;
//        }
//        
//        
//    }
//
//    
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
