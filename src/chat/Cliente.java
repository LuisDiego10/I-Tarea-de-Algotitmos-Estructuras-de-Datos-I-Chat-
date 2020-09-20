package chat;
import javax.swing.*;
public class Cliente {

    public static void main(String[] args) {
	ventanaCliente ventana_c = new ventanaCliente();
	ventana_c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
class ventanaCliente extends JFrame {
    public ventanaCliente(){
        setBounds(600,300,280,350);
        canvasCliente canvasc=new canvasCliente();
        add(canvasc);
        setVisible(true);
        }
}
class canvasCliente extends JPanel{
    public canvasCliente(){
        JLabel texto=new JLabel("CLIENTE");
        add(texto);
        areaTexto= new JTextField(25);
        add(areaTexto);
        enviarbtn =new JButton("ENVIAR");
        add(enviarbtn);
        }
    private JTextField areaTexto;

    private JButton enviarbtn;
}
