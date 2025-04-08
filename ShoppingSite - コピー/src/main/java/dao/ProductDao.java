package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDto;
import util.DBUtil;

public class ProductDao {

    // 商品一覧取得
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

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
                    rs.getString("image_Path")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    
    public List<ProductDto> getSortedProducts(String sort) {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        switch (sort) {
            case "price_asc":
                sql += " ORDER BY price ASC";
                break;
            case "price_desc":
                sql += " ORDER BY price DESC";
                break;
            case "name_asc":
                sql += " ORDER BY name ASC";
                break;
            case "name_desc":
                sql += " ORDER BY name DESC";
                break;
            default:
                sql += " ORDER BY price ASC"; // デフォルト設定
                break;
        }

        try (Connection conn = DBUtil.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductDto product = new ProductDto();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImagePath(rs.getString("image_Path"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    

    // 商品追加
    public void addProduct(ProductDto product) {
        String sql = "INSERT INTO products (name, description, price, stock, image_path) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getImagePath());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 商品更新
    public void updateProduct(ProductDto product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ? WHERE product_id = ?";

        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 商品削除
    public void deleteProduct(int product_id) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // ID で商品を取得
    public ProductDto getProductById(int product_id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DBUtil.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductDto(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getInt("stock"),
                    rs.getString("image_Path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ProductDto(0, "未取得", "データなし", 0, 0, "データなし"); // デフォルト値を返す
    }
    
    
}

