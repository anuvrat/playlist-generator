package pba.plgen.common.utils;

import java.util.ArrayList;
import java.util.List;

import net.roarsoftware.lastfm.Track;
import pba.plgen.common.api.playlist.Playlist;
import pba.plgen.common.api.song.SongDetails;
import pba.plgen.internal.data.Data;
import pba.plgen.internal.data.PlaylistData;
import pba.plgen.m3u.impl.playlist.M3Uplaylist;
import pba.plgen.m3u.impl.song.M3uSongDetails;

/**
 * The sole purpose of this class is to interact with the last.fm and get relevant data
 * 
 * @author AnuvratSingh
 */
public class LastfmUtils {
	/**
	 * Download the top tracks from last.fm for the list of data
	 * 
	 * @param datas The list of data from the config file for which the track details needs to be downloaded
	 * @return The list of playlists without files attached.
	 */
	public static List<Playlist> downloadPlaylists(List<Data> datas) {
		List<Playlist> playlists = new ArrayList<Playlist>();

		for (Data dataTemp : datas) {
			PlaylistData data = (PlaylistData) dataTemp;
			switch (data.getExtension()) {
			case M3U:
				playlists.add(LastfmUtils.downloadM3uPlaylist(data));
				break;
			default:
			}
		}

		return playlists;
	}

	/**
	 * Download the top tracks from last.fm and create a m3uPlaylist out of it.
	 * 
	 * @param data The data from the config file
	 * @return A M3Uplaylist for the data provided provided. Files from hard disk are not attached to the playlist.
	 */
	private static M3Uplaylist downloadM3uPlaylist(PlaylistData data) {
		int numberOfTracks = data.getSize();
		List<Track> topTracksList = data.getEntity().getTopTracks();
		List<SongDetails> songDetails = new ArrayList<SongDetails>(numberOfTracks);

		for (int i = 0; i < numberOfTracks; i++) {
			Track track = topTracksList.get(i);
			songDetails.add(new M3uSongDetails(track.getName(), track.getAlbum(), track.getArtist()));
		}

		String[] nameFragments = data.getSaveLocation().split("\\\\");
		String playlistName = nameFragments[nameFragments.length - 1];

		return new M3Uplaylist(playlistName, songDetails, data.getSaveLocation(), data.getLocation());
	}
}
