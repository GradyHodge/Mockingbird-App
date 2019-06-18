package com.tts.MockingBird.model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tweet_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@NotEmpty(message = "Tweet cannot be empty")
	@Length(max = 280, message = "Tweet cannot have more than 280 characters")
	private String message;

	@CreationTimestamp
	private Date createdAt;

	public Tweet() {}
	
//	public Tweet(Long id, User user,
//			@NotEmpty(message = "Tweet cannot be empty") @Length(max = 280, message = "Tweet cannot have more than 280 characters") String message,
//			Date createdAt, List<Tag> tags) {
	public Tweet(Long id, User user, String message, Date createdAt, List<Tag> tags) {
		this.id = id;
		this.user = user;
		this.message = message;
		this.createdAt = createdAt;
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", user=" + user + ", message=" + message + ", createdAt=" + createdAt + ", tags="
				+ tags + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tweet_tag", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

//GETTERS/SETTERS
	
	


	
}// endClass
