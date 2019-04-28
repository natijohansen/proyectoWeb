package controlador;

import java.sql.SQLException;

import daos.UserDaos;
import excepciones.NegocioException;
import modelo.User;

public class UserLogic {

	//Esta clase es mi conexion entre el servlet y el daos. Se busca tener solo una instancia
	//de UserDaos que se encargue de todas las consultas. El atributo instancia me va a permitir
	//validar esto. El controlador de UserLogic me va a crear una instancia de UserDaos
    private static UserLogic instancia;
    private final UserDaos userDAO;
    
    private UserLogic(){
        userDAO = new UserDaos();
    }
    
    public static UserLogic getInstancia(){
        if(instancia==null){
            instancia = new UserLogic();
        }
        return instancia;
    }
    
    public User identificarUser(String usu, String pass) throws SQLException, NegocioException{
        User usr = userDAO.identificarUser(usu, pass);
        if(usr == null){
            throw new NegocioException("No se ha encontrado al usuario.");
        } else if(!usr.getEstado()){
            throw new NegocioException("El usuario se encuentra inhabilitado");
        }
        return usr;
    }
}
