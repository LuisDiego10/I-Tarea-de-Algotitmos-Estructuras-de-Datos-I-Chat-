package chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cliente {

    public static void main(String[] args) {
	ventanaCliente ventana_c = new ventanaCliente();
	ventana_c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
class ventanaCliente extends JFrame {
    public ventanaCliente(){
        this.setTitle("CLIENTE");
        setBounds(600,300,280,350);
        canvasCliente canvasc=new canvasCliente();
        add(canvasc);
        setVisible(true);
        }
}
class canvasCliente extends JPanel{
    public canvasCliente(){
        JLabel etiqueta=new JLabel("DIGITE EL MENSAJE QUE DESEA ENVIAR");
        add(etiqueta);
        areaTexto= new JTextField(25);
        add(areaTexto);
        enviarbtn =new JButton("ENVIAR");
        enviarTexto evento_enviar=new enviarTexto();
        enviarbtn.addActionListener(evento_enviar);
        add(enviarbtn);
        }
    private class enviarTexto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("FUNCIONA EL EVENTO DEL BÓTON");
        }
    }
    private JTextField areaTexto;

    private JButton enviarbtn;
}
