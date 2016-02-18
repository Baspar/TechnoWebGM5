package dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import Model.MineCharbon;
import Model.MineOr;
import Model.Ressource;

public interface RessourceDao {
	Ressource trouverMine(String login, int id);
	Vector<MineOr> trouverMineOr(String login);
	Vector<MineCharbon> trouverMineCharbon(String login);
	void upgradeRessource(String login, int id);
	void viderRessource(String login, int id, Date g);
	void deplacerRessource(String login, int id, int x, int y);
	void ajouterRessource(String login, Ressource r);
}
