package com.service;

import com.db.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "LogLet")

/**
 * Cette classe serve à la requête de Http par le mode Get
 */
public class LogLet extends HttpServlet {

    private static final long serialVersionUID = 9036889586892331384L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");
        System.out.println(username + "--" + password);

        Service service = new Service();
        boolean loged = service.login(username, password);
        if(loged){
            System.out.println("Success");
            request.getSession().setAttribute("username", username);

        }
        else{
            System.out.println("Failed");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("Username : " + username);
        out.print("Password : " + password);
        out.flush();
        out.close();
    }
}
