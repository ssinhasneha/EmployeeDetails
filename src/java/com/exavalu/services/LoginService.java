/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {
    
    public static LoginService loginService = null;
    
    private LoginService(){}
    
    public static LoginService getInstance()
    {
        if(loginService==null)
        {
            return new LoginService();
        }
        else
        {
            return loginService;
        }
    }
    
   

//    public boolean doRegister(String emailAddress, String password, String firstName, String lastName) {
//        
//        boolean result = false;
//    
//        try {
//
// 
//
//            Connection con = JDBCConnectionManager.getConnection();
//            String sql = "INSERT INTO users (emailAddress,password,firstName,lastName,status)"
//                    + "VALUES (?,?,?,?,?)";
//
// 
//
//            PreparedStatement preparedStatement;
//            preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, emailAddress);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, firstName);
//            preparedStatement.setString(4, lastName);
//            preparedStatement.setInt(5, 1);
//
// 
//
//            int row = preparedStatement.executeUpdate();
//            if (row != 0) {
//                result = true;
//            }
//        } catch (SQLException ex) {
//          ex.printStackTrace();
//        }
//
//        return result;
//    }
   
    
    
}
