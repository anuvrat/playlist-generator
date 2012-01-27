package pba.plgen.common.api.entity;

import java.util.List;

import net.roarsoftware.lastfm.Track;

/**
 * Entity denotes elements such as User, Artist, Group from Last.fm
 * 
 * @author AnuvratSingh
 */
public interface Entity {
	/**
	 * @return Name of the entity
	 */
	public String getName();

	/**
	 * @return The top tracks for the entity
	 */
	public List<Track> getTopTracks();
}
