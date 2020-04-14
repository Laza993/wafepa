package jwd.wafepa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	@Query("select r from User r where "
			+ "(:name is null or r.firstName like :name) AND"
			+ "(:email is null or r.email like :email) AND"
			+ "(:lastName is null or r.lastName like :lastName) AND"
			+ "(:userName is null or r.username like :userName)")
	Page<User> search(
			@Param("name") String name, 
			@Param("email") String email,
			@Param("lastName") String lastName,
			@Param("userName") String userName,
			Pageable pageRequest);
	
	
//	@Query("select r from User r where r.firstName = :name")
//	List<User> findBybooomb(@Param("name") String name);

}
