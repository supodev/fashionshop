/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Kho;
import model.SanPham;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class KhoDAO extends FashionDAO<Kho, Integer> {

    @Override
    public void insert(Kho entity) {
        String sql = "INSERT INTO Kho(MaSP,MaDVT,SLNhap,GiaNhap,TongGia,NgayTao) VALUES (?,?,?,?,?,?)";
        XJdbc.update(sql, entity.getMaSP(),entity.getMaDVT(), entity.getSlNhap(), entity.getGiaNhap(), entity.getTongGia(), entity.getNgayTao());
    }

    @Override
    public void update(Kho entity) {
        String sql = "UPDATE Kho SET SLNhap=?,GiaNhap=?,NgayTao=? WHERE MaSP = ?";
        XJdbc.update(sql,  entity.getSlNhap(), entity.getGiaNhap(), entity.getNgayTao(), entity.getMaSP());
    }
    

    @Override
    public void delete(Integer key) {
        String sql = "DELETE FROM Kho WHERE MaNK=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<Kho> selectAll() {
        String sql = "SELECT * FROM Kho";
       return this.selectBySql(sql);
    }

    @Override
    public Kho selectById(Integer key) {
        String sql = "SELECT * FROM Kho WHERE MaNK=?";
        List<Kho> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    @Override
    protected List<Kho> selectBySql(String sql, Object... args) {
        List<Kho> list = new ArrayList<Kho>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                Kho ko = new Kho();
                ko.setMaNK(rs.getInt("MaNK"));
                ko.setMaSP(rs.getString("MaSP"));
                ko.setMaDVT(rs.getInt("MaDVT"));
                ko.setSlNhap(rs.getInt("SLNhap"));
                ko.setGiaNhap(rs.getDouble("GiaNhap"));
                ko.setTongGia(rs.getDouble("TongGia"));
                ko.setNgayTao(rs.getDate("NgayTao"));
                list.add(ko);
            }
            rs.getStatement().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public Kho selectBySP(String key) {
        String sql = "SELECT * FROM Kho WHERE MaSP=?";
        List<Kho> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public List<Kho> selectByName(String ten){
        String sql = "SELECT * FROM Kho WHERE MaSP = ?";
        return this.selectBySql(sql,ten);
    }
    public void updateSL(int soLuong , String maSP) {
        String sql = "UPDATE Kho SET SLNhap=? WHERE MaSP = ?";
        XJdbc.update(sql,soLuong,maSP);
    }
    
}
