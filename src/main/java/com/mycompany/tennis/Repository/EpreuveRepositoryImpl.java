package com.mycompany.tennis.Repository;

import com.mycompany.tennis.Entity.Epreuve;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class EpreuveRepositoryImpl {



    public Epreuve getById(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
        Epreuve epreuve = session.get(Epreuve.class,id) ;
        return  epreuve ;
    }

    public List<Epreuve> getListEpreuveByCode(String code){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("SELECT ep FROM Epreuve ep where ep.tournoi.code=:cd",Epreuve.class) ;
        query.setParameter("cd",code);

        return  query.getResultList();
    }

    }


