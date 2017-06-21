/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import static courseworkzakernychnaoop.MagicEntity.NEUTRAL_PERSON;
import static courseworkzakernychnaoop.MagicEntity.entityCounter;
import java.util.Random;

/**
 *
 * @author Lenka
 */
public class HeaderMagEntity extends MagicEntity{
    public final static int MAGIC_LEVEL = HEADER_MAG;
    
    public HeaderMagEntity(String name, double magicPower, int entityType) {
        entityCounter++;
        
        randomGenerator = new Random();  
        
        this.magicLevel = MAGIC_LEVEL;
        this.entityID   = entityCounter;        
        this.name       = name;
        this.magicPower = magicPower <= MAX_MAGIC_POWER ? magicPower : MAX_MAGIC_POWER;
        this.entityType = entityType;
        
        createEntityPanel();
        
        System.out.println("Був створений маг з ID " + this.entityID);
    }   
    
    public HeaderMagEntity() {
        this("Some entity", 1.0, NEUTRAL_PERSON);
    }
    
    public HeaderMagEntity(MagicEntity entity) {
        this(entity.getName(), entity.getMagicPower(), entity.getEntityType());
    }  
 
    private final static int MAX_MAGIC_POWER = MAX_MAGIC_COEFFICIENT * MAGIC_LEVEL;
}
