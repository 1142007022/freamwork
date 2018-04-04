package kaisheng.project.utils;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class JdbcHelp {

	private static QueryRunner runner = new QueryRunner(SqlManager.getDataSource());

	public static void executeUpdate(String sql, Object... params) {
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
		try {
			// System.out.println("SQL:" + sql);
			return runner.query(sql, rsh, params);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

}
