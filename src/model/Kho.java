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
public class Kho {

    private int maNK;
    private String maSP;
    private int maDVT;
    private int slNhap;
    private double giaNhap;
    private double tongGia;
    private Date ngayTao;

    public int getMaNK() {
        return maNK;
    }

    public void setMaNK(int maNK) {
        this.maNK = maNK;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(int maDVT) {
        this.maDVT = maDVT;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getTongGia() {
        return tongGia ;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = this.slNhap * this.giaNhap;
        
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
}
