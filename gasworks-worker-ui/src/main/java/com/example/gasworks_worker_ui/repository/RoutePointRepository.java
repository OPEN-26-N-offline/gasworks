package com.example.gasworks_worker_ui.repository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.gasworks_worker_ui.entity.RoutePoint;

import java.util.List;

@Repository
public class RoutePointRepository {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_URL = "http://localhost:8081/customers";

    public List<RoutePoint> findAll() {

        ResponseEntity<List<RoutePoint>> response = restTemplate.exchange(
            API_URL,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RoutePoint>>() {}
            );
            List<RoutePoint> routePoints = response.getBody();

        return routePoints;

    }

}

// return List.of(
//             new RoutePoint(
//                 "1",
//                 "渋谷駅",
//                 35.6585805,
//                 139.7013303,
//                 1,
//                 MapCategory.START
//             ),
//             new RoutePoint(
//                 "2",
//                 "巡回地点A",
//                 35.6605,
//                 139.6980,
//                 2,
//                 MapCategory.WAYPOINT
//             ),
//             new RoutePoint(
//                 "3",
//                 "巡回地点B",
//                 35.6620,
//                 139.7000,
//                 3,
//                 MapCategory.WAYPOINT
//             ),
//             new RoutePoint(
//                 "4",
//                 "渋谷区役所",
//                 35.6640351,
//                 139.6982119,
//                 4,
//                 MapCategory.END
//             )
//         );
