package test;

import java.util.Hashtable;
import java.util.Map.Entry;

import Combat.Combat;

import Model.TypeRessource;
import Model.Armee;
import Model.TypeBatiment;
import Model.TypeSoldat;
import Model.Village;

public class testCombat {


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

        Armee armee =new Armee();
        for(int i=0; i<50; i++)
        	armee.ajouterSoldat(TypeSoldat.ARCHER, 2);
        armee.ajouterSoldat(TypeSoldat.TREBUCHET, 3);

        Combat combat=new Combat(v, armee);
        Hashtable<TypeRessource, Integer> result =  combat.combattre();

        if(result.size() != 0){
            System.out.println("Vous avez gagne! Vos gains sont:");
            for(Entry<TypeRessource,Integer> ent:result.entrySet())
                System.out.println("  "+ent.getValue()+" "+ent.getKey());
        }
    }
}
