package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Activity findByName(String name);

	List<Activity> findAllByName(String name);

}
