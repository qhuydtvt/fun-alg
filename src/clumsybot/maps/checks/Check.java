package clumsybot.maps.checks;

import clumsybot.bots.Bot;
import clumsybot.maps.Map;

/**
 * Created by huynq on 1/31/18.
 */
public interface Check {
    CheckResult check(Bot bot);
}
