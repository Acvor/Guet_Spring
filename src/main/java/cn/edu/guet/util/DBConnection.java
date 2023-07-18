package cn.edu.guet.util;

import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获得连接 关闭连接
 *
 * @author liwei
 *
 */
public class DBConnection {

	//保证链接是同一个
	private static ThreadLocal<SqlSession> connectionThreadLocal=new ThreadLocal<>();
	private static ThreadLocal<Connection> connectionThreadLocal_Old=new ThreadLocal<>();

	//以下为获取MyBatis sqlSeesion对象
	public static SqlSession getSqlSession() {
		// 先从ThreadLocal取
		SqlSession sqlSession = connectionThreadLocal.get();

		if (sqlSession == null) {
			try {
				InputStream in = Resources
						.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
				sqlSession = sqlSessionFactory.openSession();
				connectionThreadLocal.set(sqlSession);
				return sqlSession;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSession;
	}
	//以下为自己写jdk动态代理，操作事务
	public static Connection getConn() {
		// 先从ThreadLocal取
		Connection conn = connectionThreadLocal_Old.get();
		//若为空则获取链接
		if (conn == null) {
			Properties prop = new Properties();
			InputStream in;
			try {
				in = Class.forName("cn.edu.guet.util.DBConnection").getResourceAsStream("/db.properties");
				prop.load(in);

				String url = prop.getProperty("url");
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(url, prop.getProperty("user"), prop.getProperty("password"));
				connectionThreadLocal_Old.set(conn);
				return conn;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	//以下为原本获取链接
	/*public static Connection getConn() {
		Connection conn=connectionThreadLocal.get();
		if (conn==null) {
			Properties prop = new Properties();
			InputStream in;
			try {
				in = Class.forName("cn.edu.guet.util.DBConnection").getResourceAsStream("/db.properties");
				prop.load(in);

				String url = prop.getProperty("url");
				Class.forName(prop.getProperty("driver"));// 加载驱动
				conn = DriverManager.getConnection(url, prop.getProperty("user"), prop.getProperty("password"));
				return conn;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}*/

	//以下为原本关闭连接
	public static void closeConn(Connection conn)
	{
		try {
			if(conn!=null) 	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}