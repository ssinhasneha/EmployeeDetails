/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.State;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sinha
 */
public class UserService {

    public static UserService userService = null;

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService();
        } else {
            return userService;
        }
    }

    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "Select * from users where emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean doRegister(User user) {
        boolean result = false;
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO users (emailAddress,password,firstName,lastName,countryCode,stateCode,districtCode,status)"
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCountryCode());
            preparedStatement.setString(6, user.getStateCode());
            preparedStatement.setString(7, user.getDistrictCode());
            preparedStatement.setInt(8, 1);

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public ArrayList getAllCountries() {
        ArrayList countryList = new ArrayList();
        Connection con = JDBCConnectionManager.getConnection();
        try {

            String sql = "select * from countries";

            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));
                country.setCountryName(rs.getString("countryName"));
                countryList.add(country);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("countrySize:" + countryList.size());
        return countryList;
    }

    public ArrayList getAllstates(String countryCode) {

        ArrayList stateList = new ArrayList();
        Connection con = JDBCConnectionManager.getConnection();
        try {

            String sql = "select * from states where countryCode=?";

            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, countryCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                State state = new State();
                state.setCountryCode(rs.getString("countryCode"));
                state.setStateCode(rs.getString("stateCode"));
                state.setStateName(rs.getString("stateName"));
                stateList.add(state);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("countrySize:" + stateList.size());
        return stateList;

    }

    public ArrayList getAllDistricts(String stateCode) {
        ArrayList districtList = new ArrayList();
        Connection con = JDBCConnectionManager.getConnection();
        try {

            String sql = "select * from districts where stateCode=?";

            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, stateCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                District district = new District();
                district.setStateCode(rs.getString("stateCode"));
                district.setDistrictCode(rs.getString("districtCode"));
                district.setDistrictName(rs.getString("districtName"));
                districtList.add(district);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("countrySize:" + districtList.size());
        return districtList;
    }

}
