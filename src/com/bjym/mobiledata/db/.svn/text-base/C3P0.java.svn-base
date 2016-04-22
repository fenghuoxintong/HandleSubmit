package com.bjym.mobiledata.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bjym.mobiledata.utils.LoggerUtil;
import com.bjym.mobiledata.utils.PropertiesUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static C3P0 instance;
	private static ComboPooledDataSource ds;

	public static synchronized C3P0 getInstance() {
		synchronized (C3P0.class) {
			if (instance == null) {
				instance = new C3P0();
			}
		}
		return instance;
	}

	private C3P0() {
		ds = new ComboPooledDataSource();
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.driverClass"));
		// LoggerUtil.info(PropertiesUtil.getParameterC3P0("c3p0.jdbcUrl"));
		// LoggerUtil.info(PropertiesUtil.getParameterC3P0("c3p0.user"));
		// LoggerUtil.info(PropertiesUtil.getParameterC3P0("c3p0.password"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.acquireIncrement"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.acquireRetryAttempts"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.acquireRetryDelay"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.autoCommitOnClose"));
		// //
		// LoggerUtil.info(PropertiesUtil.getParameterC3P0("c3p0.automaticTestTable"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.breakAfterAcquireFailure"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.checkoutTimeout"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.idleConnectionTestPeriod"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.initialPoolSize"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.maxIdleTime"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.minPoolSize"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.maxStatements"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.maxStatementsPerConnection"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.numHelperThreads"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.preferredTestQuery"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.propertyCycle"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.testConnectionOnCheckout"));
		// LoggerUtil.info(PropertiesUtil
		// .getParameterC3P0("c3p0.testConnectionOnCheckin"));
		try {
			ds.setDriverClass(PropertiesUtil
					.getParameterC3P0("c3p0.driverClass"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl(PropertiesUtil.getParameterC3P0("c3p0.jdbcUrl"));
		ds.setUser(PropertiesUtil.getParameterC3P0("c3p0.user"));
		ds.setPassword(PropertiesUtil.getParameterC3P0("c3p0.password"));
		ds.setAcquireIncrement(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.acquireIncrement")));
		ds.setAcquireRetryAttempts(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.acquireRetryAttempts")));
		ds.setAcquireRetryDelay(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.acquireRetryDelay")));
		ds.setAutoCommitOnClose(Boolean.parseBoolean(PropertiesUtil
				.getParameterC3P0("c3p0.autoCommitOnClose")));
		// ds.setAutomaticTestTable(PropertiesUtil.getParameterC3P0("c3p0.automaticTestTable"));
		ds.setBreakAfterAcquireFailure(Boolean.parseBoolean(PropertiesUtil
				.getParameterC3P0("c3p0.breakAfterAcquireFailure")));
		ds.setCheckoutTimeout(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.checkoutTimeout")));
		ds.setIdleConnectionTestPeriod(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.idleConnectionTestPeriod")));
		ds.setInitialPoolSize(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.initialPoolSize")));
		ds.setMaxIdleTime(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.maxIdleTime")));
		ds.setMaxPoolSize(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.maxPoolSize")));
		ds.setMinPoolSize(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.minPoolSize")));
		ds.setMaxStatements(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.maxStatements")));
		ds.setMaxStatementsPerConnection(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.maxStatementsPerConnection")));
		ds.setNumHelperThreads(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.numHelperThreads")));
		ds.setPreferredTestQuery(PropertiesUtil
				.getParameterC3P0("c3p0.preferredTestQuery"));
		ds.setPropertyCycle(Integer.parseInt(PropertiesUtil
				.getParameterC3P0("c3p0.propertyCycle")));
		ds.setTestConnectionOnCheckout(Boolean.parseBoolean(PropertiesUtil
				.getParameterC3P0("c3p0.testConnectionOnCheckout")));
		ds.setTestConnectionOnCheckin(Boolean.parseBoolean(PropertiesUtil
				.getParameterC3P0("c3p0.testConnectionOnCheckin")));

	}

	public synchronized Connection getConnection() {
		DataSource datasource = ds;
		Connection con = null;
		try {
			con = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (con != null) {
			return con;
		}
		return null;
	}

	public static void printDB() {
		try {
			LoggerUtil.info("【连接池最大容量: " + ds.getMaxPoolSize()
					+ "】———【连接池最小容量: " + ds.getMinPoolSize()
					+ "】——【连接池内现有连接数: " + ds.getNumConnectionsDefaultUser()
					+ "】——【正在使用连接数: " + ds.getNumBusyConnectionsDefaultUser()
					+ "】——【连接池中空闲连接数: " + ds.getNumIdleConnectionsDefaultUser()
					+ "】");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
