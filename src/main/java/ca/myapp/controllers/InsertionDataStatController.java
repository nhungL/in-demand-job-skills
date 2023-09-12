package ca.myapp.controllers;

import ca.myapp.controllers.mapping.InsertionStatMapping;
import ca.myapp.dgs.graph.schema.InsertionDataStat;
import ca.myapp.entity.InsertionStatEntity;
import ca.myapp.repositories.InsertionStatRepo;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class InsertionDataStatController {
    @Autowired
    InsertionStatRepo insertionStatRepo;

    @DgsQuery
    public List<InsertionDataStat> getInsertionDataStat() {
        try {
            List<InsertionStatEntity> insertionStatEntities = insertionStatRepo.findAll();
            return insertionStatEntities.stream().map(InsertionStatMapping::mapInsertionStatEntityToInsertionStat).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
