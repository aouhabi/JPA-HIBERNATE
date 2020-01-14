package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {

    private Connection conn =null;



    public void create (Match match){
        try {
            conn  = DataSourceProvider.getSingleDataSourceInstance().getConnection() ;

            String query = "insert into MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) value (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,match.getEpreuve().getId());
            preparedStatement.setLong(2,match.getVainqeur().getId());
            preparedStatement.setLong(3,match.getFinalise().getId());
            preparedStatement.executeUpdate() ;
            ResultSet rs = preparedStatement.getGeneratedKeys() ;
            if(rs.next()){
                System.out.println("L'identifiant du Match créer est : "+rs.getLong(1));
                match.setId(rs.getLong(1));
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


}
