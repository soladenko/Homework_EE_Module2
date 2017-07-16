package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SO on 15.06.2017.
 */
public final class Utils {

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sergey?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
    }
}