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
import utils.XJdbc;

/**
 *
 * @author vvtvo
 */
public class ThongKeDAO {
    public List<Object[]> getListOfArray(String sql,String[] cols,Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getHD(int month, int year){
        String sql  = "{CALL tk_HoaDon(?,?)}";
        String[] cols = {"MaHD", "tongSP","giamgia","ngaytao","tennv"};
        return this.getListOfArray(sql, cols, month, year);
    }
    public List<Object[]> getSP(int month, int year){
        String sql  = "{CALL tk_SPBan(?,?)}";
        String[] cols = {"TenSP", "TenDVT","soluong","DonGia","thanhtien","gianhap","tongianhap"};
        return this.getListOfArray(sql, cols, month, year);
    }
    public List<Object[]> getHD1(int day, int month, int year){
        String sql  = "{CALL tk_HoaDon1(?,?,?)}";
        String[] cols = {"MaHD", "tongSP","giamgia","ngaytao","tennv"};
        return this.getListOfArray(sql, cols, day, month, year);
    }
    public List<Object[]> getSP1(int day, int month, int year){
        String sql  = "{CALL tk_SPBan1(?,?,?)}";
        String[] cols = {"TenSP", "TenDVT","soluong","DonGia","thanhtien","gianhap","tongianhap"};
        return this.getListOfArray(sql, cols, day, month, year);
    }
    public List<Object[]> getAllHD(){
        String sql  = "{CALL tk_ToanHoaDon}";
        String[] cols = {"MaHD", "tongSP","giamgia","ngaytao","tennv"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getAllSP(){
        String sql  = "{CALL tk_ToanSanPham}";
        String[] cols = {"TenSP", "TenDVT","soluong","DonGia","thanhtien","gianhap","tongianhap"};
        return this.getListOfArray(sql, cols);
    }
    
}
