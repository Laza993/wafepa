package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Activity findByName(String name);

	List<Activity> findAllByName(String name);
	
	@Query("SELECT a FROM Activity a WHERE "
			+ ":activityName is null or a.name like :activityName")
	Page<Activity> search(
			@Param(value = "activityName") String activityName,
			Pageable pageRequest);

}
