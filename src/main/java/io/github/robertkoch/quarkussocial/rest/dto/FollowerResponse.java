package io.github.robertkoch.quarkussocial.rest.dto;

import io.github.robertkoch.quarkussocial.domain.model.Follower;

public class FollowerResponse {

	private Long id;
	private String name;
	
	public FollowerResponse() {
		super();
	}
	
	public FollowerResponse(Follower follower) {
		this(follower.getId(), follower.getFollower().getName());
	}

	public FollowerResponse(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
