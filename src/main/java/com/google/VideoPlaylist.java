package com.google;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/** A class used to represent a Playlist */
class VideoPlaylist {

  private String name;

  private LinkedHashMap<String, Video> videos;

  VideoPlaylist(String name) {
    this.videos = new LinkedHashMap<>();
    this.name = name;
  }

  public void addVideo(Video video) {
    this.videos.put(video.getVideoId(), video);
  }

  public void removeVideo(Video video) {
    this.videos.remove(video.getVideoId());
  }

  public String getName() {
    return name;
  }

  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }

  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

}