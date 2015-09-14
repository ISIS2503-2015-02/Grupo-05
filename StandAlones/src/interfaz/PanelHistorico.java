package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import GoogleStaticMaps.Marker;
import GoogleStaticMaps.StaticMap;
import GoogleStaticMaps.StaticMapException;

import com.google.code.geocoder.model.LatLng;

import java.awt.Dimension;
import javax.swing.JTextArea;

public class PanelHistorico extends JPanel implements Observer{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 447829419533454586L;

	private StaticMap mapa;
	
	private int zoom;
	
	private int i;

	private JLabel lblMapa;


	private JTextArea textArea;
	
	
	/**
	 * Create the panel.
	 * @throws StaticMapException 
	 */
	public PanelHistorico() throws StaticMapException {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		 lblMapa = new JLabel();
		scrollPane.setViewportView(lblMapa);
		
		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		
		JLabel lblOpcionesDeMapa = new JLabel("Opciones de Mapa");
		lblOpcionesDeMapa.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblOpcionesDeMapa = new GridBagConstraints();
		gbc_lblOpcionesDeMapa.insets = new Insets(0, 0, 5, 0);
		gbc_lblOpcionesDeMapa.gridx = 0;
		gbc_lblOpcionesDeMapa.gridy = 1;
		panel.add(lblOpcionesDeMapa, gbc_lblOpcionesDeMapa);
		
		JButton btnZoom = new JButton("Zoom +");
		btnZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(zoom+1>20)
				{
					JOptionPane.showMessageDialog(PanelHistorico.this, "Ya no se puede acercar más","Limite alcanzado",JOptionPane.WARNING_MESSAGE);
					return;
				}
				zoom++;
				mapa.zoomIn();
				try {
					mapa.refresh();
				} catch (StaticMapException e1) 
				{
					JOptionPane.showMessageDialog(PanelHistorico.this, e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		GridBagConstraints gbc_btnZoom = new GridBagConstraints();
		gbc_btnZoom.insets = new Insets(0, 0, 5, 0);
		gbc_btnZoom.gridx = 0;
		gbc_btnZoom.gridy = 3;
		panel.add(btnZoom, gbc_btnZoom);
		
		JButton btnZoom_1 = new JButton("Zoom -");
		btnZoom_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(zoom-1<0)
				{
					JOptionPane.showMessageDialog(PanelHistorico.this, "Ya no se puede alejar más","Limite alcanzado",JOptionPane.WARNING_MESSAGE);
					return;
				}
				zoom--;
				mapa.zoomOut();
				try {
					mapa.refresh();
				} catch (StaticMapException e1) 
				{
					JOptionPane.showMessageDialog(PanelHistorico.this, e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		GridBagConstraints gbc_btnZoom_1 = new GridBagConstraints();
		gbc_btnZoom_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnZoom_1.gridx = 0;
		gbc_btnZoom_1.gridy = 4;
		panel.add(btnZoom_1, gbc_btnZoom_1);
		
		JLabel lblReportes = new JLabel("Reportes");
		lblReportes.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblReportes = new GridBagConstraints();
		gbc_lblReportes.insets = new Insets(0, 0, 5, 0);
		gbc_lblReportes.gridx = 0;
		gbc_lblReportes.gridy = 8;
		panel.add(lblReportes, gbc_lblReportes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setMaximumSize(new Dimension(50, 100));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 9;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		 textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		
		zoom = 0;
		mapa = new StaticMap(400, 400);
		mapa.setZoom(zoom);
		mapa.refresh();
		mapa.addObserver(this);
		
		lblMapa.setIcon(new ImageIcon(mapa.getImage()));
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				mapa.removeAllMarkers();
				mapa.setZoom(0);
				zoom =0;
				i=0;
				textArea.setText("");
				try {
					mapa.refresh();
				} catch (StaticMapException e1) 
				{
					JOptionPane.showMessageDialog(PanelHistorico.this, e1.getMessage());
				}
				
			}
		});
		btnClear.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(btnClear, BorderLayout.SOUTH);
		
	}
	
	
	public void addLocation(LatLng latLng, String last) throws Exception
	{ 
		if(i==0)
			zoom = 8;
		i++;
		mapa.setZoom(zoom);
		mapa.addMarker(new Marker(Integer.toString(i).toCharArray()[0], new String[]{String.format("%s,%s",latLng.getLat(),latLng.getLng())}));
		mapa.refresh();
		textArea.setText(textArea.getText()+"\n"+i+". "+last);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		lblMapa.setIcon(new ImageIcon(mapa.getImage()));
	}

}
