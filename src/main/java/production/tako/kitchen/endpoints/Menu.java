package production.tako.kitchen.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/menu")
public class Menu {

    private Map<String, String> menus;

    @Value("${menu.folder}")
    private String menuDir;

    @PostConstruct
    public void init () {
        menus = load();
        String latest = menus.keySet().stream().max(String::compareTo).get();
        menus.put("current", menus.get(latest) );
    }

    @GetMapping
    public ResponseEntity<String> getMenu ()
    {
        return generalGet ("current");
    }

    @GetMapping("/{version}")
    public ResponseEntity<String> getMenu (@PathVariable("version") String version)
    {
        return generalGet(version);
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refresh (@RequestParam("current") String version) {
        if (version == null || version.isEmpty())
            return ResponseEntity.badRequest().body("Query parameter, current, is missing.");

        this.menus = load();
        String temp = this.menus.get(version);
        if ( temp == null )
            return ResponseEntity.badRequest().body("Query parameter, current, is not found.");
        else {
            this.menus.put("current", temp);
            return ResponseEntity.ok().body("refreshing done<br>" + temp);
        }
    }

    private ResponseEntity<String> generalGet (String version) {
        String content = menus.get(version);
        if ( content == null || content.isEmpty() )
            return ResponseEntity.badRequest().body("version not found");
        else
            return ResponseEntity.ok().body( content );
    }

    private Map<String, String> load () {
        JsonParser parser = new BasicJsonParser();
        Map<String, String> result = new HashMap<>();

        Path folder = Paths.get(".", menuDir);
        try {
            Stream<Path> folderStream = Files.list(folder);
            folderStream
                .filter (path -> path.toFile().isFile())
                .forEach(path -> {
                    try {
                        String content = new String ( Files.readAllBytes(path) );
                        String version = parser.parseMap(content).get("version").toString();
                        result.put( version, content );
                    } catch (IOException e) {
                        log.error("fail to open {}", path.getName(0));
                    }
                });
        } catch (IOException e) {
            log.error("Cannot open the menu folder, {}, {}", System.getProperty("user.dir"), menuDir, e);
        }

        return result;
    }
}
