package com.ar.dao;

import com.ar.config.ConnConfig;
import com.ar.controller.RolController;
import com.ar.interfaces.RolCRUD;
import com.ar.interfaces.UserCRUD;
import com.ar.models.RolModel;
import com.ar.models.UserModel;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserCRUD {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    Connection conn = null;
    ResultSet result = null;
    PreparedStatement statement = null;
    String query = "";


    public List<UserModel> list() {
        List<UserModel> users = new ArrayList<>();

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT id_usuario, u.nombre, apellido, usuario, password, u.id_rol, u.fecha_crea, u.activo, u.usuario_mod, u.usuario_crea, u.fecha_mod, codigo, r.nombre as rol_nombre " +
                    "FROM USUARIO u INNER JOIN ROL r ON r.id_rol = u.id_rol ;";
            System.out.println("Executed query" + query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                UserModel user = formatToObjet(result);
                users.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return users;
    }

    public List<UserModel> search(String search) {
        List<UserModel> users = new ArrayList<>();

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT id_usuario, u.nombre, apellido, usuario, password, u.id_rol, u.fecha_crea, u.activo, u.usuario_mod, u.usuario_crea, u.fecha_mod, codigo, r.nombre as rol_nombre " +
                    "FROM USUARIO u INNER JOIN ROL r ON r.id_rol = u.id_rol " +
                    "WHERE u.nombre LIKE ? OR usuario LIKE ? OR u.activo LIKE ? OR u.usuario_crea LIKE ? OR u.fecha_mod LIKE ?;";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            statement.setString(3, "%" + search + "%");
            statement.setString(4, "%" + search + "%");
            statement.setString(5, "%" + search + "%");
            result = statement.executeQuery();

            while (result.next()) {
                UserModel user = formatToObjet(result);
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return users;
    }

    public UserModel getById(Integer id) {
        UserModel user = null;

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT id_usuario, u.nombre, apellido, usuario, password, u.id_rol, u.fecha_crea, u.activo, u.usuario_mod, u.usuario_crea, u.fecha_mod, codigo, r.nombre as rol_nombre " +
                    "FROM USUARIO u INNER JOIN ROL r ON r.id_rol = u.id_rol " +
                    "WHERE id_usuario = ?";
            System.out.println("Executed query" + query);
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                user = formatToObjet(result);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

    public void create(UserModel newRol) {
        LocalDateTime todayDate = LocalDateTime.now();
        String todayDateFormated = todayDate.toString();

        try {
            conn = ConnConfig.getConnection();

            query = "INSERT INTO USUARIO (nombre, apellido, usuario, password, id_rol, fecha_crea, activo, usuario_mod, usuario_crea, fecha_mod, codigo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, newRol.getName());
            statement.setString(2, newRol.getLastName());
            statement.setString(3, newRol.getUser());
            statement.setString(4, newRol.getPassword());
            statement.setInt(5, newRol.getIdRol());
            statement.setString(6, todayDateFormated);
            statement.setByte(7, newRol.getStatus());
            statement.setString(8, newRol.getUpdatedUser());
            statement.setString(9, newRol.getCreatedUser());
            statement.setString(10, todayDateFormated);
            statement.setString(11, newRol.getCode());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
            } catch (SQLException ex) {
                Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void update(UserModel user) {
        LocalDateTime todayDate = LocalDateTime.now();
        String todayDateFormated = todayDate.toString();

        try {
            conn = ConnConfig.getConnection();

            query = "UPDATE USUARIO SET (nombre, apellido, usuario, password, id_rol, fecha_crea, activo, usuario_mod, usuario_crea, fecha_mod, codigo)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID_ROL = ?;";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUser());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getIdRol());
            statement.setString(6, todayDateFormated);
            statement.setByte(7, user.getStatus());
            statement.setString(8, user.getUpdatedDate());
            statement.setString(9, user.getCreatedDate());
            statement.setString(10, user.getUpdatedDate());
            statement.setString(11, user.getCreatedUser());
            statement.setInt(12, user.getIdUsuario());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
            } catch (SQLException ex) {
                Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(Integer idUsuario) {
        try {
            conn = ConnConfig.getConnection();

            query = "DELETE FROM USUARIO WHERE id_usuario = ?";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
            } catch (SQLException ex) {
                Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private UserModel formatToObjet(ResultSet result) throws SQLException {
        UserModel user = new UserModel();

        user.setIdUsuario(result.getInt("id_usuario"));
        user.setName(result.getString("nombre"));
        user.setLastName(result.getString("apellido"));
        user.setUser(result.getString("usuario"));
        user.setPassword(result.getString("password"));
        user.setIdRol(result.getInt("id_rol"));
        user.setStatus(result.getByte("activo"));
        user.setCreatedUser(result.getString("usuario_crea"));
        user.setUpdatedUser(result.getString("usuario_mod"));
        user.setCode(result.getString("codigo"));
        user.setRolName(result.getString("rol_nombre"));

        Date createdDate = result.getDate("fecha_crea");
        Date updatedDate = result.getDate("fecha_mod");

        user.setCreatedDate(formatter.format(createdDate));
        user.setUpdatedDate(formatter.format(updatedDate));

        return user;
    }
}
