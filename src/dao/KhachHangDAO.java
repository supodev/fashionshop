/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import utils.XJdbc;

/**
 *
 * @author Tong Van Hai
 */
public class KhachHangDAO extends FashionDAO<KhachHang, String> {

    @Override
    public void insert(KhachHang kh) {
        String sql = "INSERT INTO KhachHang(MaKH,TenKH,GioiTinh,SDT,DiaChi,GhiChu,NgayTao) VALUES (?,?,?,?,?,?,?)";
        XJdbc.update(sql, kh.getMaKH(), kh.getTenKH(), kh.isGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getGhiChu(), kh.getNgayTao());
    }

    @Override
    public void update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TenKH=?, GioiTinh=?, SDT=?, DiaChi=?, GhiChu=?, NgayTao=? WHERE MaKH=?";
        XJdbc.update(sql, kh.getTenKH(), kh.isGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getGhiChu(), kh.getNgayTao(), kh.getMaKH());
    }

    @Override
    public void delete(String MaKH) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        XJdbc.update(sql, MaKH);
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return this.selectBySql(sql);
    }

    @Override
    public KhachHang selectById(String kh) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = this.selectBySql(sql, kh);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public KhachHang selectByName(String ten) {
        String sql = "SELECT * FROM KhachHang WHERE TenKH=?";
        List<KhachHang> list = this.selectBySql(sql, ten);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                list.add(kh);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
