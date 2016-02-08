package dao;

/**
* Classe DAOConfigurationException representant les exceptions de configuration du DAO
*
* @author  Celine Chaugny & Damien Pointin 
*/
public class DAOConfigurationException extends RuntimeException {
	/**
	* Constructeur DAOConfigurationException.
	* <p>
	* Avec le paramètre message
	* </p>
	*
	* @param message
	* Le message de l'exception.
	*/ 	
	public DAOConfigurationException( String message ) {
		super( message );
	}
	
	
	/**
	* Constructeur DAOConfigurationException.
	* <p>
	* Avec le paramètre message et la cause
	* </p>
	*
	* @param message
	* Le message de l'exception.
	* @param cause
	* La cause de l'exception
	*/ 	
	public DAOConfigurationException( String message, Throwable cause ) {
		super( message, cause );
	}
	
	
	/**
	* Constructeur DAOConfigurationException.
	* <p>
	* Avec le paramètre cause
	* </p>
	*
	* @param cause
	* La cause de l'exception.
	*/ 	
	public DAOConfigurationException( Throwable cause ) {
		super( cause );
	}
	
} 