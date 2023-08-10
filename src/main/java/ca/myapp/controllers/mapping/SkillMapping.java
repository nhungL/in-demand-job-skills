package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.entity.SkillEntity;
import org.jetbrains.annotations.NotNull;

public class SkillMapping {
    public static Skill mapSkillEntityToSkill(@NotNull SkillEntity skillEntity) {
        Skill skill = new Skill();
        skill.setSkill(skillEntity.getSkill());
        skill.setCount(skillEntity.getCount());
        skill.setPercent(skillEntity.getPercent());
        return skill;
    }
}
