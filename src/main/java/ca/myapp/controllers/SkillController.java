package ca.myapp.controllers;

import ca.myapp.dgs.graph.schema.OrderDirection;
import ca.myapp.dgs.graph.schema.TitleFilter;
import ca.myapp.dto.SkillDTO;
import ca.myapp.service.SkillService;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@DgsComponent
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @DgsQuery
    public List<SkillDTO> getOverallSkillsStat(@InputArgument("order") OrderDirection order) {
        return skillService.getSkillStat();
    }

    @DgsQuery
    public List<SkillDTO> getSkillsByTitle(@InputArgument("inputFilter") TitleFilter inputFilter, @InputArgument("order") OrderDirection order) {
        
        String title = inputFilter != null ? inputFilter.getTitle() : null;
        // Sort orderDirection = order != null ? order.getDirection() : Sort.desc;
        
        if (title != null) {
            return skillService.getSkillStatByTitle(title);
        }
        else {
            System.out.println("No inputFilter found");
            return skillService.getSkillStat();
        }
    }
}
