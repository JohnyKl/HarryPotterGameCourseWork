/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Color;
import javax.swing.JFrame;

public class TheDeathEaters  extends MagicOrganisation{
    private void createArrays() {
        magsNames.add("Белатриса Лестрендж");
        magsNames.add("Люциус Малфой");
        studentsNames.add("Драко Малфой");
        studentsNames.add("Пансі Паркінсон");        
        studentsNames.add("Блейз Забіні");
        studentsNames.add("Грегорі Гойл");
        magsNames.add("Барті Крауч");
        magsNames.add("Ігорь Каркаров");
        magsNames.add("Долорес Амбридж");
        magsNames.add("Том Редл");        
        
        //specifiedNames.add("Ігорь Каркаров");
        
        entitysNames.add("Змія Нагайна");
        entitysNames.add("Дементор");
    }
    
    /*public TheDeathEaters() {
        super();
        
        createArrays();
        
        entitiesType = MagicEntity.THE_DEATH_EATER;
        
        organizationPanel = new MagicOrganizationJPanel("images/forest.jpg");
        
        organizationPanel.setBounds(MO_PANEL_WIDTH + 198, 0, MO_PANEL_WIDTH, MO_PANEL_HEIGHT);
    }*/
    
    public TheDeathEaters(JFrame frame) {
        super(frame);
        
        createArrays();
        
        entitiesType = MagicEntity.THE_DEATH_EATER;
        
        coordX = MO_PANEL_WIDTH + 25 + INTERVAL_WIDTH;
        coordY = 50;
        
        backgroundColor = SALMON;        
        title = "Смертежери";
        figureType = FT_ROUNDED_RECT;
    }
    
    @Override
    public void addSpecifiedEntity(int entityLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
