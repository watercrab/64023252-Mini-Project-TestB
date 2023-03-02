package com.example.testminiproject.repositories;



import com.example.testminiproject.models.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel,  Long> {

    @Override
    Optional<ReportModel> findById(Long aLong);

    List<ReportModel> findByName(String name);
}
