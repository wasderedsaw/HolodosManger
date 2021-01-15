package com.example.demo.repository;

import com.example.demo.entity.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
}
