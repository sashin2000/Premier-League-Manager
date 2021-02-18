package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.PlayedMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Result;
import services.PlayedMatchesService;

import java.util.List;

import static play.mvc.Results.ok;

/*
 * This class is HomeController
 * This controller class is used to retrieve played matches with dates from service class PlayedMatchesController,
 * and convert the data into json and provide a response to the request
 *
 * Last modified date - 30/12/2020
 */

public class PlayedMatchesController {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result getPlayedMatches(String searchDate) {
        List<PlayedMatch> result = PlayedMatchesService.getMatches(searchDate);
        logger.debug("In PlayedMatchesService.getPlayedMatches(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(jsonData);

    }
}
