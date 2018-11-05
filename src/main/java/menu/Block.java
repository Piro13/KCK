/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Kaveri
 */
public class Block {
    int type;
    int place;
    Terminal terminal;
    Screen screen;
    TextGraphics tg;
    Random rand = new Random();
    static int empty = 0;
    boolean[] full;
    
    public Block(Terminal m, Screen s, TextGraphics t){
        if(empty==0){
            type = rand.nextInt(8) +1;
            empty++;
        }else{
            type=9;
            empty=0;
        }
        place = -2;
        full = new boolean[5];
        load();
        this.terminal = m;
        this.screen = s;
        this.tg = t;
    }
    
    private void load(){
        switch(type){
            case 1:
                full[0]=true;
                full[1]=false;
                full[2]=false;
                full[3]=false;
                full[4]=true;
                break;
            case 2:
                full[0]=true;
                full[1]=false;
                full[2]=true;
                full[3]=false;
                full[4]=false;
                break;
            case 3:
                full[0]=false;
                full[1]=false;
                full[2]=true;
                full[3]=false;
                full[4]=true;
                break;
            case 4:
                full[0]=false;
                full[1]=true;
                full[2]=true;
                full[3]=false;
                full[4]=false;
                break;
            case 5:
                full[0]=true;
                full[1]=false;
                full[2]=false;
                full[3]=true;
                full[4]=false;
                break;
            case 6:
                full[0]=true;
                full[1]=false;
                full[2]=true;
                full[3]=false;
                full[4]=true;
                break;
            case 7:
                full[0]=false;
                full[1]=true;
                full[2]=false;
                full[3]=false;
                full[4]=false;
                break;
            case 8:
                full[0]=false;
                full[1]=false;
                full[2]=false;
                full[3]=true;
                full[4]=false;
                break;
            default:
                full[0]=false;
                full[1]=false;
                full[2]=false;
                full[3]=false;
                full[4]=false;
                break;
        }
    }
    
    public void show() throws IOException{
        if(place==38){
            place=0;
            if(empty==0){
                type = 9;
                empty++;
            }else{
                type = rand.nextInt(8)+1;
                empty=0;
            }
            load();
        }
        else{
            place+=2;
        }
        
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.setForegroundColor(TextColor.ANSI.GREEN);
        tg.putString(43, place, "###");
        tg.putString(43, place+1, "###");
        tg.putString(61, place, "###");
        tg.putString(61, place+1, "###");
        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
        tg.setForegroundColor(TextColor.ANSI.DEFAULT);

        for(int i=0; i<5; i++){
            if(full[i]==true){
                tg.putString(46+(i*3), place, "@@@");
                tg.putString(46+(i*3), place+1, "@@@");
            }else{
                tg.putString(46+(i*3), place, "   ");
                tg.putString(46+(i*3), place+1, "   ");
            }
        }

        screen.refresh();
    }
}
