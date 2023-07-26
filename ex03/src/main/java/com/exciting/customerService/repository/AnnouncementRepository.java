package com.exciting.customerService.repository;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
import org.springframework.stereotype.Repository;

import com.exciting.entity.AnnouncementEntity;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer>,JpaSpecificationExecutor<AnnouncementEntity> {

<<<<<<< HEAD
	@Query(nativeQuery = true , value="select * from announcement where announcement_num = ?1")
	Optional<AnnouncementEntity> findByAnnouncementNum(int announcement_num);
=======
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
}
