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
public class InteresUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ETIQUETA")
    private int etiqueta;

    public InteresUsuarioPK() {
    }

    public InteresUsuarioPK(int usuario, int etiqueta) {
        this.usuario = usuario;
        this.etiqueta = etiqueta;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuario;
        hash += (int) etiqueta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InteresUsuarioPK)) {
            return false;
        }
        InteresUsuarioPK other = (InteresUsuarioPK) object;
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.etiqueta != other.etiqueta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.InteresUsuarioPK[ usuario=" + usuario + ", etiqueta=" + etiqueta + " ]";
    }
    
}
