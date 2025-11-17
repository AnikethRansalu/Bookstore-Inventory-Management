package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class CategoryDAO extends BaseDAO {

    public Map<Integer,String> getAllCategories() {
        Map<Integer,String> map = new LinkedHashMap<>();
        String sql = "SELECT id, category_name FROM categories ORDER BY category_name";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getInt("id"), rs.getString("category_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}