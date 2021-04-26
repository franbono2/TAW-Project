/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taw.entity.EstudioEstadistico;

/**
 *
 * @author Francisco Bono
 */
@Stateless
public class EstudioEstadisticoFacade extends AbstractFacade<EstudioEstadistico> {

    @PersistenceContext(unitName = "TAW-ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudioEstadisticoFacade() {
        super(EstudioEstadistico.class);
    }
    
}
