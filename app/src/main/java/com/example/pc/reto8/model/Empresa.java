package com.example.pc.reto8.model;

public class Empresa {

    private long empresaId;
    private String nombre;
    private String url;
    private String numero;
    private String email;
    private String clasificacion;
    private String productosYServicios;

    public Empresa(long empresaId, String nombre, String url, String numero, String email, String clasificacion, String productosYServicios){
        this.empresaId = empresaId;
        this.nombre = nombre;
        this.url = url;
        this.numero = numero;
        this.email =email;
        this.clasificacion = clasificacion;
        this.productosYServicios = productosYServicios;
    }

    public Empresa() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductosYServicios() {
        return productosYServicios;
    }

    public void setProductosYServicios(String productosYServicios) {
        this.productosYServicios = productosYServicios;
    }

    public long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(long empresaId) {
        this.empresaId = empresaId;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public enum ClasificacionEmpresa {
        CONSULTORIA("Consultoria"),
        DESAROLLO_A_LA_MEDIDA("Desarrollo a la medida"),
        FABRICA_DE_SOFTWARE("Fabrica de software");
        private final String text;
        ClasificacionEmpresa(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
