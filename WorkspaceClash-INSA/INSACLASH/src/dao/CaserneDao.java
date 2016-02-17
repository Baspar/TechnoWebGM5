package dao;

import Model.Caserne;

public interface CaserneDao {
	void creerCaserne(Caserne caserne, String login);
	Caserne trouverCaserne(String login);
	void upgrade(String login);
	void deplacer(String login, int x, int y);
}
