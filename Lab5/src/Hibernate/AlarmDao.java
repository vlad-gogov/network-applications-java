package Hibernate;

import Alarm.IAlarm;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlarmDao {

    public void insertAlarm(IAlarm alarm) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(alarm);
        transaction.commit();
        session.close();
    }

    public void deleteAlarm(IAlarm alarm) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(alarm);
        transaction.commit();
        session.close();
    }
}
