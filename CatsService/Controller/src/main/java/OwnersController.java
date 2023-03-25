import Dto.OwnerDto;

import java.util.List;

public class OwnersController {
    private OwnerService service;

    public OwnersController(OwnerService service){
        this.service = service;
    }
    public void saveOwner(OwnerDto owner) {
        service.saveOwner(owner);
    }

    public void deleteOwner(Long id) {
        service.deleteOwner(id);
    }

    public void updateOwner(OwnerDto owner) {
        service.updateOwner(owner);
    }

    public List<OwnerDto> findAll() {
        return service.findAll();
    }

    public OwnerDto OwnerOwner(Long id) {
        return service.findOwnerDto(id);
    }
}
