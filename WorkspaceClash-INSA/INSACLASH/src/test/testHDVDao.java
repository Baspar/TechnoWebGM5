package test;

import Model.HDV;
import Model.TypeRessource;
import dao.DAOFactory;
import dao.HDVDaoImpl;

public class testHDVDao {
	
	public static void main(String[] args) {
		DAOFactory fac= DAOFactory.getInstance();
		HDVDaoImpl hdv= (HDVDaoImpl) fac.getHDVDao();
		HDV hdvtest=new HDV();
		//Fonction mais faire attention a ce que l'id soit bien dans la table joueur et 
		// qu'il n'y a pas deja un hdv pour ce joueur
		//hdv.creerHDV(hdvtest, "test");
		System.out.println("Fonction trouver");
		System.out.println("hdv existant:");
		hdvtest=hdv.trouverHDV("test");
		System.out.println("niveau "+hdvtest.getNiveau());
		System.out.println("x "+hdvtest.getX());
		System.out.println("y "+hdvtest.getY());
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.OR));
		System.out.println("hdv non existant");
		System.out.println(hdv.trouverHDV("test2"));
		System.out.println("Fonction améliorer");
		hdv.upgrade("test");
		hdvtest=hdv.trouverHDV("test");
		System.out.println("niveau "+hdvtest.getNiveau());
		System.out.println("x "+hdvtest.getX());
		System.out.println("y "+hdvtest.getY());
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.OR));
		System.out.println("Fonction deplacer");
		hdv.deplacer("test", 2, 2);
		hdvtest=hdv.trouverHDV("test");
		System.out.println("niveau "+hdvtest.getNiveau());
		System.out.println("x "+hdvtest.getX());
		System.out.println("y "+hdvtest.getY());
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.OR));
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.CHARBON));
		hdv.miseAJourOr("test", 100);
		hdv.miseAJourCharbon("test", 100);
		hdvtest=hdv.trouverHDV("test");
		System.out.println("niveau "+hdvtest.getNiveau());
		System.out.println("x "+hdvtest.getX());
		System.out.println("y "+hdvtest.getY());
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.OR));
		System.out.println("or "+hdvtest.getQuantiteActuelle().get(TypeRessource.CHARBON));
	}

}
