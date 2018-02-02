package clumsybot.maps.checks;

import clumsybot.bots.Bot;
import clumsybot.maps.Map;
import clumsybot.maps.MapVector;

/**
 * Created by huynq on 2/2/18.
 */
public class BotPositionCheck implements Check {
    private int colDestination = 3;
    private int rowDestination = 2;

    public BotPositionCheck() {
        this(3, 2);
    }

    public BotPositionCheck(int colDestination, int rowDestination) {
        this.colDestination = colDestination;
        this.rowDestination = rowDestination;
    }

    @Override
    public CheckResult check(Bot bot) {
        MapVector mapPosition = bot.getMapPosition();
        if (mapPosition.col == colDestination && mapPosition.row == rowDestination) {
            return new CheckResult(true, "Oh yeah");
        }

        return new CheckResult(false, "Not in right position");
    }
}
