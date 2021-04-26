/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco Bono
 */
@Entity
@Table(name = "ESTUDIO_ESTADISTICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudioEstadistico.findAll", query = "SELECT e FROM EstudioEstadistico e")
    , @NamedQuery(name = "EstudioEstadistico.findById", query = "SELECT e FROM EstudioEstadistico e WHERE e.id = :id")
    , @NamedQuery(name = "EstudioEstadistico.findByNombre", query = "SELECT e FROM EstudioEstadistico e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstudioEstadistico.findByDescripcion", query = "SELECT e FROM EstudioEstadistico e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "EstudioEstadistico.findByResultados", query = "SELECT e FROM EstudioEstadistico e WHERE e.resultados = :resultados")})
public class EstudioEstadistico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "RESULTADOS")
    private String resultados;
    @JoinTable(name = "ESTUDIOS_USUARIO", joinColumns = {
        @JoinColumn(name = "ESTUDIO_ESTADISTICO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Usuario> usuarioList;

    public EstudioEstadistico() {
    }

    public EstudioEstadistico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudioEstadistico)) {
            return false;
        }
        EstudioEstadistico other = (EstudioEstadistico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.EstudioEstadistico[ id=" + id + " ]";
    }
    
}
