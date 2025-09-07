package com.restaurant.restaurant.Repo;

import com.restaurant.restaurant.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo,Long> {
}
