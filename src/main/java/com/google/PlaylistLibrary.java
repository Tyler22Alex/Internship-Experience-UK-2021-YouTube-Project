package com.google;

import java.util.List;
import java.util.ArrayList;

/** A class used to represent a Video Library. */
class PlaylistLibrary {

  /** The list of playlists. */
  private List<VideoPlaylist> playlistLibrary = new ArrayList<>();

  /** Create a new playlist library. */
  PlaylistLibrary() {
    this.playlistLibrary = new ArrayList<>();
  }

  /** Return the list of all playlists. */
  List<VideoPlaylist> getPlaylists() {
    return playlistLibrary;
  }

  /** If a playlist exists with this name, return it. Else, return null. */
  VideoPlaylist getPlaylist(String playlistName) {
    for (VideoPlaylist playlist : this.playlistLibrary) {
      if (playlistName.equalsIgnoreCase(playlist.getName())) {
        return playlist;
      }
    }

    return null;
  }

  /** Add a playlist to this library. */
  void addPlaylist(VideoPlaylist playlist) {
    this.playlistLibrary.add(playlist);
  }

  /** Remove a playlist from this library. */
  void deletePlaylist(String playlistName) {
    for (int x = 0; x < this.playlistLibrary.size(); x++) {
      if (playlistName.equalsIgnoreCase(this.playlistLibrary.get(x).getName())) {
        this.playlistLibrary.remove(x);
      }
    }
  }

  /** Return the size of this playlist library. */
  int getSize() {
    return this.playlistLibrary.size();
  }

}