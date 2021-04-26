/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco Bono
 */
@Entity
@Table(name = "INTERES_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InteresUsuario.findAll", query = "SELECT i FROM InteresUsuario i")
    , @NamedQuery(name = "InteresUsuario.findByUsuario", query = "SELECT i FROM InteresUsuario i WHERE i.interesUsuarioPK.usuario = :usuario")
    , @NamedQuery(name = "InteresUsuario.findByEtiqueta", query = "SELECT i FROM InteresUsuario i WHERE i.interesUsuarioPK.etiqueta = :etiqueta")})
public class InteresUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InteresUsuarioPK interesUsuarioPK;

    public InteresUsuario() {
    }

    public InteresUsuario(InteresUsuarioPK interesUsuarioPK) {
        this.interesUsuarioPK = interesUsuarioPK;
    }

    public InteresUsuario(int usuario, int etiqueta) {
        this.interesUsuarioPK = new InteresUsuarioPK(usuario, etiqueta);
    }

    public InteresUsuarioPK getInteresUsuarioPK() {
        return interesUsuarioPK;
    }

    public void setInteresUsuarioPK(InteresUsuarioPK interesUsuarioPK) {
        this.interesUsuarioPK = interesUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interesUsuarioPK != null ? interesUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InteresUsuario)) {
            return false;
        }
        InteresUsuario other = (InteresUsuario) object;
        if ((this.interesUsuarioPK == null && other.interesUsuarioPK != null) || (this.interesUsuarioPK != null && !this.interesUsuarioPK.equals(other.interesUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.InteresUsuario[ interesUsuarioPK=" + interesUsuarioPK + " ]";
    }
    
}
