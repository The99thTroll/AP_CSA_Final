import javax.swing.*;

public class Game extends JComponent{
    private EnemyPool enemyPool = new EnemyPool();

    // Player and Enemy
    private final Player player = new Player();
    private Enemy enemy = enemyPool.getRandomEnemy().copyOfSelf();

    // Create the window + dynamic UI elements
    private final JFrame frame = new JFrame("Turn Based Combat");
    private final JTextArea output = new JTextArea();
    private final JButton[] actions = {new JButton("Attack"), new JButton("Parry"), new JButton("Heal")};
    private final JLabel[] playerStats = {
        new JLabel("Player HP: "), new JLabel("Strength: "),
        new JLabel("Defense: "), new JLabel("Heals Left: ")
    };
    private final JLabel[] enemyStats = {
        new JLabel("Enemy HP: "), new JLabel("Strength: "),
        new JLabel("Defense: "), new JLabel("Quirk: ")
    };

    public Game() {
        new UI(frame, actions, playerStats, enemyStats, output, enemy.getFlavorText()[0]);
        this.setUI();

        // Setup Action Listeners
        actions[0].addActionListener(e -> {
            if(enemy.getQuirk().equals("Nimble")){
                enemyStrike();
            }

            if(player.getHp() <= 0)
                return;

            int dmg = enemy.damageCalc(player);
            boolean enemyDead = enemy.removeHP(dmg);

            output.setText(output.getText() + "\n\nYou strike, dealing " + dmg + " DMG");

            if(!enemyDead){
                if(!enemy.getQuirk().equals("Nimble"))
                    enemyStrike();
                setUI();
            }else{
                setUpNextFight();
            }
            // Attack
        });
        actions[1].addActionListener(e -> {
            player.setDef(player.getDef() + 1);
            output.setText(output.getText() + "\n\nYou ready yourself for the attack.");

            int initHP = player.getHp();
            enemyStrike();
            int changeInHP = initHP - player.getHp();
            int reboundDmg = (int) (changeInHP * 2.25);

            if(player.getHp() > 0){
                player.setDef(player.getDef() - 1);
                output.setText(output.getText() + ". You lash back dealing " + reboundDmg + " DMG!");

                if(enemy.removeHP(reboundDmg)){
                    setUpNextFight();
                }
            }
            // Parry
            setUI();
        });
        actions[2].addActionListener(e -> {
            int initHP = player.getHp();
            if(player.heal()){
                int deltaHP = player.getHp() - initHP;
                output.setText(output.getText() + "\n\n You patch yourself up, restoring" + deltaHP + " HP.");
            }else{
                output.setText(output.getText() + "\n\n Trying to patch up, you realize you have no medical supplies");
            }

            enemyStrike();
            // Heal
            setUI();
        });
    }

    // Helper Function for Enemy attacks
    private void enemyStrike(){
        int dmg = player.damageCalc(enemy);
        output.setText(output.getText() + enemy.getFlavorText()[1] + dmg + " DMG");

        if(player.removeHP(dmg)){
            setUI();
            output.setText(output.getText() + enemy.getFlavorText()[3]);
            JOptionPane.showMessageDialog(frame, "You died!");
            for(JButton b : actions){
                b.setEnabled(false);
            }
        }
    }

    private void setUpNextFight(){
        setUI();
        player.levelUp(frame, enemy.getFlavorText()[2]);
        player.heal(Math.max(1, player.getMaxHp()/5));
        enemy = enemyPool.getRandomEnemy().copyOfSelf();
        setUI();

        output.setText(enemy.getFlavorText()[0]);

    }

    // Helper Function for setting UI
    private void setUI(){
        updateListOfUI(player.getAllStats(), playerStats);
        updateListOfUI(enemy.getAllStats(), enemyStats);

        playerStats[0].setText("HP: " + player.getHp() + "/" + player.getMaxHp());
    }

    private void updateListOfUI(Object[] data, JLabel[] list) {
        for(int i = 0; i < list.length; i++){
            String temp = list[i].getText();
            list[i].setText(
                temp.substring(0, temp.indexOf(":") + 1) +
                " " + data[i]
            );
        }
    }
}
