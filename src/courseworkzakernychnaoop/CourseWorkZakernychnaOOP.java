/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkzakernychnaoop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Lenka
 */
public class CourseWorkZakernychnaOOP {
    /*public static void main(String[] args) {
        //Lab1();
        Lab2();
    }
    
    private static void createArray() {
        entityArray = new MagicEntity[10];
        
        for(int i = 0; i < entityArray.length; i++){
            entityArray[i] = new MagicEntity();
        }
    }
    
    private static Object[] enterParameters(String message) {
        System.out.println(message);
        
        //Scanner in = new Scanner(System.in);
        
        String str = in.nextLine();
        
        Object[] params = str.split("\\s");
        
        //in.close();
        
        return params; 
    }    
    
    private static void Lab1(){
        System.out.println("Початок роботи");
        
        Object[] param = enterParameters("Введіть параметри істоти через пробіл:");
        
        staticEntity = new MagicEntity((String) param[0], 
                                        Double.valueOf((String)param[1]), 
                                        Integer.valueOf((String)param[2]), 
                                        Integer.valueOf((String)param[3]));
        
        MagicEntity globalEntity = new MagicEntity(staticEntity);
        
        createArray();
        
        System.out.println("Static entity:\n" + staticEntity.toString());
        System.out.println("Global entity:\n" + globalEntity.toString());
        
        System.out.println("Кінець роботи");
        
        in.close();
    }            
    
    private static void Lab2() {
        Object[] sizeParam = enterParameters("Введіть розмір масиву:"); 
                    
        try {
            createDynamicArray(Integer.parseInt((String)sizeParam[0]));
                
            boolean stop = false;

            String message = "\n\nОберіть пункт меню та натисніть Enter:\n 1 - Вивести всі елементи масиву;"
                    + "\n 2 - Переглянути і-ий елемент динамічного масиву;"
                    + "\n 3 - Додати на і-ту позицію в масив новий об'єкт;"
                    + "\n 4 - Видалити і-ий елемент масиву;"
                    + "\n 5 - Відсортувати масив за: 1 - ім'ям, 2 - магічним рівнем;"
                    + "\n 6 - Вихід.";

            while (!stop) {
                try {
                    Object[] param = enterParameters(message); 
                
                    int menuItem = Integer.parseInt((String)param[0]); 

                    switch(menuItem){
                        case 1: 
                            System.out.println("1 - Динамічний масив:");
                            
                            printDynamicArray();
                            break;
                        case 2:
                            System.out.println("2 - Переглянути " + (String)param[1] + "-ий елемент:");
                            
                            System.out.println(dynamicArray.get(Integer.parseInt((String)param[1])).toString());
                            break;                        
                        case 3:
                            System.out.println("3 - Додати на " + (String)param[1] + "-ту позицію елемент:");
                            
                            dynamicArray.add(Integer.parseInt((String)param[1]), new MagicEntity("Albus", 99.0, 10, 1));
                            
                            printDynamicArray();
                            break;
                        case 4: 
                            System.out.println("4 - Видалити " + (String)param[1] + "-ий елемент:");
                            
                            dynamicArray.remove(Integer.parseInt((String)param[1]));
                            
                            printDynamicArray();
                            break;
                        case 5: 
                            if(Integer.parseInt((String)param[1]) == 1) {
                                dynamicArray.sort((MagicEntity o1, MagicEntity o2) -> {
                                    String name1 = o1.getName();
                                    String name2 = o2.getName();
                                    
                                    return name1.compareTo(name2);                                    
                                });
                            }
                            else {
                                dynamicArray.sort((MagicEntity o1, MagicEntity o2) -> {
                                    int level1 = o1.getMagicLevel();
                                    int level2 = o2.getMagicLevel();
                                    
                                    if(level1 > level2) return 1;
                                    else if(level1 < level2) return -1;
                                    else return 0;
                                });
                            }
                            
                            printDynamicArray();
                            
                            break;
                        case 6: 
                            stop = true;
                            
                            System.out.println("Загальна кількість створених елементів за час роботи програми: " + MagicEntity.getEntityCounter() + "\nВихід.");
                            
                            in.close();
                            break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Помилка меню " + e.getMessage());
                }
            } 
        }
        catch(Exception e) {
            System.out.println("Помилка при створенні динамічного масиву");
        }
    }
    
    private static void printDynamicArray() {
        for (MagicEntity me : dynamicArray) {
            System.out.println(me.toString());
        }
    }
    
    private static void createDynamicArray(int size) {
        dynamicArray = new ArrayList<>();
        
        for(int i = 0; i < size; i++) {            
            dynamicArray.add(new MagicEntity());
        }
    }
    
    private static void Lab3() {
        
    }
    
    private static void Lab4() {
        
    }
    
    private static void Lab5() {
        
    }
    
    private static Scanner in = new Scanner(System.in);
    
    private static ArrayList<MagicEntity> dynamicArray;
    private static MagicEntity staticEntity;
    private static MagicEntity[] entityArray;*/
}
