package com.mphasis.model;



import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;

@Entity
public class PG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pgId;

    private String name;
    private double area;
    private int numberOfRooms;
    private int availableRooms;
    private int popularityCount;
    private String locality;

  
    private String city;
    private Long cityId;

    private double rentAmount;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"pgList"})
    private Owner owner;

    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Tenant> tenants;

    public PG() {}
    
    

    public PG(Long pgId, String name, double area, int numberOfRooms, int availableRooms, int popularityCount,
			String locality, String city, Long cityId, double rentAmount, boolean isAvailable, Owner owner,
			List<Tenant> tenants) {
		super();
		this.pgId = pgId;
		this.name = name;
		this.area = area;
		this.numberOfRooms = numberOfRooms;
		this.availableRooms = availableRooms;
		this.popularityCount = popularityCount;
		this.locality = locality;
		this.city = city;
		this.cityId = cityId;
		this.rentAmount = rentAmount;
		this.available = isAvailable;
		this.owner = owner;
		this.tenants = tenants;
	}



	public Long getPgId() { return pgId; }
    public void setPgId(Long pgId) { this.pgId = pgId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getNumberOfRooms() { return numberOfRooms; }
    public void setNumberOfRooms(int numberOfRooms) { this.numberOfRooms = numberOfRooms; }

    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }

    public int getPopularityCount() { return popularityCount; }
    public void setPopularityCount(int popularityCount) { this.popularityCount = popularityCount; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }

    public double getRentAmount() { return rentAmount; }
    public void setRentAmount(double rentAmount) { this.rentAmount = rentAmount; }

   

    



	public boolean isAvailable() {
		return available;
	}



	public void setAvailable(boolean available) {
		this.available = available;
	}



	public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public List<Tenant> getTenants() { return tenants; }
    public void setTenants(List<Tenant> tenants) { this.tenants = tenants; }



	@Override
	public String toString() {
		return "PG [pgId=" + pgId + ", name=" + name + ", area=" + area + ", numberOfRooms=" + numberOfRooms
				+ ", availableRooms=" + availableRooms + ", popularityCount=" + popularityCount + ", locality="
				+ locality + ", city=" + city + ", cityId=" + cityId + ", rentAmount=" + rentAmount + ", isAvailable="
				+ available + ", owner=" + owner + ", tenants=" + tenants + "]";
	}
    
    
    
}
