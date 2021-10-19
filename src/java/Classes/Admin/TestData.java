/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Admin;

import Classes.Connect_To_Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class TestData {
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
      
       String str = ",123,567676,90";       
       HashSet<Integer> hm = new HashSet<Integer>();
       
       int i = 1 ;
       String temp = "";
       
       
       
        while(i < str.length())
        {
           while(i < str.length() && str.charAt(i) != ',' )
           {
               temp += str.charAt(i);
               i++;
           }
           i++;
           System.out.println("ele - "+temp);
           hm.add(Integer.parseInt(temp));
           temp = "";
           
       }
       
    }
    
}
