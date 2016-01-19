abstract class Soldat{
    protected String ressourceNecessaire;
    protected int pointsDeVie;
    protected int puissanceAttaque;
    protected TypeAttaque typeAttaque;

    abstract void update();
    abstract int calculerCoutUpgrade();
}
