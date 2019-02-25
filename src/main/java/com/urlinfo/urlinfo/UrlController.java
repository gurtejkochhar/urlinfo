package com.urlinfo.urlinfo;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UrlController {

    private static final String template = "Url %s is , %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final List<String> URLS = new ArrayList<>();

    

    @RequestMapping("/urlinfo")
    public UrlData urldata(@RequestParam(value="url", defaultValue="localhost") String urlstr) {

        for (String curVal : URLS){
            if (curVal.contains(urlstr)){
                return new UrlData(counter.incrementAndGet(),
                        String.format(template, urlstr, "safe"));
            }
        }
        return new UrlData(counter.incrementAndGet(),
                String.format(template, urlstr, "unsafe"));
    }


    @RequestMapping(path = "/urladd", method = POST)
    public ResponseEntity<?> writeDownUrl(final @RequestBody Map<String, Object> input) {
        if (input.containsKey("url") && input.get("url") != null) {
            URLS.add(input.get("url").toString());
            return ok(input.get("url").toString());
        }
        else
            return badRequest().body("'url' is required.");
    }
}
