package com.tts.MockingBird.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;
	
    private String phrase;
    
    @ManyToMany(mappedBy = "tags")
    private List<Tweet> tweets;

	public void setPhrase(String phrase2) {
		// TODO Auto-generated method stub
	}
	
	public Tag() {}
	
	public Tag(Long id, String phrase, List<Tweet> tweets) {
			super();
			this.id = id;
			this.phrase = phrase;
			this.tweets = tweets;
		}
		
	
// Getters/Setters -take a hike lombak		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public String getPhrase() {
		return phrase;
	}
}//endClass