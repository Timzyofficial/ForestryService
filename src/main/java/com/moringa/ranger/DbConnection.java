package com.moringa.ranger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbConnection {

        public Connection getConnection() throws SQLException {
            // Implement your database connection logic here
            String url = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
            String username = "postgres";
            String password = "12345678";

            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("ssl", "false"); // Set SSL to false if not using SSL

            return DriverManager.getConnection(url, props);
        }


    }




