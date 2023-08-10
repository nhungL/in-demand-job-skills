package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Degree;
import ca.myapp.entity.DegreeEntity;
import org.jetbrains.annotations.NotNull;

public class DegreeMapping {
    public static Degree mapDegreeEntityToDegree(@NotNull DegreeEntity degreeEntity) {
        Degree degree = new Degree();
        degree.setDegree(degreeEntity.getDegree());
        degree.setCount(degreeEntity.getCount());
        degree.setPercent(degreeEntity.getPercent());
        return degree;
    }
}
