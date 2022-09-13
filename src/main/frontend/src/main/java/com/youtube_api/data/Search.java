package com.youtube_api.data;

/*
    키워드 검색
 */

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.gson.JsonArray;
import com.youtube_api.model.SearchItem;
import org.json.simple.parser.ParseException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Prints a list of videos based on a search term.
 *
 * @author Jeremy Walker
 */

@Service
public class Search {

    /**
     * Global instance properties filename.
     */
    //private static String PROPERTIES_FILENAME = "youtube.properties";

    private static Logger log = LoggerFactory.getLogger(Search.class);
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 2;
    private static final String GOOGLE_YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    private static final String YOUTUBE_SEARCH_FIELDS = "items(id/kind,id/videoId,snippet/title,snippet/description,snippet/channelTitle,snippet/thumbnails/default/url)";
    private static final String YOUTUBE_APIKEY = "AIzaSyCHAdXUjuGX9fznEeA6Fz6EHpABipgxN98";

    /**
     * Global instance of Youtube object to make all API requests.
     */
    private static YouTube youtube;

    static {
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }

    // 추가
    public static List<SearchItem> youTubeSearch(String searchQuery, int maxSearch) {
        String queryTerm = searchQuery + " +ASMR";
        log.info("Starting YouTube search... " +queryTerm);
        List<SearchItem> rvalue = new ArrayList<SearchItem>();

        try {
            if (youtube != null) {
                // Define the API request for retrieving search results.
                YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));

                String apiKey = YOUTUBE_APIKEY;
                search.setKey(apiKey);
                search.setQ(queryTerm);
                search.setType(Collections.singletonList("video"));
                String youTubeFields = YOUTUBE_SEARCH_FIELDS;
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                if (youTubeFields != null && !youTubeFields.isEmpty()) {
                    search.setFields(youTubeFields);
                } else {
                    search.setFields(YOUTUBE_SEARCH_FIELDS);
                }

                SearchListResponse searchResponse = search.execute();
                List<SearchResult> searchResultList = searchResponse.getItems();

                if (searchResultList != null) {
                    for (SearchResult rid : searchResultList) {
                        SearchItem item = new SearchItem(
                                GOOGLE_YOUTUBE_URL + rid.getId().getVideoId(),
                                rid.getSnippet().getTitle(),
                                rid.getSnippet().getChannelTitle(),
                                rid.getSnippet().getDescription(),
                                rid.getSnippet().getThumbnails().getDefault().getUrl());
                        rvalue.add(item);
                        log.info("title : " + rid.getSnippet().getTitle());
                    }
                }
            }

            } catch (GoogleJsonResponseException e){
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage());
            } catch(IOException e){
                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            } catch(Throwable t){
                t.printStackTrace();
            }

            return rvalue;
    }
}
