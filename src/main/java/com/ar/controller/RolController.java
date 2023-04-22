package com.ar.controller;

import com.ar.dao.RolDao;
import com.ar.models.RolModel;

import java.io.IOException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        RolDao rolDao = new RolDao();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        RequestDispatcher dispatcher = null;

        if (action != null && action.equals("form") && id != null) {
            Integer idRol = Integer.parseInt(id);
            RolModel rol = rolDao.getById(idRol);

            req.setAttribute("actualRol", rol);

            dispatcher = req.getRequestDispatcher(FORM_PAGE);
        } else {
            String search = req.getParameter("search");

            List<RolModel> roles = null;

            if (search == null || search.trim().equals("")) {
                roles = rolDao.list();
            } else {
                req.setAttribute("search", search);
                roles = rolDao.search(search);
            }

            req.setAttribute("rols", roles);

            dispatcher = req.getRequestDispatcher(LIST_PAGE);
        }

        try {
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        RolDao rolDao = new RolDao();
        String id = req.getParameter("id");
        String rolDeleteId = req.getParameter("idDelete");

        if (rolDeleteId != null) {
            Integer idRolDelete = Integer.parseInt(rolDeleteId);
            rolDao.delete(idRolDelete);

            redirectToList(res);
            return;
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Byte active = Byte.parseByte(req.getParameter("active"));
        String createdUser = req.getParameter("createdUser");
        String updatedUser = req.getParameter("updatedUser");
        RolModel rol = new RolModel(name, active, description, createdUser, updatedUser);

        if (id == null || id.trim().equals("")) {
            rolDao.create(rol);
        } else {
            Integer idRol = Integer.parseInt(id);
            rol.setIdRol(idRol);
            rolDao.update(rol);
        }

        redirectToList(res);
    }

    private void redirectToList(HttpServletResponse res) {
        try {
            res.sendRedirect("RolController?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
