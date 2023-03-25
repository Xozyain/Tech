package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatDto {
    private Long id;
    private String name;

    private LocalDate dateOfBirth;
    private String breed;
    private String color;
    private Long ownerId;

    private List<Long> friendsId;
}
