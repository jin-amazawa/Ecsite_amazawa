package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servlet.model.Product;
import util.DBUtil;

public class HomeDao {
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        
        String sql = "SELECT product_id, name, price, description, image_path FROM products";
        
        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("description"),
                    rs.getString("image_path")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
}

