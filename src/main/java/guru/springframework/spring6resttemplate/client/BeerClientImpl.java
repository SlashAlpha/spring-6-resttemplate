package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public static final String GET_BEER_PATH="/api/v1/beer";
    public static final String GET_BEER_BY_ID_PATH="/api/v1/beer/{beerId}";

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        UriComponentsBuilder uriComponentsBuilder =UriComponentsBuilder.fromPath(GET_BEER_PATH);
        ResponseEntity<BeerDTOPageImpl> pageResponse=
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(),BeerDTOPageImpl.class);
        return pageResponse.getBody();
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName,String beerStyle,Boolean showInventory,Integer pageNumber,Integer pageSize) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder =UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName!=null){
            uriComponentsBuilder.queryParam("beerName",beerName);
        }
        if (beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle",beerStyle);
        }
        if (showInventory!=null){
            uriComponentsBuilder.queryParam("showInventory",showInventory);
        }
        if (pageNumber!=null){
            uriComponentsBuilder.queryParam("pageNumber",pageNumber);
        }
        if (pageSize!=null){
            uriComponentsBuilder.queryParam("pageSize",pageSize);
        }

//        ResponseEntity<String> stringResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,String.class);
//        ResponseEntity<Map> mapResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,Map.class);
//        ResponseEntity<JsonNode> jsonResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,JsonNode.class);
        ResponseEntity<BeerDTOPageImpl> pageResponse=
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(),BeerDTOPageImpl.class);

//  jsonResponse.getBody().findPath("content").elements().forEachRemaining(node ->{
//            System.out.println(node.get("beerName"));
//        });

        System.out.println(pageResponse.getBody());
        return pageResponse.getBody();

    }

    @Override
    public BeerDTO getBeerById(UUID beerId) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<BeerDTO> beerDTOResponse=
                restTemplate.getForEntity(GET_BEER_BY_ID_PATH,BeerDTO.class,beerId);
        return beerDTOResponse.getBody();
    }

    @Override
    public BeerDTO createBeer(BeerDTO newDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        URI uri=restTemplate.postForLocation(GET_BEER_PATH,newDto);
        return restTemplate.getForObject(uri.getPath(),BeerDTO.class);
    }

    @Override
    public BeerDTO updateBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.put(GET_BEER_BY_ID_PATH,beerDTO,beerDTO.getId());


        return getBeerById(beerDTO.getId());
    }

    @Override
    public void deleteBeer(UUID beerId) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.delete(GET_BEER_BY_ID_PATH,beerId);

    }
}
