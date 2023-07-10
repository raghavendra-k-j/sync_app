package com.example.syncapp.rest;

import com.example.syncapp.rest.defaults.Response;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection-pool")
public class ConnectionPoolController {

    @Autowired
    private HikariDataSource dataSource;

    @GetMapping("/status")
    public ResponseEntity<Response> getConnectionPoolStatus() {
        HikariPoolMXBean poolMXBean = dataSource.getHikariPoolMXBean();
        int totalConnections = poolMXBean.getTotalConnections();
        int idleConnections = poolMXBean.getIdleConnections();
        int activeConnections = poolMXBean.getActiveConnections();
        int getThreadsAwaitingConnection = poolMXBean.getThreadsAwaitingConnection();
        int maxPoolSize = dataSource.getMaximumPoolSize();

        Response response = new Response();
        response.addProperty("Total Connections", totalConnections);
        response.addProperty("Idle Connections", idleConnections);
        response.addProperty("Active Connections", activeConnections);
        response.addProperty("Awaiting Connections", getThreadsAwaitingConnection);
        response.addProperty("Max Pool Size", maxPoolSize);

        return ResponseEntity.ok(response);
    }
}
