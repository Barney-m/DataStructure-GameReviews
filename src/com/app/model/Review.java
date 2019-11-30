package com.app.model;

public class Review{
	
	private String date_posted;
	private int funny;
	private int helpful;
	private int hour_played;
	private boolean is_early_access_review;
	private String recommendation;
	private String review;
	private String title;
	
	public Review() {}
	
	public Review (String date_posted,int funny,int helpful,int hour_played,boolean is_early_access_review,String recommendation,String review,String title) {
		this.date_posted = date_posted;
		this.funny = funny;
		this.helpful = helpful;
		this.hour_played = hour_played;
		this.is_early_access_review = is_early_access_review;
		this.recommendation = recommendation;
		this.review = review;
		this.title = title;
	}
	
	public String getDatePosted() {
		return date_posted;
	}
	
	public int getFunny() {
		return funny;
	}
	
	public int getHelpful() {
		return helpful;
	}
	
	public int getHourPlayed() {
		return hour_played;
	}
	
	public boolean getIsEarlyAccessReview() {
		return is_early_access_review;
	}
	
	public String getRecommendation() {
		return recommendation;
	}
	
	public String getReview() {
		return review;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDatePosted(String date_posted) {
		this.date_posted = date_posted;
	}
	
	public void setFunny(int funny) {
		this.funny = funny;
	}
	
	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}
	
	public void setHourPlayed(int hour_played) {
		this.hour_played = hour_played;
	}
	
	public void setEarlyAccess(boolean is_early_access_review) {
		this.is_early_access_review = is_early_access_review;
	}
	
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}