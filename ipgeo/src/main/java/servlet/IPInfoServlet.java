package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import bd.IPGeoDAO;
import model.IPInfo;
import util.DBHelper;

//@WebServlet("/getIPInfo")
public class IPInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IPGeoDAO dao= new IPGeoDAO();
    	
    	 String accion = request.getParameter("action");
//    	 System.out.println("  :::::::: action "+ accion);
    	
    	if(accion!=null&&accion.equals("eliminar")) {
    		String idEliminar = request.getParameter("id");
    		dao.delete(idEliminar);
    		
    		request.setAttribute("ipInfoList", dao.getAll());
    		request.getRequestDispatcher("inicio.jsp").forward(request, response);
    	}else {
	    	String ipBuscar = request.getParameter("ipgeo");
	        String json = getIPInfoJSON(ipBuscar);
	        
//	        System.out.println(""+json);
	        IPInfo ipInfo = new Gson().fromJson(json, IPInfo.class);
	    
	        
	        if (dao.getByIP(ipInfo.getIp())==null&& ipInfo!=null) {
				 dao.add(ipInfo);

				 request.setAttribute("data", ipInfo);
	        }else if(ipInfo==null){
//	        	System.out.println("No hay info ");
	        }else {
	        	response.setContentType("text/html");
	            response.getWriter().println("<script>alert('Esta IP "+ipInfo.getIp()+" ya existe');</script>");
	        	//Ya existe
	            
	            String mensaje = "<script>alert('¡Esto es una alerta desde un servlet!');</script>";

	            // Escribir el mensaje en el cuerpo de la respuesta
	            response.getWriter().println(mensaje);
	            
//	        	System.out.println("Ya existe ");
	        	
	        }
	        //IPInfo dato = dao.getByIP(ipInfo.getIp());
	    	List<IPInfo> ipInfoList = dao.getAll();
	        request.setAttribute("ipInfoList", ipInfoList);
	        request.getRequestDispatcher("inicio.jsp").forward(request, response);
    	}
//        request.getRequestDispatcher("ipinfo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
//    	System.out.println("  :::::::: doPOST accion "+ action);
    	doGet(request, response);
    }
    
    
    
    
    private String getIPInfoJSON(String ipgeo) throws IOException {
    	
    	
        String url = "https://ipinfo.io/" + ipgeo + "/json";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
            
        }
        
        return result.toString();
    }


}