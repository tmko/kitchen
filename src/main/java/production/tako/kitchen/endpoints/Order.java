package production.tako.kitchen.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import production.tako.kitchen.domain.OrderWebJSON;

@Slf4j
@RestController
@RequestMapping("/order")
public class Order {

    @PostMapping
    public ResponseEntity<String> verify (@RequestBody OrderWebJSON body)
    {
        return ResponseEntity.ok().body( body.toString() );
    }
}
