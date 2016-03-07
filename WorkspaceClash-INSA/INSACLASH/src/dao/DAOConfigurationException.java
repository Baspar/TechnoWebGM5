package dao;

public class DAOConfigurationException extends RuntimeException {	
	private static final long serialVersionUID = 1L;

	public DAOConfigurationException( String message ) {//DONE
		super( message );
	}
	 	
	public DAOConfigurationException( String message, Throwable cause ) {//DONE
		super( message, cause );
	}
		
	public DAOConfigurationException( Throwable cause ) {//DONE
		super( cause );
	}
	
} 