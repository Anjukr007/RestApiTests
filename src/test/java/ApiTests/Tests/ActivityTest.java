package ApiTests.Tests;

import ApiTests.Models.Activity;
import ApiTests.Services.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ActivityTest {
    private ActivityService service;

    @BeforeTest
    public void setup(){
        service = new ActivityService();
    }


    @Test
    public void saveNewActivity() throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        Activity activity= mapper.readValue(new File("src/test/resources/activity.json"),Activity.class);
        service.SaveNewActivty(activity);
    }

    @Test
    public void getAllActivities() throws IOException {
        service.GetAllActivities();
    }
    @Test
    public void getSingleActivity() throws IOException {
       int id= 20;
        service.GetSingleActivity(id);
    }
    @Test
    public void updateActivity() throws IOException {
        int id= 20;
        ObjectMapper mapper= new ObjectMapper();
        Activity activity= mapper.readValue(new File("src/test/resources/activity.json"),Activity.class);
        activity.setId(id);
        activity.setTitle("updated activity "+id );
        service.UpdateActivity(id,activity);
    }
    @Test
    public void deleteActivity() throws IOException {
        int id= 20;
        service.DeleteActivity(id);
    }

}
