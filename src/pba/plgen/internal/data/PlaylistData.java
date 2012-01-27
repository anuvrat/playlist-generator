package pba.plgen.internal.data;

import pba.plgen.common.api.entity.Entity;
import pba.plgen.common.enums.EntityType;
import pba.plgen.common.enums.PlaylistExtension;
import pba.plgen.common.enums.TimeDuration;

/**
 * @author AnuvratSingh
 */
public class PlaylistData extends Data {
	private PlaylistExtension m_extension;
	private String m_location;
	private Entity m_entity;
	private EntityType m_entityType;
	private int m_size;
	private TimeDuration m_timeDuration;
	private String m_saveLocation;

	public PlaylistData(PlaylistExtension extension, String location, Entity entity, EntityType entityType, int size,
	        TimeDuration timeDuration, String saveLocation) {
		super();
		m_extension = extension;
		m_location = location;
		m_entity = entity;
		m_entityType = entityType;
		m_size = size;
		m_timeDuration = timeDuration;
		m_saveLocation = saveLocation;
	}

	public PlaylistExtension getExtension() {
		return m_extension;
	}

	public void setExtension(PlaylistExtension extension) {
		m_extension = extension;
	}

	public String getLocation() {
		return m_location;
	}

	public void setLocation(String location) {
		m_location = location;
	}

	public Entity getEntity() {
		return m_entity;
	}

	public void setEntity(Entity entity) {
		m_entity = entity;
	}

	public EntityType getEntityType() {
		return m_entityType;
	}

	public void setEntityType(EntityType entityType) {
		m_entityType = entityType;
	}

	public int getSize() {
		return m_size;
	}

	public void setSize(int size) {
		m_size = size;
	}

	public TimeDuration getTimeDuration() {
		return m_timeDuration;
	}

	public void setTimeDuration(TimeDuration timeDuration) {
		m_timeDuration = timeDuration;
	}

	public String getSaveLocation() {
		return m_saveLocation;
	}

	public void setSaveLocation(String saveLocation) {
		m_saveLocation = saveLocation;
	}
}
