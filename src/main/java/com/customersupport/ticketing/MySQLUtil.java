/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.ticketing;

/**
 *
 * @author yani_
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/support_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Baby2005!!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

