package chat;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
=======
import java.io.DataInputStream;
import java.io.IOException;
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase Servidor
 * Instancia un objeto de la clase ventanaServidor y permite cerrar la ventana al pulsar X
 * @author diego
 * version 1.0
 */
public class Servidor {
    public static void main(String[] args) {
        ventanaServidor ventana_s = new ventanaServidor();
        ventana_s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
/**
 * Clase ventanaServidor
 * Generar la ventana del servidor con sus respectivas características, hereda a JFrame para el formato de la ventana e
 * implementa Runnable mismo utilizado para mantener activo y a la escucha el servidor
 * @author diego
 * version 1.0
 */
class ventanaServidor extends JFrame implements Runnable {
    /**
     * Método necesario para dar formato a lo que ven los usuarios,contiene textos y la
     * creación de un Thread necesario para el Runnable
     */
    public ventanaServidor() {
        setTitle("SERVIDOR");
<<<<<<< HEAD
        setBounds(1200, 300, 520, 450);
        JPanel canvasServidor = new JPanel();
        canvasServidor.setLayout(new BorderLayout());
        areamensajes_s = new JTextArea(25, 40);
        canvasServidor.add(areamensajes_s, BorderLayout.CENTER);
=======
        setBounds(1200,300,280,350);
        JPanel canvasServidor= new JPanel();
        canvasServidor.setLayout(new BorderLayout());
        areamensajes=new JTextArea();
        canvasServidor.add(areamensajes,BorderLayout.CENTER);
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
        add(canvasServidor);
        setVisible(true);
        Thread hilo_s = new Thread(this);//Hillo servidor
        hilo_s.start();
    }
<<<<<<< HEAD
    /**
     * Variable tipo JTextArea donde se almacenan y muestran los mensajes
     */
    private JTextArea areamensajes_s;
    /**
     * Método público
     * Através de un "try" crea socket de escucha para el servidor, almacena datos enviados y recibidos,crea flujo de entrada de datos,
     * acepta las conexiones, crea un socket para enviar datos al cliente y cierra los sockets y paquetes de datos.
     * EL catch evita las expeciones y muestra el error en consola
     * @author diego
     * version 1.0
     */
    @Override
    public void run() {
        //int puerto2 = 9090;
        try {
            ServerSocket servidor = new ServerSocket(9999);//Poner a escuchar
            String nombre, contacto, mensaje; //Variables para almacenar los datos enviados por el cliente
            detalles_s detalles_r;
            while (true) {
                Socket socket_c = servidor.accept();//Aceptar las conexiones
                ObjectInputStream flujo_e = new ObjectInputStream(socket_c.getInputStream());//Flujo de datos de entrada
                detalles_r = (detalles_s) flujo_e.readObject();
                nombre = detalles_r.getNombre();
                contacto = detalles_r.getContacto();
                mensaje = detalles_r.getMensaje();
                areamensajes_s.append("\n" + nombre + ": " + mensaje + " \n" + "Este mensaje ha sido enviado para " + contacto);
                Socket socket_c2 = new Socket("127.0.0.1", 9090);//Socket para el cliente 2
                ObjectOutputStream paquete_s2 = new ObjectOutputStream(socket_c2.getOutputStream());
                paquete_s2.writeObject(detalles_r);
                paquete_s2.close();
                socket_c2.close();
                socket_c.close();
=======
    private	JTextArea areamensajes;

    @Override
    public void run() {
        //System.out.println("FUNCIONANDO");
        while (true){
            try {
                //chat.canvasCliente.enviarMensaje puertos=new chat.canvasCliente.enviarMensaje();
                //ServerSocket servidor=new ServerSocket(puertos.get_puerto());
                ServerSocket servidor = new ServerSocket( 9999);//Poner a escuchar
                Socket socket_s=servidor.accept();//Aceptar las conexiones
                DataInputStream flujo_e=new DataInputStream(socket_s.getInputStream());//Flujo de datos de entrada
                String mensaje=flujo_e.readUTF();
                areamensajes.append("\n"+mensaje);
                socket_s.close();
            }
            catch (IOException e){
                e.printStackTrace();
>>>>>>> parent of 16f0b7b... Mejora de interfaz Cliente y creación de variables para enviar información como el nombre del cliente recibe y el nombre de cliente envía
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}