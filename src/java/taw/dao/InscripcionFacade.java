/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import taw.entity.Evento;
import taw.entity.Inscripcion;
import taw.entity.Usuario;

/**
 *
 * @author Francisco Bono
 */
@Stateless
public class InscripcionFacade extends AbstractFacade<Inscripcion> {

    @PersistenceContext(unitName = "TAW-ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionFacade() {
        super(Inscripcion.class);
    }
    
    public List<Inscripcion> usuarioInscritoEn(Evento evento, Usuario usuario){
        Query q;
        List<Inscripcion> lista;
        q = this.em.createQuery("SELECT i FROM Inscripcion i WHERE (i.evento = :idEvento) AND (i.usuario = :idUsuario)");
        q.setParameter("idEvento", evento);
        q.setParameter("idUsuario", usuario);
        lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        return lista;
    }
    
    public List<Inscripcion> inscripcionesEvento(Evento idEvento){
        Query q;
        List<Inscripcion> lista;
        q = this.em.createQuery("SELECT i FROM Inscripcion i WHERE i.evento = :idEvento ORDER BY i.fila");
        q.setParameter("idEvento", idEvento);
        lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        return lista;
    }
    
}
