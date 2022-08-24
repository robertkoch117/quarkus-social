package io.github.robertkoch.quarkussocial.rest.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.robertkoch.quarkussocial.domain.model.Follower;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower>{

}
