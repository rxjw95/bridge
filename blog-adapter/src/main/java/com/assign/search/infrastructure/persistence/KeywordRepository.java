package com.assign.search.infrastructure.persistence;

import com.assign.search.domain.Keyword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    @Query("select k from Keyword k order by k.searchFrequency desc limit 10")
    List<Keyword> findTopTenKeyword();

}
