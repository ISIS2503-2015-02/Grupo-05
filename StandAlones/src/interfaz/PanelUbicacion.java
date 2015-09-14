package interfaz;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import GoogleStaticMaps.Maps;
import GoogleStaticMaps.StaticMap;
import GoogleStaticMaps.StaticMapException;

import java.awt.Font;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.TBC;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class PanelUbicacion extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8295862386357414505L;
	private JTextField txtDir;
	private JTextField txtCiudad;
	private JTextField txtPais;
	private JLabel lblMapa;
	private StaticMap mapa;
	private int zoom;
	private LatLng actual;
	private String last;
	private InterfazGDTS principal;

	/**
	 * Create the panel.
	 * @param interfazGDTS 
	 * @param vehiculo 
	 * @throws StaticMapException 
	 */
	public PanelUbicacion(final PanelHistorico ph, final InterfazGDTS interfazGDTS, String vehiculo) throws StaticMapException {
		setLayout(new BorderLayout(0, 0));
		principal = interfazGDTS;

		final JButton btnReportarNuevaPosicion = new JButton("Reportar nueva posicion");
		btnReportarNuevaPosicion.setEnabled(false);
		btnReportarNuevaPosicion.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(btnReportarNuevaPosicion, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
					JOptionPane.showMessageDialog(PanelUbicacion.this, "Ya no se puede acercar más","Limite alcanzado",JOptionPane.WARNING_MESSAGE);
					return;
				}
				zoom++;
				mapa.zoomIn();
				try {
					mapa.refresh();
				} catch (StaticMapException e1) 
				{
					JOptionPane.showMessageDialog(PanelUbicacion.this, e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

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
					JOptionPane.showMessageDialog(PanelUbicacion.this, "Ya no se puede alejar más","Limite alcanzado",JOptionPane.WARNING_MESSAGE);
					return;
				}
				zoom--;
				mapa.zoomOut();
				try {
					mapa.refresh();
				} catch (StaticMapException e1) 
				{
					JOptionPane.showMessageDialog(PanelUbicacion.this, e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		GridBagConstraints gbc_btnZoom_1 = new GridBagConstraints();
		gbc_btnZoom_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnZoom_1.gridx = 0;
		gbc_btnZoom_1.gridy = 4;
		panel.add(btnZoom_1, gbc_btnZoom_1);

		JLabel lblUbicacion = new JLabel("Posicion");
		lblUbicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUbicacion = new GridBagConstraints();
		gbc_lblUbicacion.insets = new Insets(0, 0, 5, 0);
		gbc_lblUbicacion.gridx = 0;
		gbc_lblUbicacion.gridy = 6;
		panel.add(lblUbicacion, gbc_lblUbicacion);

		JLabel lblDireccion = new JLabel("Direccion");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 8;
		panel.add(lblDireccion, gbc_lblDireccion);

		txtDir = new JTextField();
		GridBagConstraints gbc_txtDir = new GridBagConstraints();
		gbc_txtDir.insets = new Insets(0, 0, 5, 0);
		gbc_txtDir.gridx = 0;
		gbc_txtDir.gridy = 9;
		panel.add(txtDir, gbc_txtDir);
		txtDir.setColumns(10);

		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 10;
		panel.add(lblCiudad, gbc_lblCiudad);

		txtCiudad = new JTextField();
		GridBagConstraints gbc_txtCiudad = new GridBagConstraints();
		gbc_txtCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_txtCiudad.gridx = 0;
		gbc_txtCiudad.gridy = 11;
		panel.add(txtCiudad, gbc_txtCiudad);
		txtCiudad.setColumns(10);

		JLabel lblPais = new JLabel("Pais");
		GridBagConstraints gbc_lblPais = new GridBagConstraints();
		gbc_lblPais.insets = new Insets(0, 0, 5, 0);
		gbc_lblPais.gridx = 0;
		gbc_lblPais.gridy = 12;
		panel.add(lblPais, gbc_lblPais);

		txtPais = new JTextField();
		GridBagConstraints gbc_txtPais = new GridBagConstraints();
		gbc_txtPais.insets = new Insets(0, 0, 5, 0);
		gbc_txtPais.gridx = 0;
		gbc_txtPais.gridy = 13;
		panel.add(txtPais, gbc_txtPais);
		txtPais.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dir = txtDir.getText();
				String ciu = txtCiudad.getText();
				String pais = txtPais.getText();
				if(dir.isEmpty()||ciu.isEmpty()||pais.isEmpty())
				{
					JOptionPane.showMessageDialog(PanelUbicacion.this, "Por favor ingrese todos los campos","Informacion incompleta", JOptionPane.WARNING_MESSAGE);
					return;
				}
				List<GeocoderResult> l;
				try {
					l = Maps.getLocation(String.format("%s,%s,%s", dir,ciu,pais));
					if(l.size()<1)
					{
						JOptionPane.showMessageDialog(PanelUbicacion.this, "La busqueda no arrojo resultados","No encontrado", JOptionPane.WARNING_MESSAGE);
						return;
					}
					mapa.removeAllMarkers();
					mapa.addLocation(l.get(0).getGeometry().getLocation());
					zoom = 15;
					mapa.setZoom(15);
					mapa.refresh();
					actual = l.get(0).getGeometry().getLocation();
					last = dir;
					btnReportarNuevaPosicion.setEnabled(true);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PanelUbicacion.this, e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.gridx = 0;
		gbc_btnEnviar.gridy = 14;
		panel.add(btnEnviar, gbc_btnEnviar);
		zoom = 0;

		lblMapa = new JLabel();
		scrollPane.setViewportView(lblMapa);
		mapa = new StaticMap(400, 400);
		mapa.setZoom(zoom);
		mapa.refresh();

		lblMapa.setIcon(new ImageIcon(mapa.getImage()));
		mapa.addObserver(this);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 6, 5, 0));

		JLabel lblEstado = new JLabel("Estado:");
		panel_1.add(lblEstado);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Activo", "En transito"}));
		panel_1.add(comboBox);

		JLabel lblKilometraje = new JLabel("Kilometraje: ");
		panel_1.add(lblKilometraje);

		final JSpinner spKm = new JSpinner();
		panel_1.add(spKm);
		
		JLabel lbId = new JLabel("Vehiculo: ");
		panel_1.add(lbId);

		panel_1.add(new JLabel(vehiculo));

		btnReportarNuevaPosicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					String resp = TBC.getInstance().reportarUbicacion(actual, (String)comboBox.getSelectedItem(), (Integer)spKm.getValue());
					principal.append(resp);
					txtCiudad.setText("");
					txtDir.setText("");
					txtPais.setText("");
					ph.addLocation(actual,last);
					JOptionPane.showMessageDialog(PanelUbicacion.this, "Tu ubicación fue enviado con exito.","EXITO",JOptionPane.INFORMATION_MESSAGE);
					interfazGDTS.navegar(1);
					btnReportarNuevaPosicion.setEnabled(false);
					interfazGDTS.navegar(2);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PanelUbicacion.this,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		lblMapa.setIcon(new ImageIcon(mapa.getImage()));
	}
}
