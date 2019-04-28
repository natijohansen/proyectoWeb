package excepciones;

public class NegocioException extends Exception {
	
    public NegocioException(){ super();}
    public NegocioException(String mensaje){ super(mensaje); }

}
