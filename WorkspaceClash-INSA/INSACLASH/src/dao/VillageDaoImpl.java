package dao;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import Model.Batiment;
import Model.Batiments;
import Model.Canon;
import Model.Caserne;
import Model.Defense;
import Model.MineCharbon;
import Model.MineOr;
import Model.Mortier;
import Model.Ressource;
import Model.TypeBatiment;
import Model.TypeRessource;
import Model.TypeSoldat;
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
		Vector<Batiment> bati2=new Vector<Batiment>();
		//On recupere la caserne
		CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
		Caserne c =cas.trouverCaserne(login);
		v.setArmee(c.getArmee());
		bati2.add(c);
		h.put(TypeBatiment.CASERNE, bati2);
		//ON recupere les defenses
		DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
		Vector<Batiment> bati3=new Vector<Batiment>();
		//Les mortiers
		Vector<Mortier> d= def.trouverMortier(login);
		for(int i=0; i<d.size(); i++)
			bati3.add(d.get(i));
		h.put(TypeBatiment.MORTIER, bati3 );
		Vector<Batiment> bati4=new Vector<Batiment>();
		//Les canons
		Vector<Canon> d2= def.trouverCanon(login);
		for(int i=0; i<d2.size(); i++)
			bati4.add(d2.get(i));
		h.put(TypeBatiment.CANON, bati4 );
		//On recupere les ressources
		RessourceDaoImpl r=new RessourceDaoImpl(daoFactory);
		Vector<Batiment> bati5=new Vector<Batiment>();
		//MineCharbon
		Vector<MineCharbon> res= r.trouverMineCharbon(login);
		for(int i=0; i<res.size(); i++)
			bati5.add(res.get(i));
		h.put(TypeBatiment.MINECHARBON, bati5 );
		Vector<Batiment> bati6=new Vector<Batiment>();
		//MineOr
		Vector<MineOr> res2= r.trouverMineOr(login);
		//System.out.println(res2.size());
		for(int i=0; i<res2.size(); i++)
			bati6.add(res2.get(i));
		//System.out.println(bati6.size());
		h.put(TypeBatiment.MINEOR, bati6 );
		//System.out.println(h);
		b.setBatiments(h);
		v.setBatiments(b);
	//	System.out.println(v.getBatiments().getBatiments(TypeBatiment.MINEOR));
		return v;
	}


	@Override
	public void deplacerBatiment(String login, Batiment b, int x, int y) {
		if(b.getTypeBatiment()==TypeBatiment.CASERNE){
			CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
			cas.deplacer(login, x, y);
		}
		if(b.getTypeBatiment()==TypeBatiment.HDV){
			HDVDaoImpl hdv=new HDVDaoImpl(daoFactory);
			hdv.deplacer(login, x, y);
		}
		if(b.getTypeBatiment()==TypeBatiment.CASERNE || b.getTypeBatiment()==TypeBatiment.MORTIER){
			DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
			def.deplacerDefense(login, b.getId(), x, y);
		}
		if(b.getTypeBatiment()==TypeBatiment.MINECHARBON || b.getTypeBatiment()==TypeBatiment.MINEOR){
			RessourceDaoImpl res=new RessourceDaoImpl(daoFactory);
			res.deplacerRessource(login, b.getId(), x, y);
		}
	}



	@Override
	public void ameliorerBatiment(String login, Batiment b) {
		if(b.getTypeBatiment()==TypeBatiment.CASERNE){
			CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
			cas.upgrade(login);
		}
		if(b.getTypeBatiment()==TypeBatiment.HDV){
			HDVDaoImpl hdv=new HDVDaoImpl(daoFactory);
			hdv.upgrade(login);
		}
		if(b.getTypeBatiment()==TypeBatiment.CANON|| b.getTypeBatiment()==TypeBatiment.MORTIER){
			DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
			def.upgradeDefense(login, b.getId());
		}
		if(b.getTypeBatiment()==TypeBatiment.MINECHARBON || b.getTypeBatiment()==TypeBatiment.MINEOR){
			RessourceDaoImpl res=new RessourceDaoImpl(daoFactory);
			res.upgradeRessource(login, b.getId());
		}
	}


	@Override
	public void ajouterBatiment(String login, Batiment b) {
		if(b.getTypeBatiment()==TypeBatiment.CANON || b.getTypeBatiment()==TypeBatiment.MORTIER){
			DefenseDaoImpl def=new DefenseDaoImpl(daoFactory);
			def.ajouterDefense(login, (Defense) b);
		}
		if(b.getTypeBatiment()==TypeBatiment.MINECHARBON || b.getTypeBatiment()==TypeBatiment.MINEOR){
			RessourceDaoImpl res=new RessourceDaoImpl(daoFactory);
			res.ajouterRessource(login, (Ressource) b);
			}
	}


	@Override
	public void viderRessource(String login, int id, Date d) {
		RessourceDaoImpl res=new RessourceDaoImpl(daoFactory);
		res.viderRessource(login, id, d);
	}


	@Override
	public void miseAJourRessource(String login, TypeRessource type, int qte) {
		HDVDaoImpl hdv=new HDVDaoImpl(daoFactory);
		if(type==TypeRessource.CHARBON)
			hdv.miseAJourCharbon(login, qte);
		else
			hdv.miseAJourOr(login, qte);
	}


	@Override
	public void modifNbSoldat(String login, TypeSoldat t, int qte) {
		CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
		cas.modifNombreSoldat(login, qte, t);
	}


	@Override
	public void ameliorerSoldat(String login, TypeSoldat t) {
		CaserneDaoImpl cas=new CaserneDaoImpl(daoFactory);
		cas.ameliorerSoldat(login, t);
	}
	
}
