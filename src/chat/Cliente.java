package chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import  javax.swing.ImageIcon;

public class Cliente {

    public static void main(String[] args) {
	ventanaCliente ventana_c = new ventanaCliente();
	ventana_c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class ventanaCliente extends JFrame {
    public ventanaCliente(){
        this.setTitle("CLIENTE");
        setBounds(600,300,620,650);
        canvasCliente canvasc=new canvasCliente();
        add(canvasc);
        setVisible(true);
        }
}
class canvasCliente extends JPanel{
    private JTextField areaTexto,nombre,contacto;
    private JTextArea areaMensajes_c;
    private JButton enviarbtn;
    public canvasCliente(){
        ImageIcon usericon =new ImageIcon("/home/diego/Algoritmos y Estructuras de Datos 1/JAVA/Tareas/I-Tarea-de-Algotitmos-Estructuras-de-Datos-I-Chat-/src/chat/usericon.png");
        JLabel etiqueta_u=new JLabel();
        etiqueta_u.setBounds(10,10,20,20);
        etiqueta_u.setIcon(new ImageIcon(usericon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        add(etiqueta_u);
        JLabel etiqueta_n=new JLabel("Nombre: ");
        add(etiqueta_n);
        nombre=new JTextField(15);
        add(nombre);
        JLabel etiqueta_c=new JLabel("Contacto: ");
        add(etiqueta_c);
        contacto=new JTextField(15);
        add(contacto);
        areaMensajes_c=new JTextArea(25,40);
        add(areaMensajes_c);
        areaTexto= new JTextField(30);
        add(areaTexto);
        enviarbtn =new JButton("ENVIAR");
        enviarMensaje evento_enviar=new enviarMensaje();
        enviarbtn.addActionListener(evento_enviar);
        add(enviarbtn);
        }
        public class enviarMensaje implements ActionListener{
        int puerto=10000;
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
                    //Guardar datos de envío en los métodos
                    detalles_s detalles=new detalles_s();
                    detalles.setNombre(nombre.getText());
                    detalles.setContacto(contacto.getText());
                    detalles.setMensaje(areaTexto.getText());
                    System.out.println("El puerto que se está utilizando es el: "+ puerto);
                    ObjectOutputStream paquete_s=new ObjectOutputStream(socket_c.getOutputStream());//Creación de flujo de salida
                    paquete_s.writeObject(detalles);//Guarde lo que contiene el paquete de salida
                    socket_c.close();
                    break;
                }
                catch (UnknownHostException e1){
                    e1.printStackTrace();
                }
                catch (IOException e1){
                    System.out.println("El puerto : "+ puerto +" se encuentra ocupado " + e1.getMessage());
                    puerto-=1;//Modifica el puerto hasta encontrar uno que se encuentre disponilble
                }
            }
        }
    }
}
//Clase para enviar datos de envío
class detalles_s implements Serializable {
    private String nombre, contacto, mensaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
