package com.backend.Controller;

import com.backend.model.SearchItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.data.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello";
    }

    @RequestMapping(value={"/search"}, method=RequestMethod.GET)
    public @ResponseBody
    String searchYouTube(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="50") String items) throws IOException {

        int max = Integer.parseInt(items);
        List<SearchItem> item = Search.youTubeSearch(search, max);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        System.out.println(result);
        return result;
    }

}
