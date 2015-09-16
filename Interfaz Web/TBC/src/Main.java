

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL ="http://localhost:9000/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw = response.getWriter();
		String uri = request.getParameter("URI");
		
		Pattern pat = Pattern.compile("\\{\\w+\\}");
		for(int i=indexOf(pat, uri) ; i!=-1 ; i = indexOf(pat, uri))
		{
			String name = uri.substring(i+1, uri.indexOf("}"));
			uri = uri.replaceFirst("\\{\\w+\\}", request.getParameter(name));
		}
		
		pw.println("Enviando GET a: " + BASE_URL+uri);
		
		/*
		//ENVVIAR PETICION GET
		try
		{
			pw.println(REST.GET(BASE_URL+uri));
		} 
		catch (Exception e) {
			pw.println("ERROR: "+e.getMessage());
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Map<String,String[]> params = request.getParameterMap();
		String uri = request.getParameter("URI");
		
		Pattern pat = Pattern.compile("\\{\\w+\\}");
		for(int i=indexOf(pat, uri) ; i!=-1 ; i = indexOf(pat, uri))
		{
			String name = uri.substring(i+1, uri.indexOf("}"));
			request.removeAttribute(name); //Borrar el parametro para que no se vaya en el JSON
			uri = uri.replaceFirst("\\{\\w+\\}", name);
		}
		
		pw.println("Enviando POST a: " + BASE_URL+uri);

		//CONSTRUIR JSON CON PARAMETROS
		JsonObject json = new JsonObject();
		for(String key: params.keySet())
			if(!key.equalsIgnoreCase("URI"))
				json.addProperty(key, request.getParameter(key));
		pw.println("Parametros: "+json.toString());
		
		/*
		//ENVIAR EL POST
		try {
			REST.POST(BASE_URL+uri, json);
		} catch (Exception e) {
			e.printStackTrace();
			pw.println(e.getMessage());
		}
		*/
	}
	
	public static int indexOf(Pattern pattern, String s) {
	    Matcher matcher = pattern.matcher(s);
	    return matcher.find() ? matcher.start() : -1;
	}

}
