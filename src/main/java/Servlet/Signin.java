/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DB.Repo.UserRepo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tam
 */
public class Signin extends HttpServlet {
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        
        try {
            response.getWriter().println("<h1 style=\"text-align:center;\">Sign in</h1>\n"
                    + "<form style=\"text-align:center; \" method=\"post\"> \n"
                    + "<input type=\"text\" name=\"iD\" placeholder=\"ID\"> \n"
                    + "<br> \n"
                    + "<input type=\"password\" name=\"password\" placeholder=\"Password\"> \n"
                    + "<br>\n"
                    + "<input type=\"password\" name=\"cPassword\" placeholder=\"Confirm Password\"> \n"
                    + "<br>\n"
                    + "<br> \n"
                    + "<input type=\"submit\" value=\"Sign in\"> \n"
                    + "</form>");
        } catch (IOException ex) {
            Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        
        String iD = request.getParameter("iD");// name in http
        String pW = request.getParameter("password");
        String cpW = request.getParameter("cPassword");
        
        UserRepo repo = new UserRepo();
        if (repo.checkID(iD)) {
            try {
                response.getWriter().println("ID is already taken");
                this.doGet(request, response);
            } catch (IOException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (!pW.equals(cpW)) {
            try {
                response.getWriter().println("Confirm password is not correct");
                this.doGet(request, response);
            } catch (IOException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if (repo.createUser(iD, pW)) {
            try {
                response.getWriter().println("Sign in success");
                response.sendRedirect("/login");
            } catch (IOException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }            
        } else {
            try {
                response.getWriter().println("Error");
                this.doGet(request, response);
            } catch (IOException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
