package Model;

public class MainVillage {
    public static void main(String[] args){
        Village v=new Village("blaine");

        //Ajout Batiments
        v.ajouterBatiment(TypeBatiment.CANON, 1, 0);
        for(int i=0;i<3;i++){
            v.ajouterBatiment(TypeBatiment.CANON, 3, i);
            v.ajouterBatiment(TypeBatiment.MORTIER, 4, i);
            v.ajouterBatiment(TypeBatiment.MINEOR, 5, i);
            v.ajouterBatiment(TypeBatiment.MINECHARBON, 6, i);
            v.ajouterBatiment(TypeBatiment.HDV, 7, i);
            v.ajouterBatiment(TypeBatiment.CASERNE, 8, i);
        }
        //Deplacement batiments
        v.deplacerBatiment(TypeBatiment.CANON, 0, 2, 2);
        v.deplacerBatiment(TypeBatiment.HDV, 0, 9, 9);
        v.deplacerBatiment(TypeBatiment.CASERNE, 0, 9, 8);
        //Upgrade Batiments
        v.upgradeBatiment(TypeBatiment.HDV, 0);
        for(int i=0;i<5;i++)
            v.upgradeBatiment(TypeBatiment.CANON, 0);

        //Ajout soldat
        v.ajouterSoldat(TypeSoldat.ARCHER);
        for(int i=0;i<12;i++){
            v.ajouterSoldat(TypeSoldat.TREBUCHET);
            v.ajouterSoldat(TypeSoldat.ARCHER);
        }
        v.upgradeSoldat(TypeSoldat.ARCHER);
        v.upgradeSoldat(TypeSoldat.ARCHER);
        System.out.println(v.affichageVillage());
    }
}
