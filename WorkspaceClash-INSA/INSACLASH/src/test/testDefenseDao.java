package test;


import Model.Canon;
import Model.Defense;
import Model.Mortier;
import dao.DAOFactory;
import dao.DefenseDaoImpl;

public class testDefenseDao {

	public static void main(String[] args) {

		DAOFactory fac= DAOFactory.getInstance();
		DefenseDaoImpl def= (DefenseDaoImpl) fac.getDefenseDao();
		//Fonction mais faire attention a ce que l'id soit bien dans la table joueur et 
		// qu'il n'y a pas deja un caserne pour ce joueur
		//caserne.creerCaserne(casernetest, "test");
		System.out.println("Affichage de la taille des vecteurs");
		System.out.println(def.trouverCanon("test").size());
		System.out.println(def.trouverMortier("test").size());
		System.out.println("ajout d'un canon et d'un mortier");
		def.ajouterDefense("test", new Canon());
		def.ajouterDefense("test", new Mortier());
		System.out.println(def.trouverCanon("test").size());
		System.out.println(def.trouverCanon("test").size());
		System.out.println("On essaie de trouver la defense avec l'id 1");
		Defense d=def.trouverDefense("test", 1);
		System.out.println(d.getId());
		System.out.println(d.getNiveau());
		System.out.println(d.getX());
		System.out.println(d.getY());
		System.out.println("On améliore son niveau et on le deplace");
		def.deplacerDefense("test", 1, 2, 2);
		def.upgradeDefense("test", 1);
		d=def.trouverDefense("test", 1);
		System.out.println(d.getId());
		System.out.println(d.getNiveau());
		System.out.println(d.getX());
		System.out.println(d.getY());
	}
}
