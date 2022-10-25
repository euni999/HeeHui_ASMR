package com.backend.controller;

import com.backend.dto.VideoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.service.Search;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
public class SearchController {
    @CrossOrigin("http://localhost:3000")
    @RequestMapping(value={"/search"}, method= RequestMethod.GET)
    public @ResponseBody
    String searchYouTube(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="2") String items) throws IOException {

        System.out.println("item : " + search);
        int max = Integer.parseInt(items);
        List<VideoDto> item = Search.youTubeSearch(search, max);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        System.out.println(result);
        return result;
    }



}
