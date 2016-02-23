package dao;

import java.util.Date;

import Model.Batiment;
import Model.TypeRessource;
import Model.TypeSoldat;
import Model.Village;

public interface VillageDao {
	void creerVillage(Village village, String login);
	Village chargerVillage(String login);
	void deplacerBatiment(String login, Batiment b, int x, int y);
	void ameliorerBatiment(String login, Batiment b);
	void ajouterBatiment(String login, Batiment b);
	void viderRessource(String login, int id,Date d);
	void miseAJourRessource(String login, TypeRessource type, int qte);
	void modifNbSoldat(String login, TypeSoldat t, int qte);
	void ameliorerSoldat(String login, TypeSoldat t);
}
