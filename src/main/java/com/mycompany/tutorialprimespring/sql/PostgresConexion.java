/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class PostgresConexion {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static Connection conexionBd() 
    {
            /*Connection dbConnection = null;
            try 
            {
                    Class.forName(DB_DRIVER);
            } 
            catch (ClassNotFoundException e)
            {
                    System.out.println(e.getMessage());
            }

            try 
            {
                    dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
                    System.out.println("--- Conexion exitosa--- clase PostgresConexion");			
                    return dbConnection;
            } 
            catch (SQLException e) 
            {
                    System.out.println(e.getMessage()+"---Error Conexion--- clase PostgresConexion");
            }
            return dbConnection;*/
        System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                            + "Include in your library path!");
            System.out.println(e.getMessage());
            //e.printStackTrace();
            //return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

                connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
                                "root");
                return connection;

        } catch (SQLException e) {

                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
         }

        if (connection != null) {
                System.out.println("You made it, take control your database now!");
        } else {
                System.out.println("Failed to make connection!");
        }
        
        return connection;
    }
        
}
