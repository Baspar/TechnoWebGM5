package dao;

import java.util.Hashtable;
import java.util.Vector;

import Model.Batiment;
import Model.Batiments;
import Model.Canon;
import Model.Caserne;
import Model.Defense;
import Model.HDV;
import Model.MineCharbon;
import Model.MineOr;
import Model.Mortier;
import Model.Ressource;
import Model.TypeBatiment;
import Model.Village;

public class VillageDaoImpl implements VillageDao {
	
	private DAOFactory daoFactory;

	public VillageDaoImpl(DAOFactory daof) {
		this.daoFactory=daof;
	}
	
	
	@Override
	public void creerVillage(Village village, String login) { 
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
		Village v= new Village();
		Batiments b=new Batiments();
		Hashtable<TypeBatiment, Vector<Batiment>> h=new Hashtable<TypeBatiment, Vector<Batiment>>();
		//On recupere l'hdv
		HDVDaoImpl hdv=new HDVDaoImpl(daoFactory);
		Vector<Batiment> bati=new Vector<Batiment>();
		bati.add(hdv.trouverHDV(login));
		h.put(TypeBatiment.HDV, bati);
		bati.clear();
		//On recupere la caserne
		CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
		Caserne c =cas.trouverCaserne(login);
		v.setArmee(c.getArmee());
		bati.add(c);
		h.put(TypeBatiment.CASERNE, bati);
		//ON recupere les defenses
		DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
		bati.clear();
		//Les mortiers
		Vector<Mortier> d= def.trouverMortier(login);
		for(int i=0; i<d.size(); i++)
			bati.add(d.get(i));
		h.put(TypeBatiment.MORTIER, bati );
		bati.clear();
		//Les canons
		Vector<Canon> d2= def.trouverCanon(login);
		for(int i=0; i<d.size(); i++)
			bati.add(d.get(i));
		h.put(TypeBatiment.CANON, bati );
		//On recupere les ressources
		RessourceDaoImpl r=new RessourceDaoImpl(daoFactory);
		bati.clear();
		//MineCharbon
		Vector<MineCharbon> res= r.trouverMineCharbon(login);
		for(int i=0; i<d.size(); i++)
			bati.add(res.get(i));
		h.put(TypeBatiment.MINECHARBON, bati );
		bati.clear();
		//MineOr
		Vector<MineOr> res2= r.trouverMineOr(login);
		for(int i=0; i<d.size(); i++)
			bati.add(res2.get(i));
		h.put(TypeBatiment.MINEOR, bati );
		v.setBatiments(b);
		return v;
	}


	@Override
	public void deplacerBatiment(String login, Batiment b) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ameliorerBatiment(String login, Batiment b) {
		// TODO Auto-generated method stub
		
	}

}
