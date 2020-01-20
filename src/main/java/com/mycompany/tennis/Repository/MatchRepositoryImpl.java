package com.mycompany.tennis.Repository;

import com.mycompany.tennis.Entity.Match;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;


public class MatchRepositoryImpl {




    public Match getById (Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); ;
        Match match = session.get(Match.class, id);
        return  match ;
    }


    public void update(Match match) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); ;
        session.update( match);
    }
    public void create(Match match){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); ;
        session.persist(match);
    }

    public  void  delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class,id);
        session.delete(match);
    }


}
