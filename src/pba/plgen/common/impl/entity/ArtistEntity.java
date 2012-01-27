package pba.plgen.common.impl.entity;

import java.util.ArrayList;
import java.util.List;

import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Track;
import pba.plgen.common.api.entity.Entity;
import pba.plgen.config.Authorization;

/**
 * @author AnuvratSingh
 */
public class ArtistEntity implements Entity {
	private String m_name;

	/**
	 * @param name Name of the artist
	 */
	public ArtistEntity(String name) {
		super();
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public List<Track> getTopTracks() {
		List<Track> topTracksList = new ArrayList<Track>();
		topTracksList.addAll(Artist.getTopTracks(m_name, Authorization.API_KEY));

		return topTracksList;
	}
}
