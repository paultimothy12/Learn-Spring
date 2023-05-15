package com.timothy.entities;

import com.timothy.controller.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDataRepository extends JpaRepository<TestData, Integer> {

}
