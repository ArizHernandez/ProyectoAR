package com.ar.interfaces;

import com.ar.models.RolModel;
import com.ar.models.UserModel;

import java.util.List;

public interface UserCRUD {
    public List<UserModel> list();
    public List<UserModel> search(String name);
    public UserModel getById(Integer idUser);
    public void create(UserModel rol);
    public void update(UserModel rol);
    public void delete(Integer idUser);
}