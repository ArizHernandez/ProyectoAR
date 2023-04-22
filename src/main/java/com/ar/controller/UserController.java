package com.ar.controller;

import com.ar.dao.RolDao;
import com.ar.dao.UserDao;
import com.ar.dao.UserDao;
import com.ar.models.RolModel;
import com.ar.models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final String LIST_PAGE = "user/user-list.jsp";
    private static final String FORM_PAGE = "user/user-form.jsp";


    Connection conn = null;
    ResultSet result = null;
    PreparedStatement statement = null;
    String query = "";
    private UserDao userDao = new UserDao();
    private RolDao rolDao = new RolDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        RequestDispatcher dispatcher = null;;

        if (action != null && action.equals("form")) {
            List<UserModel> users = userDao.list();
            req.setAttribute("users", users);

            List<RolModel> rols = rolDao.list();
            req.setAttribute("rols", rols);

            if(id != null && !id.trim().equals("")) {
                Integer idUser = Integer.parseInt(id);
                UserModel user = userDao.getById(idUser);

                req.setAttribute("actualUser", user);
            }

            dispatcher = req.getRequestDispatcher(FORM_PAGE);
        } else {
            String search = req.getParameter("search");

            List<UserModel> users = null;

            if (search == null || search.trim().equals("")) {
                users = userDao.list();
            } else {
                req.setAttribute("search", search);
                users = userDao.search(search);
            }

            req.setAttribute("users", users);

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
        String id = req.getParameter("id");
        String userDeleteId = req.getParameter("idDelete");

        if (userDeleteId != null) {
            Integer idRolDelete = Integer.parseInt(userDeleteId);
            userDao.delete(idRolDelete);

            redirectToList(res);
            return;
        }

        UserModel user = new UserModel();
        user.setName(req.getParameter("name"));
        user.setLastName(req.getParameter("lastName"));
        user.setUser(req.getParameter("user"));
        user.setPassword(req.getParameter("password"));
        user.setIdRol(Integer.parseInt(req.getParameter("idRol")));
        user.setStatus(Byte.parseByte(req.getParameter("active")));
        user.setCreatedUser(req.getParameter("createdUser"));
        user.setUpdatedUser(req.getParameter("updatedUser"));
        user.setCode(req.getParameter("code"));

        if (id == null || id.trim().equals("")) {
            userDao.create(user);
        } else {
            Integer idUsuario = Integer.parseInt(id);
            user.setIdUsuario(idUsuario);
            userDao.update(user);
        }

        redirectToList(res);
    }

    private void redirectToList(HttpServletResponse res) {
        try {
            res.sendRedirect("UserController?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
