/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.client.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * Entidad Usuario que representa la tabla con el mismo nombre en la BD
 *
 * @author josorio
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_usuario")
    @SequenceGenerator(name = "gen_usuario", sequenceName = "usuario_id_seq")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "rol")
    private Integer rol;
    @Column(name = "fechainactivado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainactivado;
    @Column(name = "llave_cambio_clave")
    private String llaveCambioClave;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
//  @Transient
//  @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
//  private List<ValesXpedido> valesXpedidoList;
//  @Transient
//  @OneToMany(mappedBy = "usuario1", fetch = FetchType.LAZY)
//  private List<ValesXpedido> valesXpedidoList1;
    @Transient
    String nombrecompleto;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechainactivado() {
        return fechainactivado;
    }

    public void setFechainactivado(Date fechainactivado) {
        this.fechainactivado = fechainactivado;
    }

    public String getLlaveCambioClave() {
        return llaveCambioClave;
    }

    public void setLlaveCambioClave(String llaveCambioClave) {
        this.llaveCambioClave = llaveCambioClave;
    }

    public Integer getInteger() {
        return rol;
    }

    public void setInteger(Integer rol) {
        this.rol = rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Método para asignar al usuario todos los datos del usuario que se entrega
     * como parámetro
     *
     * @param usuario
     */
    public void copiarDatos(Usuario usuario) {
        this.id = usuario.getId();
        this.apellidos = usuario.getApellidos();
        this.clave = usuario.getClave();
        this.correo = usuario.getCorreo();
        this.fechainactivado = usuario.getFechainactivado();
        this.llaveCambioClave = usuario.getLlaveCambioClave();
        this.nombres = usuario.getNombres();
        this.rol = usuario.getInteger();
        this.fechaCreacion = usuario.getFechaCreacion();
    }

    public String getNombrecompleto() {
        nombrecompleto = nombres + " " + apellidos;
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    /**
     * Setea en nulo los atributos del objeto. este método se invoca para anular
     * la sesión del usuario
     */
    public void resetear() {
        this.id = -1;
        this.rol = null;
        this.clave = null;
        this.nombres = null;
        this.apellidos = null;
        this.llaveCambioClave = null;
        this.clave = null;
        this.correo = null;
    }
}
