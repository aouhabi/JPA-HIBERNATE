package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Tournoi;
import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    private Connection conn =null;

    public TournoiRepositoryImpl() {

    }

    public void create (Tournoi tournoi){
       EntityManager em = EntityManagerHolder.getEntityManager() ;
       em.persist(tournoi);


    }

    public void update(Tournoi tournoi){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "update TOURNOI set NOM=? , CODE=? where ID= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());
            preparedStatement.setLong(3,tournoi.getId());
            preparedStatement.executeUpdate() ;

        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public List<Tournoi> getTournoiList() {
        List <Tournoi>  tournoiList = new ArrayList<>();
        try{
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "select * from  TOURNOI";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery() ;
            while (resultSet.next()){
                Tournoi tournoi = new Tournoi();
                tournoi.setId(resultSet.getLong(1));
                tournoi.setNom(resultSet.getString(2));
                tournoi.setCode(resultSet.getString(3));
                tournoiList.add(tournoi) ;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  tournoiList ;
     }

     public Tournoi getTournoiById(Long id) {
         Tournoi tournoi = new Tournoi();
         EntityManager em =  EntityManagerHolder.getEntityManager() ;
          tournoi = em.find(Tournoi.class,id);
         return  tournoi;
     }

     public void deleteTournois(Long id){

        EntityManager em =  EntityManagerHolder.getEntityManager() ;
        Tournoi t = em.find(Tournoi.class,id);
        em.remove(t);
        System.out.println("Tournoi supprimer "+t.getId());

     }
}
