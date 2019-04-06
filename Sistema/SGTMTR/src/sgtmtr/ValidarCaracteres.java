package sgtmtr;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ValidarCaracteres {

    Character s;

    public void soloLetras(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isLetter(s) && s != KeyEvent.VK_SPACE) {
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
        if (!Character.isLetter(s) && s != 46) {
            evt.consume();
        }
    }

    public void mail(KeyEvent evt) {
        s = evt.getKeyChar();
        /* punto (46), @(64), _(95), -(45) */
        if (!Character.isLetter(s) && s != 46 && s != 64 && s != 95 && s != 45) {
            evt.consume();
        }
    }

    public void soloRUT(KeyEvent evt) {
        s = evt.getKeyChar();
        if (!Character.isDigit(s) && s != 45) {
            evt.consume();
        }
    }

    String soloNumeros(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
