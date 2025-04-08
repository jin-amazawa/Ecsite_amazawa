package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import servlet.model.Product;
import dto.ProductDto;
import util.DBUtil;

public class SearchDao {
    public static List<ProductDto> searchProducts(String query){
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name COLLATE utf8mb4_general_ci LIKE ?";


        try (Connection conn = DBUtil.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDto product = new ProductDto();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImagePath(rs.getString("image_path"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

