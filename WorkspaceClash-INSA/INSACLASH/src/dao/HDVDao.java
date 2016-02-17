package dao;

import Model.HDV;

public interface HDVDao {
	void creerHDV(HDV hdv, String login);
	HDV trouverHDV(String login);
	void upgrade(String login);
	void deplacer(String login, int x, int y);
}
