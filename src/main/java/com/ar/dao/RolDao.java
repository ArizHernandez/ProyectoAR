package com.ar.dao;

import com.ar.config.ConnConfig;
import com.ar.controller.RolController;
import com.ar.models.RolModel;
import com.ar.interfaces.RolCRUD;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RolDao implements RolCRUD {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    Connection conn = null;
    ResultSet result = null;
    PreparedStatement statement = null;
    String query = "";


    public List<RolModel> list() {
        List<RolModel> rols = new ArrayList<>();

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT ID_ROL, NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD FROM ROL;";
            System.out.println("Executed query" + query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                RolModel rol = formatToObjet(result);
                rols.add(rol);
            }
        } catch (Exception ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rols;
    }

    public List<RolModel> search(String search) {
        List<RolModel> rols = new ArrayList<>();

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT ID_ROL, NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD FROM ROL " +
                    "WHERE NOMBRE LIKE ? OR DESCRIPCION LIKE ? OR ACTIVO LIKE ? OR USUARIO_CREA LIKE ? OR USUARIO_MOD LIKE ? OR FECHA_CREA LIKE ? OR FECHA_MOD LIKE ?;";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            statement.setString(3, "%" + search + "%");
            statement.setString(4, "%" + search + "%");
            statement.setString(5, "%" + search + "%");
            statement.setString(6, "%" + search + "%");
            statement.setString(7, "%" + search + "%");
            result = statement.executeQuery();

            while (result.next()) {
                RolModel rol = formatToObjet(result);
                rols.add(rol);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rols;
    }

    public RolModel getById(Integer id) {
        RolModel rol = null;

        try {
            conn = ConnConfig.getConnection();

            query = "SELECT ID_ROL, NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD FROM ROL WHERE ID_ROL = ?";
            System.out.println("Executed query" + query);
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                rol = formatToObjet(result);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnConfig.close(conn);
                ConnConfig.close(statement);
                ConnConfig.close(result);
            } catch (SQLException ex) {
                Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rol;
    }

    public void create(RolModel newRol) {
        LocalDateTime todayDate = LocalDateTime.now();
        String todayDateFormated = todayDate.toString();

        try {
            conn = ConnConfig.getConnection();

            query = "INSERT INTO ROL (NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD) VALUES (?, ?, ?, ?, ?, ?, ?)";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, newRol.getName());
            statement.setString(2, newRol.getDescription());
            statement.setByte(3, newRol.getActive());
            statement.setString(4, newRol.getCreatedUser());
            statement.setString(5, newRol.getUpdatedUser());
            statement.setString(6, todayDateFormated);
            statement.setString(7, todayDateFormated);
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

    public void update(RolModel rol) {
        LocalDateTime todayDate = LocalDateTime.now();
        String todayDateFormated = todayDate.toString();

        try {
            conn = ConnConfig.getConnection();

            query = "UPDATE ROL SET NOMBRE = ?, DESCRIPCION = ?, ACTIVO = ?, USUARIO_CREA = ?, USUARIO_MOD = ?, FECHA_MOD = ? WHERE ID_ROL = ?";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setString(1, rol.getName());
            statement.setString(2, rol.getDescription());
            statement.setByte(3, rol.getActive());
            statement.setString(4, rol.getCreatedUser());
            statement.setString(5, rol.getUpdatedUser());
            statement.setString(6, todayDateFormated);
            statement.setInt(7, rol.getIdRol());
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

    public void delete(Integer idRol) {
        try {
            conn = ConnConfig.getConnection();

            query = "DELETE FROM ROL WHERE ID_ROL = ?";
            System.out.println("Executed query" + query);

            statement = conn.prepareStatement(query);
            statement.setInt(1, idRol);
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

    private RolModel formatToObjet(ResultSet result) throws SQLException {
        Integer idRol = result.getInt("ID_ROL");
        String name = result.getString("NOMBRE");
        Byte active = result.getByte("ACTIVO");
        String description = result.getString("DESCRIPCION");
        String createdUser = result.getString("USUARIO_CREA");
        String updatedUser = result.getString("USUARIO_MOD");
        Date createdDate = result.getDate("FECHA_CREA");
        Date updatedDate = result.getDate("FECHA_MOD");

        String createdDateFormatted = formatter.format(createdDate);
        String updatedDateFormatted = formatter.format(updatedDate);

        return new RolModel(idRol, name, active, description, createdUser, updatedUser, createdDateFormatted, updatedDateFormatted);
    }
}
