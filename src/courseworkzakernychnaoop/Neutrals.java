/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Johny
 */
public class Neutrals extends MagicOrganisation {
    public Neutrals (JFrame frame) {
        super(frame);
        
        createArrays();
        
        entitiesType = MagicEntity.THE_DEATH_EATER;
        
        coordX = 0;
        coordY = 0;
                
        backgroundColor = IVORY;        
        title = "Нейтральні істоти";
        figureType = -1;
        
        isActive = true;
    }
    
    private void createArrays() {
        magsNames.add("Барті Крауч старший");
        magsNames.add("Міністр Фадж");        
        
        studentsNames.add("Колін Кріві");
        
        //specifiedNames.add("Ігорь Каркаров");
        
        entitysNames.add("Великан Гроп");
    }
    
    @Override
    public void draw(Graphics g) {
        members.stream().forEach((entity) -> {
            entity.getEntityPanel().repaint();
        });
    }
    
    public static void setRandomCoords(MagicEntity entity) {
        int x = 0;
        int y = 0;
        
        do{
            x = MagicOrganisation.randomGenerator.nextInt(MainWindow.WIDTH) + 1;
            y = MagicOrganisation.randomGenerator.nextInt(MainWindow.HEIGHT) + 1;
        }while (MainWindow.orden.isPanelCoordsInMOPanel(x, y) || MainWindow.eaters.isPanelCoordsInMOPanel(x, y));
        
        entity.panel.moveAtCoords(x, y);
    }    
    
    @Override
    public void move (int moveX, int moveY) {
        moveActivePanels(moveX, moveY);        
        
        mainFrame.repaint();
    }
            
    @Override
    public boolean isCursorAtArea(int x, int y) {
        return !MainWindow.orden.isCursorAtArea(x, y) && !MainWindow.eaters.isCursorAtArea(x, y);
    }
}

