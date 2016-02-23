package Combat;

import java.util.Vector;

public abstract class EntiteCombat{
    protected static int cpt=0;
    protected int id;
    protected int x;
    protected int y;
    protected int PV;
    protected TypeEntite type;
    protected int taille;


    public int getTaille(){//DONE
        return taille;
    }
    public int getId(){//DONE
        return id;
    }
    public void tuer(){//DONE
        PV=-1;
    }
    public boolean estATuer(){//DONE
        return (PV==0);
    }
    public boolean estMort(){//DONE
        return (PV<0);
    }
    public void setPV(int i){//DONE
        PV=i;
    }
    public void retirerPV(int pv){//DONE
        PV=Math.max(0, PV-pv);
    }
    public int getPV(){//DONE
        return PV;
    }
    public void setX(int i){//DONE
        x=i;
    }
    public void setY(int i){//DONE
        y=i;
    }
    public int getX(){//DONE
        return x;
    }
    public int getY(){//DONE
        return y;
    }
    public TypeEntite getType(){//DONE
        return type;
    }
}
