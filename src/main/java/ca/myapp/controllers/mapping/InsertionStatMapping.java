package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.InsertionDataStat;
import ca.myapp.entity.InsertionStatEntity;
import org.jetbrains.annotations.NotNull;

public class InsertionStatMapping {
    public static InsertionDataStat mapInsertionStatEntityToInsertionStat(@NotNull InsertionStatEntity insertionStatEntity) {
        InsertionDataStat insertionStat = new InsertionDataStat();
        insertionStat.setJobsAdded(insertionStatEntity.getJobsAdded());
        insertionStat.setUpdatedAt(insertionStatEntity.getUpdatedAt());
        insertionStat.setTotalJobs(insertionStatEntity.getTotalJobs());
        return insertionStat;
    }
}
