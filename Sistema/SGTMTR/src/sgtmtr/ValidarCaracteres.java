package sgtmtr;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ValidarCaracteres {

    Character s;
    
    public void soloLetras(KeyEvent evt){
        s = evt.getKeyChar();
        if(!Character.isLetter(s) && s != KeyEvent.VK_SPACE){
            evt.consume();
        }
    }
    public void soloLetrasyNumeros(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isLetter(s) && !Character.isDigit(s)) {
            evt.consume();
        }
    }
    public void LNE(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isLetter(s) && !Character.isDigit(s) && s != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }
    public void soloNumeros(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isDigit(s)) {
            evt.consume();
        }
    } 
    public void LoginUser(KeyEvent evt) {
        s = evt.getKeyChar();
        if(!Character.isLetter(s) && s != 46){
            evt.consume();
        }
    } 
    public void mail(KeyEvent evt) {
        s = evt.getKeyChar();
        /* punto (46), @(64), _(95), -(45) */
        if(!Character.isLetter(s) && s != 46 && s != 64 && s != 95 && s != 45){
            evt.consume();
        }
    } 
    public void soloRUT(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isDigit(s) && s != 45) {
            evt.consume();
        }
    } 
}
/*codigo antiguo usado por mi xD
    
    //Impedir teclear algunos caraceres para no inyectar query
        pfccn.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter == 32)) || (caracter == '@') || (caracter == ';') || (caracter == '|') || (caracter == '&') || (caracter == 39)) {
                    e.consume();
                }
            }
        });
    //Impedir teclear algunos caraceres para no inyectar query
        pfcn.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter == 32)) || (caracter == '@') || (caracter == ';') || (caracter == '|') || (caracter == '&') || (caracter == 39)) {
                    e.consume();
                }
            }
        });
    //Impedir teclear algunos caraceres para no inyectar query
        pfca.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter == 32)) || (caracter == '@') || (caracter == ';') || (caracter == '|') || (caracter == '&') || (caracter == 39)) {
                    e.consume();
                }
            }
        });
    //Impedir teclear algunos caraceres para no inyectar query
        pfccn.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if ((caracter == 32)
                    || (caracter == '@')
                    || (caracter == ';')
                    || (caracter == '|')
                    || (caracter == '&')
                    || (caracter == '=')
                    || (caracter == '*')
                    || (caracter == 39)
                    || (caracter == 13)) {
                    e.consume();
                }
            }
        });
        // SOLO NUMEROS
        txtcd.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                //capturar el caracter digitado
                char caracter = e.getKeyChar();
                if (caracter < '0' || caracter > '9') {
                    e.consume();//ignora el caracter digitado
                }
            }
        });
    */