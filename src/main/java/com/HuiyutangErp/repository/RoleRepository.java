package com.HuiyutangErp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.HuiyutangErp.pojo.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String roleName);
}
