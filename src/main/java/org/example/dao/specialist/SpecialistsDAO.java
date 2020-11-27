package org.example.dao.specialist;

import org.example.model.Specialists;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistsDAO extends PagingAndSortingRepository<Specialists, String> {
    @Query("select s from Specialists s where s.category.id =:first or s.category.id = :second order by s.date desc")
    List<Specialists> findSpecialistsByCategoriesId(@Param("first") Long first,
                                                    @Param("second") Long second,
                                                    Pageable pageable);
}
