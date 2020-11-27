package org.example.service.specialist;

import org.example.model.Specialists;

import java.util.List;

public interface SpecialistsService {
    List<Specialists> getSpecialists();

    Specialists addSpecialist(Specialists specialists);

    void deleteSpecialist(Specialists specialists);

    List<Specialists> getSpecialistsByCategoryId(Long first, Long second);

    Integer getCountOfSpecialistsByCategoryId(Long id);

    List<Specialists> findPaginated(int first, int second, int pageNo, int pageSize);

    Specialists getSpecialistById(Long id);

    List<Specialists> getAllSpecialistsFromUserResumeByUserId(Long id);
}
