package dao;

import java.util.GregorianCalendar;
import java.util.Vector;

import Model.Soldat;


public interface SoldatDao {
	Vector<Soldat> trouverSoldat(String login);
	void upgradeSoldat(String login, String type);
	void supprimerSoldat(String login, int id);
	void ajouterSoldat(String login, int id, String type);
}
