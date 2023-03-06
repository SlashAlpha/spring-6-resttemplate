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

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;
    public static final String BASE_URL="http://localhost:8080";
    public static final String GET_BEER_PATH="/api/v1/beer";

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<String> stringResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,String.class);
//        ResponseEntity<Map> mapResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,Map.class);
//        ResponseEntity<JsonNode> jsonResponse=
//                restTemplate.getForEntity(GET_BEER_PATH,JsonNode.class);
        ResponseEntity<BeerDTOPageImpl> pageResponse=
                restTemplate.getForEntity(GET_BEER_PATH,BeerDTOPageImpl.class);

//  jsonResponse.getBody().findPath("content").elements().forEachRemaining(node ->{
//            System.out.println(node.get("beerName"));
//        });

        System.out.println(pageResponse.getBody());
        return pageResponse.getBody();

    }
}
