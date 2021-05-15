/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "INSCRIPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i")
    , @NamedQuery(name = "Inscripcion.findByEvento", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.evento = :evento")
    , @NamedQuery(name = "Inscripcion.findByUsuario", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.usuario = :usuario")
    , @NamedQuery(name = "Inscripcion.findByFechaInscripcion", query = "SELECT i FROM Inscripcion i WHERE i.fechaInscripcion = :fechaInscripcion")
    , @NamedQuery(name = "Inscripcion.findByFila", query = "SELECT i FROM Inscripcion i WHERE i.fila = :fila")
    , @NamedQuery(name = "Inscripcion.findByColumna", query = "SELECT i FROM Inscripcion i WHERE i.columna = :columna")})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InscripcionPK inscripcionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSCRIPCION")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FILA")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLUMNA")
    private int columna;
    @JoinColumn(name = "EVENTO", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Inscripcion() {
    }

    public Inscripcion(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Inscripcion(InscripcionPK inscripcionPK, Date fechaInscripcion, int fila, int columna) {
        this.inscripcionPK = inscripcionPK;
        this.fechaInscripcion = fechaInscripcion;
        this.fila = fila;
        this.columna = columna;
    }

    public Inscripcion(int evento, int usuario) {
        this.inscripcionPK = new InscripcionPK(evento, usuario);
    }

    public InscripcionPK getInscripcionPK() {
        return inscripcionPK;
    }

    public void setInscripcionPK(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscripcionPK != null ? inscripcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.inscripcionPK == null && other.inscripcionPK != null) || (this.inscripcionPK != null && !this.inscripcionPK.equals(other.inscripcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.Inscripcion[ inscripcionPK=" + inscripcionPK + " ]";
    }
    
}
