package test;

import Model.Caserne;
import Model.TypeRessource;
import Model.TypeSoldat;
import dao.CaserneDaoImpl;
import dao.DAOFactory;

public class testCaserneDao{
	
	public static void main(String[] args) {

		DAOFactory fac= DAOFactory.getInstance();
		CaserneDaoImpl caserne= (CaserneDaoImpl) fac.getCaserneDao();
		Caserne casernetest=new Caserne();
		//Fonction mais faire attention a ce que l'id soit bien dans la table joueur et 
		// qu'il n'y a pas deja un caserne pour ce joueur
		//caserne.creerCaserne(casernetest, "test");
		System.out.println("Fonction trouver");
		System.out.println("caserne existant:");
		casernetest=caserne.trouverCaserne("test");
	//	System.err.println(casernetest.getArmee().getSoldats().size());
		System.out.println("niveau "+casernetest.getNiveau());
		System.out.println("x "+casernetest.getX());
		System.out.println("y "+casernetest.getY());
		System.out.println("nombreArcher "+casernetest.getNombreArcher());
		System.out.println("caserne non existant");
		System.out.println(caserne.trouverCaserne("test2"));
		System.out.println("Fonction améliorer");
		caserne.upgrade("test");
		casernetest=caserne.trouverCaserne("test");
		System.out.println("niveau "+casernetest.getNiveau());
		System.out.println("x "+casernetest.getX());
		System.out.println("y "+casernetest.getY());
		System.out.println("nombreArcher "+casernetest.getNombreArcher());
		System.out.println("Fonction deplacer");
		caserne.deplacer("test", 2, 2);
		casernetest=caserne.trouverCaserne("test");
		System.out.println("niveau "+casernetest.getNiveau());
		System.out.println("x "+casernetest.getX());
		System.out.println("y "+casernetest.getY());
		System.out.println("nombreArcher "+casernetest.getNombreArcher());
		System.out.println("nombreTrebuchet "+casernetest.getNombreTrebuchet());
		System.out.println("niveauArcher "+casernetest.getNiveauActuel().get(TypeSoldat.ARCHER));
		System.out.println("niveauTrebuchet "+casernetest.getNiveauActuel().get(TypeSoldat.TREBUCHET));
		System.out.println("changement du niveau des archers et ajout de 10 trebuchet");
		caserne.ameliorerSoldat("test", TypeSoldat.ARCHER);
		caserne.modifNombreSoldat("test", 10, TypeSoldat.TREBUCHET);
		casernetest=caserne.trouverCaserne("test");
		System.out.println("niveau "+casernetest.getNiveau());
		System.out.println("x "+casernetest.getX());
		System.out.println("y "+casernetest.getY());
		System.out.println("nombreArcher "+casernetest.getNombreArcher());
		System.out.println("nombreTrebuchet "+casernetest.getNombreTrebuchet());
		System.out.println("niveauArcher "+casernetest.getNiveauActuel().get(TypeSoldat.ARCHER));
		System.out.println("niveauTrebuchet "+casernetest.getNiveauActuel().get(TypeSoldat.TREBUCHET));
		System.out.println("suppression de 2 trebuchet");
		caserne.modifNombreSoldat("test", -2, TypeSoldat.TREBUCHET);
		casernetest=caserne.trouverCaserne("test");
		System.out.println("nombreTrebuchet "+casernetest.getNombreTrebuchet());
	}
}
