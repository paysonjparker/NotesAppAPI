package com.gcu.business;

import com.gcu.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://notes-app-react.herokuapp.com")
@RestController
@RequestMapping("/user-service")
public class UserRestService {

    Logger logger = LoggerFactory.getLogger(UserRestService.class);

    @Autowired
    UserBusinessService userBusinessService;

    /**
     * Gets all users in JSON format.
     * @return JSON users.
     */
    @GetMapping(path="/users", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUsersAsJson()
    {
        logger.info("==========> ENTER UserRestService.getUsersAsJson() at " + "/users");

        try
        {
            // Get users
            List<UserModel> users = userBusinessService.getUsers();

            // If user doesn't exist
            if(users == null)
            {
                logger.info("==========> EXIT UserRestService.getUsersAsJson() at " + "/users");

                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                logger.info("==========> EXIT UserRestService.getUsersAsJson() at " + "/users");

                // Return user and OK
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("==========> EXCEPTION UserRestService.getUsersAsJson() at " + "/users", e);
            logger.info("==========> EXIT UserRestService.getUsersAsJson() at " + "/users");

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/user-{userIdNumber}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUserById(@PathVariable("userIdNumber") int useridNumber)
    {
        logger.info("==========> ENTER UserRestService.getUserById() at " + "/users-{}", useridNumber);

        try
        {
            // Get user
            UserModel user = userBusinessService.getUserById(useridNumber);

            // If user doesn't exist
            if(user == null)
            {
                logger.info("==========> EXIT UserRestService.getUserById() at " + "/users-{}", useridNumber);

                // Return not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                logger.info("==========> EXIT UserRestService.getUserById() at " + "/users-{}", useridNumber);

                // Return user and OK
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("==========> EXCEPTION UserRestService.getUserById()", e);
            logger.info("==========> EXIT UserRestService.getUserById() at " + "/users-{}", useridNumber);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
