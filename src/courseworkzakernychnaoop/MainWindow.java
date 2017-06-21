/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.Timer;

/**
 *
 * @author Lenka
 */
public class MainWindow extends JFrame {
    public static OrdenOfThePhoenix orden;
    public static TheDeathEaters eaters; 
    public static Neutrals neutrals; 
    public static Timer timer;
    public boolean enabledAutoMove;
    
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;
    
    public MainWindow() {
        initComponents();
        createPopupMenu();
        initTimer();
        setLayout(null);        
        
        enabledAutoMove = false;
        
        orden = new OrdenOfThePhoenix(this);
        eaters = new TheDeathEaters(this);
        neutrals = new Neutrals(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        addKeyListener(new KeyAdapter() {          
            @Override
            public void keyPressed(KeyEvent event) {
                int keyID = event.getKeyCode();
                
                enabledAutoMove = false;
                
                switch(keyID) {
                    case KeyEvent.VK_LEFT:
                        orden.move(-5, 0);
                        eaters.move(-5, 0);
                        neutrals.move(-5, 0);
                        //orden.moveActivePanels(-2, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        orden.move(5, 0);
                        eaters.move(5, 0);
                        neutrals.move(5, 0);
                        break;
                    case KeyEvent.VK_UP:
                        orden.move(0, -5);
                        eaters.move(0, -5);
                        neutrals.move(0, -5);
                        break;
                    case KeyEvent.VK_DOWN:
                        orden.move(0, 5);
                        eaters.move(0, 5);
                        neutrals.move(0, 5);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        orden.deactivateAllEntity();
                        eaters.deactivateAllEntity();
                        neutrals.deactivateAllEntity();
                        break;
                    case KeyEvent.VK_INSERT:
                        new EntityCreator().setVisible(true);
                        //orden.addRandomLevelEntity();
                        //eaters.addRandomLevelEntity();  
                        break;
                    case KeyEvent.VK_DELETE:
                        orden.removeAllActiveEntity();
                        eaters.removeAllActiveEntity();
                        neutrals.removeAllActiveEntity();
                        //orden.organizationPanel.repaint();
                        break;
                    case KeyEvent.VK_1: 
                        orden.addEntity(MagicEntity.HEADER_MAG);
                        eaters.addEntity(MagicEntity.HEADER_MAG);   
                        //neutrals.addEntity(MagicEntity.HEADER_MAG); 
                        break;
                    case KeyEvent.VK_2: 
                        orden.addEntity(MagicEntity.MAG);
                        eaters.addEntity(MagicEntity.MAG);   
                        //neutrals.addEntity(MagicEntity.MAG); 
                        break;
                    case KeyEvent.VK_3:
                        orden.addEntity(MagicEntity.MAGIC_ENTITY);
                        eaters.addEntity(MagicEntity.MAGIC_ENTITY);   
                        //neutrals.addEntity(MagicEntity.MAGIC_ENTITY); 
                        break;
                    case KeyEvent.VK_F2:
                        showSaver();
                        break;
                    case KeyEvent.VK_S:
                        changePanelsSpeed();
                        break;
                    case KeyEvent.VK_A:
                        enabledAutoMove = true;
                        break;    
                    case KeyEvent.VK_B:
                        battle();
                        break;    
                }
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    jPopupMenu1.setLocation(e.getX(), e.getY());
                    jPopupMenu1.setVisible(true);
                } else if(e.getButton() == MouseEvent.BUTTON1) { 
                    eaters.deactivatePanel();  
                    orden.deactivatePanel();
                    
                    if(orden.isCursorAtArea(e.getX(), e.getY())) {                    
                        if (!orden.activateEntity(e.getX(), e.getY())) {
                            orden.activatePanel();                                              
                        }
                    }
                    else if(eaters.isCursorAtArea(e.getX(), e.getY())) { 
                        if (!eaters.activateEntity(e.getX(), e.getY())) {
                            eaters.activatePanel();                            
                        }                        
                    } 
                    else {
                        neutrals.activateEntity(e.getX(), e.getY());                       
                    }
                }
            }
        });     
        eaters.activatePanel();        
        //eaters.addEntity(MagicEntity.HEADER_MAG);      
        eaters.addEntity(MagicEntity.MAG); 
        eaters.addEntity(MagicEntity.MAGIC_ENTITY); 
        
        orden.activatePanel();
        //orden.addEntity(MagicEntity.HEADER_MAG);      
        //orden.addEntity(MagicEntity.MAG); 
        //orden.addEntity(MagicEntity.MAG); 
        orden.addEntity(MagicEntity.MAGIC_ENTITY); 
        
        orden.deactivatePanel();
        eaters.deactivatePanel();
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);        
        
        System.out.println(neutrals.members.size());
    }   
    
    private void battle() {
        int ordenSize = orden.members.size();
        int eatersSize = eaters.members.size();
        int ordenMags = orden.magsCount;
        int ordenStudents = orden.studentsCount;
        int ordenAnimals = orden.entitiesCount;
        int eatersMags = eaters.magsCount;
        int eatersStudents = eaters.studentsCount;
        int eatersAnimals = eaters.entitiesCount;        
        
        ArrayList<MagicEntity> ordenMembers;
        ArrayList<MagicEntity> eatersMembers;
        
        removeMagicEntities(orden.getMembersByRang(MagicEntity.HEADER_MAG), eaters.getMembersByRang(MagicEntity.HEADER_MAG));
        removeMagicEntities(orden.getMembersByRang(MagicEntity.MAG), eaters.getMembersByRang(MagicEntity.MAG));
        removeMagicEntities(orden.getMembersByRang(MagicEntity.MAGIC_ENTITY), eaters.getMembersByRang(MagicEntity.MAGIC_ENTITY));
       
        ordenSize -= orden.members.size();
        eatersSize -= eaters.members.size();
        
        String message1 = "В Ордені Фенікса було вбито " + Integer.toString(ordenSize) +
                "істот. З них " + Integer.toString(ordenMags - orden.magsCount) + "магів, " +
                Integer.toString(ordenStudents - orden.studentsCount) + "студентів, " +
                Integer.toString(ordenAnimals - orden.entitiesCount) + "магічни створінь." +
                " В Пожирателів було вбито " + Integer.toString(eatersSize) +
                " істот. З них " + Integer.toString(eatersMags - eaters.magsCount) + "магів, " + 
                Integer.toString(eatersStudents - eaters.studentsCount) + "студентів, " +
                Integer.toString(eatersAnimals - eaters.entitiesCount) + "магічни створінь.";
        
        JDialog dialog = new JDialog(this);
        dialog.add(new JLabel(message1));
        dialog.setSize(300, 300);
        dialog.setVisible(true);
    }
      
    
    private void removeMagicEntities(ArrayList<MagicEntity> ordenMembers, ArrayList<MagicEntity> eatersMembers) {
        int oSize = ordenMembers.size();
        int eSize = eatersMembers.size();
        
        if(oSize != 0 && eSize != 0) {        
            int removesNumbers = Math.abs(oSize - eSize);

            activateEntitiesToBattle(orden, ordenMembers);
            activateEntitiesToBattle(eaters, eatersMembers);

            if(oSize > eSize){
                System.out.println("Size before removing: " + ordenMembers.size());
                removeFromArray(ordenMembers, removesNumbers);
                System.out.println("Size after removing: " + ordenMembers.size());
                deactivateEntities(orden, ordenMembers);
            }
            else if(oSize < eSize){
                removeFromArray(eatersMembers, removesNumbers);
                deactivateEntities(eaters, eatersMembers);
            }

            orden.removeAllActiveEntity();
            eaters.removeAllActiveEntity();

            orden.deactivatePanel();
            eaters.deactivatePanel();
        }
    }
    
    private void activateEntitiesToBattle(MagicOrganisation org, ArrayList<MagicEntity> entities) {
        org.activatePanel();
        
        System.out.println("Activated " + entities.size() + " entities");
        
        for(MagicEntity e : entities) {
            e.getEntityPanel().changeBorderColor(true);
        }
    }
    
    private void deactivateEntities(MagicOrganisation org, ArrayList<MagicEntity> entities) {
        org.activatePanel();
        
        for(MagicEntity e : entities) {
            e.getEntityPanel().changeBorderColor(false);
        }
    }
    
    private void removeFromArray(ArrayList<MagicEntity> list, int number) {
        for(int i = 0; i < number; i++) {
            list.remove(i);
        }
    }
    
    private void changePanelsSpeed() {
        checkSpeed(orden);
        checkSpeed(eaters);
        checkSpeed(neutrals);
    }
    
    private void checkSpeed (MagicOrganisation org) {
        org.speed += MagicOrganisation.SPEED_DEFAULT;
        
        if(org.speed > MagicOrganisation.SPEED_DEFAULT * 10) {
            org.speed = MagicOrganisation.SPEED_DEFAULT;
        }
    }
    
    private void initTimer() {
        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(enabledAutoMove) {
                    orden.autoMoveActivePanels();
                    eaters.autoMoveActivePanels();
                    neutrals.autoMoveActivePanels();

                    repaint();
                }                
            }
        });
        
        timer.start();
    }
    
    public final void showSaver() {
        new ElementsSaver(this).setVisible(true);
    }
    
    private void createPopupMenu() {
        jPopupMenu1.setName("Зміна організації");
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed");
                
                String itemText = ((JMenuItem) e.getSource()).getText();
                switch (itemText) {
                    case EATERS_PM_ITEM_LABEL:
                        orden.changeMOOfActivePanels(MagicEntity.THE_DEATH_EATER);
                        eaters.changeMOOfActivePanels(MagicEntity.THE_DEATH_EATER);
                        break;
                    case ORDEN_PM_ITEM_LABEL:
                        orden.changeMOOfActivePanels(MagicEntity.THE_ORDER_MEMBER);
                        eaters.changeMOOfActivePanels(MagicEntity.THE_ORDER_MEMBER);
                        break;
                    case NEUTRAL_PM_ITEM_LABEL:
                        orden.changeMOOfActivePanels(MagicEntity.NEUTRAL_PERSON);
                        eaters.changeMOOfActivePanels(MagicEntity.NEUTRAL_PERSON);
                        break;                        
                }
                jPopupMenu1.setVisible(false);
            }
          };  
        
        JMenuItem m = new JMenuItem(NEUTRAL_PM_ITEM_LABEL);
        m.addActionListener(al);
        jPopupMenu1.add(m);
        m = new JMenuItem(EATERS_PM_ITEM_LABEL);
        m.addActionListener(al);
        jPopupMenu1.add(m); 
        m = new JMenuItem(ORDEN_PM_ITEM_LABEL);
        m.addActionListener(al);
        jPopupMenu1.add(m);        
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println("repaint");
        
        super.paintComponents(g);
        orden.draw(g);
        eaters.draw(g);  
        neutrals.draw(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    private static final String EATERS_PM_ITEM_LABEL = "Смертежер";
    private static final String ORDEN_PM_ITEM_LABEL = "Орденовець";
    private static final String NEUTRAL_PM_ITEM_LABEL = "Нейтральний";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
