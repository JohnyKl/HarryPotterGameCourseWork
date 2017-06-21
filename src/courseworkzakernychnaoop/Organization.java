/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johny
 */
public abstract class Organization implements Serializable{
    public final static int MO_PANEL_WIDTH  = 400;
    public final static int MO_PANEL_HEIGHT = 620;
    public final static int INTERVAL_WIDTH  = 350;
    
    public static final Color SGI_STATEBLUE = new Color(113, 113, 198);
    public static final Color SALMON = new Color(250, 128, 114);    
    public static final Color DARKSEAGREEN = new Color(143, 188, 143);       
    public static final Color IVORY = new Color(255, 255, 240);
    public static final Color TAN  = new Color(139, 90, 43);    
    public static final Color LIME  = new Color(0, 255, 0);
            
    public static int FT_OVAL = 1;
    public static int FT_ROUNDED_RECT = 2;
        
    public static void save (String path) {
        FileOutputStream fout = null;  
        File file;
        
        try {
            file = new File(path + ".slz");
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            fout = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(MainWindow.orden);  
            out.writeObject(MainWindow.eaters);  
            out.writeObject(MainWindow.neutrals);  
            out.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MagicOrganisation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MagicOrganisation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(MagicOrganisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void load (String path) {
        ObjectInputStream in = null;  
        
        OrdenOfThePhoenix orden = null;
        TheDeathEaters eaters = null;
        Neutrals neutrals = null;
        
        if(!path.endsWith(".slz")){
            path += ".slz";
        }
        
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            
            orden = (OrdenOfThePhoenix)in.readObject();
            eaters = (TheDeathEaters)in.readObject();
            neutrals = (Neutrals)in.readObject();
            /*MainWindow.orden = (OrdenOfThePhoenix)in.readObject();
            MainWindow.eaters = (TheDeathEaters)in.readObject();
            MainWindow.neutrals = (Neutrals)in.readObject();*/
            createPanels(orden);
            createPanels(eaters);
            createPanels(neutrals);
            
            copyMembers(orden, MainWindow.orden);
            copyMembers(eaters, MainWindow.eaters);            
            copyMembers(neutrals, MainWindow.neutrals);
            
            in.close();  
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MagicOrganisation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(MagicOrganisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        //return org;
    }
    
    private static void copyMembers(MagicOrganisation sOrg, MagicOrganisation dOrg)  {
        dOrg.activatePanel();        
        dOrg.clear();
                
        for(MagicEntity entity : sOrg.members) {
            dOrg.addEntity(entity);
        }
    }
    
    private static void createPanels(MagicOrganisation org) {
        if(org != null) {
            for (MagicEntity entity : org.members) {
                entity.createEntityPanel(); 
                entity.getEntityPanel().moveAtCoords(entity.coordX, entity.coordY);
            }            
        }
    }
    
    public abstract void draw(Graphics g);
}
