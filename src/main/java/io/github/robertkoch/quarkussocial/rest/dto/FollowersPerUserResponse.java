package io.github.robertkoch.quarkussocial.rest.dto;

import java.util.List;

public class FollowersPerUserResponse {

	private Integer followersCount;
	private List<FollowerResponse> content;
	
	public FollowersPerUserResponse() {
		super();
	}

	public Integer getFollowersCount() {
		return followersCount;
	}
	
	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}
	
	public List<FollowerResponse> getContent() {
		return content;
	}
	
	public void setContent(List<FollowerResponse> content) {
		this.content = content;
	}
	
}
