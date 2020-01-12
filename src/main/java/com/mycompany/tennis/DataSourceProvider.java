package com.mycompany.tennis;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static  BasicDataSource singleDataSource;

    public  static BasicDataSource getSingleDataSourceInstance(){
        if( singleDataSource == null){
            singleDataSource = new BasicDataSource() ;
            singleDataSource .setUrl("jdbc:mysql://localhost:3306/TENNIS?useSSL=false");
            singleDataSource.setInitialSize(5); // nombre de connexion ouvert au demmarage
            singleDataSource.setUsername("root");
            singleDataSource.setPassword("root");
        }
        return  singleDataSource ;
    }
}

