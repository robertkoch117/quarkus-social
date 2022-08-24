package io.github.robertkoch.quarkussocial.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.robertkoch.quarkussocial.rest.domain.repository.FollowerRepository;
import io.github.robertkoch.quarkussocial.rest.domain.repository.UserRepository;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

	private FollowerRepository followerRepository;
	private UserRepository userRepository;

	@Inject
	public FollowerResource(FollowerRepository followerRepository, UserRepository userRepository) {
		this.followerRepository = followerRepository;
		this.userRepository = userRepository;
	}
	
}
