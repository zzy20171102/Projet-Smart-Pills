package com.service;
import com.db.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegLet")
/**
 * Cette classe serve à la requête de Http par le mode Post
 */
public class RegLet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        Service service = new Service();

        boolean reg = service.register(username, password);
        if(reg){
            System.out.println("reg success");
        }else{
            System.out.println("reg failed");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(reg){
            out.print("true");

        }else{
            out.print("false");
        }
        out.flush();
        out.close();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
