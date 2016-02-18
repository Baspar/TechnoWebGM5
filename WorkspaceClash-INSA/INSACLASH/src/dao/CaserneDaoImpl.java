package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Caserne;
import Model.TypeRessource;
import Model.TypeSoldat;

public class CaserneDaoImpl implements CaserneDao {
	private static final String SQL_INSERT_CASERNE="INSERT INTO Caserne (niveau, loginJoueur, x,y, nombreArcher, niveauArcher, nombreTrebuchet, niveauTrebuchet ) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_CASERNE="SELECT * FROM Caserne where loginJoueur= ?";
	private static final String SQL_UPGRADE_CASERNE="UPDATE Caserne SET niveau=? WHERE loginJoueur=?";
	private static final String SQL_DEPLACER_CASERNE="UPDATE Caserne SET x=? y=? WHERE loginJoueur=?";
	
	private DAOFactory daoFactory;
	
	public CaserneDaoImpl(DAOFactory daof){
		daoFactory=daof;
	}
	
	@Override
	public void creerCaserne(Caserne caserne, String login) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_CASERNE, false, caserne.getNiveau(), login, caserne.getX(), caserne.getY(), 
            		caserne.getNombreArcher(), caserne.getNiveauActuel().get(TypeSoldat.ARCHER), caserne.getNombreTrebuchet(), caserne.getNiveauActuel().get(TypeSoldat.TREBUCHET));
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la caserne, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }

	}

	@Override
	public Caserne trouverCaserne(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upgrade(String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deplacer(String login, int x, int y) {
		// TODO Auto-generated method stub

	}

}
