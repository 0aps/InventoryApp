/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.TypeInventory;
import Utils.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 0aps
 */

public class InventoryServ extends BaseServ {

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<TypeInventory> inventories = new LinkedList<TypeInventory>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        TypeInventory inventory = mapper.readValue(json, TypeInventory.class);
 
        
        response.setContentType("application/json");            
        inventories.add(inventory);
        
        mapper.writeValue(response.getOutputStream(), inventories);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String action = request.getParameter("action");
        List<TypeInventory> inventories = null;
        
        if("list".equals(action)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("from TypeInventory");
            inventories = (ArrayList<TypeInventory>)query.list();
            
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), inventories);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            
        TypeInventory inventory = bufferToInventory(request.getInputStream());
        
        session.save(inventory);
        session.getTransaction().commit();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("OK");
    }
    
    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            
        TypeInventory inventory = bufferToInventory(request.getInputStream());
        
        session.update(inventory);
        session.getTransaction().commit();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("OK");
    }
    
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            
        int id = Integer.parseInt(request.getParameter("id"));
        
        TypeInventory inventory =  new TypeInventory(id);
        
        session.delete(inventory);
        session.getTransaction().commit();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("OK");
    }
    
}
