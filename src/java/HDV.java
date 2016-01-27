public class HDV extends Batiment{
    private Batiments batiment;
    public HDV(){//WIP
        typeBatiment="HDV";
    }
    public void upgrade(){//TODO
        super.upgrade();
    }
    public HDV clone(){//DONE
        return new HDV();
    }
}
