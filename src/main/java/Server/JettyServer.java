package Server;

import Servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tam
 */
public class JettyServer {
    public static void main(String[] args) throws Exception{
        //tạo Server trên port 9090
        Server server = new Server(9090);
        
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        
        
        // Create the SessionHandler (wrapper) to handle the sessions
        HashSessionManager manager = new  HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setHandler(sessions);
               
        context.addServlet(Login.class, "/login");
        context.addServlet(Home.class, "/home");
        context.addServlet(Signin.class, "/signin");
        server.setHandler(context);
        
        
        //Start
        server.start();
        server.join();
        
    }
}
