/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.gatewaykilled;

import javax.ejb.embeddable.EJBContainer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author kumm
 */
public class StatefulEjbTest {
    
    StatefulEjb instance;
    Fixture fixture;
    static EJBContainer container;
    
    @BeforeClass
    public static void beforeClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }

    @Before
    public void setUp() throws Exception {
        fixture = (Fixture) container.getContext().lookup("java:global/classes/Fixture");
        fixture.createFixture();
        instance = (StatefulEjb) container.getContext().lookup("java:global/classes/StatefulEjb");
    }

    @Test
    public void testExtendedContextByContains() throws Exception {
        TestEntity entity = instance.find(TestEntity.class, 1L);
        assertTrue("Entity should be managed.",instance.contains(entity));
    }

    @Test
    public void testExtendedContextByContainsForceMerge() throws Exception {
        TestEntity entity = instance.findAndMerge(TestEntity.class, 1L);
        assertTrue(instance.contains(entity));
    }

    @Test
    public void testExtendedContextByContainsForceMergeTrickier() throws Exception {
        TestEntity entity1 = instance.findAndMerge(TestEntity.class, 1L);
        TestEntity entity2 = instance.findAndMerge(TestEntity.class, 2L);
        assertTrue("Second entity should be managed.", instance.contains(entity2));
        assertTrue("First entity should be managed.", instance.contains(entity1));
    }
    
    @Test
    public void testExtendedContextBySave() {
        TestEntity entity = instance.find(TestEntity.class, 1L);
        entity.setName("MODIFIED");
        instance.save();
        TestEntity savedEntity = fixture.find(1L);
        assertEquals("Change should be saved", "MODIFIED", savedEntity.getName());
    }
}