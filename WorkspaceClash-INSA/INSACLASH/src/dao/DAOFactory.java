package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import Model.Joueur;
import Model.MineCharbon;
import Model.MineOr;
import Model.Ressource;
import form.ConnexionForm;


public class DAOFactory {
	private static final String FICHIER_PROPERTIES = "dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
	private String url;
	private String username; 
	private String password;
	
	DAOFactory( String url, String username, String password ) {//DONE
		this.url = url;
		this.username = username;
		this.password = password;
	}
	

	public static DAOFactory getInstance() throws DAOConfigurationException {//DONE
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
		if ( fichierProperties == null ) {
			throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
		}
		try {
			properties.load( fichierProperties );
			url = properties.getProperty( PROPERTY_URL );
			driver = properties.getProperty( PROPERTY_DRIVER );
			nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
			motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
		} catch ( IOException e ) {
			throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
		}
		try {
			Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
		}
		DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
			return instance;
		}
		/* Méthode chargée de fournir une connexion à la base de données */
		/* package */ 
		Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, username, password );
	}
		
	
	// Méthodes de récupération de l'implémentation des différents DAO (un seul pour le moment)
	public JoueurDao getJoueurDao() {//DONE
		return new JoueurDaoImpl( this );
	}
	
	public HDVDao getHDVDao() {//DONE
		return new HDVDaoImpl( this );
	}

	public CaserneDao getCaserneDao() {//DONE
		return new CaserneDaoImpl(this);
	}
		
	public DefenseDao getDefenseDao(){//DONE
		return new DefenseDaoImpl(this);
	}
	
	public RessourceDao getRessourceDao(){//DONE
		return new RessourceDaoImpl(this);
	}
	
	public VillageDao getVillageDao(){//DONE
		return new VillageDaoImpl(this);
	}
} 