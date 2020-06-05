package com.example.cardtry;

public class World {

    private String name;
    private String title;
    private int backgroundRes;
    private boolean isOpen;
    private boolean isLevelEasyOpen;
    private boolean isLevelMediumOpen;
    private boolean isLevelHardOpen;
    private Class levelEasy;
    private Class levelMedium;
    private Class levelHard;

    public World(String name, String title, int backgroundRes, boolean isOpen, boolean isLevelEasyOpen, boolean isLevelMediumOpen, boolean isLevelHardOpen, Class levelEasy, Class levelMedium, Class levelHard) {
        this.name = name;
        this.title = title;
        this.backgroundRes = backgroundRes;
        this.isOpen = isOpen;
        this.isLevelEasyOpen = isLevelEasyOpen;
        this.isLevelMediumOpen = isLevelMediumOpen;
        this.isLevelHardOpen = isLevelHardOpen;
        this.levelEasy = levelEasy;
        this.levelMedium = levelMedium;
        this.levelHard = levelHard;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public Class getLevelEasy() {
        return levelEasy;
    }

    public Class getLevelMedium() {
        return levelMedium;
    }

    public Class getLevelHard() {
        return levelHard;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isLevelEasyOpen() {
        return isLevelEasyOpen;
    }

    public void setLevelEasyOpen(boolean levelEasyOpen) {
        isLevelEasyOpen = levelEasyOpen;
    }

    public boolean isLevelMediumOpen() {
        return isLevelMediumOpen;
    }

    public void setLevelMediumOpen(boolean levelMediumOpen) {
        isLevelMediumOpen = levelMediumOpen;
    }

    public boolean isLevelHardOpen() {
        return isLevelHardOpen;
    }

    public void setLevelHardOpen(boolean levelHardOpen) {
        isLevelHardOpen = levelHardOpen;
    }

}
