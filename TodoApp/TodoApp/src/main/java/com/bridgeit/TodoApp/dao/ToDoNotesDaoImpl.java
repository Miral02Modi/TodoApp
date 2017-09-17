package com.bridgeit.TodoApp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.TodoApp.model.Collabrator;
import com.bridgeit.TodoApp.model.ToDoNotes;

/**
 * @author Miral
 *
 */
public class ToDoNotesDaoImpl implements ToDoNotesDao {

	@Autowired
	SessionFactory factory;

	@Override
	public int createNotes(ToDoNotes doNotesModel) throws Exception {
		Session session = factory.getCurrentSession();
		Serializable id = session.save(doNotesModel);
		int noteId = (int) id;
		System.out.println("id is::::" + id);
		return noteId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ToDoNotes> searchByTitle(List<Object> list) throws Exception {

		System.out.println("inside the dao search");
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(ToDoNotes.class);

		Criterion criterion2 = Restrictions.eq("title", list.get(0));
		Criterion criterion = Restrictions.eq("user.id", list.get(1));

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);
		conjunction.add(criterion2);

		criteria.add(conjunction);

		return criteria.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ToDoNotes> searchById(int id) throws Exception {

		System.out.println("inside the dao search");
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(ToDoNotes.class);

		Criterion criterion = Restrictions.eq("user.id", id);

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);

		criteria.add(conjunction);
		List list = criteria.list();
		// session.close();
		return list;
	}

	@Override
	public ToDoNotes updateNote(ToDoNotes doNotes) throws Exception {
		Session session = factory.getCurrentSession();
		// session.update(doNotes);
		System.out.println(doNotes);
		/*
		 * String updateQuery = "update ToDoNotes set ";
		 * 
		 * boolean flag = false; if (doNotes.getTitle() != null) { flag = true;
		 * updateQuery += "title=:title"; } if (doNotes.getDescription() !=
		 * null) { flag = true; updateQuery += "description=:description,"; } if
		 * (doNotes.getColor() != null) { flag = true; updateQuery +=
		 * "color=:color"; }
		 * 
		 * if (flag) updateQuery += " where id=:noteId";
		 * 
		 * Query query = null;
		 * 
		 * if (flag) query = session.createQuery(updateQuery);
		 * 
		 * if (doNotes.getTitle() != null) query.setParameter("title",
		 * doNotes.getTitle()); if (doNotes.getDescription() != null)
		 * query.setParameter("description", doNotes.getDescription()); if
		 * (doNotes.getColor() != null) query.setParameter("color",
		 * doNotes.getColor());
		 * 
		 * 
		 * 
		 * if (flag) { query.setParameter("noteId", doNotes.getId());
		 * System.out.println("inside the flag::" + flag);
		 * query.executeUpdate(); }
		 */
		
		String updateQuery = "Update ToDoNotes set date=:noteDate,description=:noteDesc, title=:noteTitle ,"
				+ " archive=:noteArchive,pinned=:notePinned,color=:noteColor,isTrash=:noteIsTrash,"
				+ "reminderTime =:noteReminderTime, image =:noteImage where id =:noteId";
		Query query = session.createQuery(updateQuery);

		query.setParameter("noteId", doNotes.getId());
		query.setParameter("noteDate", doNotes.getDate());
		query.setParameter("noteDesc", doNotes.getDescription());
		query.setParameter("noteTitle", doNotes.getTitle());
		query.setParameter("noteArchive", doNotes.getArchive());
		query.setParameter("notePinned", doNotes.getPinned());
		query.setParameter("noteColor", doNotes.getColor());
		query.setParameter("noteIsTrash", doNotes.getIsTrash());
		query.setParameter("noteReminderTime", doNotes.getReminderTime());
		query.setParameter("noteImage", doNotes.getImage());
		query.executeUpdate();

		// session.update(doNotes);

		return doNotes;
	}

	@Override
	public ToDoNotes deleteNote(ToDoNotes doNotes) throws Exception {
		System.out.println("inside the delete dao");
		Session session = factory.openSession();
		/*
		 * session.delete(doNotes);
		 * System.out.println("inside the delete dao1 end");
		 */
		Query query = session.createQuery("delete from ToDoNotes where id=:noteID");
		query.setParameter("noteID", doNotes.getId());
		query.executeUpdate();

		Query query1 = session.createQuery("delete from PageScraper where noteId=:noteID");
		query1.setParameter("noteID", doNotes.getId());
		query1.executeUpdate();

		return doNotes;
	}

	@Override
	public ToDoNotes archivedNotes(ToDoNotes doNotes) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int getOwnerId(String email) throws Exception {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("select id from UserRegistration where email=:emailID");
		query.setParameter("emailID", email);
		List list = query.list();

		System.out.println("list is:::" + list);
		int sharedWith = (int) list.get(0);
		return sharedWith;
	}

	@Override
	public void createCollbrator(Collabrator collabrator) throws Exception {
		Session session = factory.getCurrentSession();
		session.save(collabrator);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getSharedNotes(int sharedId) throws Exception {

		Session session = factory.getCurrentSession();
		Query query2 = session.createQuery("from ToDoNotes where (id in (select noteId from Collabrator where sharedWith=:sharedId)) or user.id=:userID");
		query2.setParameter("sharedId", sharedId);
		query2.setParameter("userID", sharedId);
		List<ToDoNotes> list2 = query2.list();
		System.out.println("All Shared Notes::::::\n\n\n" + list2);
		return list2;
	}

	

}
