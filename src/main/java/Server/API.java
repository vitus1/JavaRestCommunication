/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

/**
 *
 * @author ander
 */
public class API {
    private final int PORT; 
    
    public API(int port) {
        this.PORT = port; 
    }
    public void start() {
        Javalin app = Javalin.create()
                .enableRouteOverview("/routes")
                .enableCorsForAllOrigins()
                .enableAutogeneratedEtags()
                .port(PORT);
        APIHandler handler = new APIHandler();
        setRoutes(app, handler);
        app.start();
    }

    private void setRoutes(Javalin app, APIHandler handler) {
        app.routes(() -> {
            path("/api", () -> {
                //post calls
                post("/add-data/:value", handler::addData); 
                
                //get calls
                get("/get-data/", handler::getData); 
            });
        });
    }
}
