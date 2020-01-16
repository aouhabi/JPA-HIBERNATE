package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    private Connection conn =null;



    public void create (Joueur joueur){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
            session.persist(joueur);

    }


    public List<Joueur> getJoueurList() {
        List <Joueur>  joueurList = new ArrayList<>();
        try{
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "select * from  JOUEUR";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery() ;
            while (resultSet.next()){
                Joueur joueur = new Joueur();
                joueur.setId(resultSet.getLong(1));
                joueur.setNom(resultSet.getString(2));
                joueur.setPrenom(resultSet.getString(3));
                joueur.setSexe(resultSet.getString(4).charAt(0));
                joueurList.add(joueur) ;
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
        return  joueurList ;
    }

    public Joueur getJoueurById(Long id) {
        Joueur joueur = new Joueur();
         Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
         joueur = session.get(Joueur.class,id);
        return  joueur;
    }

    public void deleteJoueur(Long id){
        try{
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "delete  from JOUEUR where ID= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du Joueur supprimer est : "+rs.getLong(1));
            }
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
}
