<!DOCTYPE html>

<html lang="en">
<%@ page import="model.IPInfo" %>
<%@ page import="java.util.List" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <meta charset="UTF-8">
    <title>IP Geo</title>
    <link rel="stylesheet" type="text/css" href="static/sheetstyles.css">
    <!--  js para google map -->
    <script src="./static/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBDaeWicvigtP9xPv919E-RNoxfvC-Hqik"></script>
    <!-- Include DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Include DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    
    <script>
		function validarFormato(inputValue) {
		    // Expresión regular para el formato 7.7.7.8
		    var regex = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
		
		    // Verificar si el valor coincide con el formato
		    if (regex.test(inputValue)) {
		        //alert("Formato válido");
		    } else {
		        alert("Formato de IP inválido. Debe ser en el formato x.x.x.x");
		    }
		}
</script>
</head>
<body>
 <!--  Busqueda -->
 <div class="container-hor">
	   <div class="box-hor">
		<form action="getIPInfo?action=add" method="get">
	        <input type="text" id="ipgeo" name="ipgeo" pattern="\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}" placeholder="Ingrese una IP">
	        <button type="submit" onclick="validarFormato(document.getElementById('ipgeo').value)">Buscar</button>
	    </form>
	   </div> 
	  
	   <!-- DIV MAP -->
	   <div class="box-hor" id="map">
	   		<table >
		   		<tr><td th:text="${data.loc}"></td></tr>
		   		
		   	
	   		</table>
	        
	        <script>

				
					        // Invoke iniMap function with coordinates
					        iniMap(${data.loc});
		        
		    </script>
	     
	    </div>
  </div>
   
   
   <div class="contanier">
      <h2>Listado de IPs Guardadas</h2>
        <table id="dataTable">
            <thead>
                <tr>
                    <th>IP</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Loc</th>
                    <th>org</th>
                    <th>Postal</th>
                    <th>Timezone</th>
                    <th>Eliminar</th>
                    
                </tr>
            </thead>
            <tbody>
			       <% 
					 List<IPInfo> ipInfoList = (List<IPInfo>) request.getAttribute("ipInfoList");
			        if(ipInfoList != null) {
			            for(IPInfo ipInfo : ipInfoList) {
				    %>
				    <tr>
				        <td><%= ipInfo.getIp() %></td>
				        <td><%= ipInfo.getCity() %></td>
				        <td><%= ipInfo.getCountry() %></td>
				        <td><%= ipInfo.getLoc() %></td>
				        <td><%= ipInfo.getOrg() %></td>
				        <td><%= ipInfo.getPostal() %></td>
				        <td><%= ipInfo.getTimezone() %></td>
				        <td><a href="?action=eliminar&id=<%= ipInfo.getId() %>">Eliminar</a></td>
						    
				    </tr>
				    <% 
				        }
			        }
				    %>
                
            </tbody>
        </table>
   </div>
   
   <script>
		$(document).ready(function() {
		    //  DataTable
		    var table = $('#dataTable').DataTable();
		
		    // filters
		    $('#filterInput').on('keyup', function () {
		        table.search(this.value).draw();
		    });
		});
</script>
</body>
</html>
