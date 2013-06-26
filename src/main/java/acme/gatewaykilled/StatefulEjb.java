/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.gatewaykilled;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author kumm
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class StatefulEjb {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public <T> T findAndMerge(Class<T> entityClass, Object primaryKey) {
        return em.merge(em.find(entityClass, primaryKey));
    }

    public boolean contains(Object entity) {
        return em.contains(entity);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
        em.flush();
    }
    
}
