package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Score;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryImpl {

    private Connection conn =null;



    public void create (Score score){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;

            String query = "insert into SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) value (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,score.getMatch().getId());
            preparedStatement.setByte(2,score.getSet1());
            preparedStatement.setByte(3,score.getSet2());
            if(score.getSet3() == null){
                preparedStatement.setNull(4,Types.TINYINT);
            }else {
                preparedStatement.setByte(4,score.getSet3());
            }
            if(score.getSet3() == null){
                preparedStatement.setNull(5,Types.TINYINT);
            }else {
                preparedStatement.setByte(5,score.getSet4());
            }
            if(score.getSet3() == null){
                preparedStatement.setNull(6,Types.TINYINT);
            }else {
                preparedStatement.setByte(6,score.getSet5());
            }


            preparedStatement.executeUpdate() ;
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du Score créer est : "+rs.getLong(1));
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

    public Score getById(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
        Score score = session.get(Score.class,id) ;

        return  score ;
    }

    }


