package dao;

import java.util.Hashtable;

import Model.Batiment;
import Model.Batiments;
import Model.Defense;
import Model.Ressource;
import Model.TypeBatiment;
import Model.Village;

public class VillageDaoImpl implements VillageDao {
	
	private DAOFactory daoFactory;

	public VillageDaoImpl(DAOFactory daof) {
		this.daoFactory=daof;
	}
	
	
	@Override
	public void creerVillage(Village village, String login) { /////////////ATESTER///////////////
		HDVDaoImpl hdv=new HDVDaoImpl(daoFactory);
		hdv.creerHDV(village.getHDV(), login);
		CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
		cas.creerCaserne(village.getCaserne(), login);
		DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
		Batiments b=village.getBatiments();
		for(int i=0; i<b.getBatiments(TypeBatiment.CANON).size();i++)
			def.ajouterDefense(login, (Defense) b.getBatiments(TypeBatiment.CANON).get(i));
		for(int i=0; i<b.getBatiments(TypeBatiment.MORTIER).size();i++)
			def.ajouterDefense(login, (Defense) b.getBatiments(TypeBatiment.MORTIER).get(i));
		RessourceDaoImpl res=new RessourceDaoImpl(daoFactory);
		for(int i=0; i<b.getBatiments(TypeBatiment.MINEOR).size();i++)
			res.ajouterRessource(login, (Ressource) b.getBatiments(TypeBatiment.MINEOR).get(i));
		for(int i=0; i<b.getBatiments(TypeBatiment.MINECHARBON).size();i++)
			res.ajouterRessource(login, (Ressource) b.getBatiments(TypeBatiment.MINECHARBON).get(i));
	}

	@Override
	public Village chargerVillage(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
