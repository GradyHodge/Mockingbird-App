package com.tts.MockingBird.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.MockingBird.model.Tag;
import com.tts.MockingBird.model.Tweet;
import com.tts.MockingBird.model.User;
import com.tts.MockingBird.repository.TagRepository;
import com.tts.MockingBird.repository.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	 public  List<Tweet> findAll() {
         List<Tweet> tweets = tweetRepository.findAllByOrderByCreatedAtDesc();
         return formatTweets(tweets);
     }

	public List<Tweet> findAllByUser(User user) {
		List<Tweet> tweets = tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
		return tweets;
	}

	public List<Tweet> findAllByUsers(List<User> users) {
		List<Tweet> tweets = tweetRepository.findAllByUserInOrderByCreatedAtDesc(users);
		return tweets;
	}

	public void save(Tweet tweet) {
		//handleTags(tweet);
		tweetRepository.save(tweet);
	}

//	private void handleTags(Tweet tweet) {
//		List<Tag> tags = new ArrayList<Tag>();
//		Pattern pattern = Pattern.compile("#\\w+");
//		Matcher matcher = pattern.matcher(tweet.getMessage());
//		while (matcher.find()) {
//			String phrase = matcher.group().substring(1).toLowerCase();
//			Tag tag = tagRepository.findByPhrase(phrase);
//			if (tag == null) {
//				tag = new Tag();
//				tag.setPhrase(phrase);
//				tagRepository.save(tag);
//			}
//			tags.add(tag);
//		}
//		tweet.setTags(tags);
//	}

	private List<Tweet> formatTweets(List<Tweet> tweets) {
		addTagLinks(tweets);
		return tweets;
	}

	private void addTagLinks(List<Tweet> tweets) {
	        Pattern pattern = Pattern.compile("#\\w+");
	        for(Tweet tweet: tweets) {
	            String message = tweet.getMessage();
	            Matcher matcher = pattern.matcher(message);
	            Set<String> tags = new HashSet<String>();
	            while(matcher.find()) {
	                tags.add(matcher.group());
	            }
	            for(String tag : tags) {
	                message = message.replaceAll(tag, 
	                "<a class=\"tag\" href=\"/tweets/" + tag.substring(1).toLowerCase() + "\">" + tag + "</a>");
	            }
	            tweet.setMessage(message);
	        }
	}  
	     
	    

//suggested bug fix
//	public List<Tweet> findAllWithTag(String tag) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
