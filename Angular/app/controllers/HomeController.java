package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.LeagueTableService;

import java.util.List;

/*
 * This class is HomeController
 * This controller class is used to retrieve all match statistics from service class LeagueTableService,
 * and convert the data into json and provide a response to the request
 *
 * Last modified date - 30/12/2020
 */

public class HomeController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result getMatchStats(String clubType, String sortingCriteria) {
        List<FootballClub> result = LeagueTableService.getMatchStats(clubType, sortingCriteria);
        logger.debug("In HomeController.getMatchStats(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(jsonData).as("application/json");

    }

}
