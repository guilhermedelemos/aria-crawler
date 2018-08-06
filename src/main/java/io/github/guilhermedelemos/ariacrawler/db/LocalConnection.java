package io.github.guilhermedelemos.ariacrawler.db;

import java.sql.SQLException;

public class LocalConnection {
    private static LocalConnection ourInstance = new LocalConnection();
    private Connection local;
    private Connection log;

    public static LocalConnection getInstance() {
        return ourInstance;
    }

    private LocalConnection() {
        super();
        this.init();
    }

    private void init() {
        this.local = new Connection();
        this.local.setDatabaseName("local.db");
        this.local.connect();
        this.log = new Connection();
        this.log.setDatabaseName("log.db");
        this.log.connect();
    }

    public void diconnect() {
        try {
            this.local.getConnection().close();
            this.log.getConnection().close();
        } catch(SQLException e) {
            //e.printStackTrace();
        }
    }

    public Connection getLocal() {
        return local;
    }

    public Connection getLog() {
        return log;
    }
}
