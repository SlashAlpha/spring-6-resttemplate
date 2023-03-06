package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {
    Page<BeerDTO> listBeers(String beerName,String beerStyle,Boolean showInventory,Integer pageNumber,Integer pageSize);
    Page<BeerDTO> listBeers();


    BeerDTO getBeerById(UUID uuid);

    BeerDTO createBeer(BeerDTO newDto);

    BeerDTO updateBeer(BeerDTO beerDTO);

    void deleteBeer(UUID beerId);
}
