package chat;
import javax.swing.*;
import java.awt.*;
import java.net.ServerSocket;

public class Servidor {
    public static void main(String[] args) {
        ventanaServidor ventana_s = new ventanaServidor();
        ventana_s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ventanaServidor extends JFrame implements Runnable{
    public ventanaServidor(){
        setTitle("SERVIDOR");
        setBounds(1200,300,280,350);
        JPanel canvasServidor= new JPanel();
        canvasServidor.setLayout(new BorderLayout());
        areatexto=new JTextArea();
        canvasServidor.add(areatexto,BorderLayout.CENTER);
        add(canvasServidor);
        setVisible(true);
        Thread hilo_s=new Thread(this);//Hillo servidor
        hilo_s.start();
    }
    private	JTextArea areatexto;

    @Override
    public void run() {
        //System.out.println("FUNCIONANDO");
        //chat.canvasCliente.enviarMensaje puertos=new chat.canvasCliente.enviarMensaje();
        //ServerSocket servidor=new ServerSocket(puertos.puerto());
    }
}