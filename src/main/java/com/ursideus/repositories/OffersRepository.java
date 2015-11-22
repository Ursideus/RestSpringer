package com.ursideus.repositories;

import com.ursideus.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by dovw on 11/21/15.
 */
@Repository
public interface OffersRepository extends JpaRepository<Offer, Long> {
}
