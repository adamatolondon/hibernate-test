package com.test.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.test.hibernate.model.onetoone.Fingerprint;
import com.test.hibernate.model.onetoone.Person;

/**
 * java -jar $DERBY_HOME/lib/derbyrun.jar server start
 *
 * connect 'jdbc:derby://localhost:1527/test';
 *
 * @author adamato
 *
 */
public class OneToOneBidTest {

    @Test
    public void persist() throws Exception {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("onetoone_bid", PersistenceUnitProperties.getProperties());
	final EntityManager em = emf.createEntityManager();
	try {
	    final EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    Person person = new Person();
	    person.setName("John Smith");

	    Fingerprint fingerprint = new Fingerprint();
	    fingerprint.setType("arch");
	    fingerprint.setPerson(person);
	    person.setFingerprint(fingerprint);

	    em.persist(person);
	    em.persist(fingerprint);

	    tx.commit();

	    tx.begin();
	    em.detach(person);
	    em.detach(fingerprint);

	    Person p = em.find(Person.class, person.getId());
	    Fingerprint f = em.find(Fingerprint.class, fingerprint.getId());
	    Assertions.assertNotNull(p);
	    Assertions.assertFalse(p == person);
	    Assertions.assertEquals(person.getId(), p.getId());
	    Assertions.assertNotNull(p.getFingerprint());
	    Assertions.assertEquals(p.getFingerprint(), f);
	    Assertions.assertNotNull(p.getFingerprint().getPerson());
	    Assertions.assertEquals(p, p.getFingerprint().getPerson());
	    Assertions.assertEquals("John Smith", p.getName());
	    Assertions.assertEquals("arch", p.getFingerprint().getType());
	    em.remove(p);
	    em.remove(f);
	    tx.commit();
	} finally {
	    em.close();
	    emf.close();
	}
    }

}
