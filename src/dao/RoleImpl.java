package dao;

import entities.Role;

import javax.management.relation.RoleList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoleImpl implements IRole{
    private DB db=new DB();
    private ResultSet rs;
    private int ok;
    @Override
    public int add(Role role) {
       String sql="INSERT INTO role(idRole,name) VALUES(null,?)";
       try{
           db.initPrepar(sql);//Ouverture de la connexion et intialisation de la requette
           db.getPstm().setString(1,role.getName());//Passage du nom de role
           ok=db.executeMaj();
           db.closeConnection();
       }catch (Exception e){
           e.printStackTrace();
       }
       return ok;
    }

    @Override
    public int update(Role role, int id) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Role> list() {
        List<Role> roles=new ArrayList<>();
        String sql="SELECT * FROM role";
        try{
            db.initPrepar(sql);
            rs= db.executeSelect();
            while (rs.next()){
                Role role=new Role();
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role get(int id) {
        String sql="SELECT * FROM role WHERE idRole = ?";
        Role rl=null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs=db.executeSelect();
            if (rs.next()){
                rl=new Role();
                rl.setId(rs.getInt("idRole"));
                rl.setName(rs.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rl;
    }
}
