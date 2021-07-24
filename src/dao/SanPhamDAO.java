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
import model.SanPham;
import utils.XJdbc;

/**
 *
 * @author Tong Van Hai
 */
public class SanPhamDAO extends FashionDAO<SanPham, String>{

    @Override
    public void insert(SanPham sp) {
        String sql = "INSERT INTO SanPham(MaSP,TenSP,GiaBan,MaDVT,MaLSP,NgayTao,Hinh,MaNV) VALUES (?,?,?,?,?,?,?,?)";
        XJdbc.update(sql, sp.getMaSP(),sp.getTenSP(),sp.getGiaBan(),sp.getMaDVT(),sp.getMaLSP(),sp.getNgayTao(),sp.getHinh(),sp.getMaNV());
    }

    @Override
    public void update(SanPham sp) {
        String sql = "UPDATE SanPham SET tenSP=?, GiaBan=?, MaDVT=?, MaLSP=?,  NgayTao=?, Hinh=? ,MaNV=? WHERE MaSP=?";
        XJdbc.update(sql, sp.getTenSP(),sp.getGiaBan(),sp.getMaDVT(),sp.getMaLSP(),sp.getNgayTao(),sp.getHinh(),sp.getMaNV(),sp.getMaSP());
    }

    @Override
    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE MaSP=?";
        XJdbc.update(sql, MaSP);
    }

    @Override
    public List<SanPham> selectAll() {
       String sql = "SELECT * FROM SanPham";
       return this.selectBySql(sql);
    }

    @Override
    public SanPham selectById(String MaSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = this.selectBySql(sql, MaSP);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    public SanPham selectByName(String tenSP) {
        String sql = "SELECT * FROM SanPham WHERE TenSP=?";
        List<SanPham> list = this.selectBySql(sql, tenSP);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
      List<SanPham> list = new ArrayList<SanPham>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMaDVT(rs.getInt("MaDVT"));
                sp.setMaLSP(rs.getInt("MaLSP"));
                sp.setNgayTao(rs.getDate("NgayTao"));
                sp.setHinh(rs.getString("Hinh"));
                sp.setMaNV(rs.getString("MaNV"));
                list.add(sp);
            }
            rs.getStatement().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public List<SanPham> selectByLSP(int maLSP){
        String sql = "SELECT * FROM SanPham WHERE MaLSP = ?";
        return this.selectBySql(sql, maLSP);
    }
    public List<SanPham> selectByNameAndId(String tenMa){
        String sql = "SELECT * FROM SanPham WHERE MaSP = ? OR TenSP Like ?";
        return this.selectBySql(sql,tenMa,"%"+tenMa+"%");
    }

}
