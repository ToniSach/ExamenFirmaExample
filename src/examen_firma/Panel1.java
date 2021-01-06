/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_firma;

/**
 *
 * @author tonis
 */

import com.itextpdf.text.DocumentException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.border.Border;

public class Panel1 extends JPanel {
    JButton a, Crear, Limpiar, CLlaves, SLlavePriv;
    JComboBox options;
    JLabel titulo, Nombrel, Edadl, Mensajel;
    JTextField Nombre, Edad, Firma;
    JTextArea Mensaje;
    File llavePriv;
    /*
    JButton DEScifrar, DESdescifrar, AEScifrar, AESdescifrar;
    JRadioButton DESoption, DESoption2;
    ButtonGroup DESoptions;
    JComboBox DESAES;
    JLabel DEStexto, DESclave, AEStexto, AESclave, titulo;
    JTextField DESClave, AESClave;
    JTextArea DEStextoE, AEStextoE;*/

    public Panel1() {
        componentes();
        this.setBackground(Color.decode("#000000"));
        setLayout(null);
    }

    public void componentes() {
        titulo = new JLabel("Examen Firma");
        titulo.setBounds(290, 10, 220, 30);
        titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
        titulo.setForeground(Color.decode("#ffffff"));
        add(titulo);
        
        options = new JComboBox();
        options.addItem("Seleccionar...");
        options.addItem("Formulario & Firma");
        options.addItem("Comprobación");
        options.setBounds(275, 60, 250, 25);
        options.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        //DESAES.setForeground(Color.decode("#ffffff"));
        options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (options.getSelectedItem().equals("Formulario & Firma")) {
                    Formulario0();
                } else if (options.getSelectedItem().equals("Comprobación")) {
                    Formulario1();
                } else if (options.getSelectedItem().equals("Seleccionar...")) {
                    Formulario0();
                } else {
                    System.out.println("Error");
                }
            }
        });
        add(options);
        Formulario();
    }
    
    public void Formulario(){
        
        Nombrel = new JLabel("Nombre:");
        //Nombrel.setBounds(200, 135, 60, 15);
        Nombrel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        Nombrel.setForeground(Color.decode("#ffffff"));
        add(Nombrel);
        
        Nombre = new JTextField();
        //Nombre.setBounds(280, 125, 250, 30);
        Nombre.setFont(new Font("Arial", Font.ITALIC, 15));
        Nombre.setBackground(Color.decode("#C5CAC9"));
        add(Nombre);
        
        Edadl = new JLabel("Edad:");
        //Edadl.setBounds(200, 175, 60, 15);
        Edadl.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        Edadl.setForeground(Color.decode("#ffffff"));
        add(Edadl);
        
        Edad = new JTextField();
        //Edad.setBounds(280, 165, 250, 30);
        Edad.setFont(new Font("Arial", Font.ITALIC, 15));
        Edad.setBackground(Color.decode("#C5CAC9"));
        add(Edad);
        
        Mensajel = new JLabel("Mensaje:");
        //Mensajel.setBounds(200, 215, 60, 15);
        Mensajel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        Mensajel.setForeground(Color.decode("#ffffff"));
        add(Mensajel);
        
        Mensaje = new JTextArea();
        //Mensaje.setBounds(280, 205, 320, 200);
        Mensaje.setFont(new Font("Arial", Font.ITALIC, 15));
        Mensaje.setBackground(Color.decode("#C5CAC9"));
        Mensaje.setForeground(Color.decode("#2D3A54"));
        Mensaje.setLineWrap(true);
        Mensaje.setWrapStyleWord(true);
        Mensaje.setEditable(true);
        add(Mensaje);
        
        Crear = new JButton("Crear");
        Crear.setBackground(Color.decode("#161569"));
        //Crear.setBounds(520, 435, 120, 25);
        Crear.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        Crear.setForeground(Color.decode("#D3E7F3"));
        Crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //setLlavePriv();
                PDF crear = new PDF();
                String nombre = Nombre.getText();
                String edad = Edad.getText();
                String mensaje = Mensaje.getText();
                if (nombre.isEmpty() || edad.isEmpty() || mensaje.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, rellene los campos");
                } else {
                    try {
                        crear.generar(nombre, edad, mensaje);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
            }
        });
        add(Crear);

        Limpiar = new JButton("Limpiar");
        Limpiar.setBackground(Color.decode("#161569"));
        //Limpiar.setBounds(660, 435, 120, 25);
        Limpiar.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        Limpiar.setForeground(Color.decode("#D3E7F3"));
        Limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Nombre.setText("");
                Edad.setText("");
                Mensaje.setText("");

            }
        });
        add(Limpiar);
    }
    
    public void Formulario0(){
        
        Nombrel.setBounds(200, 135, 60, 15);
        Nombre.setBounds(280, 125, 250, 30);
        Edadl.setBounds(200, 175, 60, 15);
        Edad.setBounds(280, 165, 250, 30);
        Mensajel.setBounds(200, 215, 70, 15);
        Mensaje.setBounds(280, 205, 320, 200);
        Crear.setBounds(520, 435, 120, 25);
        Limpiar.setBounds(660, 435, 120, 25);
        /**/
    }
    
    public void Formulario1(){
        
        Nombrel.setBounds(1000, 1000, 60, 15);
        Nombre.setBounds(1000, 1000, 250, 30);
        Edadl.setBounds(1000, 1000, 60, 15);
        Edad.setBounds(1000, 1000, 250, 30);
        Mensajel.setBounds(1000, 1000, 70, 15);
        Mensaje.setBounds(1000, 1000, 320, 200);
        Crear.setBounds(1000, 1000, 120, 25);
        Limpiar.setBounds(1000, 1000, 120, 25);
        /**/
    }
    
    public void Firma(){
        
    }
    
    public void Firma0(){
        
    }
    
    public void Firma1(){
        
    }
    
    private void setLlavePriv() {
        this.llavePriv = archivo();
    }
    
    private File archivo() {
        JFileChooser jf = new JFileChooser();
        int seleccion = jf.showOpenDialog(this);
        if(seleccion==JFileChooser.APPROVE_OPTION){
            //Seleccionamos el fichero
            File fichero = jf.getSelectedFile();
            return fichero;
        }
        return null;
    }
    
    private Image fondo;
    /*private Image rndm;*/
    
    
    @Override
    public void paint(Graphics g) {
        fondo = new ImageIcon(getClass().getResource("/Img/efondo01.jpg")).getImage();
        g.drawImage(fondo, 0, 0, 800, 500, this);
        setOpaque(false);
        super.paint(g);

        /*rndm = new ImageIcon(getClass().getResource("/Img/imgrndm.jpg")).getImage();
        g.drawImage(rndm, 440, 170, 340, 220, this);
        setOpaque(false);
        super.paint(g);*/

    }
}
