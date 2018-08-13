package io.github.guilhermedelemos.ariacrawler.db;

import org.flywaydb.core.Flyway;

public class Migration {

    public static final String USER = "aria-crawler";
    public static final String TABLE = "schema_history";
    public static final String LOCAL_DATABASE = "filesystem:src/main/resources/db/migration/local";
    public static final String LOG_DATABASE = "filesystem:src/main/resources/db/migration/log";

    public void localMigration(Connection conn) {
        this.migration(conn, USER, TABLE, LOCAL_DATABASE);
    }

    public void logMigration(Connection conn) {
        this.migration(conn, USER, TABLE, LOG_DATABASE);
    }

    public void migration(Connection conn, String user, String table, String database) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(conn.getUrl(), user, null);
        flyway.setInstalledBy(user);
        flyway.setTable(table);
        flyway.setLocations(database);
        flyway.migrate();
    }

}
