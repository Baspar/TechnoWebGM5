package test;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Model.MineCharbon;
import Model.MineOr;
import Model.Ressource;
import dao.DAOFactory;
import dao.RessourceDaoImpl;


public class testRessourceDao {
	public static void main(String[] args) {

		DAOFactory fac= DAOFactory.getInstance();
		RessourceDaoImpl res= (RessourceDaoImpl) fac.getRessourceDao();
		//Fonction mais faire attention a ce que l'id soit bien dans la table joueur et 
		// qu'il n'y a pas deja un caserne pour ce joueur
		//caserne.creerCaserne(casernetest, "test");
		System.out.println("Affichage de la taille des vecteurs");
		System.out.println(res.trouverMineCharbon("test").size());
		System.out.println(res.trouverMineOr("test").size());
		System.out.println("ajout d'un canon et d'un mortier");
		res.ajouterRessource("test", new MineOr());
		res.ajouterRessource("test", new MineCharbon());
		System.out.println(res.trouverMineOr("test").size());
		System.out.println(res.trouverMineCharbon("test").size());
		System.out.println("On essaie de trouver la defense avec l'id 1");
		Ressource r=res.trouverMine("test", 1);
		System.out.println(r.getId());
		System.out.println(r.getNiveau());
		System.out.println(r.getX());
		System.out.println(r.getY());
		System.out.println(r.getDateDerniereLevee());
		System.out.println(r.getDateDerniereLevee().getTime());
		System.out.println("On améliore son niveau, on change la date de derniere levee et on le deplace");
		Scanner sc=new Scanner(System.in);
		sc.nextInt();
		res.deplacerRessource("test", 1, 2, 2);
		res.upgradeRessource("test", 1);
		res.viderRessource("test", 1, new Date());
		r=res.trouverMine("test", 1);
		System.out.println(r.getId());
		System.out.println(r.getNiveau());
		System.out.println(r.getX());
		System.out.println(r.getY());
		System.out.println(r.getDateDerniereLevee());
		System.out.println(r.getDateDerniereLevee().getTime());
	}
}
