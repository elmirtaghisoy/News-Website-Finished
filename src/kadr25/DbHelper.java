package kadr25;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Locale;

public class DbHelper {
    public static Connection getConnetion() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/KADR25.4");
        Connection c = dataSource.getConnection();
        return c;
    }
}
