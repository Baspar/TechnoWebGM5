package dao;

import Model.HDV;

public interface HDVDao {
	void creerHDV(HDV hdv, String login);
	HDV trouverHDV(String login);
	void upgrade(String login);
	void deplacer(String login, int x, int y);
	void miseAJourOr(String login, int qte);
	void miseAJourCharbon(String login, int qte);
}
