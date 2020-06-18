package com.db;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Cette classe serve à tester la connexion par Http en une application de Login et Register.
 * Login: Recherche dans la BDD le nom d'utilisateur et le mot de passe
 * Register: Insérer dans la BDD un nouveau nom avec un mot de passe
 */
public class Service {

    /**
     * Login
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password){

        String logSql = "select * from yh where name ='" + username + "' and psd ='" + password +"'";
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        try{
            ResultSet rs = sql.executeQuery(logSql);
            if(rs.next()){
                sql.closeDB();
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

    /**
     * Register
     * @param username
     * @param password
     * @return
     */

    public Boolean register(String username, String password){
        String regSql = "insert into yh values('" + username+ "','"+ password+ "')";

        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if(ret!=0){
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        return false;
    }


}
