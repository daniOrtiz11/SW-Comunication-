package swComunicacion.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import swComunicacion.Controller;
import swComunicacion.Observer;

import javax.swing.SwingConstants;

import java.awt.Font;

@SuppressWarnings("serial")
public class ToolbarSup extends JToolBar implements Observer{

	private ImageIcon atrasIc;
	private JButton atras;
	private JButton btnHelp;
	private JButton modo;
	public JTextField frec;
	private JButton btnMasfrec;
	private JButton btnMenosfrec;
	private Controller c;
	public ToolbarSup(Controller controlador, final int op, JFrame padre){
		this.c = controlador;
		this.setBackground(new Color(211, 211, 211));
		this.setLayout(new GridLayout(1,4));
		frec = new JTextField("Velocidad: "+c.getVelocidad()+"x");
		frec.setForeground(new Color(0, 0, 0));
		frec.setEditable(false);
		frec.setEnabled(false);
		frec.setFont(new Font("Roboto", Font.PLAIN, 20));
		frec.setDisabledTextColor(Color.BLACK);
		frec.setHorizontalAlignment(SwingConstants.CENTER);
		modo = new JButton("Cambiar de modo");
		modo.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		
		modo.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(c.getModo() == true){
					modo.setBackground(Color.RED);
					c.setModo(false);
				}
				else{
					modo.setBackground(null);
					c.setModo(true);
				}
				c.onCambioModo(c.getModo());
				
			}
		});
		this.add(modo);
		this.add(frec);
		btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Roboto", Font.PLAIN, 20));
		btnHelp.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnHelp.isEnabled()){
				JOptionPane.showMessageDialog(null, "Modo Ni�o: Funciona mediante un simple click. Cuando vea o escuche la opci�n deseada seleccionada en verde se debe hacer click "
						 + "\n\nModo Madre: Se debe pulsar con el rat�n en la opci�n 'Cambiar de modo' del men� superior.Una vez con las opciones en verde se puede empezar a configurar la aplicaci�n."
				         + "\nLa velocidad se puede aumentar o disminuir entre 1, 2, 3 o 4 segundos mediante los botones '+' y '-'."
				         + "\nLos cambios se ver�n reflejados al volver al modo ni�o, basta con volver a pulsar en 'Cambiar de modo'.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
				btnHelp.transferFocus();
			}
			}
		});
		this.btnMasfrec = new JButton("+");
		btnMasfrec.setFont(new Font("Roboto", Font.PLAIN, 20));
		btnMasfrec.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub		
				if(btnMasfrec.isEnabled()){
				c.setVelocidad(1);
				if(c.velocidadOk()){
				c.disminuirFrecuencia();
				c.onCambioFrecuencia(c.getFrecuencia());
				frec.setText("Velocidad: "+c.getVelocidad()+"x");
				}
				else
				c.setVelocidad(-1);
				btnMasfrec.transferFocus();
			}
			}
		});
		this.btnMenosfrec = new JButton("-");
		btnMenosfrec.setFont(new Font("Roboto", Font.PLAIN, 20));
		btnMenosfrec.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub		
				if(btnMenosfrec.isEnabled()){
				c.setVelocidad(-1);
				if(c.velocidadOk()){
				c.aumentarFrecuencia();
				c.onCambioFrecuencia(c.getFrecuencia());
				frec.setText("Velocidad: "+c.getVelocidad()+"x");
				}
				else
				c.setVelocidad(1);
				btnMenosfrec.transferFocus();
			}
			}
		});
		this.add(btnMasfrec);
		this.add(btnMenosfrec);
		this.add(btnHelp);
		
		atras = new JButton("Atr�s");
		atras.setFont(new Font("Roboto", Font.PLAIN, 20));
		this.atrasIc = new ImageIcon("imagenes/atras.png");
		atras.setIcon(atrasIc);
		if(op != 0) atras.setEnabled(true);
		else atras.setEnabled(false);
		atras.addMouseListener(new MouseAdapter(){ 
			public void mouseClicked(MouseEvent e){
				if(atras.isEnabled()){
				c.onCambioVentanaAtras();
				}
			}
			}
		);
		this.add(atras);
		if(c.getModo() == true){
			frec.setEnabled(true);
			this.disabledModo();
			
		}
		else {
			frec.setEnabled(true);
			this.enabledModo();
			
		
		}
	}
	public void setAtras(JButton atras) {
		this.atras = atras;
	}
	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onCambioOpcion(int op) {
		c.removeObserver(this);
	}
	public void onCambioModo(boolean m) {
		// TODO Auto-generated method stub
		if(m == false){
			modo.setText("Cambiar a Modo Ni�o");
			this.frec.setEnabled(true);
		} else {
			modo.setText("Cambiar a Modo Madre");
			this.frec.setEnabled(false);
		}
	}
	public void onCambioFrecuencia(int f) {
		// TODO Auto-generated method stub
		
	}
	public void enabledModo() {
		// TODO Auto-generated method stub
		this.atras.setEnabled(true);
		this.btnHelp.setEnabled(true);
		this.btnMasfrec.setEnabled(true);
		this.btnMenosfrec.setEnabled(true);
		this.atras.setEnabled(true);
	}
	public void disabledModo() {
		// TODO Auto-generated method stub
		this.atras.setEnabled(false);
		this.btnHelp.setEnabled(false);
		this.btnMasfrec.setEnabled(false);
		this.btnMenosfrec.setEnabled(false);
		this.atras.setEnabled(false);
		
	}
	public void mouseNi�o() {
		// TODO Auto-generated method stub
		
	}
	public void atras() {
		// TODO Auto-generated method stub
		
	}
}
