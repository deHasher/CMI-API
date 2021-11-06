package com.Zrips.CMI.Modules.DataBase;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Zrips.CMI.CMI;

public class DBConnectionPool {
    private DBConnection connection;
    private String url;
    private String username;
    private String password;
    public DBConnectionPool(String driverName, String url, String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Driver driver = (Driver) Class.forName(driverName, true, CMI.getInstance().getDBClassloader()).newInstance();
        DBDrivers jDriver = new DBDrivers(driver);
        DriverManager.registerDriver(jDriver);
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public synchronized DBConnection getConnection() throws SQLException {
      
        return connection;
    }
    
    public synchronized void closeConnection() {

    }
}
