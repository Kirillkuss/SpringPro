package com.example.test.services;

import com.example.test.repositories.PersonRepository;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith( JMockitExtension.class )
@DisplayName( "Тестирование сервиса PersonService используя JMockit")
public class TestPersonService {

    private static EntityManagerFactory emf;

    @Tested
    PersonService service;
    @Injectable
    EntityManager entityManager;
    @Injectable
    PersonRepository personRepository;

    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory( "test");
    }

    @AfterAll
    public static void tearDownClass() {
        emf.close();
    }

    @BeforeEach
    public void tearDown() {
        entityManager = emf.createEntityManager();
    }

    @AfterEach
    public void setUp(){
        entityManager.close();
    }

    @Test
    @DisplayName("Получение списка пользователей через EM")
    public void testFindAllTwo() throws Exception{
        assertNotNull( service.findAllTwo());
    }

    @Test
    @DisplayName("Получение списка пользователей через репозиторий")
    public void testFindAll() throws Exception{
        service.findAll();
    }

    @Test
    @DisplayName("Поиск пользователя по ИД")
    public void testFindById() throws Exception{
         service.getPersonById( 1L );
    }

    /**@Test
    @DisplayName("Добавление пользователя")
    public void testSavePerson() throws Exception{
        Person REQUET = new Person();
        service.savePerson( REQUET );
    }

    @Test
    @DisplayName("Обновление пользователя")
    public void testUpdatePerson() throws Exception{
        Person UPDATE = (Person) entityManager.createQuery( "select e from Person e where e.id = 3L ")
                                              .getResultList()
                                              .stream().findFirst().orElse( null );
        service.updatePerson( UPDATE );
    }**/

    @Test
    @DisplayName("Удаление пользователя")
    public void testDeletePerson() throws Exception{
        service.deletePerson( 13L );
    }

}
