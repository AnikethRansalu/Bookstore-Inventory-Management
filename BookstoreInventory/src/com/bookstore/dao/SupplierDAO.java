package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class SupplierDAO extends BaseDAO {

    public Map<Integer,String> getAllSuppliers() {
        Map<Integer,String> map = new LinkedHashMap<>();
        String sql = "SELECT id, name FROM suppliers ORDER BY name";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getInt("id"), rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}