package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JoueurService {
    private JoueurRepositoryImpl repository ;

    public JoueurService() {
        this.repository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur){
        Session session = null;
        Transaction tx = null ;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            this.repository.create(joueur);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void renommeJoueur(Long id , String nom){
        Session session = null;
        Transaction tx = null ;
        Joueur joueur= new Joueur();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            joueur = this.repository.getJoueurById(id);
            joueur.setNom(nom);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
    public List<Joueur> getAllJoueur(){
        return  this.repository.getJoueurList() ;
    }
    public  Joueur getJoueurById(Long id){

        Session session = null;
        Transaction tx = null ;
        Joueur joueur= new Joueur();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            joueur = this.repository.getJoueurById(id);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }

        return  joueur;
    }
    public void deleteJoueur(Long id){
        this.repository.deleteJoueur(id);
    }
}
