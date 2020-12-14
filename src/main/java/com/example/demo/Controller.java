package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.io.FileUtils;
import org.json.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


@RestController
public class Controller {

    @GetMapping("/people")
    public List<Person> getPersons() {
        PersonService service = new PersonService();
        List<Person> response = new DemoResponse(service.getAllPeople()).getPeopleList();
        return response;
    }


    @GetMapping(value = "/tocsv")
    public ResponseEntity getCsv(HttpServletRequest request) throws IOException, URISyntaxException {
        // Call other internal endpoint to get Json String
        RestTemplate restTemplate = new RestTemplate();
        String otherEndpoint = "http://localhost:8084/people";
        ResponseEntity<String> entity = restTemplate.getForEntity(otherEndpoint, String.class);
        String jsonString = entity.getBody();
        jsonString = "{\"fileName\":" + jsonString + "}";
        // Create csv
        JSONObject output;
        output = new JSONObject(jsonString);
        JSONArray docs = output.getJSONArray("fileName");
        File file = new File("people.csv");
        String csv = CDL.toString(docs);
        FileUtils.writeStringToFile(file, csv);
        //Make csv available for download
        String projectPath = System.getProperty("user.dir");
        Path path = Paths.get(new URI("file:/" + projectPath.replace("\\", "//") + "//" + file.getName()));
        Resource resource = null;
        resource = new UrlResource(path.toUri());

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {


        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);

    }


}
