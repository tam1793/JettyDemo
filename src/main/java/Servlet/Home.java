/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Object.User;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tam
 */
public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        // code lại: kiểm tra coockie để vào home nếu k thì qua login
        User u = (User) request.getSession().getAttribute("userInfo");
        if (u == null) {
            response.sendRedirect("/login");
        } else {
            response.getWriter().println("<h1>" + u.getID() + "</h1>");
            response.getWriter().println("<h1>" + u.getPassword() + "</h1>");
        }
    }
}
