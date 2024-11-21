//package com.project.system.repositories;
//
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//public interface StateRepository extends JpaRepository<State, UUID> {
//    @Query(value = "SELECT * FROM state WHERE is_active = true", nativeQuery = true)
//    List<State> findActiveStates();
//
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE state SET is_active = :status WHERE id = :id", nativeQuery = true)
//    void updateStateStatus(@Param("id") UUID id, @Param("status") boolean status);
//
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE state SET update_in = :updateIn WHERE id = :id", nativeQuery = true)
//    void updateUpdateIn(@Param("id") UUID id, @Param("updateIn") LocalDateTime updateIn);
//
//
//}
