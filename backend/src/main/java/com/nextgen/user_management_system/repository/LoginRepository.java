package com.nextgen.user_management_system.repository;

import com.nextgen.user_management_system.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
}
