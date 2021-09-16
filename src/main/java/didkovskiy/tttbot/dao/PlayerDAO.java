package didkovskiy.tttbot.dao;

import didkovskiy.tttbot.JDBCUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerDAO {
    private final Connection connection;

    public PlayerDAO(JDBCUtil jdbcUtil) {
        connection = jdbcUtil.getConnection();
    }

    public Map<String, Integer> getRatingMap() {
        Map<String, Integer> ratingMap = new HashMap<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM player");
            while (rs.next()) {
                ratingMap.put(rs.getString("nickname"), rs.getInt("points"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratingMap;
    }

    public void saveNewPlayer(String nickname) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO player(nickname) VALUES (?)");
            stmt.setString(1, nickname);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlayerExists(String nickname) {
        boolean res = false;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM player WHERE nickname = ?");
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();
            res = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
