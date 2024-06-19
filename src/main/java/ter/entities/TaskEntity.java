package ter.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("TASKENTITY")
public class TaskEntity {
    @Id
    private int task_id;
    private String task_text;
    private List<Double> params;
}
