package Model;

public class Joueur{
    private String login;
    private String motDePasse;
    private Village village;

    public Joueur(String login, String motDePasse, Village village) {
        super();
        this.login = login;
        this.motDePasse=motDePasse;
        this.village = village;
    }

    public Joueur(String login, String motDePasse) {
        super();
        this.login = login;
        this.motDePasse=motDePasse;
        this.village = new Village();
    }

    public Joueur() {
        super();
        this.login="";
        this.motDePasse="";
        this.village=new Village();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }


}
