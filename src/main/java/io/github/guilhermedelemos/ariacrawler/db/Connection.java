package io.github.guilhermedelemos.ariacrawler.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private java.sql.Connection connection = null;
    private String databaseName;
    private String databasePath;
    private String databaseUrl;

    public Connection() {
        super();
        this.init();
    }

    private void init() {
        this.databaseName = "local.db";
        this.databasePath = "./data/db/";
        this.databaseUrl = "jdbc:sqlite:";
    }

    public void connect() {
        try {
            //Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(this.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return this.databaseUrl + this.databasePath + this.databaseName;
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
}
