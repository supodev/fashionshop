/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonChiTiet;
import model.SanPham;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class HoaDonChiTietDAO extends FashionDAO<HoaDonChiTiet, Integer>{

    @Override
    public void insert(HoaDonChiTiet entity) {
        String sql = "INSERT INTO HoaDonChiTiet (MaHD,MaSP,DonGia,SoLuong,ThanhTien) VALUES (?,?,?,?,?)";
        XJdbc.update(sql, entity.getMaHD(),entity.getMaSP(),entity.getDonGia(),entity.getSoLuong(),entity.getThanhTien());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
         String sql = "UPDATE HoaDonChiTiet SET MaHD,MaSP,DonGia,SoLuong,ThanhTien WHERE MaHDCT=?";
         XJdbc.update(sql, entity.getMaHD(),entity.getDonGia(),entity.getSoLuong(),entity.getThanhTien(),entity.getMaHDCT());
    }

    @Override
    public void delete(Integer key) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE MaHDCT";
        XJdbc.update(sql, key);
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return this.selectBySql(sql);
    }

    @Override
    public HoaDonChiTiet selectById(Integer key) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHDCT=?";
        List<HoaDonChiTiet> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<HoaDonChiTiet>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                HoaDonChiTiet cc = new HoaDonChiTiet();
                cc.setMaHDCT(rs.getInt("MaHDCT"));
                cc.setMaHD(rs.getString("MaHD"));
                cc.setMaSP(rs.getString("MaSP"));
                cc.setDonGia(rs.getDouble("DonGia"));
                cc.setSoLuong(rs.getInt("Soluong"));
                cc.setThanhTien(rs.getDouble("ThanhTien"));
                list.add(cc);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public List<HoaDonChiTiet> selectByHD(String key) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD=?";
        return this.selectBySql(sql, key);
    }
    public HoaDonChiTiet selectByIdSP(SanPham sp) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaSP=?";
        List<HoaDonChiTiet> list = this.selectBySql(sql, sp);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

}
