/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;

import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }

    public static Employee getEmployee(String employeeId) {
        Employee emp = new Employee();

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, roles r "
                    + "where e.departmentId=d.departmentId and e.roleId=r.roleId and e.employeeId =?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;
    }

   
   
    public ArrayList getAllEmployees()
    {
        ArrayList empList = new ArrayList();
        String sql = "Select * from employees e,departments d,roles r where e.departmentId=d.departmentId and e.roleId=r.roleId ";
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Employee emp = new Employee();
                
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeeid"));
                emp.setFirstName(rs.getString("firstname"));
                emp.setLastName(rs.getString("lastname"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentId(rs.getString("departmentId"));
                emp.setRoleId(rs.getString("roleId"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));
                empList.add(emp);
            }
            
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("Total rows:"+empList.size());
        return empList;
    }

    public boolean doAddEmployee(Employee emp) {
        boolean result = false;
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO employees (firstName,lastName,phone,address,gender,age,departmentId,roleId,basicSalary,carAllowance)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getFirstName());      
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
         
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setString(7, emp.getDepartmentId());
//           System.out.println(emp.getDepartmentId());
           preparedStatement.setString(8, emp.getRoleId());
           
            preparedStatement.setString(9, emp.getBasicSalary());
        
            preparedStatement.setString(10, emp.getCarAllowance());

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
        
        
    }

    public ArrayList getSearchResult(Employee emp) {
        boolean result = true;
       //Employee emp = new Employee();
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, roles r where e.departmentId=d.departmentId and e.roleId=r.roleId "
                    + "having firstName like ?"
                    + "and lastName like ?"
                    + "and gender like ?"
                    + "and departmentName like ?"
                    + "and roleName like ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getFirstName() + "%");
            preparedStatement.setString(2, emp.getLastName() + "%");
            preparedStatement.setString(3, emp.getGender() + "%");
            preparedStatement.setString(4, emp.getDepartmentName() + "%");
            preparedStatement.setString(5, emp.getRoleName() + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee empi = new Employee();
                empi.setAddress(rs.getString("address"));
                empi.setEmployeeId(rs.getString("employeeId"));
                empi.setFirstName(rs.getString("firstName"));
                empi.setLastName(rs.getString("lastName"));
                empi.setPhone(rs.getString("phone"));
                empi.setGender(rs.getString("gender"));
                empi.setAge(rs.getString("age"));
                empi.setDepartmentName(rs.getString("departmentName"));
                empi.setRoleName(rs.getString("roleName"));
                empi.setBasicSalary(rs.getString("basicSalary"));
                empi.setCarAllowance(rs.getString("carAllowance"));

                empList.add(empi);
            }
            // if(emp.getFirstName()==null ||emp.getLastName()==null || emp.getGender()==null)
            //result=false;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
System.out.println(empList.size());
        return empList;
    }

    public boolean updateEmployee(Employee emp, String employeeId) {
       boolean result = false;
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees\n"
                    + "SET firstName = ? , lastName = ? , phone = ? , address = ?,\n"
                    + "gender = ? , age = ? , basicSalary = ? , carAllowance = ? , departmentId = ? , roleId = ?\n"
                    + "WHERE employeeId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setDouble(7, Double.parseDouble(emp.getBasicSalary()));
//             System.out.println("1");
            preparedStatement.setDouble(8, Double.parseDouble(emp.getCarAllowance()));
//             System.out.println("2");
            preparedStatement.setString(9, emp.getDepartmentId());
//            System.out.println("2");
            preparedStatement.setString(10, emp.getRoleId());
//            System.out.println("2");
            preparedStatement.setString(11, employeeId);
//            System.out.println("2");

            int row = preparedStatement.executeUpdate();

            if (row == 1) {

                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("das");
        return result;
    }

    
    
}
