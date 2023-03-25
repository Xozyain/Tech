import Dto.CatDto;
import dao.CatDao;
import models.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceTest {
    private CatDao catDao;
    private CatService catService;
    @BeforeEach
    public void setup() {
        catDao = Mockito.mock(CatDao.class);
        catService = new CatService(catDao);
        CatDto catDto = new CatDto(1L, "cat", LocalDate.of(2023, 3, 8), "d",
                "white", -1L, new ArrayList<>());
    }
    @Test
    public void saveCatCheckExistence(){
        Long catId = 1L;
        Mockito.when(catDao.save(Mockito.any(Cat.class))).thenReturn(catId);
        CatDto catDto = new CatDto(1L, "cat", LocalDate.of(2023, 3, 8), "d",
                "white", -1L, new ArrayList<>());
        Long actualId = catService.saveCat(catDto);
        Assertions.assertEquals(catId, actualId);
    }

    @Test
    public void saveCatDeleteCat(){
        Long catId = 1L;
        Mockito.when(catDao.save(Mockito.any(Cat.class))).thenReturn(catId);
        CatDto catDto = new CatDto(1L, "cat", LocalDate.of(2023, 3, 8), "d",
                "white", -1L, new ArrayList<>());
        Long actualId = catService.saveCat(catDto);
        Assertions.assertEquals(catId, actualId);

        Mockito.doNothing().when(catDao).delete(Mockito.any(Cat.class));
        Mockito.when(catDao.findCat(1L)).thenReturn(null);

        catService.deleteCat(catDto.getId());
        Cat foundCat = catDao.findCat(1L);

        Assertions.assertNull(foundCat);
    }

}
