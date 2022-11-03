package dev.migwel.friends;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class FriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication.class, args);
    }

    @Bean
    public InstanceInformation instanceInformation(@Value("${instanceId}") String instanceIdStr) {
        if (instanceIdStr == null) {
            throw new RuntimeException("Wrong instance ID provided");
        }
        int instanceId = Integer.parseInt(instanceIdStr);
        return new InstanceInformation(instanceId);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
}
