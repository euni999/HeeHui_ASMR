package com.youtube_api.Controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import com.google.gson.JsonArray;
import com.youtube_api.data.Search;
import com.youtube_api.model.SearchItem;
import com.youtube_api.model.Video;
import com.youtube_api.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONArray;
import org.json.JSONObject;
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
        mapper.writeValue(new File("tingle.json"), item);
        String result = mapper.writeValueAsString(item);
        System.out.println(result);
        return result;
    }

}
