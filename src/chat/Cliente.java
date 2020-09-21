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
        public class enviarMensaje implements ActionListener{
        int puerto=9999;
        public int get_puerto(){
            return puerto;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(areaTexto.getText());
            //Creación del socket y de el flujo de datos
            while (true) {
                try {
                    Socket socket_c = new Socket("127.0.0.1", puerto);
                    System.out.println("El puerto que se está utilizando es el: "+ puerto);
                    DataOutputStream flujo_s =new DataOutputStream(socket_c.getOutputStream());//Creación de flujo de salida
                    flujo_s.writeUTF(areaTexto.getText());//Guarde lo que contiene area de texto
                    flujo_s.close();
                    break;
                }
                catch (UnknownHostException e1){
                    e1.printStackTrace();
                }
                catch (IOException e1){
                    System.out.println("El puerto : "+ puerto +" se encuentra ocupado " + e1.getMessage());
                    puerto-=1;//Modifica el puerto hasta encontrar uno que se encuentre
                }
            }
        }
    }
    private JTextField areaTexto;

    private JButton enviarbtn;
}
