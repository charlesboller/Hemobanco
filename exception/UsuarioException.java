package exception;

public class UsuarioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioException() {
		// TODO Auto-generated constructor stub
		super("Erro Generico.");
	}

	public UsuarioException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}