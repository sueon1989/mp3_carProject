package com.mp3.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	static {	// 어디에서나 선언 가능
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// Oracle
//			Class.forName("com.mysql.cj.jdbc.Driver");			// MySQL
//			Class.forName("org.mariadb.jdbc.Driver");			// MariaDB
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/* Oracle JDBC 연결 */
	@Test
	public void testConnection() {
		// try(자동으로 닫을 자원)
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.11:1521:XE", "car_mp3", "1234")){
			log.info(conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit에서 에러메세지 찍기
		}
	}
	
		
	/* MariaDB JDBC 연결 */
	@Test
	public void testConnection_MariaDB() {
		// try(자동으로 닫을 자원)
		try(Connection conn = DriverManager.getConnection("jdbc:mariadb://192.168.0.38:3307/car_mp3", "car_mp3", "1234")){
			log.info("MariaDB JDBC 연결 성공"+conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit에서 에러메세지 찍기
		}
	}	
	
	
	/* MySQL JDBC 연결 */
	@Test
	public void testConnection_MySQL() {
		// try(자동으로 닫을 자원)
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.11:3307/book_ex?useSSL=false&serverTimezone=Asia/Seoul", "book_ex", "book_ex")){
			log.info(conn);	// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			fail(e.getMessage());	// JUnit에서 에러메세지 찍기
		}
	}
	
	
	/* Oracle JDBC 연결 - 기존방법 (static 사용안함) */
	@Test
	public void testConnection_Previous() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.11:1521:XE", "car_mp3", "1234");
			log.info(conn);
			// INFO : orz.zerock.persistence.JDBCTests - oracle.jdbc.driver.T4CConnection@685cb137
		} catch (Exception e) {
			e.printStackTrace();	// Console에서 에러메세지 찍기
			fail(e.getMessage());	// JUnit에서 에러메세지 찍기
		}
	}
}
