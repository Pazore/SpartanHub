package me.spadelic.spspawn.misc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Criterias;

import java.util.List;

public class SB {

    private Scoreboard scoreboard;
    private Objective objective;

    public SB(FileConfiguration config) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        ConfigurationSection scoreboardConfig = config.getConfigurationSection("scoreboard");
        String title = scoreboardConfig.getString("title", "&a&lSpartanHub");
        String objectiveName = "CustomScoreboard";
        String criteria = "dummy";
        String displayName = scoreboardConfig.getString("title", "&a&lSpartanHub");

        objective = scoreboard.registerNewObjective(objectiveName, "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> lines = scoreboardConfig.getStringList("lines");
        int score = lines.size();
        for (String line : lines) {
            addLine(line, score);
            score--;
        }
    }

    public void addLine(String text, int score) {
        objective.getScore(text).setScore(score);
    }

    public void setScore(Player player) {
        player.setScoreboard(scoreboard);
    }
}
