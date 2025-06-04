/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.jdbc1;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author crist
 */
public class jdbc3_InserindoDadosNoBancoDeDados {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            
            st = conn.prepareStatement(
                    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary,"
                    + "DepartmentId) VALUES (?, ?, ?, ?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Cristhian Gustavo Lourenço");
            st.setString(2, "cristhianlourenco90@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("10/11/2000").getTime()));
            st.setDouble(4, 50000.0);
            st.setInt(5, 6);
            
            //st = conn.prepareStatement("insert into department (Name) values ('Fantasy'),('Security Web')",Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();
            
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id: " + id);
                }
            }else{
                System.out.println("No rown affected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnetion();
        }

    }
 }
