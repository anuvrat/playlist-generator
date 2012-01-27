package pba.plgen.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import pba.plgen.common.api.playlist.Playlist;
import pba.plgen.common.api.song.SongDetails;

/**
 * This class looks for files on the hard disk
 * 
 * @author AnuvratSingh
 */
public class Scanner {
	/**
	 * Scan for song files and attach them to the playlist when a suitable match is found
	 * 
	 * @param playlists The list of playlists for which the files need to be searched
	 */
	public static void scanFiles(List<Playlist> playlists) {
		for (Playlist playlist : playlists) {
			// This map contains the best percentage map and the file for each required song.
			// TODO: songMapMatch not yet implemented
			Map<SongDetails, Pair<File, Integer>> songMatchMap = new HashMap<SongDetails, Pair<File, Integer>>();

			List<SongDetails> songDetails = playlist.getSongDetails();
			String searchLocation = playlist.getSearchLocation();

			int count = songDetails.size();
			Stack<File> files = new Stack<File>();
			files.add(new File(searchLocation));
			while (count > 0 && !files.empty()) {
				File file = files.pop();
				if (file.isDirectory())
					files.addAll(Arrays.asList(file.listFiles()));
				else {
					boolean match = Scanner.checkMatch(file, songDetails, songMatchMap);
					if (match)
						count--;
				}
			}
		}
	}

	/**
	 * Check if the file tag matches the file required
	 * 
	 * @param file The song file which needs to be checked
	 * @param songDetails The details of the sing which is to be checked for
	 * @param songMatchMap The song match map which stores the best match yet for each song
	 * @return true if a 100% match was found, false otherwise
	 */
	private static boolean checkMatch(File file, List<SongDetails> songDetails,
	        Map<SongDetails, Pair<File, Integer>> songMatchMap) {
		boolean match = false;
		try {
			AudioFile audioFile = AudioFileIO.read(file);
			Tag tag = audioFile.getTag();
			String artistNameFile = tag.getFirst(FieldKey.ARTIST);
			String albumNameFile = tag.getFirst(FieldKey.ALBUM);
			String songNameFile = tag.getFirst(FieldKey.TITLE);

			for (SongDetails songDetail : songDetails) {
				if (songDetail.getFile() != null)
					continue;
				if (songDetail.getSongName().equalsIgnoreCase(songNameFile)) {
					songDetail.setFile(file);
					songDetail.setTrackLength(audioFile.getAudioHeader().getTrackLength());
					return true;
				}
			}
		} catch (CannotReadException e) {
		} catch (IOException e) {
		} catch (TagException e) {
		} catch (ReadOnlyFileException e) {
		} catch (InvalidAudioFrameException e) {
		}
		return match;
	}
}
