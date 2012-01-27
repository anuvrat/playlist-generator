package pba.plgen.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import pba.plgen.common.api.entity.Entity;
import pba.plgen.common.enums.ConfigFileParams;
import pba.plgen.common.enums.EntityType;
import pba.plgen.common.enums.PlaylistExtension;
import pba.plgen.common.enums.TimeDuration;
import pba.plgen.common.impl.entity.ArtistEntity;
import pba.plgen.config.Configurations;
import pba.plgen.internal.data.Data;
import pba.plgen.internal.data.MusicData;
import pba.plgen.internal.data.PlaylistData;
import pba.plgen.xml.binding.Music;
import pba.plgen.xml.binding.Playlist;
import pba.plgen.xml.binding.PlaylistTypeGroup;
import pba.plgen.xml.binding.Plgen;

/**
 * Reads the configuration xml file and prepares a map of elements and their properties
 * 
 * @author AnuvratSingh
 */
public class XMLConfigReader {
	private Plgen m_plgen;
	private String m_configFilePath;
	private Map<ConfigFileParams, List<Data>> m_elements;

	/**
	 * @param filePath The path of the config file. If the file is not found, a defualt config file is used.
	 */
	public XMLConfigReader(String filePath) {
		File tempFile = new File(filePath);
		m_configFilePath = !tempFile.exists() ? Configurations.DEFAULT_CONFIG_FILE_LOCATION : filePath;

		m_elements = new HashMap<ConfigFileParams, List<Data>>();
	}

	/**
	 * Parse the xml config file
	 */
	public void parse() {
		try {
			m_plgen = Plgen.unmarshal(new FileReader(m_configFilePath));
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param elementType The type of element which is being added
	 * @param data The data that the element contains
	 */
	private void addElementToMap(ConfigFileParams elementType, Data data) {
		List<Data> mapData = null;
		if (m_elements.containsKey(elementType))
			mapData = m_elements.get(elementType);
		if (mapData == null)
			m_elements.put(elementType, mapData = new ArrayList<Data>());
		mapData.add(data);
	}

	/**
	 * Read the elements enclosed within the <i>music</i> tags in the config file.<br>
	 * <ul>
	 * <li><b>location</b>: The default location of music directories. More than one can be specified.</li>
	 * </ul>
	 * 
	 * @param doc The config file document
	 * @return the number of elements found
	 */
	private int parseMusicElements(Music musicElement) {
		if (musicElement == null)
			return 0;

		addElementToMap(ConfigFileParams.MUSIC, new MusicData(musicElement.getLocation()));
		return 1;
	}

	/**
	 * Read the elements enclosed within the <i>playlist</i> tags in the config file.<br>
	 * <ul>
	 * <li><b>extension</b>: The extension of the playlist</li>
	 * <li><b>save_location</b>: The location at which the playlist needs to be saved</li>
	 * <li><b>artist</b>: The name of the artist</li>
	 * <li><b>size</b>: Number of songs to be included in the playlist</li>
	 * <li><b>duration</b>: Timeline to be used to find the top songs</li>
	 * <li><b>location</b>: The default location of music directories. More than one can be specified.</li>
	 * <li><b>enabled</b>: Whether a playlist for this particular setting should be prepared</li>
	 * </ul>
	 * 
	 * @param doc The config file document
	 * @return the number of playlist elements found in the config xml file
	 */
	private int parsePlaylistElements(List<Playlist> playlistElement) {
		if (playlistElement == null)
			return 0;

		for (Playlist playlist : playlistElement) {
			if (!playlist.isEnabled())
				continue;

			Pair<EntityType, Entity> entity = getEntity(playlist.getPlaylistTypeGroup());
			addElementToMap(ConfigFileParams.PLAYLIST, new PlaylistData(PlaylistExtension.fromValue(playlist
			        .getExtension().toString()), playlist.getLocation(0), entity.getElementB(), entity.getElementA(),
			        (int) playlist.getSize(), TimeDuration.getTimeDuration(playlist.getDuration().toString()), playlist
			                .getSave_location(0)));

		}

		return playlistElement.size();
	}

	/**
	 * @return The entity type and the entity as a pair
	 */
	private Pair<EntityType, Entity> getEntity(PlaylistTypeGroup playlistTypeGroup) {
		if (playlistTypeGroup.getArtist() != null)
			return new Pair<EntityType, Entity>(EntityType.ARTIST, new ArtistEntity(playlistTypeGroup.getArtist()));

		return null;
	}

	/**
	 * @param parameter For which data is required
	 * @return The data from the config file for the requested parameter
	 */
	@SuppressWarnings("unchecked")
	public List<Data> getParameterValue(ConfigFileParams parameter) {
		if (m_elements.containsKey(parameter))
			return m_elements.get(parameter);

		int count = 0;

		switch (parameter) {
		case MUSIC:
			count = parseMusicElements(m_plgen.getMusic());
			break;
		case PLAYLIST:
			count = parsePlaylistElements(Arrays.asList(m_plgen.getPlaylist()));
			break;
		default:
		}

		return count > 0 ? m_elements.get(parameter) : Collections.EMPTY_LIST;
	}
}
