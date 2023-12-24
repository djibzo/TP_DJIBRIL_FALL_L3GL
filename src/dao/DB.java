package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    //Connexion
    private Connection cnx;
    //Prepared statements
    private PreparedStatement pstm;
    //SELECT statements results
    private ResultSet rs;
    //UPDATE statements results(insert,update,delete)
    private int ok;
    //Connetion method to the DB
    private void getConnection(){
        //Connexion's params
        String host="localhost";
        String database="gestion_user";
        int port=3306;
        String url="jdbc:mysql://"+host+":"+port+"/"+database;
        String user="root";
        String password="";
        try {
            //Load driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Base opening
            cnx= DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initPrepar(String sql){
        try{
            getConnection();
            pstm=cnx.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet executeSelect(){
        rs=null;
        try{
            rs=pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public int executeMaj(){
        try{
            ok =pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }
    public void closeConnection(){
        try{
           if (cnx!=null)
               cnx.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }
}
