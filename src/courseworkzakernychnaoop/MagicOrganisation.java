/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.Graphics;

public abstract class MagicOrganisation extends Organization  { 
    public static final int SPEED_DEFAULT = 5;
    
    public ArrayList<String> magsNames;
    public ArrayList<String> studentsNames;
    public ArrayList<String> entitysNames;  
    public ArrayList<String> specifiedNames;
    
    public ArrayList<MagicEntity> membersToRemove;
    public ArrayList<MagicEntity> members;
    
    public transient JFrame mainFrame;
    public int coordX;
    public int coordY;
    public Color backgroundColor;
    public Color borderColor;
    public String title;
    public int figureType;
    public boolean isActive;
    public int speed;    
    public int entitiesType;
    public int magsCount;
    public int studentsCount;
    public int entitiesCount;
    
    /*public MagicOrganisation() {
        magsNames = new ArrayList<>();
        entitysNames = new ArrayList<>();
        studentsNames = new ArrayList<>();
        specifiedNames = new ArrayList<>();
        members = new ArrayList<>();
    }*/
    
    public MagicOrganisation(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        magsNames = new ArrayList<>();
        entitysNames = new ArrayList<>();        
        studentsNames = new ArrayList<>();
        specifiedNames = new ArrayList<>();
        members = new ArrayList<>();
        membersToRemove = new ArrayList<>();
        
        magsCount = 0;
        studentsCount = 0;
        entitiesCount = 0;
        
        borderColor = SGI_STATEBLUE;
        speed = SPEED_DEFAULT;
    }
        
    public void setFrame(JFrame frame) {
        mainFrame = frame;
        
        for(MagicEntity entity : members) {
            mainFrame.add(entity.getEntityPanel());
        }
        
        //frame.repaint();
    }
    
    public String[] getNamesList(int level) {        
        switch(level) {
            case MagicEntity.HEADER_MAG: return magsNames.toArray(new String[magsNames.size()]);
            case MagicEntity.MAG: return studentsNames.toArray(new String[studentsNames.size()]);
            case MagicEntity.MAGIC_ENTITY: return entitysNames.toArray(new String[entitysNames.size()]);                    
        }
        return null;
    }
    
    public String getRandomMagName() {        
        if(magsNames.size() > 0) {
            int index = randomGenerator.nextInt(magsNames.size());               

            return magsNames.remove(index); 
        }
        else {
            return null;
        }
    }
    
    public String getRandomStudentName() {        
        if(studentsNames.size() > 0) {
            int index = randomGenerator.nextInt(studentsNames.size());               

            return studentsNames.remove(index); 
        }
        else {
            return null;
        }
    }
    
    public void changeMOOfActivePanels(int entityType) {
        if(isActive){
            for (MagicEntity entity : members) {
                if(entity.getEntityPanel().isActivePanel()){
                    changeMOOfEntity(entity, entityType);
                    
                    setNewEntityPanelLocation(entity);

                    entity.panel.repaint();
                }
            }
            
            clearMembersList();
        }        
    }
    
    private void clearMembersList() {
        members.removeAll(membersToRemove);
        
        //System.out.println("Deleting success");
        
        membersToRemove.clear();      
        
    }
    
    private void changeMOOfEntity (MagicEntity entity, int entityType) {
        entity.entityType = entityType;
        entity.getEntityPanel().changeMO(entityType);
        
        membersToRemove.add(entity);          
    }
    
    public ArrayList<MagicEntity> getMembersByRang(int entityLevel) {
        ArrayList<MagicEntity> membersByRank = new ArrayList<>();
        
        for(MagicEntity entity : members) {
            if(entity.getMagicLevel() == entityLevel) {
                membersByRank.add(entity);
            }
        }
        
        return membersByRank;
    }
    
    /*public void removeEntities(ArrayList<MagicEntity> listToRemove){
        for(MagicEntity entity : listToRemove) {
            members.remove(entity);
            
        }
    }
    */
    public void autoMoveActivePanels() {
        int x = (int) (System.currentTimeMillis() % 5);
        int y = (int) ((System.currentTimeMillis() + 8) % 5);        
        
        int directionX = x % 2 == 0 ? 1 : -1;
        int directionY = y % 2 == 0 ? 1 : -1;
        
        //System.out.println(directionX * x * speed + ", " + directionY * y * speed);
        
        for (MagicEntity entity : members){
            if(entity.getEntityPanel().isActivePanel()){
                entity.getEntityPanel().movePanel(directionX * x * speed, directionY * y * speed, coordX, coordY);
                
                if(!isCursorAtArea(entity.getEntityPanel().getX(), entity.getEntityPanel().getY())){//ПОЛІМОРФІЗМ getX(), getY()
                    changeMOOfEntity(entity, MagicEntity.NEUTRAL_PERSON);   
                    
                    MainWindow.neutrals.addEntity(entity);
                }
            }
        }        
    }
    
    private void setNewEntityPanelLocation(MagicEntity entity) {
        switch(entity.entityType) {
            case MagicEntity.THE_DEATH_EATER: 
                MainWindow.eaters.addEntity(entity);
                entity.panel.moveAtCoords(MainWindow.eaters.coordX, MainWindow.eaters.coordY);
                break;
            case MagicEntity.THE_ORDER_MEMBER: 
                MainWindow.orden.addEntity(entity);
                entity.panel.moveAtCoords(MainWindow.orden.coordX, MainWindow.orden.coordY);
                break;
            case MagicEntity.NEUTRAL_PERSON: 
                MainWindow.neutrals.addEntity(entity);
                Neutrals.setRandomCoords(entity);
                break;
        }
        
        mainFrame.repaint();
    }
    
    public boolean activateEntity(int mouseX, int mouseY) {  
        for (MagicEntity entity : members) {
            if(entity.getEntityPanel().isCursorInPanelArea(mouseX, mouseY)){
                entity.autoMove = true;
                entity.getEntityPanel().changeBorderColor(true);
                
                return true;
            }
        }
        
        return false;
    }
    
    public void activatePanel () {        
        isActive = true;
        
        borderColor = LIME; 
        
        mainFrame.repaint();
    }
    
    public void deactivatePanel() {
        deactivateAllEntity();
        
        isActive = false;
        
        borderColor = SGI_STATEBLUE; 
        
        mainFrame.repaint();
    }
    
    public void deactivateAllEntity(){
        if(isActive){
            for (MagicEntity entity : members) {
                entity.getEntityPanel().changeBorderColor(false);
                entity.autoMove = false;
            }
            
            mainFrame.repaint();
        }
    }
    
    public void removeAllActiveEntity() {
        if(isActive){
            for (MagicEntity entity : members) {
                if(entity.getEntityPanel().isActivePanel()){
                    membersToRemove.add(entity);
                    mainFrame.remove(entity.getEntityPanel());
                    
                    switch(entity.getMagicLevel()){
                        case MagicEntity.HEADER_MAG: 
                            magsCount--;
                            break;
                            case MagicEntity.MAG: studentsCount--;
                            break;
                                case MagicEntity.MAGIC_ENTITY: 
                                    entitiesCount--;
                                    break;
                    }   
                    //organizationPanel.remove(entity.getEntityPanel());
                }
            }
            clearMembersList();
            
            mainFrame.repaint();            
        }
    }
    
    public void addEntity(MagicEntity entity) {
        if(!members.contains(entity)) {
            members.add(entity);

            switch(entity.getMagicLevel()) {
                case MagicEntity.HEADER_MAG: 
                    magsCount ++;
                    magsNames.remove(entity.getName());
                    break;
                case MagicEntity.MAG: 
                    studentsCount++;
                    studentsNames.remove(entity.getName());
                    break;
                case MagicEntity.MAGIC_ENTITY: 
                    entitiesCount++;
                    entitysNames.remove(entity.getName());
                    break;    
            }

            entity.setPanelPosition(coordX, coordY);
        }
        
        try {
            mainFrame.remove(entity.getEntityPanel());
        }
        catch(Exception e) {
            //ignore
        }
        
        mainFrame.add(entity.getEntityPanel());                
            
        mainFrame.repaint();
    }
    
    public void addRandomLevelEntity() {
        addEntity(randomGenerator.nextInt(MagicEntity.MAGIC_ENTITY) + MagicEntity.MAGIC_ENTITY);
    }
    
    public void addEntity(int entityLevel) {
        if(isActive) {
            String name = "";        
        
            if(entityLevel == MagicEntity.HEADER_MAG) {
                magsCount++;
                
                name = getRandomMagName();
            } else if (entityLevel == MagicEntity.MAGIC_ENTITY){
                studentsCount++;
                
                name = getRandomEntityName();
            } else if (entityLevel == MagicEntity.MAG){
                entitiesCount++;
                
                name = getRandomStudentName();
            }

            if(name != null && !name.equals("")) {            
                double magicPower = ((randomGenerator.nextInt(200) + 1)/10) * entityLevel;
                
                MagicEntity newEntity = MagicEntity.getEntity(name, magicPower, entityLevel, entitiesType);

                members.add(newEntity);

                newEntity.setPanelPosition(coordX, coordY);

                mainFrame.add(newEntity.getEntityPanel());                
            }
            mainFrame.repaint();
        }
    }
    
    public void clear() {
        for(MagicEntity entity : members) {
            mainFrame.remove(entity.getEntityPanel());
        }
        members.clear();        
    }
    
    public void addSpecifiedEntity(int entityLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public MagicEntity getEntityAtCursorPosition(int x, int y) {
        for (MagicEntity entity : members) {
            if(entity.getEntityPanel().isCursorInPanelArea(x, y)){
                return entity;
            }
        }
        
        return null;
    }
            
    public void move (int moveX, int moveY) {
        if(isActive) {
            moveMOPanel(moveX, moveY);
        }
        else {
            moveActivePanels(moveX, moveY);
        }
        
        mainFrame.repaint();
    }
    
    public void moveActivePanels(int moveX, int moveY){
        for (MagicEntity entity : members) {
            if(entity.getEntityPanel().isActivePanel()){
                entity.getEntityPanel().movePanel(moveX, moveY, coordX, coordY);
                
                entity.coordX = entity.getEntityPanel().getX();
                entity.coordY = entity.getEntityPanel().getY();
                
                boolean isOrden = MainWindow.orden.isCursorAtArea(entity.getEntityPanel().getX() + EntityJPanel.WIDTH / 2, entity.getEntityPanel().getY() + EntityJPanel.HEIGHT / 2);
                boolean isEaters = MainWindow.eaters.isCursorAtArea(entity.getEntityPanel().getX() + EntityJPanel.WIDTH / 2, entity.getEntityPanel().getY() + EntityJPanel.HEIGHT / 2);
                boolean isNeutral = MainWindow.neutrals.isCursorAtArea(entity.getEntityPanel().getX() + EntityJPanel.WIDTH / 2, entity.getEntityPanel().getY() + EntityJPanel.HEIGHT / 2);
                
                if(isOrden && entity.entityType != MagicEntity.THE_ORDER_MEMBER){
                    changeMOOfEntity(entity, MagicEntity.THE_ORDER_MEMBER);
                    MainWindow.orden.members.add(entity);
                } else if(isEaters && entity.entityType != MagicEntity.THE_DEATH_EATER){
                    changeMOOfEntity(entity, MagicEntity.THE_DEATH_EATER);
                    MainWindow.eaters.members.add(entity);
                }
                else if (isNeutral && entity.entityType != MagicEntity.NEUTRAL_PERSON) {
                    System.out.println("Істота " + entity.getName() + " перейшла в ряди нейтральних істот");
                    
                    changeMOOfEntity(entity, MagicEntity.NEUTRAL_PERSON);
                    MainWindow.neutrals.members.add(entity);
                }
            }            
        }
        
        clearMembersList();
    }
    
    private void moveMOPanel(int moveX, int moveY) {
        coordX += moveX;
        coordY += moveY;
                
        if(coordX + MO_PANEL_WIDTH / 2 < 0 || coordX + MO_PANEL_WIDTH / 2 > MainWindow.WIDTH) {
            coordX -= moveX;
            
            moveX = 0;
        }
        if(coordY + MO_PANEL_HEIGHT / 2 < 0 || coordY + MO_PANEL_HEIGHT / 2 > MainWindow.HEIGHT) {
            coordY -= moveY;
            
            moveY = 0;
        }
        
        if(!(moveX == 0 && moveY == 0)) {
            moveAllEntity(moveX, moveY);
        }   
    }
    
    private void moveAllEntity(int moveX, int moveY) {
        for (MagicEntity entity : members) {
            entity.getEntityPanel().movePanel(moveX, moveY, coordX, coordY);
        }
    }
    
    public String getRandomEntityName() { 
        if(entitysNames.size() > 0) {
            int index = randomGenerator.nextInt(entitysNames.size());               

            return entitysNames.remove(index);
        }
        else {
            return null;
        }
    }
    
    public boolean isPanelCoordsInMOPanel(int x, int y) {        
        return (coordX > x + EntityJPanel.WIDTH / 2 || 
                coordX + MO_PANEL_WIDTH < x + EntityJPanel.WIDTH / 2) && 
                (coordY > y + EntityJPanel.HEIGHT / 2 || 
                coordY + MO_PANEL_HEIGHT < y + EntityJPanel.HEIGHT / 2);
    }
    
    @Override
    public void draw(Graphics g) {        
        //draw borders
        g.setColor(borderColor);        
        g.fillRect(coordX - PANEL_BORDER_SIZE, coordY - PANEL_BORDER_SIZE, MO_PANEL_WIDTH + PANEL_BORDER_SIZE * 2, MO_PANEL_HEIGHT + PANEL_BORDER_SIZE * 2);
        //draw panel
        g.setColor(backgroundColor);
        g.fillRect(coordX, coordY, MO_PANEL_WIDTH, MO_PANEL_HEIGHT);
        //draw figure        
        g.setColor(IVORY);
        
        if(figureType == FT_OVAL) {
            g.fillOval(coordX + MO_PANEL_WIDTH / 2 - 50, coordY + MO_PANEL_HEIGHT / 2 - 50, 100, 100);
        } else if (figureType == FT_ROUNDED_RECT){
            g.fillRoundRect(coordX + MO_PANEL_WIDTH / 2 - 50, coordY + MO_PANEL_HEIGHT / 2 - 50, 100, 100, 10, 10);
        }
        
        //draw text
        g.setColor(TAN);
        g.drawString(title, coordX + 20, coordY + 20);
        g.drawString("Кількість об'єктів - " + Integer.toString(members.size()), coordX + 20, coordY + 50);
        
        for (MagicEntity entity : members) {
            entity.getEntityPanel().repaint();
        }
    }
    
    public boolean isCursorAtArea(int x, int y) {
        return (coordX - PANEL_BORDER_SIZE <= x && coordX + MO_PANEL_WIDTH + PANEL_BORDER_SIZE >= x) &&
                (coordY - PANEL_BORDER_SIZE <= y && coordY + MO_PANEL_HEIGHT + PANEL_BORDER_SIZE >= y);
    }
    
    private static final int PANEL_BORDER_SIZE = 10;
    
    public static Random randomGenerator = new Random();
}
