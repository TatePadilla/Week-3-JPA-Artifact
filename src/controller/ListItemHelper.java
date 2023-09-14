package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

/* Next is the listItemhelper, which contains the logic behind the methods that are used to interact with the database and its associated tables.
 * This file contains much of the actual JSQL language and also the entity manager
 * The entity manager provides the operations from and to the DB, finds objects, persists them, removes objects and more.
 */


public class ListItemHelper {
	/*
	 * The entity manager provides the operations from and to the DB, finds objects, persists them, removes objects and more.
	 * The entity manager is created by the entity manager factory which is configured by the persistence XML
	 * A persistence defines the connection data to the DB (The driver, username, password)
	 */
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("InstrumentList");
	
	public void insertItem(ListItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListItem>showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ListItem>allItems = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allItems;
	}
	
	public void deleteItem(ListItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.instrumentClass = :selectedInstrumentClass and li.instrument = :selectedInstrument", ListItem.class);
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedInstrumentClass", toDelete.getInstrumentClass());
		typedQuery.setParameter("selectedInstrument", toDelete.getInstrument());
		//we only want one result
		typedQuery.setMaxResults(1);
		//get the result and save it into a new list item
		ListItem result = typedQuery.getSingleResult();
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em
		.close();
	}

	public ListItem searchForItemById(int idToEdit) {
		/* The next thing our program does in the StartProgram.java is return those objects that were found that meet the criteria with the id.
		 *  The user then picks the ID that we want to delete.
		*/
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(ListItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit); // After merging in an entity we can change its property and the EntityManager would update the database automatically.
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItem> searchForItemByStore(String instrumentClassName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.instrumentClass = :selectedInstrumentClass", ListItem.class);
		typedQuery.setParameter("selectedInstrumentClass", instrumentClassName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListItem> searchForItemByItem(String instrumentName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.instrument = :selectedInstrument", ListItem.class);
		typedQuery.setParameter("selectedInstrument", instrumentName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	// This method closes the connection to the DB
	public void cleanUp(){
		emfactory.close();
	}

}
