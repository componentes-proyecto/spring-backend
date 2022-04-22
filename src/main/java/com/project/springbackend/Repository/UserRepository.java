package com.project.springbackend.Repository;
import com.project.springbackend.Domain.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUsername(String username);
}
