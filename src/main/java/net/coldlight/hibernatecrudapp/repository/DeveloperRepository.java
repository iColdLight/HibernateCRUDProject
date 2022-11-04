package net.coldlight.hibernatecrudapp.repository;

import net.coldlight.hibernatecrudapp.model.Developer;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    boolean haveSkill(long developerId, long skillId);
}
