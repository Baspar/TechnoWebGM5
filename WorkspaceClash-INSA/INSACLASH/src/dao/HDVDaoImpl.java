package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import Model.HDV;
import Model.Joueur;
import Model.TypeRessource;

public class HDVDaoImpl implements HDVDao {
	//Différentes requêtes dont on a besoin
    private static final String SQL_SELECT_PAR_LOGIN = "SELECT * FROM HDV WHERE login = ?";
    private static final String SQL_UPDATE_HDV ="UPDATE HDV set niveau=? WHERE login= ?";
	private static final String SQL_DEPLACEMENT_HDV ="UPDATE HDV set x=?, y=? where login= ?";
    private static final String SQl_AJOUT_HDV="INSERT INTO HDV (niveau, loginJoueur, x,y, quantiteOr, quantiteCharbon) VALUES (?, ?, ?, ?)";

	
	DAOFactory daoFactory;
	
	HDVDaoImpl(DAOFactory dao){
		daoFactory=dao;
	}
	
	@Override
	public void creerHDV(HDV hdv, String login) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQl_AJOUT_HDV, false, hdv.getNiveau(), login, hdv.getX(), hdv.getY(), hdv.getQuantiteActuelle().get(TypeRessource.OR), hdv.getQuantiteActuelle().get(TypeRessource.CHARBON));
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'hdv, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public HDV trouverHDV(String login) {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet = null;
	     HDV hdv = null;

	     try {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         /*
	          * Préparation de la requête avec les objets passés en arguments
	          * (ici, uniquement une adresse email) et exécution.
	         */
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_LOGIN, false, login );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            if ( resultSet.next() ) {
	                hdv = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return hdv;
	}

	@Override
	public void upgrade(String login) {
		// TODO Auto-generated method stub
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	HDV hdv=trouverHDV(login);
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_UPDATE_HDV, false, login, hdv.getNiveau()+1);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'augmentation de niveau de l'hdv, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }
	}

	
	private HDV map(ResultSet res) throws SQLException{
		HDV hdv=new HDV();
		int lvl=res.getInt("niveau");
		for(int i=0; i<lvl;i++)
			hdv.upgrade();
		hdv.setNiveau(lvl);
		hdv.setX(res.getInt("x"));
		hdv.setY(res.getInt("Y"));
		Hashtable<TypeRessource, Integer> quantiteActuelle=new Hashtable<TypeRessource,Integer>();
		quantiteActuelle.put(TypeRessource.OR, res.getInt("quantiteOr"));
		quantiteActuelle.put(TypeRessource.CHARBON, res.getInt("quantiteCharbon"));
		hdv.setQuantiteActuelle(quantiteActuelle);
		return hdv;
	}

	@Override
	public void deplacer(String login, int x, int y) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
	    try{
	    	connexion =daoFactory.getConnection();
	    	HDV hdv=trouverHDV(login);
	    	preparedStatement=initialisationRequetePreparee(connexion, SQL_DEPLACEMENT_HDV, false, login, x,y);
	    	int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'augmentation de niveau de l'hdv, aucune ligne modifiée dans la table." );
            }
	    }catch(SQLException e){
	    	throw new DAOException(e);
	    } finally{
	    	fermeturesSilencieuses(preparedStatement, connexion);
	    }
		
	}
}
