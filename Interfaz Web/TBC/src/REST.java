import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import sun.net.www.protocol.http.*;

import com.google.gson.JsonObject;

public class REST {
	
	public static String POST(String url, JsonObject json) throws Exception
	{
		URL obj = new URL(url);
		sun.net.www.protocol.https.HttpsURLConnectionImpl con = (sun.net.www.protocol.https.HttpsURLConnectionImpl) obj.openConnection();
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
	
	public static String GET(String url) throws Exception
	{
		URL obj = new URL(url);
		sun.net.www.protocol.https.HttpsURLConnectionImpl con = (sun.net.www.protocol.https.HttpsURLConnectionImpl) obj.openConnection();

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