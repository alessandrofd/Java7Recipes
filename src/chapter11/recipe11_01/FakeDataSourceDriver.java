package chapter11.recipe11_01;

/**
 * This class represents a fake database driver class that is used for demonstration purposes only.
 */
public class FakeDataSourceDriver {

    private String databaseName;
    private String serverName;
    private String description;

    public FakeDataSourceDriver() { }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
