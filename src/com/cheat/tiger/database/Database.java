package com.cheat.tiger.database;

import com.cheat.tiger.TigerHack;

import java.sql.*;
import java.util.UUID;

public class Database {

    private static Connection connection = null;
    private static String url, user, pass;

    private static String nameTigerTable = "tigerhack";
    private static String nameTigerBansTable = "tigerhack_bans";

    public static void setAuth(String url, String user, String pass){
        Database.url = url;
        Database.user = user;
        Database.pass = pass;
    }

    public static void connect() throws SQLException {
        if(connection != null)
            disconnect();
        if(url != null && user != null && pass != null){
            connection = DriverManager.getConnection(url, user, pass);
            TigerHack.log("Connection database success!");
        }else TigerHack.log("[TigerHack Database Connection] Connection Error");
    }

    private static void disconnect() throws SQLException {
        if(!connection.isClosed()){
            connection.close();
            TigerHack.log("Connection database closed with success!");
        }
    }

    private static PreparedStatement createStatement(String query) throws SQLException {
        if(connection.isClosed())
            connect();
        return connection.prepareStatement(query);
    }

    public static int pluginHasGoodLicence(String licence) throws SQLException{
        int activated = 0;
        PreparedStatement queryStatement = Database.createStatement("SELECT activated FROM licence WHERE licence = ?");
        queryStatement.setString(1, licence);
        ResultSet rs = queryStatement.executeQuery();
        if(!rs.next()) {
            return activated;
        }
        activated = rs.getInt("activated");
        queryStatement.close();
        return activated;
    }

    public static void createTableTigerHack() throws SQLException{
        String createTable = "CREATE TABLE IF NOT EXISTS " + Database.nameTigerTable + " (id int(11) NOT NULL AUTO_INCREMENT, uuid varchar(36) NOT NULL, " +
                "name varchar(16) NOT NULL, date datetime NOT NULL, PRIMARY KEY (id)) ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT = 1";

        PreparedStatement queryStatement = createStatement(createTable);
        queryStatement.executeUpdate();
    }

    public static void createTableTigerHackBans() throws SQLException{
        String createTable = "CREATE TABLE IF NOT EXISTS " + Database.nameTigerBansTable + " (id int(11) NOT NULL AUTO_INCREMENT, uuid varchar(36) NOT NULL, " +
                "name varchar(16) NOT NULL, reason varchar(100) NOT NULL, date datetime NOT NULL, server varchar(20) NOT NULL, ping int(11) NOT NULL, " +
                "PRIMARY KEY (id)) ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT = 1";

        PreparedStatement queryStatement = createStatement(createTable);
        queryStatement.executeUpdate();
    }

    public static void createPlayerProfile(UUID uuid, String name, String date) throws SQLException {
        PreparedStatement queryStatement = createStatement("INSERT INTO tigerhack (uuid, name, date) VALUES (?, ?, ?)");
        queryStatement.setString(1, uuid.toString());
        queryStatement.setString(2, name);
        queryStatement.setString(3, date);
        queryStatement.executeUpdate();
    }

    public static void createPlayerBanProfile(UUID uuid, String name, String reason, String date, String serverName, int ping) throws SQLException {
        PreparedStatement queryStatement = createStatement("INSERT INTO tigerhack_bans (uuid, name, reason, date, server, ping) VALUES (?, ?, ?, ?, ?, ?)");
        queryStatement.setString(1, uuid.toString());
        queryStatement.setString(2, name);
        queryStatement.setString(3, reason);
        queryStatement.setString(4, date);
        queryStatement.setString(5, serverName);
        queryStatement.setInt(6, ping);
        queryStatement.executeUpdate();
        TigerHack.log("[TigerHack Bans Database] Player added!");
    }

    public static boolean profileExists(UUID uuid) throws SQLException {
        PreparedStatement queryStatement = createStatement("SELECT uuid FROM tigerhack WHERE uuid = ?");
        queryStatement.setString(1, uuid.toString());
        queryStatement.executeQuery();
        ResultSet resultSet = queryStatement.getResultSet();
        boolean exists = resultSet.next();
        queryStatement.close();
        return exists;
    }

    public static boolean profileExistsIntoBans(UUID uuid) throws SQLException {
        PreparedStatement queryStatement = createStatement("SELECT uuid FROM tigerhack_bans WHERE uuid = ?");
        queryStatement.setString(1, uuid.toString());
        queryStatement.executeQuery();
        ResultSet resultSet = queryStatement.getResultSet();
        boolean exists = resultSet.next();
        queryStatement.close();
        return exists;
    }

    public static String getBanReason(UUID uuid){
        String reason = "";
        try {
            PreparedStatement preparedStatement = createStatement("SELECT reason FROM tigerhack_bans WHERE uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                return String .valueOf(reason);
            }
            reason = rs.getString("reason");
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(reason);
    }
}
