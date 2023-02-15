/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.models.Country;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.UserService;
import com.exavalu.utils.JDBCConnectionManager;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sinha
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String countryCode;
    private String stateCode;
    private String districtCode;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        setMap((ApplicationMap) application);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        setSessionMap((SessionMap<String, Object>) (SessionMap) session);
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = UserService.getInstance().doLogin(this);

        if (success) {
            System.out.println("returning Success from doLogin method");
            getSessionMap().put("Loggedin", this);
           
            result = "SUCCESS";
        } else {
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String doPreSignUp() throws Exception {
        String result = "SUCCESS";
        ArrayList countryList = UserService.getInstance().getAllCountries();
        ArrayList stateList = null;
        ArrayList districtList = null;
        sessionMap.put("CountryList", countryList);
        System.out.println("CountryCode=" + this.countryCode);
        System.out.println("StateCode=" + this.stateCode);
        System.out.println("DistrictCode=" + this.districtCode);
        if (this.countryCode != null) {
            stateList = UserService.getInstance().getAllstates(this.countryCode);
            sessionMap.put("StateList", stateList);
            sessionMap.put("User", this);
            result="STATELIST";

        }
        
        if (this.stateCode != null) {
            districtList = UserService.getInstance().getAllDistricts(this.stateCode);
            sessionMap.put("DistrictList", districtList);
            sessionMap.put("User", this);
            result="DISTRICTLIST";

        }
//        if (this.countryCode != null && this.stateCode != null) {
//            districtList = UserService.getInstance().getAllDistricts(this.stateCode);
//            sessionMap.put("DistrictList", districtList);
//            sessionMap.put("User", this);
//
//        }
        if (this.countryCode != null && this.stateCode != null && this.districtCode != null && this.countryCode.length() > 0 && this.stateCode.length() > 0 && this.districtCode.length() > 0) {
            boolean success = UserService.getInstance().doRegister(this);
            if (success) {
                result = "DONE";
                String successMsg = "Account Created,Now Login to your Account";

                sessionMap.put("SignUpSuccessMsg", successMsg);

            } else {
                result = "EXISTS";
                String errorMsg = "This Email is already registered..Try with another Email";

                sessionMap.put("SignUpFailureMsg", errorMsg);
            }
        }

        return result;

    }

    public String doLogout() {
        String result = "SUCCESS";
        getSessionMap().clear();
        return result;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode the stateCode to set
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * @return the distCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * @param distCode the distCode to set
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * @return the sessionMap
     */
    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    /**
     * @param sessionMap the sessionMap to set
     */
    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    /**
     * @return the map
     */
    public ApplicationMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(ApplicationMap map) {
        this.map = map;
    }

}
