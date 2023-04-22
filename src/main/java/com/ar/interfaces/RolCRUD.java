package com.ar.interfaces;

import com.ar.models.RolModel;

import java.sql.ResultSet;
import java.util.List;

public interface RolCRUD {
    public List<RolModel> list();
    public List<RolModel> search(String name);
    public RolModel getById(Integer idRol);
    public void create(RolModel rol);
    public void update(RolModel rol);
    public void delete(Integer idRol);
}