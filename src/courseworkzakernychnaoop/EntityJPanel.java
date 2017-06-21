/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Johny
 */
public class EntityJPanel extends javax.swing.JPanel{
    public final static int WIDTH  = 150;
    public final static int HEIGHT = 255;
    
    private boolean isActive;
    public int organizationType;
    
    private int coordX = 50;
    private int coordY = 50;    
    
    public EntityJPanel(String fileName, String name, double magicPower, int level, int organizationType) {
        initComponents();
        
        isActive = false;
        this.organizationType = organizationType;
        
        jLabelName.setText(name);
        jLabelMO.setText(MagicEntity.getEntityTypeText(organizationType));        
        
        loadImage(fileName); 
        
        createProgressBars();
        
        jProgressBarLevel.setValue(level);
        jProgressBarMagicPower.setValue((int) magicPower);                       
    }
    
    private void createProgressBars() {
        jProgressBarLevel.setMinimum(0);
        jProgressBarLevel.setMaximum(MagicEntity.HEADER_MAG);
        jProgressBarLevel.setStringPainted(true);
        jProgressBarMagicPower.setStringPainted(true);
    }
    
    public void changeMO(int organisationType) {
        this.organizationType = organisationType;
        
        jLabelMO.setText(MagicEntity.getEntityTypeText(organizationType));  
        changeBorderColor(isActive);    
        
        System.out.println("Зміна організації відобразилась на вмісту панелі");
    }
    
    public void changeBorderColor(boolean isActive) {
        this.isActive = isActive;        
        
        int r = 0;
        int g = 0;
        int b = 0;
        
        if(isActive){
            switch(organizationType) {
                case MagicEntity.NEUTRAL_PERSON:
                    r = 255;
                    g = 255;
                    break;
                case MagicEntity.THE_DEATH_EATER: 
                    r = 255;
                    break;
                case MagicEntity.THE_ORDER_MEMBER:
                    b = 255;
                    break;
            }
        }
        
        setBorder(new LineBorder(new java.awt.Color(r, g, b), 5, true));
    }
    
    public void moveAtCoords(int x, int y) {
        if ((coordX!=x) || (coordY!=y)) {  
            coordX=x;
            coordY=y;
            setBounds(coordX, coordY, WIDTH, HEIGHT);         
        } 
    }
    
    @Override
    public int getX (){ return coordX; } 
    @Override
    public int getY (){ return coordY; }
    
    public void movePanel(int x, int y, int opX, int opY) {
        coordX += x;
        coordY += y;
        
        if(! (coordX + WIDTH / 2 > 0 && coordX + WIDTH / 2 < MainWindow.WIDTH) ) {
            System.out.println("Error: x = "+ coordX);
            coordX -= x;
        }
        if(! (coordY + HEIGHT / 2 > 0 && coordY + HEIGHT / 2 < MainWindow.HEIGHT) ) {
            System.out.println("Error: y = "+ coordY);
            coordY -= y;
        }
        //System.out.println("Рух панелі істоти " + coordX + ", " + coordY);
        setBounds(coordX, coordY, WIDTH, HEIGHT);   
        //System.out.println("Рух панелі істоти " + coordX);
        /*if(organizationType != MagicEntity.NEUTRAL_PERSON) {
            if(coordX < opX){
                coordX = opX;
            } 
            else if(coordX > opX + MagicOrganisation.MO_PANEL_WIDTH - WIDTH){
                coordX = opX + MagicOrganisation.MO_PANEL_WIDTH - WIDTH;
            }

            if(coordY < opY){ 
                coordY = opY;
            } 
            else if(coordY > opY + MagicOrganisation.MO_PANEL_HEIGHT - HEIGHT){
                coordY = opY + MagicOrganisation.MO_PANEL_HEIGHT - HEIGHT;
            }

            setBounds(coordX, coordY, WIDTH, HEIGHT);    
        }
        else {
            if (MainWindow.orden.isPanelCoordsInMOPanel(coordX, coordY) || 
                MainWindow.eaters.isPanelCoordsInMOPanel(coordX, coordY)) {
                coordX -= x;
                coordY -= y;
            }
        }*/
    }   
    
    /*@Override
    public Dimension getPreferredSize() {
        return new Dimension(150,245);
    } */      
    
    public static int showOkCancelDialog(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
            "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
    
    public boolean isCursorInPanelArea(int x, int y) {
        return (x >= coordX && x <= (coordX + WIDTH)) && (y >= coordY && y <= (coordY + HEIGHT));
    }
        
    public boolean isActivePanel() {
        return isActive;
    }
    
    public void setPosition(int x, int y) {
        coordX = x;
        coordY = y;
        
        setBounds(coordX, coordY, WIDTH, HEIGHT);
    }
    
    private void loadImage(String fileName) {
        jLabelImage.setIcon(new ImageIcon(fileName));        
    }  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelImage = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelMO = new javax.swing.JLabel();
        jProgressBarMagicPower = new javax.swing.JProgressBar();
        jProgressBarLevel = new javax.swing.JProgressBar();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        setMaximumSize(new java.awt.Dimension(173, 245));
        setMinimumSize(null);

        jLabelImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImage.setToolTipText("");
        jLabelImage.setAlignmentX(0.5F);

        jLabelName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabelName.setText("Some entity");
        jLabelName.setToolTipText("Ім'я");
        jLabelName.setAlignmentX(0.5F);

        jLabelMO.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabelMO.setText("Neutral");
        jLabelMO.setToolTipText("Належність до магічної організації");
        jLabelMO.setAlignmentX(0.5F);

        jProgressBarMagicPower.setForeground(new java.awt.Color(153, 153, 153));

        jProgressBarLevel.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarMagicPower, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jLabelMO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarMagicPower, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelImage.getAccessibleContext().setAccessibleName("");
        jLabelName.getAccessibleContext().setAccessibleName("");
        jLabelName.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JLabel jLabelMO;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JProgressBar jProgressBarLevel;
    private javax.swing.JProgressBar jProgressBarMagicPower;
    // End of variables declaration//GEN-END:variables
}
