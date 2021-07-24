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
import model.LoaiSanPham;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class LoaiSanPhamDAO extends FashionDAO<LoaiSanPham, Integer>{

    @Override
    public void insert(LoaiSanPham entity) {
        String sql = "INSERT INTO LoaiSanPham(TenLSP) VALUES (?)";
        XJdbc.update(sql, entity.getTenLSP());
    }

    @Override
    public void update(LoaiSanPham entity) {
        String sql = "UPDATE LoaiSanPham SET TenLSP=? WHERE MaLSP = ?";
        XJdbc.update(sql,entity.getTenLSP(),entity.getMaLSP());
    }

    @Override
    public void delete(Integer key) {
        String sql = "DELETE FROM LoaiSanPham WHERE MaLSP=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<LoaiSanPham> selectAll() {
        String sql="SELECT * FROM LoaiSanPham";
        return this.selectBySql(sql);
    }

    @Override
    public LoaiSanPham selectById(Integer key) {
        String sql = "SELECT * FROM LoaiSanPham WHERE MaLSP=?";
        List<LoaiSanPham> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public LoaiSanPham selectByName(Object keyword){
        String sql = "SELECT * FROM LoaiSanPham WHERE TenLSP = ?";
        List<LoaiSanPham> list = this.selectBySql(sql, keyword);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<LoaiSanPham> selectBySql(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<LoaiSanPham>();
        try {
            ResultSet rs  = XJdbc.query(sql, args);
            while(rs.next()){
                LoaiSanPham ls = new LoaiSanPham();
                ls.setMaLSP(rs.getInt("MaLSP"));
                ls.setTenLSP(rs.getString("TenLSP"));
                list.add(ls);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
