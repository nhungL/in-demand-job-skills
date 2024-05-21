package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Degree;
import ca.myapp.dto.DegreeDTO;
import ca.myapp.entity.DegreeEntity;

public class DegreeMapping {
        // DTO to Entity
        public static DegreeEntity mapDTOToEntity(DegreeDTO degreeDTO) {
            DegreeEntity degreeEntity = new DegreeEntity();
            degreeEntity.setDegreeId(degreeDTO.getDegreeId());
            degreeEntity.setTitle(degreeDTO.getTitle());
            degreeEntity.setDegree(degreeDTO.getDegree());
            return degreeEntity;
        }
    
        // Entity to GraphQL Object
        public static Degree mapEntityToGraphQLObject(DegreeEntity degreeEntity) {
            Degree degree = new Degree();
            degree.setDegreeId(degreeEntity.getDegreeId());
            degree.setTitle(degreeEntity.getTitle());
            degree.setDegree(degreeEntity.getDegree());
            return degree;
        }
    
        // DTO to GraphQL Object
        public static Degree mapDTOToGraphQLObject(DegreeDTO degreeDTO) {
            Degree degree = new Degree();
            degree.setDegreeId(degreeDTO.getDegreeId());
            degree.setTitle(degreeDTO.getTitle());
            degree.setDegree(degreeDTO.getDegree());
            degree.setCountByTitle(degreeDTO.getCountByTitle());
            degree.setPercByTitle(degreeDTO.getPercByTitle());
            degree.setOverallPerc(degreeDTO.getOverallPerc());
            return degree;
        }
}