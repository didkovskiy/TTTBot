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
        String getAllRatingRecordsSQL = "SELECT * FROM player";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(getAllRatingRecordsSQL);
            while (rs.next()) {
                ratingMap.put(rs.getString("nickname"), rs.getInt("points"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratingMap;
    }

    public void saveNewPlayer(String nickname) {
        String saveNewPlayerSQL = "INSERT INTO player(nickname) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(saveNewPlayerSQL)) {
            stmt.setString(1, nickname);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlayerExists(String nickname) {
        boolean res = false;
        String findPlayerByNicknameSQL = "SELECT * FROM player WHERE nickname = ?";
        try (PreparedStatement stmt = connection.prepareStatement(findPlayerByNicknameSQL)) {
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();
            res = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void addPointsToThePlayer(String nickname) {
        String addPointsToThePlayerSQL = "UPDATE player SET points = points + 10 WHERE nickname = ?";
        try (PreparedStatement stmt = connection.prepareStatement(addPointsToThePlayerSQL)) {
            stmt.setString(1, nickname);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePointsFromAPlayer(String nickname) {
        String removePointsFromAPlayer = "UPDATE player SET points = points - 10 WHERE nickname = ?";
        try (PreparedStatement stmt = connection.prepareStatement(removePointsFromAPlayer)) {
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
}
