package io.github.robertkoch.quarkussocial.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.robertkoch.quarkussocial.domain.model.Post;
import io.github.robertkoch.quarkussocial.domain.model.User;
import io.github.robertkoch.quarkussocial.rest.domain.repository.FollowerRepository;
import io.github.robertkoch.quarkussocial.rest.domain.repository.PostRepository;
import io.github.robertkoch.quarkussocial.rest.domain.repository.UserRepository;
import io.github.robertkoch.quarkussocial.rest.dto.CreatePostRequest;
import io.github.robertkoch.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	private FollowerRepository followerRepository;

	@Inject
	public PostResource(UserRepository userRepository, PostRepository postRepository, FollowerRepository followerRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.followerRepository = followerRepository;
	}
	
	@POST
	@Transactional
	public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request) {
		User user = userRepository.findById(userId);
		if(user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Post post = new Post();
		post.setText(request.getText());
		post.setUser(user);
		
		postRepository.persist(post);
		
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public Response listPosts(@PathParam("userId") Long userId, @HeaderParam("followerId") Long followerId) {
		User user = userRepository.findById(userId);
		
		if(user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		User follower = userRepository.findById(followerId);
		
		if(follower == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Inexistent followerId").build();
		}
		
		boolean follows = followerRepository.follows(follower, user);
		if(!follows) {
			return Response.status(Response.Status.FORBIDDEN).entity("You can't see these posts").build();
		}
		
		PanacheQuery<Post> query = postRepository.find("user", Sort.by("dateTime",Sort.Direction.Descending), user);
		
		List<Post> list = query.list();
		
		List<PostResponse> postResponseList = list.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
		
		return Response.ok(postResponseList).build();
	}
}
