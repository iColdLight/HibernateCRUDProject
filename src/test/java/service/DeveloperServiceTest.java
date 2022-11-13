package service;

import net.coldlight.hibernatecrudapp.model.Developer;
import net.coldlight.hibernatecrudapp.repository.DeveloperRepository;
import net.coldlight.hibernatecrudapp.service.DeveloperService;
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
public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperService developerService;

    @Captor
    ArgumentCaptor<Developer> developerArgumentCaptor;

    @Test
    public void createDeveloperTest() {
        //given
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);

        //when
        when(developerRepository.save(developer)).thenReturn(developer);

        //then
        assertEquals(developer, developerService.createDeveloper(firstName, lastName));

    }

    @Test(expected = RuntimeException.class)
    public void createDeveloperExceptionTest() {
        //given
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);

        //when
        when(developerRepository.save(developer)).thenThrow(new RuntimeException());

        //then
        developerService.createDeveloper(firstName, lastName);

    }

    @Test
    public void getAllDevelopersEmptyTest() {
        //when
        when(developerRepository.getAll()).thenReturn(new ArrayList<>());

        //then
        assertTrue(developerService.getAllDevelopers().isEmpty());

    }

    @Test
    public void getAllDevelopersTest() {
        //given
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = Developer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();


        String firstName2 = "Bob";
        String lastName2 = "Bobovich";
        Developer developer2 = Developer.builder()
                .firstName(firstName2)
                .lastName(lastName2)
                .build();


        String firstName3 = "John";
        String lastName3 = "Johnovich";
        Developer developer3 = Developer.builder()
                .firstName(firstName3)
                .lastName(lastName3)
                .build();

        //when
        when(developerRepository.getAll()).thenReturn(List.of(developer, developer2, developer3));

        //then
        List<Developer> allDevelopers = developerService.getAllDevelopers();
        assertEquals(3, allDevelopers.size());

        Developer firstDeveloper = allDevelopers.get(0);
        assertEquals(firstName, firstDeveloper.getFirstName());
        assertEquals(lastName, firstDeveloper.getLastName());

        Developer secondDeveloper = allDevelopers.get(1);
        assertEquals(firstName2, secondDeveloper.getFirstName());
        assertEquals(lastName2, secondDeveloper.getLastName());

        Developer thirdDeveloper = allDevelopers.get(2);
        assertEquals(firstName3, thirdDeveloper.getFirstName());
        assertEquals(lastName3, thirdDeveloper.getLastName());

    }

    @Test(expected = RuntimeException.class)
    public void getDevelopersExceptionTest() {


        //when
        when(developerRepository.getAll()).thenThrow(new RuntimeException());

        //then
        developerService.getAllDevelopers();

    }

    @Test
    public void getDeveloperByIDTest() {
        //given
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = Developer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        Long id = 1L;

        //when
        when(developerRepository.getByID(id)).thenReturn(developer);

        //then
        Developer developerByID = developerService.getDeveloperByID(id);
        assertEquals(firstName, developerByID.getFirstName());
        assertEquals(lastName, developerByID.getLastName());

    }

    @Test(expected = RuntimeException.class)
    public void getDeveloperByIDException() {
        //given
        Long id = 1L;

        //when
        when(developerRepository.getByID(id)).thenThrow(new RuntimeException());

        //then
        developerService.getDeveloperByID(id);
    }

    @Test(expected = RuntimeException.class)
    public void updateDeveloperDataBaseExceptionTest() {

        //when
        when(developerRepository.getByID(any())).thenThrow(new RuntimeException());

        //then
        developerService.updateDeveloper(1L, null, null);
    }


    @Test(expected = RuntimeException.class)
    public void updateDeveloperNotFoundTest() {
        //when
        when(developerRepository.getByID(any())).thenReturn(null);

        //then
        developerService.updateDeveloper(1L, null, null);
    }

    @Test
    public void updateDeveloperTest() {
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = Developer.builder()
                .id(1L)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        //when
        when(developerRepository.getByID(1L)).thenReturn(developer);
        when(developerRepository.update(developerArgumentCaptor.capture())).thenReturn(developer);

        //then
        developerService.updateDeveloper(1L, "Bob", "Bobovich");
        assertEquals("Bob", developerArgumentCaptor.getValue().getFirstName());
        assertEquals("Bobovich", developerArgumentCaptor.getValue().getLastName());
        assertEquals(1L, developerArgumentCaptor.getValue().getId().longValue());

    }

    @Test(expected = RuntimeException.class)
    public void deleteDeveloperNotFoundTest() {
        //when
        when(developerRepository.getByID(any())).thenReturn(null);

        //then
        developerService.deleteDeveloperByID(1L);
    }

    @Test
    public void deleteDeveloperTest() {
        String firstName = "Igor";
        String lastName = "Popovich";
        Developer developer = Developer.builder()
                .id(1L)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        //when
        when(developerRepository.getByID(1L)).thenReturn(developer);
        doNothing().when(developerRepository).delete(developerArgumentCaptor.capture());


        //then
        developerService.deleteDeveloperByID(1L);
        assertEquals(1L, developerArgumentCaptor.getValue().getId().longValue());
    }

}
