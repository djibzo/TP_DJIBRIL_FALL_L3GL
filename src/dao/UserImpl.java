package dao;

import entities.Role;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser{
    private DB db=new DB();
    private ResultSet rs;
    private int ok;
    @Override
    public int add(User user) {
        String sql="INSERT INTO user(idUser,email,passwordHashed,role) VALUES(null,?,?,?)";
        try {
            //Ouverture de la connexion a la BD/Initialisation de la requete
            db.initPrepar(sql);
            //Passage de valeurs a la requette
            db.getPstm().setString(1, user.getEmail());
            db.getPstm().setString(2, user.getPasswordHashed());
            db.getPstm().setInt(3,user.getRole().getId());
            ok=db.executeMaj();
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(User user, int id) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<User> list() {
       List<User> users=new ArrayList<>();
       String sql="SELECT * FROM user u,role r where u.role=r.idRole";
       try {
           db.initPrepar(sql);
           rs= db.executeSelect();
           while (rs.next()){
               User user=new User();
               user.setId(rs.getInt("idUser"));
               user.setEmail(rs.getString("email"));
               user.setPassword(rs.getString("passwordHashed"));
               IRole roleDao=new RoleImpl();
               Role role=roleDao.get(rs.getInt("r.idRole"));
               user.setRole(role);
               users.add(user);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return users;
    }

    @Override
    public User get(int id) {
        return null;
    }
}
