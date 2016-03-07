package dao;


public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DAOException( String message ) {//DONE
		super( message );
	}
	
	public DAOException( String message, Throwable cause ) {//DONE
		super( message, cause );
	}
		
	public DAOException( Throwable cause ) {//DONE
		super( cause );
	}
	
} 