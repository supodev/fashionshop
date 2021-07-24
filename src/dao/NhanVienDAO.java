/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class NhanVienDAO extends FashionDAO<NhanVien, String>{
    @Override
    public void insert(NhanVien nv){
        String sql = "INSERT INTO NhanVien(MaNV,TenNV,MatKhau,VaiTro,Hinh)VALUES(?,?,?,?,?)";
        XJdbc.update(sql, nv.getMaNV(),nv.getTenNV(),nv.getMatKhau(),nv.isVaiTro(),nv.getHinh());
    }
    @Override
    public void update(NhanVien nv){
        String sql = "UPDATE NhanVien SET TenNV=? ,MatKhau=?,VaiTro=? ,Hinh=? WHERE MaNV=?";
        XJdbc.update(sql ,nv.getTenNV(),nv.getMatKhau(),nv.isVaiTro(),nv.getHinh(),nv.getMaNV());
    }
    @Override
    public void delete(String MaNV){
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, MaNV);
    }
    @Override
    public List<NhanVien> selectAll(){
        String sql="SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }  
    @Override
    public NhanVien selectById(String id){
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    @Override
    public List<NhanVien> selectBySql(String sql,Object...args){
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs  = XJdbc.query(sql, args);
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                nv.setHinh(rs.getString("Hinh"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
