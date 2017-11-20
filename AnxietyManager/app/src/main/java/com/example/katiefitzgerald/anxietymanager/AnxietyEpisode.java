package com.example.katiefitzgerald.anxietymanager;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class AnxietyEpisode {
    private String date;
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AnxietyEpisode{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
