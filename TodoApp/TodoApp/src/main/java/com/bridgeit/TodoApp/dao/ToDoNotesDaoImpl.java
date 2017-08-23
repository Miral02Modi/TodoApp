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
		Serializable id= session.save(doNotesModel);
		int noteId =(int) id;
		System.out.println("id is::::"+id);
		return  noteId;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ToDoNotes> searchById(int id) throws Exception {

		System.out.println("inside the dao search");
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(ToDoNotes.class);

		Criterion criterion = Restrictions.eq("user.id", id);

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);

		criteria.add(conjunction);

		return criteria.list();
	}

	@Override
	public ToDoNotes updateNote(ToDoNotes doNotes) throws Exception {
		Session session = factory.getCurrentSession();
		// session.update(doNotes);
		System.out.println(doNotes);
		/*String updateQuery = "update ToDoNotes set ";

		boolean flag = false;
		if (doNotes.getTitle() != null) {
			flag = true;
			updateQuery += "title=:title";
		}
		if (doNotes.getDescription() != null) {
			flag = true;
			updateQuery += "description=:description,";
		}
		if (doNotes.getColor() != null) {
			flag = true;
			updateQuery += "color=:color";
		}

		if (flag)
			updateQuery += " where id=:noteId";

		Query query = null;
		
		if (flag)
			query = session.createQuery(updateQuery);

		if (doNotes.getTitle() != null)
			query.setParameter("title", doNotes.getTitle());
		if (doNotes.getDescription() != null)
			query.setParameter("description", doNotes.getDescription());
		if (doNotes.getColor() != null)
			query.setParameter("color", doNotes.getColor());

		

		if (flag) {
			query.setParameter("noteId", doNotes.getId());
			System.out.println("inside the flag::" + flag);
			query.executeUpdate();
		}*/
		
		session.update(doNotes);	

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

}
