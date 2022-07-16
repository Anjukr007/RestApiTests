package ApiTests.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {
    private int id;
    private String title;
    private String dueDate;
    private Boolean completed;
}
