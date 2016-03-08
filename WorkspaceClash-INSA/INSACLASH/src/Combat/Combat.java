package Combat;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map;
import java.util.Hashtable;

import Model.TypeBatiment;
import Model.TypeRessource;
import Model.Armee;
import Model.Village;

public class Combat{
    private VillageCombat village;
    private ArmeeCombat armee;
    private Vector<Vector<Hashtable<Integer, EntiteCombat>>> terrain;
    private int tailleVillage;
    private int zoom=2;
    private Hashtable<TypeRessource, Integer> gains;
    private boolean hasMove=false;

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

        gains = new Hashtable<TypeRessource, Integer>();
        gains.put(TypeRessource.CHARBON, 0);
        gains.put(TypeRessource.OR, 0);
    }
    private boolean estTermine(){//DONE
        boolean resteSoldats=false;
        for(SoldatCombat soldat : armee.getSoldats())
            if(soldat.getX()!=-1 && !soldat.estMort() && !soldat.estATuer()){
                resteSoldats=true;
                break;
            }

        boolean resteBatiments=false;
        for(BatimentCombat batiment : village.getBatiments())
            if(!batiment.estMort() && !batiment.estATuer()){
                resteBatiments=true;
                break;
            }
        return (!resteBatiments || !resteSoldats);
    }
    private void deplacementSoldat(SoldatCombat soldat){//DONE
    Vector<Integer> result = soldat.ouAller(village.getBatiments(), tailleVillage, zoom);

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
            if(soldat.getX()!=-1 && soldat.getY()!=-1 && !soldat.estMort()){
                Vector<BatimentCombat> batiments = soldat.trouverBatimentAPortee(village.getBatiments(), zoom);
                if(batiments.size()>0)
                    soldat.attaquer(batiments);
                else{
                    hasMove=true;
                    deplacementSoldat(soldat);
                }
            }
        }
    }
    private void checkMorts(){//DONE
        for(int i=0; i<armee.getSoldats().size(); i++)
            if(armee.getSoldats().get(i).estATuer()){
                armee.getSoldats().get(i).tuer();
                int x = armee.getSoldats().get(i).getX();
                int y = armee.getSoldats().get(i).getY();
                int id = armee.getSoldats().get(i).getId();
                armee.getSoldats().remove(i);
                terrain.get(x).get(y).remove(id);
                hasMove=true;
            }

        for(int i=0; i<village.getBatiments().size(); i++)
            if(village.getBatiments().get(i).estATuer()){
                village.getBatiments().get(i).tuer();
                int x = village.getBatiments().get(i).getX()*zoom+1;
                int y = village.getBatiments().get(i).getY()*zoom+1;
                int id = village.getBatiments().get(i).getId();
                for(int dx=0; dx<zoom; dx++)
                    for(int dy=0; dy<zoom; dy++)
                        terrain.get(x+dx).get(y+dy).remove(id);
                hasMove=true;


                Hashtable<TypeRessource, Integer> ressource = village.getBatiments().get(i).getBatiment().perteBatiment();
                Iterator<Map.Entry<TypeRessource, Integer>> it = ressource.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<TypeRessource, Integer> ent = it.next();
                    int qtite = ent.getValue();
                    qtite += gains.get(ent.getKey());

                    gains.put(ent.getKey(), qtite);
                }
            }
    }
    public Hashtable<TypeRessource, Integer> combattre(){//DONE
        int nbTour=0;
        while(!estTermine() & nbTour<120){
            System.err.println("Tour num "+nbTour);
            hasMove=false;
            tourSoldat();
            tourBatiment();
            checkMorts();

            if(hasMove){
                String aff = afficherCombat();
                try {
                    PrintWriter writer = new PrintWriter("/tmp/ASCIItour"+nbTour+".txt", "UTF-8");
                    writer.println(aff);
                    writer.close();
                } catch (Exception e) {
                    System.err.println("Problem writing to the file statsTest.txt");
                }
                aff = afficherCombat();
                try {
                    PrintWriter writer = new PrintWriter("/tmp/tour"+nbTour+".txt", "UTF-8");
                    writer.println(aff);
                    writer.close();
                } catch (Exception e) {
                    System.err.println("Problem writing to the file statsTest.txt");
                }
                nbTour++;
            }
        }
        System.err.println("Fini");

        try {
            PrintWriter writer = new PrintWriter("/tmp/nbTour.txt", "UTF-8");
            writer.println(nbTour);
            writer.close();
        } catch (Exception e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
        return gains;
    }
    private String afficherCombat(){//DONE
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

        return out;
    }
    private String afficherCombatHTML(){//DONE
        String out="";

        for(int x=0; x<tailleVillage+2; x++){
            for(int y=0; y<tailleVillage+2; y++){
                out += "    <img src=\"/INSACLASH/inc/";
                int nbSol=0;
                for(SoldatCombat soldat : armee.getSoldats())
                    if (soldat.getX() == x && soldat.getY() == y)
                        nbSol++;
                if(nbSol != 0)
                    if(nbSol < 11)
                        out +="img"+nbSol;
                    else
                        out +="img10+";
                else{
                    // Batiment
                    Iterator<Map.Entry<Integer, EntiteCombat>> it = terrain.get(x).get(y).entrySet().iterator();
                    Integer id = -1;
                    if(it.hasNext()){
                        id = it.next().getKey();
                        if(id!=-1){
                            TypeBatiment typeBat = ((BatimentCombat)terrain.get(x).get(y).get(id)).getBatiment().getTypeBatiment();
                            out+=   typeBat==TypeBatiment.CASERNE?"Caserne":
                                    typeBat==TypeBatiment.HDV?"HotelDeVille":
                                    typeBat==TypeBatiment.CANON?"Canon":
                                    typeBat==TypeBatiment.MORTIER?"Mortier":
                                    typeBat==TypeBatiment.MINEOR?"MineOr":
                                    typeBat==TypeBatiment.MINECHARBON?"MineCharbon":
                                    "XXX";
                        } else
                            out += "carreHerbe";
                    } else
                        out += "carreHerbe";
                }
                out+= ".png\" width=\"20\" height=\"20\">\n";
            }
            out += "<br>\n";
        }

        return out;
    }
    public void placerSoldats(boolean N, boolean S, boolean E, boolean W){//DONE
        int nb=0, mod;
        int nbN=0, nbS=0, nbE=0, nbW=0;
        if(N)
            nb++;
        if(S)
            nb++;
        if(E)
            nb++;
        if(W)
            nb++;

        mod = armee.getSoldats().size()%nb;

        if(N){
            nbN = armee.getSoldats().size()/nb;
            if(mod != 0){
                nbN++;
                mod--;
            }
        }
        if(S){
            nbS = armee.getSoldats().size()/nb;
            if(mod != 0){
                nbS++;
                mod--;
            }
        }
        if(E){
            nbE = armee.getSoldats().size()/nb;
            if(mod != 0){
                nbE++;
                mod--;
            }
        }
        if(W){
            nbW = armee.getSoldats().size()/nb;
            if(mod != 0){
                nbW++;
                mod--;
            }
        }


        Random r = new Random();

        int id=0;
        if(N)
            for(int i=0; i<nbN; i++){
                double rand=-1;
                while(rand<2 || rand>tailleVillage)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                deplacementSoldat(armee.getSoldats().get(id), 0, pos);
                id++;
            }
        if(S)
            for(int i=0; i<nbS; i++){
                double rand=-1;
                while(rand<2 || rand>tailleVillage)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                deplacementSoldat(armee.getSoldats().get(id), tailleVillage+1, pos);
                id++;
            }
        if(E)
            for(int i=0; i<nbE; i++){
                double rand=-1;
                while(rand<2 || rand>tailleVillage)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                deplacementSoldat(armee.getSoldats().get(id), pos, tailleVillage+1);
                id++;
            }
        if(W)
            for(int i=0; i<nbW; i++){
                double rand=-1;
                while(rand<2 || rand>tailleVillage)
                    rand = (r.nextGaussian()+1)/2*tailleVillage;
                int pos = (int)Math.floor(rand);
                deplacementSoldat(armee.getSoldats().get(id), pos, 0);
                id++;
            }
    }
}
