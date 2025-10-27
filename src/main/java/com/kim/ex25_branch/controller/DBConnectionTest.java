package com.kim.ex25_branch.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBConnectionTest {

	private DataSource dataSource;

    public DBConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@GetMapping("/db")
	private String dBConnectionTest() {

		String msg = "";
		
		try (Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();

			msg += "데이터베이스 연결 성공!";
			msg += "<br>" + "JDBC 드라이버: " + metaData.getDriverName();
			msg += "<br>" + "드라이버 버전: " + metaData.getDriverVersion();
			msg += "<br>" + "데이터베이스 제품명: " + metaData.getDatabaseProductName();
			msg += "<br>" + "URL: " + metaData.getURL();
			msg += "<br>" + "사용자명: " + metaData.getUserName();

		} catch (Exception e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
}
