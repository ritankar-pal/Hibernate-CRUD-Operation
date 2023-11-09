package in.ineuron.Test;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;


public class PersistAppDynamicInsert {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		
		try {
			
			session = HibernateUtil.getSession();
			
			if(session != null) {
				transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				
				Student student = new Student();
				student.setSid(10);
				student.setSname("Maheshwari");
				student.setSaddress("Delhi");
				
				session.persist(student); //this is from the JPA specification but not from the hibernate
				flag = true;
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
				System.out.println("Object saved to Database");
				System.out.println("Object Saved with the ID:: " + id); //the id that is generated is null if we use session.persist();
			}
			else {
				transaction.rollback();
				System.out.println("Object failed to save in the Database");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
			
		}
		
	}

}
