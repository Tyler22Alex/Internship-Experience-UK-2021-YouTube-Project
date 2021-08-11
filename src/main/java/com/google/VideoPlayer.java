package com.google;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/** A class to play the videos in the library. */
public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private final PlaylistLibrary playlists;

  private Video currentVideo;
  private boolean isPaused;

  /** Create a new video player with the library. */
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.playlists = new PlaylistLibrary();
  }

  /** Print the number of videos in the library. */
  public void numberOfVideos() {
    System.out.printf("%s videos in the library\n", videoLibrary.getVideos().size());
  }

  /** Print all the videos formatted. */
  public void showAllVideos() {
    System.out.println("Here's a list of all available videos: ");

    List<String> videoStrings = new ArrayList<>();

    for (Video video : videoLibrary.getVideos()) {
      videoStrings.add(video.toString());
    }

    Collections.sort(videoStrings);

    for (String video : videoStrings) {
      System.out.println(video);
    }
  }

  /** Play a video by printing a message and it's title. */
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
      isPaused = false;
    } catch (NullPointerException e) {
      System.out.println("Cannot play video: Video does not exist. ");
    }
  }

  /** Stop the currently playing video by printing a message and it's title. */
  public void stopVideo() {
    if (currentVideo == null) {
      System.out.println("Cannot stop video: No video is currently playing");
    } else {
      System.out.printf("Stopping video: %s\n", currentVideo.getTitle());
      currentVideo = null;
    }
  }

  /** Play a random video from the library. */
  public void playRandomVideo() {
    int size = videoLibrary.getVideos().size();

    Random rand = new Random();

    int randNum = rand.nextInt(size);

    playVideo(videoLibrary.getVideos().get(randNum).getVideoId());
  }

  /** Pause the currently playing video by printing a message and it's title. */
  public void pauseVideo() {
    if (currentVideo == null) {
      System.out.println("Cannot pause video: No video is currently playing");
    } else if (isPaused) {
      System.out.printf("Video already paused: %s\n", currentVideo.getTitle());
    } else {
      System.out.printf("Pausing video: %s\n", currentVideo.getTitle());
      isPaused = true;
    }
  }

  /** Continue playing the video by printing a message and it's title. */
  public void continueVideo() {
    if (currentVideo == null) {
      System.out.println("Cannot continue video: No video is currently playing");
    } else if (!isPaused) {
      System.out.println("Cannot continue video: Video is not paused");
    } else {
      System.out.printf("Continuing video: %s\n", currentVideo.getTitle());
      isPaused = false;
    }
  }

  /** Print the currently playing video. */
  public void showPlaying() {
    if (currentVideo == null) {
      System.out.println("No video is currently playing");
    } else if (isPaused) {
      System.out.printf("Currently playing: %s - PAUSED\n", currentVideo.toString());
    } else {
      System.out.printf("Currently playing: %s\n", currentVideo.toString());
    }
  }

  /** Create an empty playlist and add it to the list of playlists. */
  public void createPlaylist(String playlistName) {
    // If this playlist already exists, print a message and do nothing. 
    if (playlists.getPlaylist(playlistName) != null) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
        return;
    }

    VideoPlaylist playlist = new VideoPlaylist(playlistName);
    playlists.addPlaylist(playlist);

    System.out.printf("Successfully created new playlist: %s\n", playlistName);
  }

  /** Add a video to a playlist. */
  public void addVideoToPlaylist(String playlistName, String videoId) {
    VideoPlaylist playlist = playlists.getPlaylist(playlistName);

    // If this playlist exists, add a video to it, if the video exists and is not already added. 
    if (playlist != null) {
      try {
        Video video = videoLibrary.getVideo(videoId);
        String title = video.getTitle();

        // If this video isn't in the playlist, add it. 
        if (playlist.getVideo(videoId) == null) {
          playlist.addVideo(video);
          System.out.printf("Added video to %s: %s\n", playlistName, title);
          return;
        } else {
          System.out.printf("Cannot add video to %s: Video already added\n", playlistName);
          return;
        }
      } catch (NullPointerException e) {
        System.out.printf("Cannot add video to %s: Video does not exist\n", playlistName);
        return;
      }
    }

    System.out.printf("Cannot add video to %s: Playlist does not exist\n", playlistName);
  }

  /** Print all the playlists. */
  public void showAllPlaylists() {
    if (playlists.getSize() != 0) {
      System.out.println("Showing all playlists: ");
      List<String> playlistStrings = new ArrayList<>();

      for (VideoPlaylist playlist : playlists.getPlaylists()) {
        playlistStrings.add(playlist.getName());
      }

      Collections.sort(playlistStrings);

      for (String playlist : playlistStrings) {
        System.out.println(playlist);
      }
    } else {
      System.out.println("No playlists exist yet");
    }
  }

  /** Show all the videos in a playlist. */
  public void showPlaylist(String playlistName) {
    VideoPlaylist playlist = playlists.getPlaylist(playlistName);

    // If this playlist exists, show all videos in it. 
    if (playlist != null) {
      System.out.printf("Showing playlist: %s\n", playlistName);
      List<Video> videos = playlist.getVideos();

      if (videos.size() != 0) {
        for (Video video : videos) {
          System.out.printf("\t%s\n", video.toString());
        }
      } else {
        System.out.printf("\tNo videos here yet\n");
      }
      return;
    }

    System.out.printf("Cannot show playlist %s: Playlist does not exist\n", playlistName);
  }

  /** Remove a video from a playlist. */
  public void removeFromPlaylist(String playlistName, String videoId) {
    VideoPlaylist playlist = playlists.getPlaylist(playlistName);

    // If this playlist exists, remove the video from it, if the video exists and is added. 
    if (playlist != null) {
      try {
        Video video = videoLibrary.getVideo(videoId);
        String title = video.getTitle();

        // If this video is in the playlist, remove it. 
        if (playlist.getVideo(videoId) != null) {
          playlist.removeVideo(video);
          System.out.printf("Removed video from %s: %s\n", playlistName, title);
          return;
        } else {
          System.out.printf("Cannot remove video from %s: Video is not in playlist\n", playlistName);
          return;
        }
      } catch (NullPointerException e) {
        System.out.printf("Cannot remove video from %s: Video does not exist\n", playlistName);
        return;
      }
    }

    System.out.printf("Cannot remove video from %s: Playlist does not exist\n", playlistName);
  }

  /** Remove all videos from a playlist. */
  public void clearPlaylist(String playlistName) {
    VideoPlaylist playlist = playlists.getPlaylist(playlistName);

    // If this playlist exists, remove all videos from it. 
    if (playlist != null) {
      for (Video video : playlist.getVideos()) {
        playlist.removeVideo(video);
      }

      System.out.printf("Successfully removed all videos from %s\n", playlistName);
      return;
    }

    System.out.printf("Cannot clear playlist %s: Playlist does not exist\n", playlistName);
  }

  /** Delete a playlist. */
  public void deletePlaylist(String playlistName) {
    // If this playlists exists, delete it. 
    if (playlists.getPlaylist(playlistName) != null) {
      playlists.deletePlaylist(playlistName);
      System.out.printf("Deleted playlist: %s\n", playlistName);
      return;
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