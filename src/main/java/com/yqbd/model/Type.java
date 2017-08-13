package com.yqbd.model;

public class Type {
    private Integer typeId;

    private String typeClassification;

    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeClassification() {
        return typeClassification;
    }

    public void setTypeClassification(String typeClassification) {
        this.typeClassification = typeClassification == null ? null : typeClassification.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}