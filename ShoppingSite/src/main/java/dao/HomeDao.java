package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDto;
import util.DBUtil;

public class HomeDao {
    
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        
        String sql = "SELECT product_id, name, price, description, stock, image_path FROM products";
        
        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ProductDto product = new ProductDto(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getInt("stock"),
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

