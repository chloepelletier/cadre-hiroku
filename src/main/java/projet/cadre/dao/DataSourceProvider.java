package projet.cadre.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider {

	private static class DataSourceProviderHolder {
		private final static DataSourceProvider instance = new DataSourceProvider();
	}
	
	public static DataSourceProvider getInstance() {
		return DataSourceProviderHolder.instance;
	}

	private MysqlDataSource dataSource;

	private DataSourceProvider() {
		initDataSource();
	}

	private void initDataSource() {
		Properties jdbcProperties = new Properties();
		InputStream configFileStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			jdbcProperties.load(configFileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataSource = new MysqlDataSource();
			dataSource.setServerName("vlvlnl1grfzh34vj.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("ohwx147hd9g2vgz4");
			dataSource.setUser("ifw3s4zebh0lo8js");
			dataSource.setPassword("bkc7x46u5gzdykhs");
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}

