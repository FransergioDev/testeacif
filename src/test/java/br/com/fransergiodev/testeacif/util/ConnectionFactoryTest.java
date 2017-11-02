/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.util;

import java.sql.Connection;
import org.junit.Test;

/**
 *
 * @author fransergio-dev
 */
public class ConnectionFactoryTest {
    @Test
    public void TestConnection(){
        Connection connection = ConnectionFactory.getConnection();
        System.out.println("Conexão realizada com sucesso!");
    }
}
