package com.example.test.services;

import com.example.test.entity.Animal;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;


@Disabled
@DisplayName( "Тестирование сервиса AnimalService через Mockito c подкл БД")
public class TestAnimalService {

   private static EntityManagerFactory emf;
   private        EntityManager em;
   private final List<Animal> animalList = new ArrayList<>();
   private Animal animal = new Animal();
   private  AnimalService service;

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
        service = mock( AnimalService.class );
        service.em = em;
    }

    @AfterEach
    public void setUp(){
        em.close();
    }

    @Test
    @DisplayName( "Получение списка всех питомцев")
    public void testGetAll() throws Exception{
        Mockito.lenient().when( service.getAll() ).thenCallRealMethod();
        Mockito.lenient().when( service.getAll() ).thenReturn(  animalList );
        Mockito.lenient().when( service.getAll() ).then(( InvocationOnMock inv ) ->{
            return inv.callRealMethod();
        });
        assertNotNull( service.getAll());
        assertEquals( em.createQuery(" select e from Animal e").getResultList(), service.getAll() );
        Mockito.verify( service, times(2 )).getAll();
    }

    @Test
    @DisplayName( "Получение питомца по ИД")
    public void testFindById() throws Exception{
        Long ID = 1L;
        Mockito.lenient().when( service.getById( ID )).thenCallRealMethod();
        Mockito.lenient().when( service.getById( ID )).thenReturn( animal );
        Mockito.lenient().when( service.getById( ID )).then(( InvocationOnMock inv ) ->{
            return inv.callRealMethod();
        });
        assertNotNull( service.getById( ID ));
        assertEquals( service.getById( ID ), em.createQuery("select e from Animal e where e.id = ?1")
                                                .setParameter(1 , ID )
                                                .getResultList()
                                                .stream().findFirst().orElse( null ));
        Mockito.verify( service, times( 2 )).getById( ID );
    }

    @Test
    @DisplayName("Получение количества питомцев")
    public void testCount() throws Exception{
        Mockito.lenient().when( service.getCount() ).thenCallRealMethod();
        Mockito.lenient().when( service.getCount()).thenReturn( Integer.SIZE );
        Mockito.lenient().when( service.getCount() ).then(( InvocationOnMock inv ) ->{
            return inv.callRealMethod();
        });
        assertNotNull( service.getCount() );
        assertNotEquals( 0, service.getCount() );
        Mockito.verify( service, times( 2 )).getCount();
    }
}
