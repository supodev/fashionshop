/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class HoaDonDAO extends FashionDAO<HoaDon, String> {

    @Override
    public void insert(HoaDon entity) {
        String sql = "INSERT INTO HoaDon(MaHD,MaKH,NgayTao,TienDV,GiamGia,TongTien,TienKhachTra,TienTraKhach,MaNV) VALUES (?,?,?,?,?,?,?,?,?)";
        XJdbc.update(sql, entity.getMaHD(), entity.getMaKH(), entity.getNgayTao(),entity.getTienDV(), entity.getGiamGia(), entity.getTongTien(), entity.getTienKhachTra(),entity.getTienTraKhach(),entity.getMaNV());
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HoaDon SET MaKH = ?,NgayTao= ?,TienDV = ? ,GiamGia = ?,TongTien= ?,TienTraKhach= ?,TienKhachTra = ? ,MaNV=? WHERE MaHD=?";
        XJdbc.update(sql, entity.getMaKH(), entity.getNgayTao(),entity.getTienDV(),entity.getGiamGia(), entity.getTongTien(), entity.getTienTraKhach(),entity.getTienKhachTra(),entity.getMaNV(), entity.getMaHD());
    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM HoaDon WHERE MaHD";
        XJdbc.update(sql, key);
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM HoaDon";
        return this.selectBySql(sql);
    }

    @Override
    public HoaDon selectById(String key) {
        String sql = "SELECT * FROM SanPham WHERE MaHD=?";
        List<HoaDon> list = this.selectBySql(sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienTraKhach(rs.getDouble("TienTraKhach"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTienDV(rs.getDouble("TienDV"));
                hd.setGiamGia(rs.getDouble("GiamGia"));
                hd.setMaNV(rs.getString("MaNV"));
                list.add(hd);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
