package io.github.guilhermedelemos.ariacrawler.db;

import org.flywaydb.core.Flyway;

public class Migration {

    public void localMigration(Connection connection) {
        String url = connection.getUrl();
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, "aria-crawler", null);
        flyway.setInstalledBy("aria-crawler");
        flyway.setTable("schema_history");
        flyway.setLocations("filesystem:src/main/resources/db/migration/local");
        flyway.migrate();
    }

    public void logMigration(Connection connection) {
        String url = connection.getUrl();
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, "aria-crawler", null);
        flyway.setInstalledBy("aria-crawler");
        flyway.setTable("schema_history");
        flyway.setLocations("filesystem:src/main/resources/db/migration/log");
        flyway.migrate();
    }
}
