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
 * ������� �ر�����
 *
 * @author liwei
 *
 */
public class DBConnection {

	//��֤������ͬһ��
	private static ThreadLocal<SqlSession> connectionThreadLocal=new ThreadLocal<>();
	private static ThreadLocal<Connection> connectionThreadLocal_Old=new ThreadLocal<>();

	//����Ϊ��ȡMyBatis sqlSeesion����
	public static SqlSession getSqlSession() {
		// �ȴ�ThreadLocalȡ
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
	//����Ϊ�Լ�дjdk��̬������������
	public static Connection getConn() {
		// �ȴ�ThreadLocalȡ
		Connection conn = connectionThreadLocal_Old.get();
		//��Ϊ�����ȡ����
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

	//����Ϊԭ����ȡ����
	/*public static Connection getConn() {
		Connection conn=connectionThreadLocal.get();
		if (conn==null) {
			Properties prop = new Properties();
			InputStream in;
			try {
				in = Class.forName("cn.edu.guet.util.DBConnection").getResourceAsStream("/db.properties");
				prop.load(in);

				String url = prop.getProperty("url");
				Class.forName(prop.getProperty("driver"));// ��������
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

	//����Ϊԭ���ر�����
	public static void closeConn(Connection conn)
	{
		try {
			if(conn!=null) 	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}