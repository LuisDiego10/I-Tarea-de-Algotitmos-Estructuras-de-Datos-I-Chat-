package chat;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
<<<<<<< HEAD
<<<<<<< HEAD
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
=======
>>>>>>> parent of f5e59d8... Cambio al flujo de salida y Serialización
=======
>>>>>>> parent of f5e59d8... Cambio al flujo de salida y Serialización
import java.net.*;
<<<<<<< HEAD
import java.net.ServerSocket;
import java.net.Socket;
import  javax.swing.ImageIcon;
/**
 * Clase Cliente
 * Instancia un objeto de la clase ventanaCliente y permite cerrar la ventana al pulsar X
 * @author diego
 * version 1.0
 */
=======
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía

public class Cliente {

    public static void main(String[] args) {
        ventanaCliente ventana_c = new ventanaCliente();
        ventana_c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
/**
 * Clase ventanaCliente
 * Le da tamaño a la ventana Cliente, hereda JFrame necesario para dar formato a la ventana además crea una instancia de la clase canvasCliente
 * @author diego
 * version 1.0
 */
class ventanaCliente extends JFrame {
    public ventanaCliente(){
        this.setTitle("CLIENTE");
        setBounds(600,300,280,350);
        canvasCliente canvasc=new canvasCliente();
        add(canvasc);
        //this.setIconImage(new ImageIcon(getClass().getResource("icon ventana.jpg")).getImage());
        setVisible(true);
    }
}
<<<<<<< HEAD
/**
 * Clase canvasCliente
 * Esta clase  hereda JPanel y contiene el método canvasCliente que da formato a lo que se muestra en pantalla, además
 * implementa Runnable para mantener a la escucha el Socket
 * @author diego
 * version 1.0
 */
class canvasCliente extends JPanel implements Runnable{
    /**
     * Método necesario para dar formato a lo que ven los usuarios,contiene etiquetas, imagenes, botones, textos y la
     * creación de un Thread necesario para el Runnable
     */
    public canvasCliente(){
        ImageIcon usericon =new ImageIcon("/home/diego/Algoritmos y Estructuras de Datos 1/JAVA/Tareas/I-Tarea-de-Algotitmos-Estructuras-de-Datos-I-Chat-/src/chat/usericon.png");
        JLabel etiqueta_u=new JLabel();
        etiqueta_u.setBounds(10,10,20,20);
        etiqueta_u.setIcon(new ImageIcon(usericon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        add(etiqueta_u);
        JLabel etiqueta_n=new JLabel("Nombre: ");
        add(etiqueta_n);
        String nombre_u=JOptionPane.showInputDialog("Ingrese su nombre: ");
        nombre=new JLabel();
        nombre.setText(nombre_u);
        add(nombre);
        ImageIcon contacticon =new ImageIcon("/home/diego/Algoritmos y Estructuras de Datos 1/JAVA/Tareas/I-Tarea-de-Algotitmos-Estructuras-de-Datos-I-Chat-/src/chat/contacticon.png");
        JLabel etiqueta_ce=new JLabel();
        etiqueta_ce.setBounds(10,10,20,20);
        etiqueta_ce.setIcon(new ImageIcon(contacticon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        add(etiqueta_ce);
        JLabel etiqueta_c=new JLabel("Contacto: ");
        add(etiqueta_c);
        contacto=new JTextField(15);
        add(contacto);
        areaMensajes_c=new JTextArea(25,40);
        add(areaMensajes_c);
        areaTexto= new JTextField(30);
=======
class canvasCliente extends JPanel{
    public canvasCliente(){
        JLabel etiqueta=new JLabel("DIGITE EL MENSAJE QUE DESEA ENVIAR");
        add(etiqueta);
        areaTexto= new JTextField(25);
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
        add(areaTexto);
        enviarbtn =new JButton("ENVIAR");
        enviarMensaje evento_enviar=new enviarMensaje();
        enviarbtn.addActionListener(evento_enviar);
        add(enviarbtn);
        Thread hilo_s=new Thread(this);
        hilo_s.start();
    }

    /**
     * Método público de la clase Runnable, crea un socket y permite mantener a la escucha constantemente,
     * además da formato a lo que se muestra en la caja de mensajes (areaMensajes_c)
     */
    @Override
    public void run() {
        int puerto2 = 9999;
        ServerSocket servidor_cliente;
        try {
            try {
                servidor_cliente = new ServerSocket(puerto2);
            } catch (Exception e) {
                puerto2 =9090;
                servidor_cliente=new ServerSocket(puerto2);
            }
            detalles_s paqueteRecibido;
            while (true) {
                Socket cliente = servidor_cliente.accept();
                ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (detalles_s) flujoentrada.readObject();
                areaMensajes_c.append("\n" + paqueteRecibido.getNombre() + ": " + paqueteRecibido.getMensaje() + "Este mensaje ha sido enviado para: " + paqueteRecibido.getContacto());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
<<<<<<< HEAD
    }

    /**
     *Clase enviarMensaje
     *Implementa ActionListener para ejecutar eventos, además esta clase contiene otra clase llamada actionPerformed,
     * misma contiene los Sockets de los clientesm alamacena datos, escribe en los textos y muestra las exepciones
     *@author diego
     *version 1.0
     */
    public class enviarMensaje implements ActionListener{
        int puerto=9999;
=======
        public class enviarMensaje implements ActionListener{
        int puerto=9999;
        public int get_puerto(){
            return puerto;
        }
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
        @Override
        public void actionPerformed(ActionEvent e) {
            while (true) {
                //areaMensajes_c.append("\n"+areaTexto.getText());
                try {
                    Socket socket_c = new Socket("127.0.0.1", puerto);
                    //Guardar datos de envío en los métodos
                    detalles_s detalles=new detalles_s();
                    detalles.setNombre(nombre.getText());
                    detalles.setContacto(contacto.getText());
                    detalles.setMensaje(areaTexto.getText());
                    /*System.out.println("El puerto que se está utilizando es el: "+ puerto);
                    DataOutputStream flujo_s =new DataOutputStream(socket_c.getOutputStream());//Creación de flujo de salida
                    flujo_s.writeUTF(areaTexto.getText());//Guarde lo que contiene area de texto
                    flujo_s.close();*/
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
<<<<<<< HEAD

    /**
     * Variables de la clase javax.swing.*; utilizadas para la realización de la interfaz gráfica, ejecución de tareas y muestra de datos
     */
    private JTextField areaTexto,contacto;
    private JLabel nombre;
    private JTextArea areaMensajes_c;
    private JButton enviarbtn;
}
<<<<<<< HEAD
<<<<<<< HEAD

/**
 * Clase detalles_s, esta clase contiene los métodos para obtener y accesar a datos que son enviados en un paquete,
 * para poder enviar estos datos utiliza la clase Serializable para trasformar los objetos en bytes
 * @author diego
 * @version 1
 */
class detalles_s implements Serializable {
    /**
     * Variables tipo String privadas que almacenan datos tipo String
     */
=======
//Clase para enviar datos de envío
class detalles_s{
>>>>>>> parent of f5e59d8... Cambio al flujo de salida y Serialización
=======
//Clase para enviar datos de envío
class detalles_s{
>>>>>>> parent of f5e59d8... Cambio al flujo de salida y Serialización
    private String nombre, contacto, mensaje;

    /**
     * Método público para obtener el Nombre del usuario
     * @return nombre del usuario
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Método público para accesar al Nombre del usuario
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método público para obtener el Nombre de contacto o 2 cliente
     * @return nombre contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     *  Método público para accesar al Nombre de contacto o 2 cliente
     * @param contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Método público para obtener el mensaje que fue enviado enviado
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     *  Método público para accesar el mensaje que fue enviado
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
=======
    private JTextField areaTexto;

    private JButton enviarbtn;
}
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
