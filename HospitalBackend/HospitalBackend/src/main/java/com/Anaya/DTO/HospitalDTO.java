package com.Anaya.DTO;

public class HospitalDTO {

    private Integer idHospital;
    private String nombre;
    private Integer antiguedad;
    private Double area;
    private String descSede;
    private String descGerente;
    private String descCondicion;
    private String descDistrito;
    private String descProvincia;

    public HospitalDTO(Integer idHospital, String nombre, Integer antiguedad, Double area,
                       String descSede, String descGerente, String descCondicion,
                       String descDistrito, String descProvincia) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.antiguedad = antiguedad;
        this.area = area;
        this.descSede = descSede;
        this.descGerente = descGerente;
        this.descCondicion = descCondicion;
        this.descDistrito = descDistrito;
        this.descProvincia = descProvincia;


    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getDescSede() {
        return descSede;
    }

    public void setDescSede(String descSede) {
        this.descSede = descSede;
    }

    public String getDescGerente() {
        return descGerente;
    }

    public void setDescGerente(String descGerente) {
        this.descGerente = descGerente;
    }

    public String getDescCondicion() {
        return descCondicion;
    }

    public void setDescCondicion(String descCondicion) {
        this.descCondicion = descCondicion;
    }

    public String getDescDistrito() {
        return descDistrito;
    }

    public void setDescDistrito(String descDistrito) {
        this.descDistrito = descDistrito;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }
}
