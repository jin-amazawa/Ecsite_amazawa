package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.UsersDto;
import util.DBUtil;

public class UsersDao {
	
    public UsersDto selectByIdAndPassword(String username, String password) {
        UsersDto user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection con = DBUtil.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UsersDto();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));  // ハッシュ化が必要
                user.setRole(rs.getString("role")); // role カラムがある場合
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
    public boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'user')";

        try (Connection con = DBUtil.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // 実際はパスワードをハッシュ化する
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    
}

