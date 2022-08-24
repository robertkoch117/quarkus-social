package io.github.robertkoch.quarkussocial.rest.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.robertkoch.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{

}
