package com.gl.bms.repository;

import com.gl.bms.entity.Branchs;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

// Make this into a Branch repository
public interface BranchRepository extends JpaRepository<Branchs,Long> {

}
