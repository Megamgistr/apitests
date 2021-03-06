package api_tests.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetInfo {
    private String id;
    private Category category;
    private String name;
    private List<Tag> tags;
    private Status status;
    private List<String> photoUrls;
}
