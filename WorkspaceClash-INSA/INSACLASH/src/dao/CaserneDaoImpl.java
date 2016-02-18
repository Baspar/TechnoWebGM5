package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import Model.Armee;
import Model.Caserne;
import Model.HDV;
import Model.TypeRessource;
import Model.TypeSoldat;

public class CaserneDaoImpl implements CaserneDao {
	private static final String SQL_INSERT_CASERNE="INSERT INTO Caserne (niveau, loginJoueur, x,y, nombreArcher, niveauArcher, nombreTrebuchet, niveauTrebuchet ) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_CASERNE="SELECT * FROM Caserne where loginJoueur= ?";
	private static final String SQL_UPGRADE_CASERNE="UPDATE Caserne SET niveau=? WHERE loginJoueur=?";
	private static final String SQL_DEPLACER_CASERNE="UPDATE Caserne SET x=?, y=? WHERE loginJoueur=?";
	
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
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Caserne caserne = null;

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_CASERNE, false, login );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            if ( resultSet.next() ) {
	            	caserne= map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return caserne;
	}

	@Override
	public void upgrade(String login) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	Caserne caserne=trouverCaserne(login);
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_UPGRADE_CASERNE, false, caserne.getNiveau()+1, login);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'augmentation de niveau de la caserne, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }

	}

	@Override
	public void deplacer(String login, int x, int y) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_DEPLACER_CASERNE, false, x,y, login);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec du deplacement de l'hdv, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }
	}
	
	private Caserne map(ResultSet res) throws SQLException{
		Caserne cas =new Caserne();
		cas.setNiveau(res.getInt("niveau"));
		cas.setX(res.getInt("X"));
		cas.setY(res.getInt("y"));
		Hashtable<TypeSoldat, Integer> niveauActuel=new Hashtable<TypeSoldat, Integer>();
		int niveauArcher=res.getInt("niveauArcher");
		int niveauTrebuchet=res.getInt("niveauTrebuchet");
		niveauActuel.put(TypeSoldat.ARCHER, niveauArcher);
		niveauActuel.put(TypeSoldat.TREBUCHET, niveauTrebuchet);
		cas.setNiveauActuel(niveauActuel);
		Armee armee=new Armee();
		int nbArcher=res.getInt("nombreArcher");
		int nbTrebuchet=res.getInt("nombreTrebuchet");
		for(int i=0; i<nbArcher; i++)
			armee.ajouterSoldat(TypeSoldat.ARCHER, niveauArcher);
		for(int j=0; j<nbTrebuchet;j++)
			armee.ajouterSoldat(TypeSoldat.TREBUCHET, niveauTrebuchet);
		return cas;
	}
	

}
