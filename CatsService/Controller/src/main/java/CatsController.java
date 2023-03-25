import java.util.List;
import Dto.CatDto;

public class CatsController {
    private CatService service;
    public CatsController(CatService service){
        this.service = service;
    }
    public void saveCat(CatDto cat) {
        service.saveCat(cat);
    }

    public void deleteCat(Long id) {
        service.deleteCat(id);
    }

    public void updateCat(CatDto cat) {
        service.updateCat(cat);
    }

    public List<CatDto> findAll() {
        return service.getAll();
    }

    public CatDto findCat(Long id) {
        return service.findCat(id);
    }
}
