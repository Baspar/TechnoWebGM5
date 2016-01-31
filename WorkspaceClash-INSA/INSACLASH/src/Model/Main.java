package Model;

import java.util.*;
public class Main{
    public static void main (String [] arg){
        MineOr mine=new MineOr();
        System.out.println(mine.calculProduction());
        System.out.println(mine.getTauxParHeure());
        Scanner sc=new Scanner(System.in);
        int nb=sc.nextInt();
        for(int i=0; i<nb;i++)
            mine.upgrade();
        System.out.println(mine.getTauxParHeure());
        System.out.println(mine.calculProduction());
    }
}
