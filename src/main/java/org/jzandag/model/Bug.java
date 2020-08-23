package org.jzandag.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "bugs")
public class Bug {
	
	private Long id;
	private String issue;
	private Date createdDate;
	@OneToMany(mappedBy = "bug")
	private List<BugHistory> bugHistory;
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Project.class)
	@JoinColumn(name = "project_fk")
	private Project project;
	
	public Bug() {	
	}
	
	public Bug(String issue) {
		this.issue = issue;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "issue_name")
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@PrePersist
    protected void prePersist() {
        if (this.createdDate == null) 
        	this.createdDate = new Date();   
    }
}
