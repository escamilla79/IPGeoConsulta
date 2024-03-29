package model;

public class IPInfo {
	
	private Long id;
    private String ip;
    private String city;
    private String country;
    private String loc;
    private String org;
    private String postal;
    private String timezone;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	@Override
	public String toString() {
		return "IPInfo [id=" + id + ", ip=" + ip + ", city=" + city + ", country=" + country + ", loc=" + loc + ", org="
				+ org + ", postal=" + postal + ", timezone=" + timezone + "]";
	}
	
    
    
    
}