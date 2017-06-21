/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Lenka
 */
public abstract class MagicEntity implements Serializable{
    public static int entityCounter = 0;
    
    public static String ORDEN_LABEL_PATH = "images/theOrdenOfPhoenixLabel.jpg";
    public static String EATERS_LABEL_PATH = "images/theDeathEatersLabel.jpg";
    public static String NEUTRAL_LABEL_PATH = "images/hatLabel.jpg";
        
    public final static int MAX_MAGIC_COEFFICIENT = 20;
    
    public final static int NEUTRAL_PERSON   = 0;
    public final static int THE_DEATH_EATER  = 1;
    public final static int THE_ORDER_MEMBER = 2;
    
    public final static int HEADER_MAG = 5;
    public final static int MAG = 4;
    public final static int MAGIC_ENTITY = 3;
    
    public String name;
    public double magicPower;
    public int magicLevel;
    public int entityType;
    public int entityID;
    public boolean autoMove;
    public int coordX;
    public int coordY;
    
    public static Random randomGenerator;
    
    public transient EntityJPanel panel;    
    
    public static Map entityIconPathNames = new HashMap();    
        
    public static MagicEntity getEntity(String name, double magicPower, int magicLevel, int entityType) {
        MagicEntity entity = null;
                
        switch (magicLevel) {
            case HEADER_MAG: 
                entity = new HeaderMagEntity(name, magicPower, entityType);
                
                break;
            case MAG: 
                entity = new StudentMagEntity(name, magicPower, entityType);
                
                break;
            case MAGIC_ENTITY:
                entity = new AnimalMagicEntity(name, magicPower, entityType);
                
                break;
        }
        
        if(entity != null) entity.createEntityPanel();
        
        return entity;
    }
    
    /*public MagicEntity(String name, double magicPower, int magicLevel, int entityType) {
        entityCounter++;
        
        randomGenerator = new Random();   
        
        this.entityID   = entityCounter;        
        this.name       = name;
        this.magicPower = magicPower;
        this.magicLevel = magicLevel;
        this.entityType = entityType;
        
        createEntityPanel();
        
        System.out.println("Була створена магічна істота з ID " + this.entityID);
    }   
    
    public MagicEntity() {
        this("Some entity", 1.0, 1, NEUTRAL_PERSON);
    }
    
    public MagicEntity(MagicEntity entity) {
        this(entity.getName(), entity.getMagicPower(), entity.getMagicLevel(), entity.getEntityType());
    }*/
    
    public void createEntityPanel() {
        String iconPath = NEUTRAL_LABEL_PATH;   
        
        if(entityIconPathNames.containsKey(name)){
            iconPath = (String) entityIconPathNames.get(name);
        }
        else{
            if (entityType == THE_DEATH_EATER) {
                iconPath = EATERS_LABEL_PATH;                
            }
            else if (entityType == THE_ORDER_MEMBER) {
                iconPath = ORDEN_LABEL_PATH;
            } 
        }        
        
        panel = new EntityJPanel(iconPath, name, magicPower, magicLevel, entityType);
    }
    
    public void setPanelPosition(int opX, int opY){
        int offsetX = 0;
        int offsetY = 0;
        
        offsetX = randomGenerator.nextInt(MagicOrganisation.MO_PANEL_WIDTH - 147) + opX;
                
        offsetY = randomGenerator.nextInt(MagicOrganisation.MO_PANEL_HEIGHT - 255) + 1;
        
        panel.setPosition(offsetX, offsetY);
        
        if(entityType == NEUTRAL_PERSON) {
            Neutrals.setRandomCoords(this);
        }
        
        //System.out.println("panel coord (" + offsetX + ", " + offsetY + ")");
        panel.setVisible(true);
    }
    
    @Override
    public String toString() { 
        String str = entityID + ". " + name + ":\nМагічна сила - " + magicPower + 
               "\nМагічний рівень - ";// + magicLevel + "\nНалежність до органіації - ";
        
        if (magicLevel == HEADER_MAG) str += "Могутній маг";
        else if (magicLevel == MAG) str += "Маг";
        else if (magicLevel == MAGIC_ENTITY) str += "Магічна істота";
        else str += "Невідомий тип магічної істоти";
        
        str += "\nНалежність до органіації - ";
        
        str += getEntityTypeText(entityType);
        
        return str;
    }
    
    public static int getEntityCounter() { return entityCounter;}
    
    public int getEntityID() { return entityID; }
    public int getMagicLevel() { return magicLevel; }
    public int getEntityType() { return entityType; }
    
    public static String getEntityTypeText(int entityType) {
        String str = "";
        
        if (entityType == NEUTRAL_PERSON) str += "Нейтральний";
        else if (entityType == THE_DEATH_EATER) str += "Смертежери";
        else if (entityType == THE_ORDER_MEMBER) str += "Орден Фенікса";
        else str += "Невідомий тип магічної істоти";
        
        return str;
    }
    
    public double getMagicPower() { return magicPower; }
    public String getName() { return name; }
    public EntityJPanel getEntityPanel() { return panel; }
    
    public void setMagicLevel(int magicLevel) { this.magicLevel = magicLevel; }
    public void setEntityType(int entityType) { this.entityType = entityType; }
    public void setMagicPower(double magicPower) { this.magicPower = magicPower; }
    public void setName(String name) { this.name = name; }
    public void setEntityPanel(EntityJPanel panel) { this.panel = panel; }
    
    static void addIconNames() {
        entityIconPathNames.put("Северус Снейп", "images/Severus.jpg");
        entityIconPathNames.put("Альбус Дамблдор", "images/Dumbledore.jpg");
        entityIconPathNames.put("Гаррі Поттер", "images/Harry.jpg");
        entityIconPathNames.put("Люциус Малфой", "images/Lucius.jpg");
        entityIconPathNames.put("Сіріус Блек", "images/Sirius.jpg");       
        entityIconPathNames.put("Том Редл", "images/Voldemort.jpg");
    } 
    
    static {
        addIconNames();
    }
}
