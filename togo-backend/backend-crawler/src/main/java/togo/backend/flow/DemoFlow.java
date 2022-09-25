package togo.backend.flow;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import togo.backend.base.TogoTask;
import togo.backend.base.TogoWorkflow;
import togo.backend.entity.CrawlerEvent;
import togo.backend.task.DemoTask;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

public class DemoFlow extends TogoWorkflow<CrawlerEvent> {

    @Inject
    public DemoFlow(DemoTask demoTask) {
        addTask(demoTask);
        addTask(new TogoTask<>() {
            @Override
            protected Future<CrawlerEvent> exec(CrawlerEvent event) {
                Promise<CrawlerEvent> promise = Promise.promise();
                event.setEventId(UUID.randomUUID().toString());
                event.setFromDate(new Date(System.currentTimeMillis()));
                event.setToDate(new Date(System.currentTimeMillis()));
                promise.complete(event);
                log.info("Event = " + JsonObject.mapFrom(event));
                return promise.future();
            }
        });
        addTask(new TogoTask<>() {
            @Override
            protected Future<CrawlerEvent> exec(CrawlerEvent event) {
                Promise<CrawlerEvent> promise = Promise.promise();
                event.setEventId(UUID.randomUUID().toString());
                event.setFromDate(new Date(System.currentTimeMillis()));
                event.setToDate(new Date(System.currentTimeMillis()));
                promise.complete(event);
                log.info("Event = " + JsonObject.mapFrom(event));
                return promise.future();
            }
        });
    }
}
