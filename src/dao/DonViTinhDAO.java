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
import model.DonViTinh;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class DonViTinhDAO extends FashionDAO<DonViTinh, Integer>{

    @Override
    public void insert(DonViTinh entity) {
        String sql = "INSERT INTO DonViTinh(TenDVT) VALUES (?)";
        XJdbc.update(sql, entity.getTenDVT());
    }

    @Override
    public void update(DonViTinh entity) {
        
    }

    @Override
    public void delete(Integer key) {
        String sql = "DELETE FROM DonViTinh WHERE MaDVT=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<DonViTinh> selectAll() {
        String sql="SELECT * FROM DonViTinh";
        return this.selectBySql(sql);
    }

    @Override
    public DonViTinh selectById(Integer key) {
        //chỉ lấy tên
        String sql = "SELECT * FROM DonViTinh WHERE MaDVT=?";
        List<DonViTinh> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public DonViTinh selectByName(String key) {
        //chỉ lấy tên
        String sql = "SELECT * FROM DonViTinh WHERE TenDVT=?";
        List<DonViTinh> list = this.selectBySql(sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DonViTinh> selectBySql(String sql, Object... args) {
        List<DonViTinh> list = new ArrayList<DonViTinh>();
        try {
            ResultSet rs  = XJdbc.query(sql, args);
            while(rs.next()){
                DonViTinh dt = new DonViTinh();
                dt.setMaDVT(rs.getInt("MaDVT"));
                dt.setTenDVT(rs.getString("TenDVT"));
                list.add(dt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
