package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Result;
import services.MatchGenerateService;

import java.util.List;

import static play.mvc.Results.ok;

/*
* This class is GenerateMatchController
* This controller class is used to retrieve generated matches from service class MatchGenerateService,
* and convert the data into json and provide a response to the request
*
* Last modified date - 30/12/2020
*/
public class GenerateMatchController {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result getRandomMatchResults() {
        List<FootballClub> result = MatchGenerateService.generateMatch();
        logger.debug("In GenerateMatchController.getRandomMatchResults(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(jsonData);

    }
}
