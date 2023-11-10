package in.ineuron.Test;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;


public class LoadAndDelete {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		boolean flag = false;
		
		int id = 11;
		
		try {
			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, id);
			
			if(session != null) {
				 transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				
				if(student != null) {
					session.delete(student);
					flag = true;
				}
				else {
					System.out.println("Record not available for deletion:: " + id);
				}
				
			}
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(flag) {
				transaction.commit();
				System.out.println("Object Deleted from the database...");
			}
			else {
				transaction.rollback();
				System.out.println("Object failed to delete...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
