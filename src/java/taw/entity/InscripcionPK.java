/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Francisco Bono
 */
@Embeddable
public class InscripcionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "EVENTO")
    private int evento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO")
    private int usuario;

    public InscripcionPK() {
    }

    public InscripcionPK(int evento, int usuario) {
        this.evento = evento;
        this.usuario = usuario;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) evento;
        hash += (int) usuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionPK)) {
            return false;
        }
        InscripcionPK other = (InscripcionPK) object;
        if (this.evento != other.evento) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.InscripcionPK[ evento=" + evento + ", usuario=" + usuario + " ]";
    }
    
}
