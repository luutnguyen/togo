package togo.backend.task;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import togo.backend.base.TogoTask;
import togo.backend.entity.CrawlerEvent;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

public class DemoTask extends TogoTask<CrawlerEvent> {

    @Inject
    public DemoTask() {
    }

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
}
