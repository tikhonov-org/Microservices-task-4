package microservices.task.service;

import microservices.task.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UsersApiService {
    private final String url = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<String> getAllUsersResponse() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class
        );
    }

    public HttpHeaders getCurSessionHeaders(){
        ResponseEntity<String> response = getAllUsersResponse();
        HttpHeaders headers = response.getHeaders();
        String sessionIdHeader = headers.getFirst(HttpHeaders.SET_COOKIE);

        HttpHeaders curSessionHeaders = new HttpHeaders();
        curSessionHeaders.set(HttpHeaders.COOKIE, sessionIdHeader);
        curSessionHeaders.setContentType(MediaType.APPLICATION_JSON);
        return curSessionHeaders;
    }

    public ResponseEntity<String> addUser(User user, HttpHeaders headers) {
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(
                url,
                request,
                String.class
        );
    }

    public ResponseEntity<String> updateUser(User user, HttpHeaders headers) {
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                String.class
        );
    }

    public ResponseEntity<String> deleteUser(User user, HttpHeaders headers) {
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        return restTemplate.exchange(
                url+"/"+user.getId(),
                HttpMethod.DELETE,
                request,
                String.class
        );
    }
}
