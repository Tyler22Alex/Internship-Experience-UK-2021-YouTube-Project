package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/** A class used to represent a Video Library. */
class VideoLibrary {

  // The videos in this library gotten from a text file. 
  private final HashMap<String, Video> videos;

  /** Create a new video library with all the videos in the text file. */
  VideoLibrary() {
    this.videos = new HashMap<>();

    try {
      // Get the file path and file for the videos text file. 
      String filePath = URLDecoder.decode(this.getClass().getResource("/videos.txt").toString(), StandardCharsets.UTF_8.name());
      filePath = filePath.substring(6);
      File file = new File(filePath);

      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] split = line.split("\\|");
        String title = split[0].strip();
        String id = split[1].strip();
        List<String> tags;
        if (split.length > 2) {
          tags = Arrays.stream(split[2].split(",")).map(String::strip).collect(Collectors.toList());
        } else {
          tags = new ArrayList<>();
        }
        this.videos.put(id, new Video(title, id, tags));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find videos.txt");
      e.printStackTrace();
    } catch (UnsupportedEncodingException e)
    {
      System.out.println("You are using an unsupported encoding type. ");
      e.printStackTrace();
    }
  }

  /** Return an array list of the video titles. */
  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  /** Get a video by id. Returns null if the video is not found. */
  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }
  
}