import java.sql.*;

class Bank {

    private static final String uri = "jdbc:mysql://localhost:3306/student?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8";
    private static final String user = "root";
    private static final String password = "root";

    index transferMoney(String user_out, String user_in, int count) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(uri, user, password);

            String sql;

            sql = "select * from student where username = ? or username = ?";
            assert con != null;
            statement = con.prepareStatement(sql);
            statement.setString(1, user_out);
            statement.setString(2, user_in);
            res = statement.executeQuery();
            res.last();
            int rowCount = res.getRow();
            if (rowCount < 2) {
                return index.NULL_ACCOUNT;
            }
            sql = "select * from student where username = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user_out);
            res = statement.executeQuery();
            if (res.next()) {
                int balance = res.getInt("balance");
                if (balance <= count) {
                    return index.NO_MONEY;
                }
            }

            index code = index.OTHER;
            con.setAutoCommit(false);

            sql = "update student set balance = balance - ? where username = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, count);
            statement.setString(2, user_out);
            statement.executeUpdate();

            sql = "update student set balance = balance + ? where username = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, count);
            statement.setString(2, user_in);
            statement.executeUpdate();
            code = index.SUCCESS;

            con.commit();

            return code;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index.OTHER;
    }
}
