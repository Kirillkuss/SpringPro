package com.example.test.services;

import com.example.test.entity.Animal;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ExtendWith( JMockitExtension.class )
@DisplayName( "Тестирование сервиса AnimalService используя JMockit")
public class AnimalServiceTest {

    private static EntityManagerFactory emf;

    @Tested      AnimalService service;
    @Injectable  EntityManager em;
    Animal animal = new Animal();

    Long ID = 1L;

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
        em = emf.createEntityManager();
    }

    @AfterEach
    public void setUp(){
        em.close();
    }

    @Test
    @DisplayName( "Список питомцев")
    public void testGetAll() throws Exception{
        assertNotNull(service.getAll());
    }

    @Test
    @DisplayName( "Поиск по ИД ")
    public void testFindIdAnimal() throws Exception{
        assertNotNull( service.getById( ID ));
    }
    @Test
    @DisplayName( "Удаление пользователя")
    public void testDeleteAnimalByID() throws Exception{
        service.delAnimal( 4L );
    }

    @Test
    @DisplayName("Добавление питомца")
    public void testAddAnimal() throws Exception{
        animal.setId( 500L );
        animal.setName("JMockit");
        animal.setCount( 10 );
        animal.setAmount( BigDecimal.ONE );
        service.addAnimal( animal );
    }

    @Test
    @DisplayName("Обновление питомца")
    public void testModyAnimal() throws Exception{
        animal = service.getById( 1L);
        service.modyAnimal( animal );
    }

    @Test
    @DisplayName("Количество питомцев")
    public void testGetCount() throws Exception{
        assertNotEquals(0, service.getCount());
    }

}
