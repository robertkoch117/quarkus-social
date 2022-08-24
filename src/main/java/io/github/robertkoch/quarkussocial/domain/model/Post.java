package io.github.robertkoch.quarkussocial.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="post_text")
	private String text;
	
	@Column(name="dateTime")
	private LocalDateTime dateTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Post() {
		super();
	}

	public Post(Long id, String text, LocalDateTime dateTime, User user) {
		super();
		this.id = id;
		this.text = text;
		this.dateTime = dateTime;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
	public void prePersist() {
		setDateTime(LocalDateTime.now());
	}
	
}
