package service;

import net.coldlight.hibernatecrudapp.model.Speciality;
import net.coldlight.hibernatecrudapp.repository.SpecialityRepository;
import net.coldlight.hibernatecrudapp.service.SpecialityService;
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
public class SpecialityServiceTest {

    @Mock
    private SpecialityRepository specialityRepository;

    @InjectMocks
    private SpecialityService specialityService;

    @Captor
    ArgumentCaptor<Speciality> specialityArgumentCaptor;

    @Test
    public void createSpecialityTest(){
        //given
        String specialityName = "Java Developer";
        Speciality speciality = new Speciality();
        speciality.setSpecialityName(specialityName);

        //when
        when(specialityRepository.save(speciality)).thenReturn(speciality);

        //then
        assertEquals(speciality, specialityService.createSpeciality(specialityName));
    }

    @Test(expected = RuntimeException.class)
    public void createSpecialityExceptionTest() {
        //given
        String specialityName = "Java Developer";
        Speciality speciality = new Speciality();
        speciality.setSpecialityName(specialityName);

        //when
        when(specialityRepository.save(speciality)).thenThrow(new RuntimeException());

        //then
        assertEquals(speciality, specialityService.createSpeciality(specialityName));
    }

    @Test
    public void getAllSpecialitiesEmptyTest() {
        //when
        when(specialityRepository.getAll()).thenReturn(new ArrayList<>());

        //then
        assertTrue(specialityService.getAllSpecialities().isEmpty());
    }

    @Test
    public void getAllSpecialitiesTest() {
        //given
        String specialityName = "Java developer";
        Speciality speciality = Speciality.builder().specialityName(specialityName).build();

        String specialityName2 = "Python developer";
        Speciality speciality2 = Speciality.builder().specialityName(specialityName2).build();

        String specialityName3 = "Data analyst";
        Speciality speciality3 = Speciality.builder().specialityName(specialityName3).build();

        //when
        when(specialityRepository.getAll()).thenReturn(List.of(speciality, speciality2, speciality3));

        //then
        List<Speciality> allSpecialities = specialityService.getAllSpecialities();
        assertEquals(3, allSpecialities.size());

        Speciality firstSpeciality = allSpecialities.get(0);
        assertEquals(specialityName, firstSpeciality.getSpecialityName());

        Speciality secondSpeciality = allSpecialities.get(1);
        assertEquals(specialityName2, secondSpeciality.getSpecialityName());

        Speciality thirdSpeciality = allSpecialities.get(2);
        assertEquals(specialityName3, thirdSpeciality.getSpecialityName());
    }

    @Test(expected = RuntimeException.class)
    public void getSpecialitiesExceptionTest() {

        //when
        when(specialityRepository.getAll()).thenThrow(new RuntimeException());

        //then
        specialityService.getAllSpecialities();
    }

    @Test
    public void getSpecialityByIDTest() {
        //given
        String specialityName = "Java developer";
        Speciality speciality = Speciality.builder()
                .specialityName(specialityName)
                .build();
        Long id = 1L;

        //when
        when(specialityRepository.getByID(id)).thenReturn(speciality);

        //then
        Speciality specialityByID = specialityService.getSpecialityByID(id);
        assertEquals(specialityName, specialityByID.getSpecialityName());
    }

    @Test(expected = RuntimeException.class)
    public void updateSpecialityDataBaseExceptionTest() {

        //when
        when(specialityRepository.getByID(any())).thenThrow(new RuntimeException());

        //then
        specialityService.updateSpeciality(1L, null);
    }

    @Test(expected = RuntimeException.class)
    public void updateSpecialityNotFoundTest() {
        //when
        when(specialityRepository.getByID(any())).thenReturn(null);

        //then
        specialityService.updateSpeciality(1L, null);
    }

    @Test(expected = RuntimeException.class)
    public void deleteSpecialityNotFoundTest() {
        //when
        when(specialityRepository.getByID(any())).thenReturn(null);

        //then
        specialityService.deleteSpeciality(1L);
    }

    @Test
    public void deleteSpecialityTest() {
        String specialityName = "Java developer";
        Speciality speciality = Speciality.builder()
                .id(1L)
                .specialityName(specialityName)
                .build();

        //when
        when(specialityRepository.getByID(1L)).thenReturn(speciality);
        doNothing().when(specialityRepository).delete(specialityArgumentCaptor.capture());

        //then
        specialityService.deleteSpeciality(1L);
        assertEquals(1L, specialityArgumentCaptor.getValue().getId().longValue());
    }
}
