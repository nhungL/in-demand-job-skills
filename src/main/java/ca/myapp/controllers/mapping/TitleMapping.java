package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Degree;
import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.dto.TitleDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

public class TitleMapping {
    public static Title mapTitleDTOToTitle(@NotNull TitleDTO titleDTO) {
        Title title = new Title();
        title.setTitle(titleDTO.getTitle());
        title.setMinSalary(titleDTO.getMinSalary());
        title.setMaxSalary(titleDTO.getMaxSalary());
        title.setAvgSalary(titleDTO.getAvgSalary());
        title.setCount(titleDTO.getCount());
        // title.setTop10Skills(titleDTO.getTop10Skills());
        // Convert DegreeDTO to Degree
        List<Degree> eduDegrees = titleDTO.getEduDegree().stream()
                                           .map(DegreeMapping::mapDTOToGraphQLObject)
                                           .collect(Collectors.toList());
        title.setEduDegree(eduDegrees);
        
        return title;
    }
}
