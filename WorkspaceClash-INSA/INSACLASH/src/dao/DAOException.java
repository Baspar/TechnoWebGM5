package dao;


public class DAOException extends RuntimeException {
	

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