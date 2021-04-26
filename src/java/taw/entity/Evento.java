/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco Bono
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id")
    , @NamedQuery(name = "Evento.findByCosteInscripcion", query = "SELECT e FROM Evento e WHERE e.costeInscripcion = :costeInscripcion")
    , @NamedQuery(name = "Evento.findByAforo", query = "SELECT e FROM Evento e WHERE e.aforo = :aforo")
    , @NamedQuery(name = "Evento.findByLimiteEntradasPorUsuario", query = "SELECT e FROM Evento e WHERE e.limiteEntradasPorUsuario = :limiteEntradasPorUsuario")
    , @NamedQuery(name = "Evento.findByNombre", query = "SELECT e FROM Evento e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Evento.findByLocalizacion", query = "SELECT e FROM Evento e WHERE e.localizacion = :localizacion")
    , @NamedQuery(name = "Evento.findByDescripcion", query = "SELECT e FROM Evento e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Evento.findByFechaCelebracion", query = "SELECT e FROM Evento e WHERE e.fechaCelebracion = :fechaCelebracion")
    , @NamedQuery(name = "Evento.findByFechaLimiteReserva", query = "SELECT e FROM Evento e WHERE e.fechaLimiteReserva = :fechaLimiteReserva")
    , @NamedQuery(name = "Evento.findByNumeroFilas", query = "SELECT e FROM Evento e WHERE e.numeroFilas = :numeroFilas")
    , @NamedQuery(name = "Evento.findByAsientosPorFila", query = "SELECT e FROM Evento e WHERE e.asientosPorFila = :asientosPorFila")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTE_INSCRIPCION")
    private int costeInscripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AFORO")
    private int aforo;
    @Column(name = "LIMITE_ENTRADAS_POR_USUARIO")
    private Integer limiteEntradasPorUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LOCALIZACION")
    private String localizacion;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CELEBRACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCelebracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_LIMITE_RESERVA")
    @Temporal(TemporalType.DATE)
    private Date fechaLimiteReserva;
    @Column(name = "NUMERO_FILAS")
    private Integer numeroFilas;
    @Column(name = "ASIENTOS_POR_FILA")
    private Integer asientosPorFila;
    @ManyToMany(mappedBy = "eventoList")
    private List<Etiqueta> etiquetaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "evento1")
    private Inscripcion inscripcion;
    @JoinColumn(name = "CREADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario creador;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, int costeInscripcion, int aforo, String nombre, String localizacion, Date fechaCelebracion, Date fechaLimiteReserva) {
        this.id = id;
        this.costeInscripcion = costeInscripcion;
        this.aforo = aforo;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.fechaCelebracion = fechaCelebracion;
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCosteInscripcion() {
        return costeInscripcion;
    }

    public void setCosteInscripcion(int costeInscripcion) {
        this.costeInscripcion = costeInscripcion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public Integer getLimiteEntradasPorUsuario() {
        return limiteEntradasPorUsuario;
    }

    public void setLimiteEntradasPorUsuario(Integer limiteEntradasPorUsuario) {
        this.limiteEntradasPorUsuario = limiteEntradasPorUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCelebracion() {
        return fechaCelebracion;
    }

    public void setFechaCelebracion(Date fechaCelebracion) {
        this.fechaCelebracion = fechaCelebracion;
    }

    public Date getFechaLimiteReserva() {
        return fechaLimiteReserva;
    }

    public void setFechaLimiteReserva(Date fechaLimiteReserva) {
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

    public Integer getNumeroFilas() {
        return numeroFilas;
    }

    public void setNumeroFilas(Integer numeroFilas) {
        this.numeroFilas = numeroFilas;
    }

    public Integer getAsientosPorFila() {
        return asientosPorFila;
    }

    public void setAsientosPorFila(Integer asientosPorFila) {
        this.asientosPorFila = asientosPorFila;
    }

    @XmlTransient
    public List<Etiqueta> getEtiquetaList() {
        return etiquetaList;
    }

    public void setEtiquetaList(List<Etiqueta> etiquetaList) {
        this.etiquetaList = etiquetaList;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entity.Evento[ id=" + id + " ]";
    }
    
}
