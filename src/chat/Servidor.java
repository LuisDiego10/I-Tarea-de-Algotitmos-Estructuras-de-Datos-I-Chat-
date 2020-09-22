package chat;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ventanaServidor ventana_s = new ventanaServidor();
        ventana_s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ventanaServidor extends JFrame implements Runnable {
    public ventanaServidor() {
        setTitle("SERVIDOR");
        setBounds(1200, 300, 520, 450);
        JPanel canvasServidor = new JPanel();
        canvasServidor.setLayout(new BorderLayout());
        areamensajes_s = new JTextArea(25, 40);
        canvasServidor.add(areamensajes_s, BorderLayout.CENTER);
        add(canvasServidor);
        setVisible(true);
        Thread hilo_s = new Thread(this);//Hillo servidor
        hilo_s.start();
    }

    private JTextArea areamensajes_s;

    @Override
    public void run() {
        int puerto2 = 9090;
        try {
            ServerSocket servidor = new ServerSocket(9999);//Poner a escuchar
            String nombre, contacto, mensaje; //Variables para almacenar los datos enviados por el cliente
            detalles_s detalles_r;
            while (true) {
                Socket socket_s = servidor.accept();//Aceptar las conexiones
                ObjectInputStream flujo_e = new ObjectInputStream(socket_s.getInputStream());//Flujo de datos de entrada
                detalles_r = (detalles_s) flujo_e.readObject();
                nombre = detalles_r.getNombre();
                contacto = detalles_r.getContacto();
                mensaje = detalles_r.getMensaje();
                areamensajes_s.append("\n" + nombre + ": " + mensaje + " \n" + "Este mensaje ha sido enviado para " + contacto);
                Socket socket_c2 = new Socket("127.0.0.1", puerto2);
                ObjectOutputStream paquete_s2 = new ObjectOutputStream(socket_c2.getOutputStream());
                paquete_s2.writeObject(detalles_r);
                paquete_s2.close();
                socket_c2.close();
                socket_s.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}