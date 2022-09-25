package togo.backend;

import io.vertx.core.json.JsonObject;
import togo.backend.base.TogoLogger;
import togo.backend.entity.CrawlerEvent;
import togo.backend.flow.DemoFailFlow;
import togo.backend.flow.DemoFlow;
import togo.backend.task.DemoTask;

import java.util.Date;

public class CrawlerMain {

    private static final TogoLogger log = TogoLogger.getLogger(CrawlerMain.class);

    public static void main(String[] args) {
        log.info("Hello world!");
        CrawlerEvent event = new CrawlerEvent();
        event.setFromDate(new Date(System.currentTimeMillis()));
        event.setToDate(new Date(System.currentTimeMillis()));
        log.info("Event = " + JsonObject.mapFrom(event));

        DemoTask demoTask = new DemoTask();
        DemoFlow demoFlow = new DemoFlow(demoTask);
        DemoFailFlow demoFailFlow = new DemoFailFlow(demoTask);

        demoTask.run(event).onComplete(result -> log.info("demoTask = " + result.succeeded()));
        demoFlow.run(event).onComplete(result -> log.info("demoFlow = " + result.succeeded()));
        demoFailFlow.run(event).onComplete(result -> log.info("demoFailFlow = " + result.succeeded()));
    }
}