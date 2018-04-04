package kaisheng.project.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class SqlManager {

	public static String DRIVER;
	public static String URL;
	public static String NAME;
	public static String PASSWORD;

	private static BasicDataSource dataSource = new BasicDataSource();
	private static Properties prop = new Properties();

	// 静态代码块 只执行一次 数据库连接池只进行一次连接
	static {
		try {
			prop.load(JdbcHelp.class.getClassLoader().getResourceAsStream("config.properties"));
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			NAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			try {
				throw new Exception("数据库连接异常", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// 数据库连接的配置
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(NAME);
		dataSource.setPassword(PASSWORD);

		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(20);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5000);
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
