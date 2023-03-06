package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;
    @Test
    void beerClientTestImplNull() {
        beerClient.listBeers();
    }

    @Test
    void beerClientTestImpl() {
        beerClient.listBeers(null,"ALE",false,10,10);
    }

    @Test
    void getBeerById() {
        Page<BeerDTO> beerDTOS=beerClient.listBeers();
        BeerDTO beerDTO=beerDTOS.getContent().get(0);

       BeerDTO byId= beerClient.getBeerById(beerDTO.getId());
        assertNotNull(byId);
    }

    @Test
    void createNewBeerTest() {
        BeerDTO newDto=BeerDTO.builder()
                .beerName("SlashBeer")
                .beerStyle(BeerStyle.GOSE)
                .upc("12345")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(10)
                .build();
        BeerDTO savedBeerDTO=beerClient.createBeer(newDto);
        assertNotNull(savedBeerDTO);
    }

    @Test
    void updateBeerTest() {
        BeerDTO newDto=BeerDTO.builder()
                .beerName("SlashBeer")
                .beerStyle(BeerStyle.GOSE)
                .upc("12345")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(10)
                .build();
        BeerDTO beerDTO=beerClient.createBeer(newDto);
        final String newName="mango Slash";
        beerDTO.setBeerName(newName);
        BeerDTO updatedBeer= beerClient.updateBeer(beerDTO);
        assertEquals(newName,updatedBeer.getBeerName());

    }

    @Test
    void deleteBeerTest() {
        BeerDTO newDto=BeerDTO.builder()
                .beerName("SlashBeer")
                .beerStyle(BeerStyle.GOSE)
                .upc("12345")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(10)
                .build();
        BeerDTO beerDTO=beerClient.createBeer(newDto);
        beerClient.deleteBeer(beerDTO.getId());
        assertThrows(HttpClientErrorException.class,()->{
            beerClient.getBeerById(beerDTO.getId());
        });


    }
}