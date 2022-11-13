package service;

import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.repository.SkillRepository;
import net.coldlight.hibernatecrudapp.service.SkillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Captor
    ArgumentCaptor<Skill> skillArgumentCaptor;

    @Test
    public void createSkillTest() {
        //given
        String skillName = "Java";
        Skill skill = new Skill();
        skill.setSkillName(skillName);

        //when
        when(skillRepository.save(skill)).thenReturn(skill);

        //then
        assertEquals(skill, skillService.createSkill(skillName));
    }

    @Test(expected = RuntimeException.class)
    public void createSkillExceptionTest() {
        //given
        String skillName = "Java";
        Skill skill = new Skill();
        skill.setSkillName(skillName);

        //when
        when(skillRepository.save(skill)).thenThrow(new RuntimeException());

        //then
        assertEquals(skill, skillService.createSkill(skillName));
    }

    @Test
    public void getAllSkillsEmptyTest() {
        //when
        when(skillRepository.getAll()).thenReturn(new ArrayList<>());

        //then
        assertTrue(skillService.getAllSkills().isEmpty());
    }

    @Test
    public void getAllSkillsTest() {
        //given
        String skillName = "Java";
        Skill skill = Skill.builder().skillName(skillName).build();


        String skillName2 = "Python";
        Skill skill2 = Skill.builder().skillName(skillName2).build();


        String skillName3 = "SQL";
        Skill skill3 = Skill.builder().skillName(skillName3).build();

        //when
        when(skillRepository.getAll()).thenReturn(List.of(skill, skill2, skill3));

        //then
        List<Skill> allSkills = skillService.getAllSkills();
        assertEquals(3, allSkills.size());

        Skill firstSkill = allSkills.get(0);
        assertEquals(skillName, firstSkill.getSkillName());

        Skill secondSkill = allSkills.get(1);
        assertEquals(skillName2, secondSkill.getSkillName());

        Skill thirdSkill = allSkills.get(2);
        assertEquals(skillName3, thirdSkill.getSkillName());
    }

    @Test(expected = RuntimeException.class)
    public void getSkillsExceptionTest() {

        //when
        when(skillRepository.getAll()).thenThrow(new RuntimeException());

        //then
        skillService.getAllSkills();
    }

    @Test
    public void getSkillByIDTest() {
        //given
        String skillName = "Java";
        Skill skill = Skill.builder()
                .skillName(skillName)
                .build();
        Long id = 1L;

        //when
        when(skillRepository.getByID(id)).thenReturn(skill);

        //then
        Skill skillByID = skillService.getSkillByID(id);
        assertEquals(skillName, skillByID.getSkillName());
    }

    @Test(expected = RuntimeException.class)
    public void getSkillByIDException() {
        //given
        Long id = 1L;

        //when
        when(skillRepository.getByID(id)).thenThrow(new RuntimeException());

        //then
        skillService.getSkillByID(id);
    }

    @Test(expected = RuntimeException.class)
    public void updateSkillDataBaseExceptionTest() {

        //when
        when(skillRepository.getByID(any())).thenThrow(new RuntimeException());

        //then
        skillService.updateSkill(1L, null);
    }

    @Test(expected = RuntimeException.class)
    public void updateSkillNotFoundTest() {
        //when
        when(skillRepository.getByID(any())).thenReturn(null);

        //then
        skillService.updateSkill(1L, null);
    }

    @Test(expected = RuntimeException.class)
    public void deleteSkillNotFoundTest() {
        //when
        when(skillRepository.getByID(any())).thenReturn(null);

        //then
        skillService.deleteSkillByID(1L);
    }

    @Test
    public void deleteSkillTest() {
        String skillName = "Java";
        Skill skill = Skill.builder()
                .id(1L)
                .skillName(skillName)
                .build();

        //when
        when(skillRepository.getByID(1L)).thenReturn(skill);
        doNothing().when(skillRepository).delete(skillArgumentCaptor.capture());

        //then
        skillService.deleteSkillByID(1L);
        assertEquals(1L, skillArgumentCaptor.getValue().getId().longValue());
    }
}
