package chat;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ventanaServidor ventana_s = new ventanaServidor();
        ventana_s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ventanaServidor extends JFrame implements Runnable{
    public ventanaServidor(){
        setTitle("SERVIDOR");
        setBounds(1200,300,520,450);
        JPanel canvasServidor= new JPanel();
        canvasServidor.setLayout(new BorderLayout());
        areamensajes_s=new JTextArea(25,40);
        canvasServidor.add(areamensajes_s,BorderLayout.CENTER);
        add(canvasServidor);
        setVisible(true);
        Thread hilo_s=new Thread(this);//Hillo servidor
        hilo_s.start();
    }
    private	JTextArea areamensajes_s;

    @Override
    public void run() {
        //System.out.println("FUNCIONANDO");
        while (true){
            try {
                ServerSocket servidor = new ServerSocket( 9999);//Poner a escuchar
                Socket socket_s=servidor.accept();//Aceptar las conexiones
                DataInputStream flujo_e=new DataInputStream(socket_s.getInputStream());//Flujo de datos de entrada
                String mensaje=flujo_e.readUTF();
                areamensajes_s.append("\n"+mensaje);
                socket_s.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}