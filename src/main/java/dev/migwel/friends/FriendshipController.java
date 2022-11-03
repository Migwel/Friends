package dev.migwel.friends;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class FriendshipController {

    private final InstanceInformation instanceInformation;
    private final HttpClient httpClient;

    public FriendshipController(InstanceInformation instanceInformation, HttpClient httpClient) {
        this.instanceInformation = instanceInformation;
        this.httpClient = httpClient;
    }

    @GetMapping("/friends") 
    @ResponseBody
    public boolean friends(@RequestParam int otherInstancePort) throws IOException, InterruptedException {
        HttpRequest internalRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+ otherInstancePort +"/internalfriends?otherInstancePort="+ instanceInformation.id()))
                .build();
        HttpResponse<String> httpInternalResponse = httpClient.send(internalRequest, HttpResponse.BodyHandlers.ofString());
        return Boolean.parseBoolean(httpInternalResponse.body());
    }

    @GetMapping("/internalfriends")
    @ResponseBody
    public boolean internalFriends(@RequestParam int otherInstancePort) {
        return instanceInformation.id() % 2 == otherInstancePort % 2;
    }
}
