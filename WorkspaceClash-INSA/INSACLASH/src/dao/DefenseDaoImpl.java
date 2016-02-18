package dao;

import java.util.Vector;

import Model.Canon;
import Model.Defense;
import Model.Mortier;

public class DefenseDaoImpl implements DefenseDao {
	private static final String SQL_FIND_DEFENSE_PAR_TYPE="SELECT * FROM Defense where loginJoueur= ?, typeDefense=?";
	private static final String SQL_UPGRADE_DEFENSE="UPDATE Defense SET niveau=? WHERE loginJoueur=?, id=?";
	private static final String SQL_DEPLACER_DEFENSE="UPDATE Defense SET x=?, y=? WHERE loginJoueur=?, id=?";
	private static final String SQL_AJOUTER_DEFENSE="INSERT INTO Defense (niveau, loginJoueur, typeDefense, x,y ) VALUES (?,?,?,?,?)";
		
	private DAOFactory daoFactory;
	
	public DefenseDaoImpl(DAOFactory daof) {
		daoFactory=daof;
	}
	
	@Override
	public Vector<Canon> trouverCanon(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Mortier> trouverMortier(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upgradeDefense(String login, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deplacerDefense(String login, int id, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterDefense(String login, Defense defense) {
		// TODO Auto-generated method stub

	}

}
