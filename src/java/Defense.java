abstract class Defense extends Batiment{
    protected TypeAttaque typeDefense;
    protected int puissanceDefense;
    
    public void setTypeDefense(TypeAttaque t){
        typeDefense=t;
    }
    
    public TypeAttaque getTypeDefense(){
        return typeDefense;
    }
    
    public void setPuissanceDefense(int puiss){
        puissanceDefense=puiss;
    }
    
    public int getPuissanceDefense(){
        return puissanceDefense;
    }
}
