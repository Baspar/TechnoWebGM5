package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import Model.Joueur;
import form.ConnexionForm;

public class JoueurDaoImpl implements JoueurDao { 
    private static final String SQL_SELECT_PAR_LOGIN = "SELECT login, motDePasse FROM Joueur WHERE login = ?";
    private static final String SQL_INSERT           = "INSERT INTO Joueur (login, motDePasse) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT login FROM Joueur WHERE login != ?";
    private DAOFactory daoFactory;
    
    JoueurDaoImpl( DAOFactory daoFactory ) {//DONE
        this.daoFactory = daoFactory;
    }

    @Override
    public Joueur trouver( String login ) throws DAOException {//DONE
    	return trouver( SQL_SELECT_PAR_LOGIN, login );
    }

  
    @Override
    public void creer( Joueur joueur ) throws DAOException {//DONE
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, joueur.getLogin(), joueur.getMotDePasse());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du joueur, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

   
    
    private Joueur trouver( String sql, Object... objets ) throws DAOException {//DONE
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Joueur joueur = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                joueur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return joueur;
    }

   
    private static Joueur map( ResultSet resultSet ) throws SQLException {//DONE
        Joueur joueur = new Joueur();
        joueur.setLogin( resultSet.getString( "login" ) );
        joueur.setMotDePasse( resultSet.getString( "motDePasse" ) );
        return joueur;
    }

	@Override
	public Vector<String> trouverTousLesJoueurs(String login) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Vector<String> joueur = new Vector<String>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false, login );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            while ( resultSet.next() ) {
                joueur.add( resultSet.getString("login") );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return joueur;
	}

}