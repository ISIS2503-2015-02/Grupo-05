package logica;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.code.geocoder.model.LatLng;
import com.google.gson.JsonObject;



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
		String url = "https://arqui201326232.herokuapp.com/vehiculos/"+id;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String linea;
		StringBuilder response = new StringBuilder();

		while ((linea = in.readLine()) != null) {
			response.append(linea);
		}
		in.close();
		this.id = id;
		return con.getResponseCode()== HttpsURLConnection.HTTP_OK;
	}
	
	public String reportarUbicacion(LatLng pos, String estado, int km) throws Exception
	{
		JsonObject json = new JsonObject();
		json.addProperty("latitud", pos.getLat().doubleValue());
		json.addProperty("longitud", pos.getLat().doubleValue());
		json.addProperty("hora", System.currentTimeMillis());
		json.addProperty("estado", estado);
		json.addProperty("kilometraje", km);


		String url = "https://arqui201326232.herokuapp.com/vehiculos/"+id+"/posiciones/agregar";
		URL obj = new URL(url);
		sun.net.www.protocol.http.HttpURLConnection con = (sun.net.www.protocol.http.HttpURLConnection) obj.openConnection();
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

		return response.toString();
	}
}
