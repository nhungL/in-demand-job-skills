package ca.myapp.controllers;

import ca.myapp.dgs.graph.schema.TitleFilter;
import ca.myapp.dto.TitleDTO;
import ca.myapp.service.TitleService;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@DgsComponent
public class TitleController {
    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @DgsQuery
    public List<TitleDTO> getAllTitles(@InputArgument("inputFilter") TitleFilter inputFilter) {
        String title = inputFilter != null ? inputFilter.getTitle() : null;
        return titleService.getAllTitles(title);
    }

    @DgsQuery
    public List<String> getDistinctTitles() {
        return titleService.getDistinctTitles();
    }
}
