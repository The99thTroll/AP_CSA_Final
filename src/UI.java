import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI {
    private Font font = new Font("Bold", Font.BOLD, 12);
    private JPanel playerPanel = new JPanel(null);
    private JPanel enemyPanel = new JPanel(null);

    public UI(JFrame frame, JButton[] actions, JLabel playerStats[], JLabel[] enemyStats, JTextArea output,
              String initText){
        // Initialize the window
        frame.setLayout(null);
        frame.setSize(400, 280);
        
        // Initialize element properties            
        output.setEnabled(false);
        output.setText(initText);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setFont(font);

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBounds(10, 10, 250, 120);
        
        setPositions(actions, 270, 10, 40, 120, 40);
       
        playerPanel.setBackground(Color.LIGHT_GRAY); 
        playerPanel.setBounds(10, 140, 180, 100);

        playerStats[0].setForeground(new Color(25, 176, 62));
        setPositions(playerStats, 10, 5, 22, 100, 30);

        enemyPanel.setBackground(Color.LIGHT_GRAY); 
        enemyPanel.setBounds(210, 140, 180, 100);

        enemyStats[0].setForeground(Color.RED);
        setPositions(enemyStats, 10, 5, 22, 100, 30);

        // Add elements to the frame/panel    
        frame.add(scrollPane);
        
        for(JButton a : actions) {
        	frame.add(a);
        }

        for(JLabel a : playerStats) {
        	playerPanel.add(a);
        }

        for(JLabel a : enemyStats) {
        	enemyPanel.add(a);
        }

        frame.add(playerPanel);
        frame.add(enemyPanel);
        
        // Clean up resources on close
        frame.addWindowListener(new WindowAdapter() {
    	    @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        
        // Display the frame, update text fields
        frame.setVisible(true);
    }

    // Helper Function for UI components
    private void setPositions(JComponent[] data, int x, int yInit,
    int yIncr, int width, int height){
        for(int i = 0; i < data.length; i++) {
        	data[i].setBounds(
                x,
                yInit + i * yIncr,
                width,
                height
            );
        }
    }
}
