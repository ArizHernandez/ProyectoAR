package com.ar.controller;

import com.ar.dao.RolDao;
import com.ar.models.RolModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RolController")
public class RolController extends HttpServlet {
    private static final String LIST_PAGE = "rol/rol-list.jsp";
    private static final String FORM_PAGE = "rol/rol-form.jsp";


    Connection conn = null;
    ResultSet result = null;
    PreparedStatement statement = null;
    String query = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RolDao rolDao = new RolDao();
        String action = req.getParameter("action");

        if (action.equals("form")) {
            String id = req.getParameter("id");

            if (id != null) {
                Integer idRol = Integer.parseInt(id);
                RolModel rol = rolDao.getById(idRol);
                req.setAttribute("actualRol", rol);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(FORM_PAGE);
            dispatcher.forward(req, res);
        } else {
            String search = req.getParameter("search");

            List<RolModel> roles = null;

            if (search == null) {
                roles = rolDao.list();
            } else {
                roles = rolDao.search(search);
            }
            System.out.println(roles);

            req.setAttribute("roles", roles);

            RequestDispatcher dispatcher = req.getRequestDispatcher(LIST_PAGE);
            dispatcher.forward(req, res);
        }

    }

}
