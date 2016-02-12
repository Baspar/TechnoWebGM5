package Model;

public class MainVillage {
    public static void affichageVillage(Village v){
        System.out.println("Nom village : " +v.getNom() );
        System.out.println("#####BATIMENTS#####");
        for(TypeBatiment type: TypeBatiment.values()){
            System.out.println("  "+type+"["+v.getBatiments().getBatiments(type).size()+"]:");
            for(Batiment batiment:v.getBatiments().getBatiments(type)){
                System.out.println("    * lvl"+batiment.getNiveau()+"  x="+batiment.getX()+"   y="+batiment.getY());
            }
        }
        System.out.println("#####ARMEE#####");
        System.out.println("Taille armee "+v.getArmee().calculNbSoldat());
        System.out.println("Compo armee");
        for(int i=0;i<v.getArmee().getSoldats().size();i++)
            System.out.println("\t Type " +v.getArmee().getSoldats().get(i).getType()+"niveau"+v.getArmee().getSoldats().get(i).getNiveau());
        System.out.println("\n");
    }

    public static void main(String[] args){
        Village v=new Village("blaine");
        affichageVillage(v);
        v.ajouterBatiment(TypeBatiment.CANON, 0, 0);
        affichageVillage(v);
        System.out.println("ok");
        v.deplacerBatiment(TypeBatiment.CANON, 0, 2, 2);
        affichageVillage(v);
        v.ajouterSoldat(TypeSoldat.ARCHER);
        affichageVillage(v);
        for(int i=0;i<12;i++){
            v.ajouterSoldat(TypeSoldat.TREBUCHET);
            v.ajouterSoldat(TypeSoldat.ARCHER);
        }
        affichageVillage(v);
        for(int i=0;i<3;i++){
            v.ajouterBatiment(TypeBatiment.CANON, i, i);
            v.ajouterBatiment(TypeBatiment.MORTIER, i, i);
            v.ajouterBatiment(TypeBatiment.MINEOR, i, i);
            v.ajouterBatiment(TypeBatiment.MINECHARBON, 0, 0);
            v.ajouterBatiment(TypeBatiment.HDV, 0, 0);
            v.ajouterBatiment(TypeBatiment.CASERNE, 0, 0);
        }
        affichageVillage(v);
        v.upgradeBatiment(TypeBatiment.HDV, 0);
        for(int i=0;i<5;i++)
            v.upgradeBatiment(TypeBatiment.CANON, 0);
        affichageVillage(v);
        v.upgradeSoldat(TypeSoldat.ARCHER);
        v.upgradeSoldat(TypeSoldat.ARCHER);
        affichageVillage(v);
    }
}
