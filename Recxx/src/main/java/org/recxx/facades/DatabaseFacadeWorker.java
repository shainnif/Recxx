package org.recxx.facades;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.recxx.AbstractRecFeed;
import org.recxx.Recxx;
import org.recxx.utils.FileUtils;

/**
 * This class represents a facade on to a Database for use as a data source when
 * rec'ing 2 sources of data
 */
public class DatabaseFacadeWorker extends AbstractRecFeed implements RecxxWorker {

    protected Properties properties = new Properties();
    private Connection connection;
    private Statement statment;
    private Recxx rec = null;

    Logger LOGGER = Logger.getLogger(DatabaseFacadeWorker.class.getName());

    /**
     * Constructor for DatabaseFacadeWorker.
     */
    public DatabaseFacadeWorker(String prefix, String propertiesFile) {
        // constructor
        init(prefix, propertiesFile);
    }

    /**
     * run the class
     *
     * @see Runnable#run()
     */
    public void run() {
        try {
            getData();

            // update the data back..
            HashMap finishedData = new HashMap();
            finishedData.put(Recxx.COLUMNS, columns);
            finishedData.put(Recxx.DATA, data);
            finishedData.put(Recxx.PROPERTIES, properties);

            rec.setDataToCompare(finishedData,
                    properties.getProperty("order"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set the properties object
     */
    public void setRunTimeProperties(Properties p) {
        properties = p;
    }

    /**
     * validate the properties, run the query and process the data
     */
    private void getData() throws Exception {
        String uid = properties.getProperty("uid");
        String pwd = properties.getProperty("pwd");
        String sql = properties.getProperty("sql");
        String url = properties.getProperty("url");
        String key = properties.getProperty("key");
        String driver = properties.getProperty("driver");

        if (uid == null || pwd == null || sql == null || url == null
                || driver == null || key == null) {
            properties.list(System.err);
            throw new Exception(
                    "One or properties could not be found to initialise class..properties set are: ");
        } else {
            ResultSet rs = performDBQuery(driver, sql, uid, url, pwd);

            data = processResultSet(key, rs, properties);

            rs.close();

            closeDB();
        }

    }

    /**
     * execute the given sql on the given database
     *
     * @param driver
     * @param sql
     * @param uid
     * @param url
     * @param pwd
     * @return ResultSet
     */
    private ResultSet performDBQuery(String driver, String sql, String uid,
                                     String url, String pwd) throws Exception {
        ResultSet rs = null;
        openDB(driver, url, uid, pwd);
        File file = new File(sql);
        if (file.exists()) {
        	LOGGER.log(Level.INFO, "File based SQL discovered, will attempt to load file");
        	sql = FileUtils.readFileAsString(file);
        }
        LOGGER.log(Level.INFO, "Running sql :" + sql);
        rs = statment.executeQuery(sql);
        return rs;
    }

    /**
     * open the database connection
     *
     * @param driver
     * @param url
     * @param uid
     * @param pwd
     * @throws ClassNotFoundException if the JDBC driver can't be found
     * @throws java.sql.SQLException
     */
    private void openDB(String driver, String url, String uid, String pwd)
            throws ClassNotFoundException, SQLException {
        Class.forName(driver);

        // get the connection
        connection = DriverManager.getConnection(url, uid, pwd);
        statment = connection.createStatement();

        LOGGER.log(Level.INFO, "Connected to DB using " + url);
        LOGGER.log(Level.INFO, "Successfully initialised the DB connections...");
    }

    /**
     * close the database connection
     *
     * @throws java.sql.SQLException
     */
    private void closeDB() throws SQLException {
        if (statment != null)
            statment.close();

        if (connection != null)
            connection.close();

    }

    /**
     * set the datastore ot send the data back to..
     */
    public void setDataStore(Recxx rec) {
        this.rec = rec;
    }

}
