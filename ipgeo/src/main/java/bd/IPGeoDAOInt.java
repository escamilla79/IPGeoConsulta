package bd;

import java.util.List;

import model.IPInfo;

public interface IPGeoDAOInt {
	
    void add(IPInfo ipGeo);
    IPInfo getByIP(String ip);
    List<IPInfo> getAll();
    void update(IPInfo ipGeo);
    void delete(String ip);

}
