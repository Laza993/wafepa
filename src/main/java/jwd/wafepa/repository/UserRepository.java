package jwd.wafepa.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByFirstName(String name);

	List<User> findAllByFirstName(String name);

	List<User> findAllByLastName(String lastName);

	List<User> findAllByEmail(String email);
	
	
//	@Query("select r from User r where r.firstName = :name")
//	List<User> findBybooomb(@Param("name") String name);

}
