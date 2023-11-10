package in.ineuron.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;


public class SaveORUpdate {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				 transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				
				Student student = new Student();
				student.setSid(14);
				student.setSname("Mahesh");
				student.setSaddress("Mumbai");
				student.setSage(28);
				
				session.saveOrUpdate(student);
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
				System.out.println("Object updated to the database...");
			}
			else {
				transaction.rollback();
				System.out.println("Object failed to update...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
