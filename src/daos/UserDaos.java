package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.User;

public class UserDaos {

	public ArrayList<User> getAll() throws SQLException{
        
		ArrayList<User> usuarios = new ArrayList<>();
		String query = "select * from usuarios";
		PreparedStatement statement = null;
		
		try{
			statement = Conexion.createQuery(query);
            ResultSet myRs = statement.executeQuery();

            while(myRs.next()){
                User user = new User();
                user.setUser(myRs.getString("user"));
                user.setPass(myRs.getString("pass"));
                user.setEmail(myRs.getString("email"));
                user.setNombre(myRs.getString("nombre"));
                user.setAdmin(myRs.getBoolean("admin"));
                user.setEstado(myRs.getBoolean("estado"));
                
                usuarios.add(user);
            }
		} finally {
		
			}
		return usuarios;
	}

    public User identificarCliente(String usu, String pass) throws SQLException {
        User user = new User();
        String query = "select * from usuarios where user=? and pass=?";
		PreparedStatement statement = null;
        try{
        	statement = Conexion.createQuery(query);
        	statement.setString(1, usu);
            statement.setString(2, pass);
            ResultSet myRs = statement.executeQuery();
			
	if (myRs.next()){
                user.setUser(myRs.getString("user"));
                user.setPass(myRs.getString("pass"));
                user.setEmail(myRs.getString("email"));
                user.setNombre(myRs.getString("nombre"));
                user.setAdmin(myRs.getBoolean("admin"));
                user.setEstado(myRs.getBoolean("estado"));
            } else {
                user = null;
            }
        }
        finally{

        }
        return user;
}
	
	}
