/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static sgtmtr.ComprobanteVta.tbvprod;
import static sgtmtr.ComprobanteVta.txtmtotal;

/**
 *
 * @author ZuluCorp
 */
public class CalculaPrecioTB {
    public void calcular() {
        String pre;
        String can;
        int subtotal = 0;
        int precio;
        int cantidad;
        int stotal = 0;
        //int red=(int) stotal;
            /*can=Integer.parseInt(cant);
         imp=pre*can;
         dato[4]=Float.toString(imp);*/

        for (int i = 0; i < tbvprod.getRowCount(); i++) {
            pre = tbvprod.getValueAt(i, 2).toString();
            can = tbvprod.getValueAt(i, 3).toString();
            precio = Integer.parseInt(pre);
            cantidad = Integer.parseInt(can);
            stotal = precio * cantidad;
            subtotal = subtotal + stotal;
            tbvprod.setValueAt((stotal), i, 4);
        }
        txtmtotal.setText("" + (subtotal));
    }

}
