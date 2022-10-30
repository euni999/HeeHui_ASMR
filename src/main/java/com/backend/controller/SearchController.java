package com.backend.controller;

import com.backend.dto.VideoDto;
import com.backend.service.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.service.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class SearchController {
    @Autowired
    private VideoService videoService;

    // 검색창
    @CrossOrigin("http://localhost:3000")
    @RequestMapping(value={"/search"}, method= RequestMethod.GET)
    public @ResponseBody
    String searchYouTube(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="10") String items) throws IOException {

        System.out.println("item : " + search);
        int max = Integer.parseInt(items);
        List<VideoDto> item = Search.youTubeSearch(search, max);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        System.out.println(result);
        return result;
    }

    @CrossOrigin("http://localhost:3000")
    @RequestMapping(value={"/category/{word}"}, method= RequestMethod.GET)
    public List<Map<String, Objects>> searchCategory(@PathVariable String word) throws IOException {
        System.out.println("category : " + word);
        return videoService.categoryVideo(word);
    }

}
