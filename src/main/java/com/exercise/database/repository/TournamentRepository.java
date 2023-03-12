package com.exercise.database.repository;

import com.exercise.database.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Tournament findByName(String name);
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE product SET description = ?1, category = ?2, price = ?3 WHERE id = ?4", nativeQuery = true)
//    void updateById(String description, String category, BigDecimal price, long id);

}
