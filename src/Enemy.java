public class Enemy extends Entity{
    protected String quirk;
    protected String name;
    protected String flavorText[];
    // Should be entered in the format of {Appear Text,
    // Attack Text, Defeated it Text, Lost to Text}

    public Enemy(String name, int hp, int def, int str, String quirk,
     String[] flavorText){
        super(hp, def, str);
        this.quirk = quirk;
        this.name = name;
        this.flavorText = flavorText;
    }

    public Object[] getAllStats(){
        return new Object[] {this.cHP, this.str, this.def, this.quirk};
    }

    public String getQuirk(){ return this.quirk; }
    public String getName(){ return this.name; }
    public String[] getFlavorText(){ return this.flavorText; }

    public Enemy copyOfSelf(){
        return new Enemy(
            this.name,
            this.cHP,
            this.def,
            this.str,
            this.quirk,
            this.flavorText
        );
    }


}
