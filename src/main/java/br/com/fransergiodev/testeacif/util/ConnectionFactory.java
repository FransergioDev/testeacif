/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fransergio-dev
 */
public class ConnectionFactory {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/teste?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "dev123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
