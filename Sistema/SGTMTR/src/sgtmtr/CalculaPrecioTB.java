/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgtmtr;

import static sgtmtr.ComprobanteVta.tbvprod;
import static sgtmtr.ComprobanteVta.txtmtotal;
import static sgtmtr.PagoReparaciones.tbrep;
//import static sgtmtr.SeleccionDesperfecto.txtcont;
import static sgtmtr.diagnostico.tbdiag;
import static sgtmtr.diagnostico.txtve;


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

    public void calcularRep() {
        String prerep;
        String can;
        int subtotal = 0;
        int precio;
        int cantidad;
        int stotal = 0;
        for (int i = 0; i < tbdiag.getRowCount(); i++) {
            can = tbdiag.getValueAt(i, 2).toString();
            prerep = tbdiag.getValueAt(i, 3).toString();
            cantidad = Integer.parseInt(can);
            precio = Integer.parseInt(prerep);
            stotal = precio * cantidad;
            subtotal = subtotal + stotal;
            tbdiag.setValueAt((stotal), i, 4);
            String Estado = "En Taller";
            diagnostico.tbdiag.setValueAt(Estado, i, 5);
        }
        txtve.setText("" + (subtotal));
    }
    
    public void calcularPrecioRep() {
        String pre;
        String can;
        int subtotal = 0;
        int precio;
        int cantidad;
        int stotal = 0;
        for (int i = 0; i < tbrep.getRowCount(); i++) {
            can = tbrep.getValueAt(i, 3).toString();
            pre = tbrep.getValueAt(i, 4).toString();
            precio = Integer.parseInt(pre);
            cantidad = Integer.parseInt(can);
            stotal = precio * cantidad;
            subtotal = subtotal + stotal;
            //tbrep.setValueAt((stotal), i, 4);
        }
        PagoReparaciones.txtmtotal.setText("" + (subtotal));
    }
}
