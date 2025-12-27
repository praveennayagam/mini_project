package com.mphasis.model;


import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String name;
    private int age;
    private String contactNo;
    private String emailId;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PG> pgList = new ArrayList<>();

    public Owner() {}
    
    

    public Owner(Long ownerId, String name, int age, String contactNo, String emailId, List<PG> pgList) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.age = age;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.pgList = pgList;
	}



	public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public List<PG> getPgList() { return pgList; }
    public void setPgList(List<PG> pgList) { this.pgList = pgList; }



	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + ", age=" + age + ", contactNo=" + contactNo
				+ ", emailId=" + emailId + "]";
	}
    
    
}
