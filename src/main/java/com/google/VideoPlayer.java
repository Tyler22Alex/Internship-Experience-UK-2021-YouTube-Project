package com.google;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  private Video currentVideo;
  private boolean paused;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos: ");
    List<String> videoStrings = new ArrayList<>();
    for (Video video : videoLibrary.getVideos()) {
      videoStrings.add(video.toString());
    }
    Collections.sort(videoStrings);

    for (String video : videoStrings)
    {
      System.out.println(video);
    }
  }

  public void playVideo(String videoId) {
    for (Video video : videoLibrary.getVideos()) {
      if (video.getVideoId().equals(videoId)) {
        if (currentVideo == null) {
          System.out.printf("Playing video: %s%n", video.getTitle());
        } else {
          System.out.printf("Stopping video: %s%n", currentVideo.getTitle());
          System.out.printf("Playing video: %s%n", video.getTitle());
        }
        currentVideo = video;
        paused = false;
        return;
      }
    }
    System.out.println("Cannot play video: Video does not exist. ");
  }

  public void stopVideo() {
    if (currentVideo == null)
    {
      System.out.println("Cannot stop video: No video is currently playing");
    }
    else
    {
      System.out.printf("Stopping video: %s%n", currentVideo.getTitle());
      currentVideo = null;
    }
  }

  public void playRandomVideo() {
    int size = videoLibrary.getVideos().size();

    Random rand = new Random();

    int randNum = rand.nextInt(size);

    playVideo(videoLibrary.getVideos().get(randNum).getVideoId());
  }

  public void pauseVideo() {
    if (currentVideo == null)
    {
      System.out.println("Cannot pause video: No video is currently playing");
    }
    else if (paused)
    {
      System.out.printf("Video already paused: %s%n", currentVideo.getTitle());
    }
    else
    {
      System.out.printf("Pausing video: %s%n", currentVideo.getTitle());
      paused = true;
    }
  }

  public void continueVideo() {
    if (currentVideo == null)
    {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else if (!paused)
    {
      System.out.println("Cannot continue video: Video is not paused");
    }
    else
    {
      System.out.printf("Continuing video: %s%n", currentVideo.getTitle());
      paused = false;
    }
  }

  public void showPlaying() {
    if (currentVideo == null)
    {
      System.out.println("No video is currently playing");
    }
    else if (paused)
    {
      System.out.printf("Currently playing: %s - PAUSED%n", currentVideo.toString());
    }
    else
    {
      System.out.printf("Currently playing: %s%n", currentVideo.toString());
    }
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}