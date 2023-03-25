
import Dto.CatDto;
import Dto.OwnerDto;
import dao.*;
import models.*;
import java.util.List;
public class OwnerService {
    private OwnerDao ownerDao = new OwnerDao();
    private CatDao catDao = new CatDao();

    public OwnerService() {
    }

    public void saveOwner(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getDateOfBirth(),
                ownerDto.getCatsId().stream().map(x -> catDao.findCat(x)).toList());
        ownerDao.save(owner);
    }

    public void deleteOwner(Long id) {
        Owner owner = findOwner(id);
        ownerDao.delete(owner);
    }

    public void updateOwner(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getDateOfBirth(),
                ownerDto.getCatsId().stream().map(x -> catDao.findCat(x)).toList());
        ownerDao.update(owner);
    }

    public List<OwnerDto> findAll() {
        return ownerDao.findAll().stream().map(x -> new OwnerDto(x.getId(), x.getName(), x.getDateOfBirth(),
                x.getCats().stream().map(y -> y.getId()).toList())).toList();
    }

    public Owner findOwner(Long id) {
        return ownerDao.findOwner(id);
    }
    public OwnerDto findOwnerDto(Long id) {
        Owner owner = ownerDao.findOwner(id);
        return new OwnerDto(owner.getId(), owner.getName(), owner.getDateOfBirth(),
                owner.getCats().stream().map(x -> x.getId()).toList());
    }
}
