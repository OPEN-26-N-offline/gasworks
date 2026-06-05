package com.example.gasworks_worker_ui.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.gasworks_worker_ui.Category.MapCategory;
import com.example.gasworks_worker_ui.entity.RoutePoint;

@Repository
public class RoutePointRepository {
    public List<RoutePoint> findTodayRoute() {
        return List.of(
    new RoutePoint(
        "1",
        "東京駅",
        35.681236,
        139.767125,
        1,
        MapCategory.START
    ),
    new RoutePoint(
        "2",
        "上野駅",
        35.713768,
        139.777254,
        2,
        MapCategory.WAYPOINT
    ),
    new RoutePoint(
        "3",
        "浅草駅",
        35.710063,
        139.810700,
        3,
        MapCategory.END
    )
);
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
