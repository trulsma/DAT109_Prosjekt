package no.hvl.dat109.spring.test;

import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.controller.BedriftController;
import no.hvl.dat109.spring.repository.BedriftRepository;
import no.hvl.dat109.spring.service.BedriftService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
public class Test {

    @TestConfiguration
    static class BedriftServiceConf{
        @Bean
        public BedriftService bedriftService(){
            return new BedriftService();
        }
    }

    @Autowired
    private BedriftService bedriftService;

    @MockBean
    private BedriftRepository bedriftRepository;

    @Before
    public void setUp(){

        BedriftBean b = new BedriftBean("B1", "Bes1");
        int id = b.getBedriftid();
        bedriftRepository.save(b);
      Mockito.when(bedriftRepository.findById(id)).thenReturn(java.util.Optional.of(b));
    }
    @org.junit.Test
    public void compExists(){
        String name = "B1";


        BedriftBean b1 = bedriftService.getBedriftById(1);
        System.out.println(b1);
        assertEquals(name, b1.getBedriftnavn());
    }
}
