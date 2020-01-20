package com.mycompany.tennis.service;

import com.mycompany.tennis.DTO.EpreuveDTO;
import com.mycompany.tennis.Entity.Epreuve;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EpreuveService {
    EpreuveRepositoryImpl epreuveRepository ;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }



    public  Epreuve getEpreuveAvecTournoi(Long id){
        Epreuve epreuve = null;
        EpreuveDTO dto = new EpreuveDTO();
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx= session.beginTransaction();
            epreuve = this.epreuveRepository.getById(id);
           // Hibernate.initialize(epreuve.getTournoi());
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTournoi(epreuve.getTournoi());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            Hibernate.initialize(epreuve.getParticipants());
            dto.setParticipants(epreuve.getParticipants());
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return  epreuve;
    }
    public EpreuveDTO getEpreuveBySansTournoi(Long id){
        Epreuve epreuve = null;
        EpreuveDTO dto = new EpreuveDTO();
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx= session.beginTransaction();
            epreuve = this.epreuveRepository.getById(id);
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return  dto;
    }

    public List<Epreuve> getListEpreuveByCode(String code){
        List<Epreuve> epreuves = null;
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx= session.beginTransaction();
            epreuves = this.epreuveRepository.getListEpreuveByCode(code);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return epreuves;
        }


}
