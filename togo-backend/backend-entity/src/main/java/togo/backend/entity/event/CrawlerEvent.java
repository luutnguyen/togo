package togo.backend.entity.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import togo.backend.entity.StdTogoEvent;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrawlerEvent extends StdTogoEvent {

    private Date fromDate;
    private Date toDate;
}
