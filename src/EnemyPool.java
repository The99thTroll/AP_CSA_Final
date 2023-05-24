import java.util.*;

public class EnemyPool {
    private ArrayList<Enemy> allEnemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> enemyPool = new ArrayList<Enemy>();
    private int enemiesDefeated = 0;

    public EnemyPool() {
        //Initialize both ArrayLists
        enemyPool.add(
            new Enemy(
            "Thug", 5, 0, 3, "None",
            new String[]{
                "You're just minding your own business when a thug gets in your way!",
                "\n\nThe when takes a punch at you, dealing ",
                "\n\nWith an sad thump, the thug falls flat, knocked out.",
                "\n\nThe last thing you hear is a crazy cackle before you black out"
            })
        );
        enemyPool.add(
            new Enemy(
            "Angry Turtle", 6, 2, 1, "None",
            new String[]{
                "You stumble over something, then realizing you've angered a turtle!",
                "\n\nThe turtle bites you, dealing ",
                "\n\nRolling over, the turtle dies. You can't help but feel sorry for it.",
                "\n\nYou died... to a turtle? Damn, that's sad."
            })
        );

        allEnemies.add(
            new Enemy(
            "Recruit", 10, 1, 3, "Noble",
            new String[]{
                "A fresh recruit walks over to you with arrest notice in hand...",
                "\n\nThe recruit wacks you with a baton, dealing ",
                "\n\nHe falls to the floor and life moves on.",
                "\n\nYou faint just to reawaken in a prison cell where you quickly fall ill..."
            })
        );
        allEnemies.add(
            new Enemy(
            "Bandit", 5, 1, 4, "Nimble",
            new String[]{
                "A bandit quickly dashes by you and attempts to steal your money!",
                "\n\nThey lash out with blinding speed, dealing ",
                "\n\nFinally you land a finishing blow and the bandit crumples to the floor.",
                "\n\nOutmatched by their speed, you lose both your gold and your life."
            })
        );
        allEnemies.add(
            new Enemy(
            "Gambler", 7, 1, 3, "Lucky",
            new String[]{
                "A man with buldging pockets approaches you!",
                "\n\nHe lobs coins in your direction, dealing ",
                "\n\nThe rich dude dies and bystanders flock to steal his money",
                "\n\nYou can't help but feel helpless as the man steals your money as you fade to black."
            })
        );
        allEnemies.add(
            new Enemy(
            "Paladin", 12, 5, 2, "Noble",
            new String[]{
                "A shiny paladin appears eyeing you up!",
                "\n\nThey slash at you with their sword, dealing ",
                "\n\nWith a grunt he kneels, ackowledging your strength",
                "\n\n\"I didn't mean to hit you that hard!\" they say before you pass out."
            })
        );
        allEnemies.add(
            new Enemy(
            "Snake", 2, 0, 6, "Nimble",
            new String[]{
                "You hear a \"hssssss\" behind you and see a snake!",
                "\n\nIt goes \"hsssssssss\", dealing ",
                "\n\n*sad hsss* ",
                "\n\nHsSSSSsssSSssSSsSsssS"
            })
        );
        allEnemies.add(
            new Enemy(
            "Giant", 20, 0, 5, "None",
            new String[]{
                "You hear large footsteps as a giant comes from behind you!",
                "\n\nIt wacks you with a large fist, dealing ",
                "\n\nThe giant crashes down to the ground, shattering the Earth around. ",
                "\n\nYou faint just to reawaken in the giant's cooking pot..."
            })
        );
    }

    public Enemy getRandomEnemy(){
        Random rand = new Random();
        enemiesDefeated += 1;


        if(enemiesDefeated%3 == 0){
            if(allEnemies.size() > 0){
                Enemy newEnemy = allEnemies.get(rand.nextInt(allEnemies.size()));
                removeFromArrayList(allEnemies, newEnemy.getName());
                enemyPool.add(newEnemy);
            }

            if(enemiesDefeated%2 == 0)
                this.scaleEnemyStats(Math.max(1, enemiesDefeated/10 - 1));
        }

        return enemyPool.get(rand.nextInt(enemyPool.size()));
    }

    public void removeFromArrayList(ArrayList<Enemy> list, String name){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(name)){
                list.remove(i);
            }
        }
    }

    public void scaleEnemyStats(int num){
        for(int i = 0; i < enemyPool.size(); i++){
            Enemy enemy = enemyPool.get(i);
            enemy.setHp(enemy.getHp() + num);
            enemy.setDef(enemy.getDef() + num);
            enemy.setStr(enemy.getStr() + num);
        }
    }
}
