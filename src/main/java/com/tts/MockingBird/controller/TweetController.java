package com.tts.MockingBird.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.MockingBird.model.Tweet;
import com.tts.MockingBird.model.User;
import com.tts.MockingBird.service.TweetService;
import com.tts.MockingBird.service.UserService;

@Controller
	public class TweetController {
	    @Autowired
	    private UserService userService;
		
	    @Autowired
	    private TweetService tweetService;
	    
	    @GetMapping(value= {"/tweets", "/"})
	    	public String getFeed(Model model){
	        List<Tweet> tweets = tweetService.findAll();
	        model.addAttribute("tweetList", tweets);
	        return "feed";
	    }
	    
	    @GetMapping(value = "/tweets/new")
	    public String getTweetForm(Model model) {
	        System.out.println("this is user in post " +" yo momma");

	        model.addAttribute("tweet", new Tweet());
	        return "newTweet";
	    }
	    
	    @PostMapping(value = "/tweets")
	    public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model) {
	        User user = userService.getLoggedInUser();
	        System.out.println("this is user in post " + user);
	        if (!bindingResult.hasErrors()) {
	            tweet.setUser(user);
	            tweetService.save(tweet);
	            model.addAttribute("successMessage", "Tweet successfully created!");
	            model.addAttribute("tweet", new Tweet());
	        }
	        return "newTweet";
	    }
	    
//	    @GetMapping(value = "/tweets/{tag}")
//	    public String getTweetsByTag(@PathVariable(value="tag") String tag, Model model) {
//	        List<Tweet> tweets = tweetService.findAllWithTag(tag);
//	        model.addAttribute("tweetList", tweets);
//	        model.addAttribute("tag", tag);
//	        return "taggedTweets";
//	    }
	    
}//endClass
	
