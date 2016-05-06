/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Article;
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
import Models.Transaction;
import Models.TransactionType;

/**
 *
 * @author 0aps
 */
public class TransactionServ extends BaseServ {

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
        
        List<Transaction> transactions = new LinkedList<Transaction>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        ObjectMapper mapper = new ObjectMapper();
        Transaction article = mapper.readValue(json, Transaction.class);
 
        
        response.setContentType("application/json");            
        transactions.add(article);
        
        mapper.writeValue(response.getOutputStream(), transactions);
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
        
        if("list".equals(action)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            List<Transaction> transactions = null;
            Query query = session.createQuery("from Transaction");
            transactions = (ArrayList<Transaction>)query.list();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), transactions);
            
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
        else if("listTypes".equals(action)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            List<TransactionType> transactions = null;
            Query query = session.createQuery("from TransactionType");
            transactions = (ArrayList<TransactionType>)query.list();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), transactions);
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
        
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
            
        Transaction transaction = bufferToTransaction(request.getInputStream());
        int articleId = transaction.getIdArticle().getId();
        int transTypeId = transaction.getType().getId();
        Article article = (Article) session.get(Article.class, articleId);
        TransactionType transactionType = (TransactionType) session.get(TransactionType.class, transTypeId);
        
        if ("Entrada".equals(transactionType.getDescription())){
            int difference = article.getStock()+transaction.getAmount();
            article.setStock(difference);
        }else if("Salida".equals(transactionType.getDescription())){    
            int difference = article.getStock()-transaction.getAmount();
            article.setStock(difference);
        }
        
        session.update(article);
        session.save(transaction);
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
            
        Transaction transaction = bufferToTransaction(request.getInputStream());
        
        session.update(transaction);
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
        
        Transaction transaction =  new Transaction(id);
        
        session.delete(transaction);
        session.getTransaction().commit();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("OK");
    }
}
