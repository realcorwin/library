package dik.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import java.sql.SQLException;

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(LibraryApplication.class, args);
	}

}

