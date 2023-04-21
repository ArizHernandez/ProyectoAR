package com.ar.models;

public class RolModel {

    private Integer idRol;
    private String name;
    private Byte active;
    private String description;
    private String createdUser;
    private String updatedUser;
    private String createdDate;
    private String updatedDate;

    public RolModel() {
    }

    public RolModel(String name, Byte active, String description, String createdUser, String updatedUser, String createdDate, String updatedDate) {
        this.name = name;
        this.active = active;
        this.description = description;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public RolModel(Integer idRol, String name, Byte active, String description, String createdUser, String updatedUser, String createdDate, String updatedDate) {
        this.idRol = idRol;
        this.name = name;
        this.active = active;
        this.description = description;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
