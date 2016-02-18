package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Canon;
import Model.Caserne;
import Model.Defense;
import Model.Mortier;
import Model.TypeSoldat;

public class DefenseDaoImpl implements DefenseDao {
	private static final String SQL_FIND_DEFENSE_ID="SELECT * FROM Defense where loginJoueur=?, id=?";
	private static final String SQL_FIND_DEFENSE_PAR_TYPE="SELECT * FROM Defense where loginJoueur= ?, typeDefense=?";
	private static final String SQL_UPGRADE_DEFENSE="UPDATE Defense SET niveau=? WHERE loginJoueur=?, id=?";
	private static final String SQL_DEPLACER_DEFENSE="UPDATE Defense SET x=?, y=? WHERE loginJoueur=?, id=?";
	private static final String SQL_AJOUTER_DEFENSE="INSERT INTO Defense (niveau, loginJoueur, typeDefense, x,y ) VALUES (?,?,?,?,?)";
		
	private DAOFactory daoFactory;
	
	public DefenseDaoImpl(DAOFactory daof) {
		daoFactory=daof;
	}
	
	@Override
	public Defense trouverDefense(String login, int id) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Defense def = null;

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_DEFENSE_ID, false, login, id );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            if ( resultSet.next() ) {
	            	def= map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return def;
	}
	
	@Override
	public Vector<Canon> trouverCanon(String login) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Vector<Canon> def = new Vector<Canon>();

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_DEFENSE_ID, false, login, "Canon" );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            while(resultSet.next()){
	            	def.add((Canon) map( resultSet ));
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return def;
	}

	@Override
	public Vector<Mortier> trouverMortier(String login) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Vector<Mortier> def = new Vector<Mortier>();

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_DEFENSE_ID, false, login, "Mortier" );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            while(resultSet.next()){
	            	def.add((Mortier) map( resultSet ));
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return def;
	}

	@Override
	public void upgradeDefense(String login, int id) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	Defense d=trouverDefense( login, id);
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_DEPLACER_DEFENSE, false, d.getNiveau()+1, login,id);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec du deplacement de la defense, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }
	}

	@Override
	public void deplacerDefense(String login, int id, int x, int y) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_DEPLACER_DEFENSE, false, x,y, login,id);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec du deplacement de la defense, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }
	}

	@Override
	public void ajouterDefense(String login, Defense defense) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_AJOUTER_DEFENSE, true, defense.getNiveau(),login, defense.getTypeBatiment(), defense.getX(), defense.getY());
            int statut = preparedStatement.executeUpdate();
            //ON DONNE A LA DEFENSE SON ID GENERE DANS LA TABLE 
            defense.setId(preparedStatement.getGeneratedKeys().getInt("id"));
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'ajout de la defense, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }

	}

	Defense map(ResultSet res) throws SQLException{
		Defense d;
		if(res.getString("typeDefense")=="Canon")
			d=new Canon();
		else 
			d=new Mortier();
		d.setId(res.getInt("id"));
		int lvl=res.getInt("niveau");
		for(int i=0; i<lvl; i++)
			d.upgrade();
		d.setX(res.getInt("x"));
		d.setY(res.getInt("Y"));
		return d;
	}

}
