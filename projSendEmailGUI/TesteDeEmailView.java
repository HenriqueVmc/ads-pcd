package pct;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Message;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TesteDeEmailView {

	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TesteDeEmailView();
            }
        });
    }

    private int linhaSelecionada = -1;
    private JFrame jFrame;
    private JLabel lblTO;
	private JTextField txtTO;
	private JLabel lblFROM;
	private JTextField txtFROM;
	private JLabel lblPSWD;
	private JTextField txtPSWD;
	private JLabel lblSBJC;
	private JTextField txtSBJC;
	private JLabel lblMSG;
	private JTextArea txtMSG;
	private JButton btnEnviar;
	private JButton btnLimpar;
	private JScrollPane scroll;
    
    public TesteDeEmailView() {
	
    	 JPanel tudo = new JPanel(new BorderLayout(5, 5));
         jFrame = new JFrame("Envio de E-mail");
         jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         jFrame.setLayout(null);
         jFrame.setSize(540, 400);
         jFrame.setLocationRelativeTo(null);
         jFrame.setContentPane(tudo);
         
         lblFROM = new JLabel("E-mail (From): ");
         txtFROM = new JTextField(20);
         
         lblPSWD = new JLabel("Password: ");
         txtPSWD = new JTextField(10);
         
         lblTO = new JLabel("E-mail (To): ");
         txtTO = new JTextField(10);
         
         lblSBJC= new JLabel("Subject: ");
         txtSBJC = new JTextField(10);
         
         lblMSG = new JLabel("Message: ");
         txtMSG = new JTextArea();
         
         JPanel top = new JPanel(new GridLayout(2,0,5,5));         
         JPanel topFrom = new JPanel(new GridLayout(2,2,5,0));
         
         JPanel fieldFROM = new JPanel(new GridLayout(2,1));
         fieldFROM.add(lblFROM);
         fieldFROM.add(txtFROM);
         
         JPanel fieldPSWD = new JPanel(new GridLayout(2,1));
         fieldPSWD.add(lblPSWD);
         fieldPSWD.add(txtPSWD);
         
         JPanel fieldTO = new JPanel(new GridLayout(2,1));
         fieldTO.add(lblTO);
         fieldTO.add(txtTO);
         
         JPanel fieldSBJC = new JPanel(new GridLayout(2,1));
         fieldSBJC.add(lblSBJC);
         fieldSBJC.add(txtSBJC);
         
         topFrom.add(fieldFROM);
         topFrom.add(fieldPSWD);
         topFrom.add(fieldTO);
         topFrom.add(fieldSBJC);
         
         JPanel fieldMSG = new JPanel(new BorderLayout());         
         fieldMSG.add(lblMSG, BorderLayout.CENTER);         
         
         top.add(topFrom);
         top.add(fieldMSG);         
                  
         scroll = new JScrollPane(txtMSG);
                  
         tudo.add(top, BorderLayout.NORTH);
         tudo.add(scroll, BorderLayout.CENTER);
         
         btnEnviar = new JButton("Enviar");
         acaoBotaoEnviar();
         
         tudo.add(btnEnviar, BorderLayout.SOUTH);
         
         jFrame.setVisible(true);
	}
    
    private void acaoBotaoEnviar() {
        btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TesteDeEmail sendEmail = new TesteDeEmail(txtFROM.getText(),
														  txtPSWD.getText(),
														  txtTO.getText(),
														  txtSBJC.getText(),
														  txtMSG.getText());
				if(sendEmail.Enviar()){
					JOptionPane.showMessageDialog(null, "Enviado com sucesso!");
					limparCampos();
				}
				else{
					JOptionPane.showMessageDialog(null, "Falha ao enviar e-mail!");
				}
			}
		});
    }
    
    private void limparCampos(){
    	txtFROM.setText("");
    	txtTO.setText("");
    	txtMSG.setText("");
    	txtPSWD.setText("");
    	txtSBJC.setText("");
    }
}


