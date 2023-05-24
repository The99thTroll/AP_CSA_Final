import java.util.Random;

public class Entity {
    protected int mHP; // Max Health Points
    protected int cHP; // Current Health Points
    protected int def; // Defense
    protected int str; // Strength

    public Entity(int hp, int def, int str){
        this.mHP = hp;
        this.cHP = hp;
        this.def = def;
        this.str = str;
    };

    // Precondition: Values must be able to be printed easily
    public Object[] getAllStats(){
        return new Object[] {this.cHP, this.str, this.def};
    }

    public int getHp(){ return cHP; }
    public int getMaxHp(){ return mHP; }
    public int getDef(){ return def; }
    public int getStr(){ return str; }

    public int damageCalc(Entity attacker){
        // Damage = 1.5 x Strength/(Defense + 1) x Crit
        Random random = new Random();
        int crit = 1;

        // 10% chance to deal x2 damage
        if(random.nextInt(10) == 0){
            crit = 2;
        }

        if(this instanceof Enemy){
            Enemy self = (Enemy) this;

            // Prevents crits
            if(self.getQuirk().equals("Noble")){
                crit = 1;
            }

            // Gives it an 18% chance to crit rather than a 10%
            if(self.getQuirk().equals("Lucky")
            && random.nextInt(10) == 0){
                crit = 2;
            }
        }

        return Math.max(1, (int) (1.5 * attacker.getStr() / (getDef() + 1) * crit));
    }

    // Returns if the target is dead or not
    public boolean removeHP(int num){
        cHP -= num;
        if(cHP <= 0)
            return true;
        return false;
    }

    public void setHp (int num){ mHP = num; cHP = num; }
    public void setDef (int num){ def = num; }
    public void setStr (int num){ str = num; }
}
