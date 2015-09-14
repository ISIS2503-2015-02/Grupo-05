package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import logica.TBC;
import GoogleStaticMaps.StaticMapException;

public class InterfazGDTS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5377654695899899130L;

	private JPanel contentPane;

	private PanelUbicacion panelUbicacion;
	
	private PanelHistorico panelHistorico;

	private JTabbedPane tabbedPane;
	
	private JTextArea txtOut;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String ans = null;
					while( (ans= JOptionPane.showInputDialog(null, "Id","Iniciar sesión", JOptionPane.INFORMATION_MESSAGE) ) !=null && ans.isEmpty()) ;
					if(ans==null)
						return;
					TBC.getInstance().login(ans);
					InterfazGDTS frame = new InterfazGDTS(ans);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param id 
	 * @throws StaticMapException 
	 */
	public InterfazGDTS(String id) throws StaticMapException 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		panelHistorico = new PanelHistorico();
		panelUbicacion = new PanelUbicacion(panelHistorico, this, id);
		
		txtOut = new JTextArea();
		txtOut.setEditable(false);
		
		JPanel output = new JPanel();
		output.setLayout(new BorderLayout());
		output.add(txtOut, BorderLayout.CENTER);
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				txtOut.setText("");
			}
		});
		output.add(btnClear,BorderLayout.SOUTH);
		
		setContentPane(contentPane);
		
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		tabbedPane.insertTab("Reportar ubicacion", null,panelUbicacion, "", 0);
		tabbedPane.insertTab("Historial", null, panelHistorico,"",1);
		tabbedPane.insertTab("Output", null, output, "", 2);
		
		setSize(540, 540);
		setResizable(false);
	}
	
	public void append(String txt)
	{
		txtOut.setText(txtOut.getText()+"\n-----------------------------------------------------\n"+new Date()+"\n"+txt);
	}
	
	public void navegar(int tab)
	{
		tabbedPane.setSelectedIndex(tab);
	}

}
