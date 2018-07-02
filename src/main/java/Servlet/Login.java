/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DB.Repo.UserRepo;
import Method.CheckCookie;
import Object.User;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tam
 */
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        User user = CheckCookie.checkUserInCookie(request);
        if (user == null) {
            response.getWriter().println("  <h1 style=\"text-align:center;\">Login</h1>"
                    + "<form style=\"text-align:center; \" method = \"post\"> "
                    + "<input type=\"text\" name=\"iD\" placeholder=\"ID\"> "
                    + "<br> "
                    + "<input type=\"password\" name=\"password\" placeholder=\"Password\"> "
                    + "<br><br> "
                    + "<input type=\"submit\" value=\"Login\"> "
                    + "</form>");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", user);
            response.sendRedirect("/home");
        }

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        User user = null;

        String iD = request.getParameter("iD");// name in http
        String pW = request.getParameter("password");       

        UserRepo repo = new UserRepo();
        user = repo.checkUser(iD, pW);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", user);
            Cookie ckID = new Cookie("ckID", user.getID());
            ckID.setMaxAge(10);
            response.addCookie(ckID);
            Cookie ckPassword = new Cookie("ckPassword", user.getPassword());
            ckPassword.setMaxAge(10);
            response.addCookie(ckPassword);
            response.sendRedirect("/home");
        } else {
//            System.out.println("Login Faild");
            response.getWriter().println("ID or Password wrong");
            this.doGet(request, response);
        }
    }
}
