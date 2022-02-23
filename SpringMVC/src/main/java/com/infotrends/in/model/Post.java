
package com.infotrends.in.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infotrends.in.utils.EntityType;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "Post")
@Data
public class Post {

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private int postId;
	
	@Column(name = "title", length = 100)
	private String title;
	
	@Column(name = "content", length = 500)
	private String content;
	
	@Column(name = "post_status")
	private String postStatus;

	@Column(name = "postType")
	private EntityType postType;
	
	@Column(name = "attachments", length = 200)
	private String attachments;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "author")
	@ToString.Exclude
	private User author;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_on")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime modifiedOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_on")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createdOn;
	
}
