package mozt.springframework.spring6restmvc.bootstrap;

import mozt.springframework.spring6restmvc.repository.BeverageRepository;
import mozt.springframework.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DataLoaderTest {
    DataLoader dataLoader;

    @Autowired
    BeverageRepository bs;
    @Autowired
    CustomerRepository cs;


    void beforeEach(){
        this.dataLoader = new DataLoader(this.bs, this.cs);
    }


    public DataLoaderTest(DataLoader dl){
        dataLoader = dl;
    }

    @Test
    void dataLoaderTest() throws Exception {
        this.dataLoader.run(null);
        assertThat(this.bs.count()).isEqualTo(3);
        assertThat(this.cs.count()).isEqualTo(3);
    }
}
