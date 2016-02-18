package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import Model.MineCharbon;
import Model.MineOr;
import Model.Ressource;
import Model.TypeRessource;

public class RessourceDaoImpl implements RessourceDao {
	private static final String SQL_FIND_RESSOURCE_ID="SELECT YEAR(derniereLevee), MONTH(derniereLevee), DAY(derniereLevee), HOUR(derniereLevee), MINUTE(derniereLevee), SECOND(derniereLevee), typeRessource, id, niveau, x, y FROM Ressource WHERE loginJoueur=? AND id=?";
	private static final String SQL_FIND_RESSOURCE_PAR_TYPE="SELECT YEAR(derniereLevee), MONTH(derniereLevee), DAY(derniereLevee), HOUR(derniereLevee), MINUTE(derniereLevee), SECOND(derniereLevee), typeRessource, id, niveau, x, y   FROM Ressource WHERE loginJoueur= ? AND typeRessource= ?";
	private static final String SQL_UPGRADE_RESSOURCE="UPDATE Ressource SET niveau=? WHERE loginJoueur=? AND id=?";
	private static final String SQL_DEPLACER_RESSOURCE="UPDATE Ressource SET x=?, y=? WHERE loginJoueur=? AND id=?";
	private static final String SQL_UPDATE_DATE_LEVEE="UPDATE Ressource SET derniereLevee=? WHERE loginJoueur=? AND id=?";
	private static final String SQL_AJOUTER_RESSOURCE="INSERT INTO Ressource (niveau, loginJoueur, typeRessource, derniereLevee, x,y ) VALUES (?,?,?,?,?,?)";
	
	
	private DAOFactory daoFactory;
	
	public RessourceDaoImpl(DAOFactory daof) {
		daoFactory=daof;
	}

	@Override
	public Ressource trouverMine(String login, int id) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Ressource res = null;

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_RESSOURCE_ID, false, login, id );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            if ( resultSet.next() ) {
	            	res= map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }
	     
	        return res;
	}
	
	@Override
	public Vector<MineOr> trouverMineOr(String login) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Vector<MineOr> mine = new Vector<MineOr>();

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_RESSOURCE_PAR_TYPE, false, login, "MineOr" );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            while(resultSet.next()){
	            	mine.add((MineOr) map( resultSet ));
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return mine;
	}

	@Override
	public Vector<MineCharbon> trouverMineCharbon(String login) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     Vector<MineCharbon> mine = new Vector<MineCharbon>();

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_RESSOURCE_PAR_TYPE, false, login, "MineCharbon" );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            while(resultSet.next()){
	            	mine.add((MineCharbon) map( resultSet ));
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return mine;
	}

	@Override
	public void upgradeRessource(String login, int id) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	Ressource d=trouverMine( login, id);
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_UPGRADE_RESSOURCE, false, d.getNiveau()+1, login,id);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'amélioration de la mine, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }

	}

	@Override
	public void viderRessource(String login, int id, Date g) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_UPDATE_DATE_LEVEE, false, g, login,id);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la mise a Jour de la derniere leve de la mine, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }

	}

	@Override
	public void deplacerRessource(String login, int id, int x, int y) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_DEPLACER_RESSOURCE, false, x,y, login,id);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec du deplacement de la ressource, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }

	}

	@Override
	public void ajouterRessource(String login, Ressource r) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            if (r.getTypeRessource()==TypeRessource.OR)
            	preparedStatement = initialisationRequetePreparee( connexion, SQL_AJOUTER_RESSOURCE, true, r.getNiveau(),login, "MineOr", r.getDateDerniereLevee(), r.getX(), r.getY());
            else
            	preparedStatement = initialisationRequetePreparee( connexion, SQL_AJOUTER_RESSOURCE, true, r.getNiveau(),login, "MineCharbon", r.getDateDerniereLevee(), r.getX(), r.getY());
            int statut = preparedStatement.executeUpdate();
            //ON DONNE A LA DEFENSE SON ID GENERE DANS LA TABLE 
            ResultSet result=preparedStatement.getGeneratedKeys();
            if(result.next())
            	r.setId(result.getInt(1));
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'ajout de la mine, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }

	}

	
	private Ressource map(ResultSet res) throws SQLException{
		Ressource r;
		if(res.getString("typeRessource").equals("MineCharbon"))
			r=new MineCharbon();
		else
			r=new MineOr();
		r.setId(res.getInt("id"));
		int lvl=res.getInt("niveau");
		for(int i=0;i<lvl; i++)
			r.upgrade();
		//Date d=new Date(res.getInt("YEAR(derniereLevee)"), res.getInt("MONTH(derniereLevee)"), res.getInt("DAY(derniereLevee)"), res.getInt("HOUR(derniereLevee)"), res.getInt("MINUTE(derniereLevee)"),res.getInt("SECOND(derniereLevee)") );
		Date d=new Date(res.getInt(1),res.getInt(2)-1,res.getInt(3),res.getInt(4),res.getInt(5),res.getInt(6));
		//System.err.println(res.getInt(3));
		r.setDateDerniereLevee(d);
		r.setX(res.getInt("x"));
		r.setY(res.getInt("y"));
		return r;
	}

}
