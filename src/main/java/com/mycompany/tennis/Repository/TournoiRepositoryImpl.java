package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Tournoi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    private Connection conn =null;

    public TournoiRepositoryImpl() {

    }

    public void create (Tournoi tournoi){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;

        String query = "insert into TOURNOI (NOM, CODE) VALUE (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,tournoi.getNom());
        preparedStatement.setString(2,tournoi.getCode());
        preparedStatement.executeUpdate() ;
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du tournoi créer est : "+rs.getLong(1));
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

         try {
             String query = "select * from  TOURNOI where  id = ?";
             conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             preparedStatement.setLong(1,id);
             ResultSet resultSet = preparedStatement.executeQuery() ;
             if(resultSet.next()){
                 tournoi.setId(resultSet.getLong(1));
                 tournoi.setNom(resultSet.getString(2));
                 tournoi.setCode(resultSet.getString(3));
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
         return  tournoi;
     }

     public void deleteTournois(Long id){
        try{
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;
            String query = "delete  from TOURNOI where ID= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du tournoi supprimer est : "+rs.getLong(1));
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
