/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author vvtvo
 */
public class HoaDon {
    private String maHD;
    private String maKH;
    private Date ngayTao;
    private double tienDV;
    private double giamGia;
    private double tongTien;
    private double tienKhachTra;
    private double tienTraKhach;
    private String maNV;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public double getTienDV() {
        return tienDV;
    }

    public void setTienDV(double tienDV) {
        this.tienDV = tienDV;
    }
    

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }
    

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }


    public double getTienTraKhach() {
        return tienTraKhach;
    }

    public void setTienTraKhach(double tienTraKhach) {
        this.tienTraKhach = tienTraKhach;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
}
