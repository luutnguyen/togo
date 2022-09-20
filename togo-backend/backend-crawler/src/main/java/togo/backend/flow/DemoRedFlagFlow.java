package togo.backend.flow;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import togo.backend.base.TogoTask;
import togo.backend.base.TogoWorkflow;
import togo.backend.entity.event.CrawlerEvent;
import togo.backend.task.DemoTask;

import javax.inject.Inject;

public class DemoRedFlagFlow extends TogoWorkflow<CrawlerEvent, CrawlerEvent> {

    @Inject
    public DemoRedFlagFlow(DemoTask demoTask, DemoFlow demoFlow) {
        addTask(demoTask);
        addTask(demoFlow);
        addTask(new TogoTask<>() {
            @Override
            protected Future<CrawlerEvent> exec(CrawlerEvent in, CrawlerEvent out) {
                System.out.println(DemoRedFlagFlow.class.getSimpleName() + "1");
                out.setRedFlag(true);
                return Future.future(Promise::complete);
            }
        });
        addTask(new TogoTask<>() {
            @Override
            protected Future<CrawlerEvent> exec(CrawlerEvent in, CrawlerEvent out) {
                System.out.println(DemoRedFlagFlow.class.getSimpleName() + "2");
                return Future.future(Promise::complete);
            }
        });
    }
}
