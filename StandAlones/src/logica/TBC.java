package logica;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;


import com.google.code.geocoder.model.LatLng;
import com.google.gson.JsonObject;

import sun.net.www.protocol.http.HttpURLConnection;
import org.apache.commons.codec.binary.Base64;




public class TBC 
{
	private String id;
	private static TBC tbc;
	
	public static TBC getInstance()
	{
		if(tbc==null)
			tbc = new TBC();
		return tbc;
	}
	
	private TBC() {
		// TODO Auto-generated constructor stub
	}

	public boolean login(String id) throws Exception
	{
		String url = "http://arquisoft20152.herokuapp.com/webresources/users/login";


		JsonObject json = new JsonObject();
		json.addProperty("userName", "vehiculoUser");
		json.addProperty("password", "Vehiculo123");



		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		String params = json.toString();

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String linea;
		StringBuilder response = new StringBuilder();

		while ((linea = in.readLine()) != null) {
			response.append(linea);
		}
		in.close();
		
		
		this.id = id;
		System.out.println(response);
		return con.getResponseCode()== HttpURLConnection.HTTP_OK;
	}
	
	public String reportarUbicacion(LatLng pos, String estado, int km) throws Exception
	{
		String url = "http://arquisoft20152.herokuapp.com/webresources/vehiculos/"+id+"/posiciones";
		
		JsonObject json = new JsonObject();
		json.addProperty("latitud", pos.getLat().doubleValue());
		json.addProperty("longitud", pos.getLat().doubleValue());
		json.addProperty("hora", System.currentTimeMillis());
		json.addProperty("estado", estado);
		json.addProperty("kilometraje", km);


		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		String encoding = Base64.encodeBase64String ("admin:Admin123".getBytes());
		con.setRequestProperty("Authorization", "Basic " + encoding);
		
		String params = json.toString();

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String linea;
		StringBuilder response = new StringBuilder();

		while ((linea = in.readLine()) != null) {
			response.append(linea);
		}
		in.close();
		
		System.out.println(response);
		return response.toString();
	}
}
