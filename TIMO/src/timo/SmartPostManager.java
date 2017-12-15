package timo;

import java.util.ArrayList;

public class SmartPostManager {
	private static SmartPostManager instance = null;
	private ArrayList<SmartPost> postList = null;
	private ArrayList<SmartPost> markedPostList = null;
	
	private SmartPostManager() {
		postList = new ArrayList<SmartPost>();
		markedPostList = new ArrayList<SmartPost>();
		}
	
	public static SmartPostManager getInstance() {
		if(instance == null) {
			instance = new SmartPostManager();
		}
		return instance;
	}
	
	public ArrayList<SmartPost> getPosts(){
		return postList;
	}
	
	public ArrayList<SmartPost> getMarkedPosts(){
		return markedPostList;
	}
	
	public void setPosts(ArrayList<SmartPost> pl){
		this.postList = pl;
	}
}
