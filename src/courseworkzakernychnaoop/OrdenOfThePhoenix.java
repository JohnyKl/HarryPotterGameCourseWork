/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Lenka
 */
public class OrdenOfThePhoenix extends MagicOrganisation{    
    private void createArrays() {
        magsNames.add("Джеймс Поттер");
        magsNames.add("Лілі Поттер");
        magsNames.add("Артур Візлі");
        magsNames.add("Моллі Візлі");
        magsNames.add("Мінерва Макгонагал");
        magsNames.add("Дедалус Дігл");
        magsNames.add("Френк Лонгботтом");
        magsNames.add("Аліса Лонгботтом");        
        magsNames.add("Аберфорт Дамблдор");        
        magsNames.add("Аластор Муді");
        magsNames.add("Ремус Люпин");
        magsNames.add("Сіріус Блек");
        magsNames.add("Рубеус Хагрід");
        studentsNames.add("Гаррі Поттер");
        studentsNames.add("Герміона Грейнджер");
        studentsNames.add("Рон Візлі");
        studentsNames.add("Симус Фініган");
        studentsNames.add("Джордж Візлі");
        studentsNames.add("Фред Візлі");
        studentsNames.add("Джині Візлі");
        magsNames.add("Альбус Дамблдор");
        
        magsNames.add("Пітер Петігрю");
        magsNames.add("Северус Снейп");
        
        //specifiedNames.add("Пітер Петігрю");
        //specifiedNames.add("Северус Снейп");
        
        entitysNames.add("Фенікс Фоукс");
        entitysNames.add("Гіпогриф Бакбик");
        entitysNames.add("Тестрал");
        entitysNames.add("Кентавр Фіренце");
        entitysNames.add("Домовий ельф Доббі");
    }
    
    /*public OrdenOfThePhoenix() {
        super();
        
        createArrays();
        
        entitiesType = MagicEntity.THE_ORDER_MEMBER;
        
        organizationPanel = new MagicOrganizationJPanel("images/hogwarts.jpg");
        organizationPanel.setBounds(0, 0, MO_PANEL_WIDTH, MO_PANEL_HEIGHT);
    }*/
    
    public OrdenOfThePhoenix(JFrame frame) {
        super(frame);
        
        createArrays();
        
        entitiesType = MagicEntity.THE_ORDER_MEMBER;
        
        coordX = 25;
        coordY = 50;
        backgroundColor = DARKSEAGREEN;
        title = "Орден Фенікса";
        figureType = FT_OVAL;
    }
    
    @Override
    public void addSpecifiedEntity(int entityLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
