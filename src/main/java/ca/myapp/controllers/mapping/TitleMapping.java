package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.entity.TitleEntity;
import org.jetbrains.annotations.NotNull;

public class TitleMapping {
    public static Title mapTitleEntityToTitle(@NotNull TitleEntity titleEntity) {
        Title title = new Title();
        title.setTitle(titleEntity.getTitle());
        title.setMinSalary(titleEntity.getMinSalary());
        title.setMaxSalary(titleEntity.getMaxSalary());
        title.setAvgSalary(titleEntity.getAvgSalary());
        title.setCount(titleEntity.getCount());
        title.setTopSkills(titleEntity.getTopSkills());
        title.setEduDegree(titleEntity.getDegrees());
        return title;
    }
}
