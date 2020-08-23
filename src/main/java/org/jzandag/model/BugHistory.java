package org.jzandag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bug_history")
public class BugHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private Date historyDate;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Bug.class)
	@JoinColumn(name = "bug_fk")
	private Bug bug;
}
