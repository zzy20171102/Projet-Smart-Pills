package com.db;

import java.sql.*;

/**
 * Class pour réaliser les opérations avec MySQL
 * Il faut modifier le password et le nom de la base de données
 */
public class DBManager {
    public static final String DRIVER = "com.mysql.jdbc.Driver"; //il faut ajouter une JDBC jar librairie
    public static final String USER = "root"; // default username
    public static final String PASS = "ZKL20141018"; //password
    public static final String URL = "jdbc:mysql://localhost:3306/serverdatabase";

    // format : jdbc:mysql://localhost:3306/(le nom de base de données)

    private static DBManager per = null;
    private Connection conn = null;
    private Statement stmt = null;

    private DBManager() {
    }

    /**
     * Crétation une instance de la classe
     * @return l'instance
     */

    public static DBManager createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    /**
     * Initialisation de la base de données avec le driveur
     */
    public void initDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * La connexion
     */

    public void connectDB() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager:Connect to database successful.");
    }

    /**
     * Fermer la BDD
     */

    public void closeDB() {
        System.out.println("Close connection to database..");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }

    /**
     * Récupération
     * @param sql Commande sql de recherche
     * @return
     */
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Insert
     * @param sql commande sql d'insertion
     * @return
     */

    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Méthode principale pour le tester
     * @param args
     */

    public static void main(String[] args) {
        String u1 = "aaa";
        String p1 = "111";
        String u2 = "bbb";
        String p2 = "222";
        String u3 = "ccc";
        String p3 = "333";
        Service service = new Service();
        System.out.println(service.login(u1, p1));
        System.out.println(service.login(u2, p2));
        System.out.println(service.login(u3, p3));
        System.out.println(service.register(u3,p3));
        System.out.println(service.login(u3,p3));

    }
}
