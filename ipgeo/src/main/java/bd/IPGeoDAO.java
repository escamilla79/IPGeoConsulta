package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.IPInfo;
import util.DBHelper;

public class IPGeoDAO implements IPGeoDAOInt {
	
	private Connection connection;

    public IPGeoDAO() {
        this.connection = null;
		try {
			this.connection = DBHelper.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void add(IPInfo info) {
		String sql = "INSERT INTO ip_info (ip, city, country, loc, org, postal, timezone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try  {
        	PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, info.getIp());
            statement.setString(2, info.getCity());
            statement.setString(3, info.getCountry());
            statement.setString(4, info.getLoc());
            statement.setString(5, info.getOrg());
            statement.setString(6, info.getPostal());
            statement.setString(7, info.getTimezone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public IPInfo getByIP(String ip) {
		 String sql = "SELECT * FROM ip_info WHERE ip = ?";
	        try {
	        	PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, ip);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return extractIPGeoFromResultSet(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	public List<IPInfo> getAll() {
		List<IPInfo> infoList = new ArrayList<>();
		String sql = "SELECT * FROM ip_info";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				infoList.add(extractIPGeoFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("numero de registros :"+infoList.size());
		return infoList;
	}

	public void update(IPInfo ipGeo) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String id) {
		 String sql = "DELETE FROM ip_info WHERE id = ?";
	        try  {
	        	PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, id);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	private IPInfo extractIPGeoFromResultSet(ResultSet resultSet) throws SQLException {
		IPInfo info = new IPInfo();
		
		info.setId(resultSet.getLong("id"));
		info.setIp(resultSet.getString("ip"));
		info.setCity(resultSet.getString("city"));
		info.setCountry(resultSet.getString("country"));
		info.setLoc(resultSet.getString("loc"));
		info.setOrg(resultSet.getString("org"));
		info.setPostal(resultSet.getString("postal"));
		info.setTimezone(resultSet.getString("timezone"));
        return info;
    }
}
