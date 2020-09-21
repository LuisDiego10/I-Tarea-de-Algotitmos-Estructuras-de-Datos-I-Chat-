package chat;
import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

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
        enviarMensaje evento_enviar=new enviarMensaje();
        enviarbtn.addActionListener(evento_enviar);
        add(enviarbtn);
        }
    private class enviarMensaje implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(areaTexto.getText());
            int puerto=6942;

            while (true) {
                try {
                    Socket socket_enviar = new Socket("127.0.0.1", puerto);
                    System.out.println("El puerto que se está utilizando es el: "+ puerto);
                    DataOutputStream flujo_s =new DataOutputStream(socket_enviar.getOutputStream());
                    flujo_s.writeUTF(areaTexto.getText());
                    flujo_s.close();
                    break;
                }
                catch (UnknownHostException e1){
                    e1.printStackTrace();
                }
                catch (IOException e1){
                    System.out.println("El puerto : "+ puerto +" se encuentra ocupado " + e1.getMessage());
                    puerto-=1;
                }
            }
        }
    }
    private JTextField areaTexto;

    private JButton enviarbtn;
}
