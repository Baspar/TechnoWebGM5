abstract class Soldat{
    private String ressourceNecessaire;
    private int pointsDeVie;
    private int puissanceAttaque;
    private TypeAttaque typeAttaque;

    abstract void update();
    abstract int calculerCoutUpgrade();
}
