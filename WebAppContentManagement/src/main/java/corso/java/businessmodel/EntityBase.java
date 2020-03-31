package corso.java.businessmodel;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EntityBase implements Comparable<EntityBase> {

	@JsonProperty(value = "id")
	private Long id;
	private String name;

	private Date createdAt;
	private Date lastModified;

	public EntityBase() {
		Date now = Calendar.getInstance().getTime();
		createdAt = now;
		lastModified = now;
	}

	public EntityBase(Long id, String name) {
		setId(id);
		setName(name);
	}

	@Transient
	@Id
	public Long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	@Column(name = "denominazione")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EntityBase)
			return obj.hashCode() == hashCode();
		return false;
	}

	@Override
	public int hashCode() {

		return id.hashCode();
	}

	@Override
	public int compareTo(EntityBase other) {
		return name.compareTo(other.name);
	}

	@Column(name = "data_creazione", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	@Column(name = "ultima_modifica", nullable = false)
	public Date getLastModified() {
		return lastModified;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
