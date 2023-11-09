package in.ineuron.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;


public class LoadRecordApp {

	public static void main(String[] args) {
		
		Session session = null; 
		int id = 11;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				Student student = session.load(Student.class, id);
				if(student != null) {
					System.out.println("Student id is:: " + student.getSid());
					System.out.println("Student name is:: " + student.getSname());
					System.out.println("Student age is:: " + student.getSage());
					System.out.println("Student address is:: " + student.getSaddress());
				}
				else {
					System.out.println("Record Not Found for the given ID:: " + id);
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
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
