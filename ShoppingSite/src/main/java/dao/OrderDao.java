package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.OrderHistoryDto;
import dto.ProductDto;
import util.DBUtil;

public class OrderDao {

    public int insertOrder(int userId, List<ProductDto> cart) throws SQLException {
        Connection conn = DBUtil.openConnection();

        try {
            conn.setAutoCommit(false);

            // 注文情報を orders に追加
            String orderSql = "INSERT INTO orders (user_id) VALUES (?)";
            PreparedStatement psOrder = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userId);
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1); // 注文IDを取得

            // 商品ごとの注文情報を order_items に追加
            String itemSql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement psItem = conn.prepareStatement(itemSql);

            for (ProductDto item : cart) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getId());
                psItem.setInt(3, item.getStock()); // stock を数量として使用
                psItem.addBatch();
            }

            psItem.executeBatch();
            conn.commit();

            return orderId;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    
    public List<OrderHistoryDto> findAllOrderHistory() throws SQLException {
        List<OrderHistoryDto> list = new ArrayList<>();
        Connection conn = DBUtil.openConnection();

        String sql = "SELECT u.username, p.name AS product_name, oi.quantity, o.order_date " +
                     "FROM orders o " +
                     "JOIN users u ON o.user_id = u.user_id " +
                     "JOIN order_items oi ON o.order_id = oi.order_id " +
                     "JOIN products p ON oi.product_id = p.product_id " +
                     "ORDER BY o.order_date DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            OrderHistoryDto dto = new OrderHistoryDto();
            dto.setUsername(rs.getString("username"));
            dto.setProductName(rs.getString("product_name"));
            dto.setQuantity(rs.getInt("quantity"));
            dto.setOrderDate(rs.getTimestamp("order_date"));
            list.add(dto);
        }

        conn.close();
        return list;
    }

    
    
}
