package com.assign.search.infrastructure.persistence;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

import com.assign.search.domain.Keyword;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    @Query("select k from Keyword k order by k.searchFrequency desc limit 10")
    List<Keyword> findTopTenKeyword();

    @Lock(PESSIMISTIC_WRITE)
    @Query("select k from Keyword k where k.keyword = :keyword")
    Optional<Keyword> findByIdWithLock(@Param("keyword") String keyword);

}
