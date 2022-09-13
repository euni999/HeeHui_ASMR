package com.youtube_api.Controller;

import com.youtube_api.data.Search;
import com.youtube_api.model.SearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
    List<SearchItem> searchYouTube(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="5") String items) {

        int max = Integer.parseInt(items);
        List<SearchItem> result = Search.youTubeSearch(search, max);
        return result;
    }

}
