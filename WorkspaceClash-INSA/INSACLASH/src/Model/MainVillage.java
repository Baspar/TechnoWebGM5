package Model;

public class MainVillage {
    public static void main(String[] args){
        Village v=new Village("blaine");
        System.out.println(v.affichageVillage());
        v.ajouterBatiment(TypeBatiment.CANON, 0, 0);
        System.out.println(v.affichageVillage());
        System.out.println("ok");
        v.deplacerBatiment(TypeBatiment.CANON, 0, 2, 2);
        System.out.println(v.affichageVillage());
        v.ajouterSoldat(TypeSoldat.ARCHER);
        System.out.println(v.affichageVillage());
        for(int i=0;i<12;i++){
            v.ajouterSoldat(TypeSoldat.TREBUCHET);
            v.ajouterSoldat(TypeSoldat.ARCHER);
        }
        System.out.println(v.affichageVillage());
        for(int i=0;i<3;i++){
            v.ajouterBatiment(TypeBatiment.CANON, i, i);
            v.ajouterBatiment(TypeBatiment.MORTIER, i, i);
            v.ajouterBatiment(TypeBatiment.MINEOR, i, i);
            v.ajouterBatiment(TypeBatiment.MINECHARBON, 0, 0);
            v.ajouterBatiment(TypeBatiment.HDV, 0, 0);
            v.ajouterBatiment(TypeBatiment.CASERNE, 0, 0);
        }
        System.out.println(v.affichageVillage());
        v.upgradeBatiment(TypeBatiment.HDV, 0);
        for(int i=0;i<5;i++)
            v.upgradeBatiment(TypeBatiment.CANON, 0);
        System.out.println(v.affichageVillage());
        v.upgradeSoldat(TypeSoldat.ARCHER);
        v.upgradeSoldat(TypeSoldat.ARCHER);
        System.out.println(v.affichageVillage());
    }
}
