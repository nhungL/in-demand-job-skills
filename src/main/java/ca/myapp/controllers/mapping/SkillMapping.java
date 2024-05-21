package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.dto.SkillDTO;
import org.jetbrains.annotations.NotNull;

public class SkillMapping {
    public static Skill mapSkillEntityToSkill(@NotNull SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setSkillId(skillDTO.getSkillId());
        skill.setTitle(skillDTO.getTitle());
        skill.setSkill(skillDTO.getSkill());
        skill.setCountByTitle(skillDTO.getCountByTitle());
        skill.setPercByTitle(skillDTO.getPercByTitle());
        skill.setOverallPerc(skillDTO.getOverallPerc());
        return skill;
    }
}
