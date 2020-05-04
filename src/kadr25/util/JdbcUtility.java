package kadr25.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtility {
    public static void close(Connection c, ResultSet rs, PreparedStatement ps) throws Exception {
        if (c != null) {
            c.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}
