package org.jzandag.dto;

import java.util.Date;

public class BugDTO {
	
	private Long id;
	private String issue;
	private String description;
	private Date createdDate;
	
	private Long project;
	private Long assignedTo;
	private Long reportedBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getProject() {
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}
	public Long getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Long getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(Long reportedBy) {
		this.reportedBy = reportedBy;
	}
}
