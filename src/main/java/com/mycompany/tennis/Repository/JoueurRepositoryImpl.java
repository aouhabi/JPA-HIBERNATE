package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    private Connection conn =null;



    public void create (Joueur joueur){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;

            String query = "insert into JOUEUR (NOM, PRENOM, SEXE) value(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.executeUpdate() ;
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du Joueur créer est : "+rs.getLong(1));
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

    }

    public void update(Joueur joueur){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "update JOUEUR set NOM=? , PRENOM=?, SEXE=? where ID= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.setLong(4,joueur.getId());
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
        Session session = null;
        try {
         session = HibernateUtil.getSessionFactory().openSession() ;
         joueur = session.get(Joueur.class,id);

        }catch (Throwable e){
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
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
