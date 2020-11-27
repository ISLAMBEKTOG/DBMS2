package org.example.service.specialist;

import org.example.dao.specialist.SpecialistDAOImpl;
import org.example.dao.specialist.SpecialistsDAO;
import org.example.model.Specialists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistsServiceImpl implements SpecialistsService {
    private final SpecialistsDAO specialistsDAO;
    private final SpecialistDAOImpl specialistDAOImpl;

    @Autowired
    public SpecialistsServiceImpl(SpecialistsDAO specialistsDAO, SpecialistDAOImpl specialistDAOImpl) {
        this.specialistsDAO = specialistsDAO;
        this.specialistDAOImpl = specialistDAOImpl;
    }

    @Override
    public List<Specialists> getSpecialists() {
        return (List<Specialists>) specialistDAOImpl.findAll();
    }

    @Override
    public Specialists addSpecialist(Specialists specialists) {
        return specialistDAOImpl.addSpecialist(specialists);
    }

    @Override
    public void deleteSpecialist(Specialists specialists) {
//        specialistDAOImpl.deleteSpecialist(specialists.getId());
        specialistsDAO.delete(specialists);
    }

    @Override
    public List<Specialists> getSpecialistsByCategoryId(Long first, Long second) {
        return null;
    }

    @Override
    public Integer getCountOfSpecialistsByCategoryId(Long id) {
        return specialistDAOImpl.getCountOfAllSpecialistsByCategoryId(id);
    }

    @Override
    public List<Specialists> findPaginated(int first, int second, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return specialistsDAO.findSpecialistsByCategoriesId((long) first, (long) second, paging);
    }

    @Override
    public Specialists getSpecialistById(Long id) {
        return specialistDAOImpl.findSpecialistsById(id);
    }

    @Override
    public List<Specialists> getAllSpecialistsFromUserResumeByUserId(Long id) {
        return specialistDAOImpl.getAllSpecialistFromUserResumeByUserId(id);
    }
}
