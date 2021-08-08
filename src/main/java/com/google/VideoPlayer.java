package com.google;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  private Video currentVideo;
  private boolean paused;

  private List<VideoPlaylist> playlists = new ArrayList<>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library\n", videoLibrary.getVideos().size());
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
    try {
      Video video = videoLibrary.getVideo(videoId);
      String title = video.getTitle();
      if (currentVideo == null) {
        System.out.printf("Playing video: %s\n", title);
      } else {
        System.out.printf("Stopping video: %s\n", currentVideo.getTitle());
        System.out.printf("Playing video: %s\n", title);
      }
      currentVideo = video;
      paused = false;
    }
    catch (NullPointerException e) {
      System.out.println("Cannot play video: Video does not exist. ");
    }
  }

  public void stopVideo() {
    if (currentVideo == null)
    {
      System.out.println("Cannot stop video: No video is currently playing");
    }
    else
    {
      System.out.printf("Stopping video: %s\n", currentVideo.getTitle());
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
      System.out.printf("Video already paused: %s\n", currentVideo.getTitle());
    }
    else
    {
      System.out.printf("Pausing video: %s\n", currentVideo.getTitle());
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
      System.out.printf("Continuing video: %s\n", currentVideo.getTitle());
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
      System.out.printf("Currently playing: %s - PAUSED\n", currentVideo.toString());
    }
    else
    {
      System.out.printf("Currently playing: %s\n", currentVideo.toString());
    }
  }

  public void createPlaylist(String playlistName) {
    for (VideoPlaylist playlist : playlists) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        System.out.println("Cannot create playlist: A playlist with the same name already exists");
        return;
      }
    }

    VideoPlaylist playlist = new VideoPlaylist(playlistName);
    playlists.add(playlist);

    System.out.printf("Successfully created new playlist: %s\n", playlistName);
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    for (VideoPlaylist playlist : playlists) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        try {
          Video video = videoLibrary.getVideo(videoId);
          String title = video.getTitle();

          if (playlist.getVideo(videoId) == null)
          {
            playlist.addVideo(video);
            System.out.printf("Added video to %s: %s\n", playlistName, title);
            return;
          }
          else
          {
            System.out.printf("Cannot add video to %s: Video already added\n", playlistName);
            return;
          }
        }
        catch (NullPointerException e) {
          System.out.printf("Cannot add video to %s: Video does not exist\n", playlistName);
          return;
        }
      }
    }

    System.out.printf("Cannot add video to %s: Playlist does not exist\n", playlistName);
  }

  public void showAllPlaylists() {
    if (playlists.size() != 0)
    {
      System.out.println("Showing all playlists: ");
      List<String> playlistStrings = new ArrayList<>();
      for (VideoPlaylist playlist : playlists) {
        playlistStrings.add(playlist.getName());
      }
      Collections.sort(playlistStrings);

      for (String playlist : playlistStrings)
      {
        System.out.println(playlist);
      }
    }
    else
    {
      System.out.println("No playlists exist yet");
    }
  }

  public void showPlaylist(String playlistName) {
    for (VideoPlaylist playlist : playlists) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        System.out.printf("Showing playlist: %s\n", playlistName);
        List<Video> videos = playlist.getVideos();
        if (videos.size() != 0) {
          for (Video video : videos) {
            System.out.printf("\t%s\n", video.toString());
          }
        }
        else
        {
          System.out.printf("\tNo videos here yet\n");
        }
        return;
      }
    }

    System.out.printf("Cannot show playlist %s: Playlist does not exist\n", playlistName);
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    for (VideoPlaylist playlist : playlists) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        try {
          Video video = videoLibrary.getVideo(videoId);
          String title = video.getTitle();

          if (playlist.getVideo(videoId) != null)
          {
            playlist.removeVideo(video);
            System.out.printf("Removed video from %s: %s\n", playlistName, title);
            return;
          }
          else
          {
            System.out.printf("Cannot remove video from %s: Video is not in playlist\n", playlistName);
            return;
          }
        }
        catch (NullPointerException e) {
          System.out.printf("Cannot remove video from %s: Video does not exist\n", playlistName);
          return;
        }
      }
    }

    System.out.printf("Cannot remove video from %s: Playlist does not exist\n", playlistName);
  }

  public void clearPlaylist(String playlistName) {
    for (VideoPlaylist playlist : playlists) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        System.out.printf("Successfully removed all videos from %s\n", playlistName);
        List<Video> videos = playlist.getVideos();
        for (Video video : videos) {
          playlist.removeVideo(video);
        }
        return;
      }
    }

    System.out.printf("Cannot clear playlist %s: Playlist does not exist\n", playlistName);
  }

  public void deletePlaylist(String playlistName) {
    for (int x = 0; x < playlists.size(); x++) {
      if (playlistName.equalsIgnoreCase(playlists.get(x).getName())) {
        playlists.remove(x);
        System.out.printf("Deleted playlist: %s\n", playlistName);
        return;
      }
    }

    System.out.printf("Cannot delete playlist %s: Playlist does not exist\n", playlistName);
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