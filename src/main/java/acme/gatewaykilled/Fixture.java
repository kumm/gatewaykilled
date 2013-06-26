/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.gatewaykilled;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kumm
 */
@Stateless
public class Fixture {
    @PersistenceContext
    EntityManager em;
    
    public void createFixture() {
        em.createQuery("DELETE FROM TestEntity").executeUpdate();
        TestEntity first = new TestEntity(1L,"FIRST");
        TestEntity second = new TestEntity(2L,"SECOND");
        em.persist(first);
        em.persist(second);
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
    public TestEntity find(Long id) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.find(TestEntity.class, id);
    }
}
