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

public class SQLiteUtil {
    private static final String URL = "jdbc:sqlite:ticketing.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

