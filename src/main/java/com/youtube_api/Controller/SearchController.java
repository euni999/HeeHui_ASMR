package com.youtube_api.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube_api.data.Search;
import com.youtube_api.model.SearchItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value="items", required=false, defaultValue="5") String items) throws JsonProcessingException {

        int max = Integer.parseInt(items);
        List<SearchItem> item = Search.youTubeSearch(search, max);
        String result = new ObjectMapper().writeValueAsString(item);
        System.out.println(result);
        return result;
    }

}
