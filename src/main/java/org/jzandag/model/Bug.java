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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "bugs")
public class Bug {
	
	private Long id;
	private String issue;
	private String description;
	private Date createdDate;
	
	private List<BugHistory> bugHistory;
	private Project project;
	
	private Users assignedTo;
	private Users reportedBy;
	
	
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "created_date")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@OneToMany(mappedBy = "bug")
	public List<BugHistory> getBugHistory() {
		return bugHistory;
	}

	public void setBugHistory(List<BugHistory> bugHistory) {
		this.bugHistory = bugHistory;
	}

	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Project.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "project_fk")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "assigned_to_fk")
	public Users getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Users assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "reported_by_fk")
	public Users getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(Users reportedBy) {
		this.reportedBy = reportedBy;
	}

}
