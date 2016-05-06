/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Article;
import Models.TypeInventory;
import Models.Warehouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import Models.Transaction;

/**
 *
 * @author 0aps
 */

public class BaseServ extends HttpServlet {
    
    public Article bufferToArticle(ServletInputStream input)
        throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        Article article = mapper.readValue(json, Article.class);
        
        return article;
        
    }
    
    public TypeInventory bufferToInventory(ServletInputStream input)
        throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        TypeInventory inventory = mapper.readValue(json, TypeInventory.class);
        
        return inventory;
        
    }
    
    public Warehouse bufferToWareHouse(ServletInputStream input)
        throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        Warehouse wareHouse = mapper.readValue(json, Warehouse.class);
        
        return wareHouse;
        
    }
    public Transaction bufferToTransaction(ServletInputStream input)
        throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = mapper.readValue(json, Transaction.class);
        
        return transaction;
        
    }
    

}
