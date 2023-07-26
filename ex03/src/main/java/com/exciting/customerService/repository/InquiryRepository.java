package com.exciting.customerService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
import org.springframework.stereotype.Repository;

import com.exciting.entity.InquiryEntity;

<<<<<<< HEAD
import javax.transaction.Transactional;

=======
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Integer>,JpaSpecificationExecutor<InquiryEntity>{

	@Query(value="select * from inquiry where ref = ?1",nativeQuery = true)
	List<Optional<InquiryEntity>> findByRef(int inquiry_num);
<<<<<<< HEAD
	@Transactional
	@Modifying
	@Query(value="delete from inquiry where ref=:inquiry_num", nativeQuery = true)
	void deleteinquiry(@Param("inquiry_num") int inquiry_num);
=======
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

}
