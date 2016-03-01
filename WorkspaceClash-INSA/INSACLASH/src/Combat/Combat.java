package Combat;

import java.util.Random;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map;
import java.util.Hashtable;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

import Model.TypeBatiment;
import Model.TypeRessource;
import Model.Ressource;
import Model.Armee;
import Model.Batiment;
import Model.Village;

public class Combat{
    private VillageCombat village;
    private ArmeeCombat armee;
    private Vector<Vector<Hashtable<Integer, EntiteCombat>>> terrain;
    private int tailleVillage;
    private int zoom=2;

    // Note terrain :
    // Si le Model.village a une taille de X, le terrain aura une taille de X*zoom, plus deux ligne et colonnes sur les bords permettant le placement des soldats
    // En clair, les batiments font 1x1 dans Model.Village, mais font Zoom*Zoom dans VillageCombat

    public Combat(Village tVillage, Armee tArmee){//DONE
        //Creation village et armee
        armee=new ArmeeCombat(tArmee);
        village=new VillageCombat(tVillage, zoom);

        //Recuperation taillevillage
        tailleVillage=tVillage.getTailleVillage()*zoom+2;

        //Creation terrain vierge
        terrain=new Vector<Vector<Hashtable<Integer, EntiteCombat>>>();
        for(int i=0; i<tailleVillage+2; i++){
            terrain.add(new Vector<Hashtable<Integer, EntiteCombat>>());
            for(int j=0; j<tailleVillage+2; j++)
                terrain.get(i).add(new Hashtable<Integer, EntiteCombat>());
        }

        //Placement batiments sur terrain
        for(BatimentCombat batiment:village.getBatiments()){
            for(int i=0; i<zoom; i++)
                for(int j=0; j<zoom; j++){
                    terrain.get(batiment.getX()*zoom+i+1).get(batiment.getY()*zoom+j+1).put(batiment.getId(), batiment);
                }
        }
    }
    private boolean estTermine(){//DONE
        boolean resteSoldats=(armee.getSoldats().size()>0);
        boolean resteBatiments=false;
        for(BatimentCombat batiment : village.getBatiments())
            if(!batiment.estMort() && !batiment.estATuer()){
                resteBatiments=true;
                break;
            }
        return (!resteBatiments || !resteSoldats);
    }
    private double distance(int x1, int y1, int x2, int y2){//DONE
        return Math.sqrt( Math.pow((y1-y2), 2) + Math.pow((y1-y2), 2));
    }
    private void deplacementSoldat(SoldatCombat soldat){//DONE
        Vector<Integer> result = soldat.ouAller(village.getBatiments(), tailleVillage);

        int id = soldat.getId();
        int newX = result.get(0);
        int newY = result.get(1);
        int oldX = soldat.getX();
        int oldY = soldat.getY();

        soldat.setX(newX);
        soldat.setY(newY);
        terrain.get(oldX).get(oldY).remove(id);
        terrain.get(newX).get(newY).put(id, soldat);
    }

    private void deplacementSoldat(SoldatCombat soldat, int newX, int newY){//DONE
        Vector<Integer> result = soldat.ouAller(village.getBatiments(), tailleVillage);

        int id = soldat.getId();
        int oldX = soldat.getX();
        int oldY = soldat.getY();

        soldat.setX(newX);
        soldat.setY(newY);
        if(oldX!=-1 && oldY!=-1)
            terrain.get(oldX).get(oldY).remove(id);
        terrain.get(newX).get(newY).put(id, soldat);
    }
    private void tourBatiment(){//DONE
        for(BatimentCombat batiment:village.getBatiments()){
            if(!batiment.estMort()){
                Vector<SoldatCombat> soldats = batiment.trouverSoldatAPortee(armee.getSoldats());
                if(soldats.size()!=0)
                    batiment.attaquer(soldats);
            }
        }
    }
    private void tourSoldat(){//DONE
        for(SoldatCombat soldat : armee.getSoldats()){
            System.out.print("Soldat n°"+soldat.getId()+", fais tu quelque chose ?");
            if(!soldat.estMort()){
                System.out.println("  Oui!");
                Vector<BatimentCombat> batiments = soldat.trouverBatimentAPortee(village.getBatiments(), zoom);
                if(batiments.size()>0){
                    System.out.println("  J'attaque!");
                    soldat.attaquer(batiments);
                } else {
                    System.out.println("  Je bouge!");
                    //deplacementSoldat(soldat);
                }
            } else
                System.out.println("  Non...");
        }
    }
    private void checkMorts(){//WIP
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).estATuer()){
                armee.getSoldats().get(i).tuer();
                int x = armee.getSoldats().get(i).getX();
                int y = armee.getSoldats().get(i).getY();
                int id = armee.getSoldats().get(i).getId();
                terrain.get(x).get(y).remove(id);
            }

        for(int i=0; i<village.getBatiments().size(); i++)
            if(village.getBatiments().get(i).estATuer()){
                village.getBatiments().get(i).tuer();
                int x = village.getBatiments().get(i).getX();
                int y = village.getBatiments().get(i).getY();
                int id = village.getBatiments().get(i).getId();
                terrain.get(x).get(y).remove(id);
            }
    }
    private Hashtable<TypeRessource, Integer> combattreTmp(){//DONE
        Hashtable<TypeRessource, Integer> out = new Hashtable<TypeRessource, Integer>();

        //Division par 2 de l'amree
        int nbSoldat=armee.getSoldats().size();
        for(int i=0; i<nbSoldat/2; i++)
            armee.getSoldats().remove(i);


        for(BatimentCombat batCom : village.getBatiments()){
            Batiment bat = batCom.getBatiment();
            if( bat.getTypeBatiment() == TypeBatiment.MINEOR
                || bat.getTypeBatiment() == TypeBatiment.MINECHARBON){

                TypeRessource typeRes = ((Ressource)bat).getTypeRessource();
                Integer prelevement = ((Ressource)bat).prelever();

                Integer dejaPresent = 0;

                prelevement=60;

                if(!out.contains(typeRes))
                    out.put(typeRes, 0);
                else
                    dejaPresent = out.get(typeRes);

                out.put(typeRes, dejaPresent+prelevement);
            }
        }
        return out;
    }
    public Hashtable<TypeRessource, Integer> combattre(){//DONE
        //placerSoldat(true, true, true, true);
        placerSoldat(false, false, true, true);
        afficherCombat();
        //while(!estTermine()){
            //tourSoldat();
            //tourBatiment();
            //checkMorts();
            //afficherCombat();
            //try{
                //Thread.sleep(4000);
            //} catch (Exception e){}
        //}
        return combattreTmp();
    }
    private void afficherCombat(){//DONE
        String out="";

        out +="+";
        for(int i=0; i<tailleVillage+2; i++)
            out += "---+";
        out += "\n";

        for(int x=0; x<tailleVillage+2; x++){
            out +="+";
            //
            // Soldat
            for(int y=0; y<tailleVillage+2; y++){
                int nbSol=0;
                for(SoldatCombat soldat : armee.getSoldats())
                    if (soldat.getX() == x && soldat.getY() == y)
                        nbSol++;
                if(nbSol != 0)
                    out +=" "+nbSol+" ";
                else{
                    // Batiment
                    Iterator<Map.Entry<Integer, EntiteCombat>> it = terrain.get(x).get(y).entrySet().iterator();
                    Integer id = -1;
                    if(it.hasNext()){
                        id = it.next().getKey();
                        if(id!=-1){
                            TypeBatiment typeBat = ((BatimentCombat)terrain.get(x).get(y).get(id)).getBatiment().getTypeBatiment();
                            out+=   typeBat==TypeBatiment.CASERNE?"CAS":
                                    typeBat==TypeBatiment.HDV?"HDV":
                                    typeBat==TypeBatiment.CANON?"CAN":
                                    typeBat==TypeBatiment.MORTIER?"MOR":
                                    typeBat==TypeBatiment.MINEOR?"MiO":
                                    typeBat==TypeBatiment.MINECHARBON?"MiC":
                                    "XXX";
                        } else
                            out += "   ";
                    } else
                        out += "   ";
                }
                out +="+";
            }
            out += "\n";
        }
        out +="+";
        for(int i=0; i<tailleVillage+2; i++)
            out += "---+";
        out += "\n";

        System.out.println(out);
    }
    private void placerSoldat(boolean N, boolean S, boolean E, boolean W){//WIP
        int nb=0;
        if(N)
            nb++;
        if(S)
            nb++;
        if(E)
            nb++;
        if(W)
            nb++;

        Random r = new Random();

        int id=0;
        if(N)
            for(int i=0; i<armee.getSoldats().size()/nb; i++){
                double rand=-1;
                while(rand<1 || rand>tailleVillage+1)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                System.out.println(pos);
                deplacementSoldat(armee.getSoldats().get(id), 0, pos);
                id++;
            }
        if(S)
            for(int i=0; i<armee.getSoldats().size()/nb; i++){
                double rand=-1;
                while(rand<1 || rand>tailleVillage+1)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                System.out.println(pos);
                deplacementSoldat(armee.getSoldats().get(id), tailleVillage+1, pos);
                id++;
            }
        if(E)
            for(int i=0; i<armee.getSoldats().size()/nb; i++){
                double rand=-1;
                while(rand<1 || rand>tailleVillage+1)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                System.out.println(pos);
                deplacementSoldat(armee.getSoldats().get(id), pos, tailleVillage+1);
                id++;
            }
        if(W)
            for(int i=0; i<armee.getSoldats().size()/nb; i++){
                double rand=-1;
                while(rand<1 || rand>tailleVillage+1)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                System.out.println(pos);
                deplacementSoldat(armee.getSoldats().get(id), pos, 0);
                id++;
            }
    }
}
