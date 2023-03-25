import Dto.*;
import dao.*;
import models.*;

import java.util.ArrayList;
import java.util.List;
public class CatService {
    private CatDao catDao;
    private OwnerService ownerService = new OwnerService();

    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public Long saveCat(CatDto cat) {
        Owner owner = ownerService.findOwner(cat.getOwnerId());
        Cat catModel = new Cat(cat.getId(), cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor(), owner, new ArrayList<>());
        return catDao.save(catModel);
    }

    public void deleteCat(Long id) {
        Cat cat = catDao.findCat(id);
        catDao.delete(cat);
    }

    public void updateCat(CatDto cat) {
        Owner owner = ownerService.findOwner(cat.getOwnerId());
        Cat catModel = new Cat(cat.getId(), cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor(), owner, new ArrayList<>());
        catDao.update(catModel);
    }

    public List<CatDto> getAll() {
        return  catDao.getAll().stream().map(x -> new CatDto(x.getId(), x.getName(),
                x.getDateOfBirth(), x.getBreed()
                , x.getColor(), x.getOwner().getId(), x.getCatFriends().stream().map(y -> y.getId()).toList())).toList();
    }

    public CatDto findCat(Long id) {
        Cat cat = catDao.findCat(id);
        CatDto catDto = new CatDto(cat.getId(), cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor(), cat.getOwner().getId(), new ArrayList<>());
        return catDto;
    }
}
