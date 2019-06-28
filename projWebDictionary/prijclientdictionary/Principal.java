/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijclientdictionary;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author henrique
 */
public class Principal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Principal();
            }
        });
    }

    private JFrame jFrame;
    private JTextField txtText, txtTranslation;
    private JLabel lblText, lblTranslation;
    private JButton btnSend;

    public Principal() {

        JPanel tudo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel r1 = new JPanel(new GridLayout(1,2));
        JPanel r2 = new JPanel(new GridLayout(1,1));
                
        lblText = new JLabel("Text:");
        txtText = new JTextField(12);
        
        JPanel r1Fild = new JPanel(new FlowLayout());
        r1Fild.add(lblText);
        r1Fild.add(txtText);
                
        btnSend = new JButton("Translate");
        
        r1.add(r1Fild);
        r1.add(btnSend);               
        
        lblTranslation = new JLabel("Translation:");
        txtTranslation = new JTextField(12);
        
        JPanel r2Fild = new JPanel(new FlowLayout());
        r2Fild.add(lblTranslation);
        r2Fild.add(txtTranslation);
        
        r2.add(r2Fild);
        
        tudo.add(r1);
        tudo.add(r2);
        
        jFrame = new JFrame("Translate: Engilsh to German");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setSize(420, 100);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(tudo);
        
        acaoBotao();
        
        jFrame.setVisible(true);
    }

    private void acaoBotao() {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txtText.getText();
                String translation = sendText(text);
                txtTranslation.setText(translation);
            }
        });
    }
    
    private String sendText(String text) {
        String translation = "";

        Socket client;
        DataInputStream is;
        DataOutputStream os;

        try {

            System.out.println("\nCliente vai se conectar ao server");
            // localhost pois cliente/servidor estao locais				
            client = new Socket("192.168.25.94", 1234);
            System.out.println("\nconectado!");

            // Sistema de comunicação (RxT)			
            is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());

            os.writeUTF(text);

            String msg = is.readUTF();
            System.out.println("(Client)Mensagem recebida: " + msg);

            translation = msg;

            is.close();
            os.close();

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return translation;
    }
}
