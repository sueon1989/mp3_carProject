package com.mp3.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	static {	// ��𿡼��� ���� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// Oracle
//			Class.forName("com.mysql.cj.jdbc.Driver");			// MySQL
//			Class.forName("org.mariadb.jdbc.Driver");			// MariaDB
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/* Oracle JDBC ���� */
	@Test
	public void testConnection() {
		// try(�ڵ����� ���� �ڿ�)
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.11:1521:XE", "car_mp3", "1234")){
			log.info(conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit���� �����޼��� ���
		}
	}
	
		
	/* MariaDB JDBC ���� */
	@Test
	public void testConnection_MariaDB() {
		// try(�ڵ����� ���� �ڿ�)
		try(Connection conn = DriverManager.getConnection("jdbc:mariadb://192.168.0.11:3306/car_mp3", "car_mp3", "1234")){
			log.info("MariaDB JDBC ���� ����"+conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit���� �����޼��� ���
		}
	}	
	
	
	/* MySQL JDBC ���� */
	@Test
	public void testConnection_MySQL() {
		// try(�ڵ����� ���� �ڿ�)
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.11:3307/book_ex?useSSL=false&serverTimezone=Asia/Seoul", "book_ex", "book_ex")){
			log.info(conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit���� �����޼��� ���
		}
	}
	
	
	/* Oracle JDBC ���� - ������� (static ������) */
	@Test
	public void testConnection_Previous() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.11:1521:XE", "car_mp3", "1234");
			log.info(conn);
			// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			e.printStackTrace();	// Console���� �����޼��� ���
			fail(e.getMessage());	// JUnit���� �����޼��� ���
		}
	}
}