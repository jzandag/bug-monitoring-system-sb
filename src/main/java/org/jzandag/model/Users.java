package org.jzandag.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	private String role;
	
	@OneToMany(mappedBy = "assignedTo", fetch = FetchType.EAGER)
	private List<Bug> assignedBugs;
	@OneToMany(mappedBy = "reportedBy", fetch = FetchType.EAGER)
	private List<Bug> reportedBugs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Bug> getAssignedBugs() {
		return assignedBugs;
	}
	public void setAssignedBugs(List<Bug> assignedBugs) {
		this.assignedBugs = assignedBugs;
	}
	public List<Bug> getReportedBugs() {
		return reportedBugs;
	}
	public void setReportedBugs(List<Bug> reportedBugs) {
		this.reportedBugs = reportedBugs;
	}
}
