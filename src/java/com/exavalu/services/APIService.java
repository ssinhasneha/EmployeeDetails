/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.APIUser;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class APIService {
    
    public static ArrayList consumeDataFromAPI() throws ParseException{
        
        //String result = null;
        ArrayList<APIUser> apiUsers = new ArrayList();
         try {

		URL url = new URL("https://jsonplaceholder.typicode.com/users");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
                else{
                    
                    String inline = "";
                    Scanner scanner = new Scanner(url.openStream());
                    while(scanner.hasNext()){
                        inline += scanner.nextLine();
                    }
                    scanner.close();
                    JSONParser parse = new JSONParser();
                    JSONArray jsonArray = (JSONArray)parse.parse(inline);
                    //JSONArray jsonArray = data_obj.getJSONArra("languages");
                    
                    for(int i = 0; i<10; i++){
                        
                        APIUser apiUser = new APIUser();
                        JSONObject obj = (JSONObject)jsonArray.get(i);
                        String id = obj.get("id").toString();
                        String name = obj.get("name").toString();
                        String username = obj.get("username").toString();
                        String email = obj.get("email").toString();
                        String website = obj.get("website").toString();
                        String phone = obj.get("phone").toString();
                        

                        apiUser.setEmail(email);
                        apiUser.setId(id);
                        apiUser.setUsername(username);
                        apiUser.setName(name);
                        apiUser.setWebsite(website);
                        apiUser.setPhone(phone);

                        apiUsers.add(apiUser);
                        
                    }
                    
                    System.out.println("Size of User List"+apiUsers.size());
                }

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
         return apiUsers;
	}

    public static boolean insertDataInDB(ArrayList apiUsers) {
        boolean result = false;
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO apiuser(id,name,username,email,website,phone)" +"VALUES(?,?,?,?,?,?)";

            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            //Iterator itr = apiUsers.iterator();
            //int i = apiUsers.size();
            Iterator itr = apiUsers.iterator();
            while(itr.hasNext()){
                
                APIUser apiUser = (APIUser) itr.next();
                preparedStatement.setString(1,apiUser.getId());
                preparedStatement.setString(2, apiUser.getName());
                preparedStatement.setString(3, apiUser.getUsername());
                preparedStatement.setString(4, apiUser.getEmail());
                preparedStatement.setString(5, apiUser.getWebsite());
                preparedStatement.setString(6, apiUser.getPhone());
                
                preparedStatement.addBatch();
                
               
            }
                int[] count = preparedStatement.executeBatch();
                con.commit();

                if (count.length >= 1) {
                    result = true;
                    System.out.println(count.length);
                }
               
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
  
    }
}

