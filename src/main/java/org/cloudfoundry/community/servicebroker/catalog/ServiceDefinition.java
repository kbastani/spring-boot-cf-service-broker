package org.cloudfoundry.community.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * A service offered by this broker.
 * 
 * @author sgreenberg@gopivotal.com
 *
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class ServiceDefinition implements Serializable {

	@NotEmpty
	@JsonSerialize
	@JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@NotEmpty
	@JsonSerialize
	@JsonProperty("name")
	private String name;
	
	@NotEmpty
	@JsonSerialize
	@JsonProperty("description")
	private String description;
	
	@JsonSerialize
	@JsonProperty("bindable")
	private boolean bindable;

	@JsonSerialize
	@JsonProperty("plan_updateable")
	private boolean planUpdateable;

	@JsonSerialize
	@JsonProperty("plans")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Plan> plans = new HashSet<>();
	
	@JsonSerialize
	@JsonProperty("tags")
	private HashSet<String> tags = new HashSet<>();
	
	@JsonSerialize
	@JsonProperty("metadata")
    @Transient
	private Map<String,Object> metadata = new HashMap<String,Object>();
	
	@JsonSerialize
	@JsonProperty("requires")
    @Basic
	private HashSet<String> requires = new HashSet<>();
	
	@JsonSerialize
	@JsonProperty("dashboard_client")
	private DashboardClient dashboardClient;

	public ServiceDefinition() {
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, Set<Plan> plans) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bindable = bindable;
        this.plans = plans;
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, boolean planUpdateable, 
			Set<Plan> plans, Set<String> tags, Map<String,Object> metadata, Set<String> requires,
			DashboardClient dashboardClient) {
		this(id, name, description, bindable, plans);
		setTags(tags);
		setMetadata(metadata);
		setRequires(requires);
		setPlanUpdateable(planUpdateable);
		this.dashboardClient = dashboardClient;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isBindable() {
		return bindable;
	}

	public boolean isPlanUpdateable() {
		return planUpdateable;
	}

	public void setPlanUpdateable(boolean planUpdateable) {
		this.planUpdateable = planUpdateable;
	}

	public Collection<Plan> getPlans() {
		return plans;
	}

	private void setPlans(Set<Plan> plans) {
		if ( plans == null ) {
			// ensure serialization as an empty array and not null
			this.plans = new HashSet<Plan>();
		} else {
			this.plans = new HashSet<>(plans);
		}
	}

	public HashSet<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		if (tags == null) {
			this.tags = new HashSet<>();
		} else {
			this.tags = new HashSet<>(tags);
		}
	}

	public Set<String> getRequires() {
		return requires;
	}

	public void setRequires(Set<String> requires) {
		if (requires == null) {
			this.requires = new HashSet<String>();
		} else {
			this.requires = new HashSet<>(requires);
		}
	}

    @Transient
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		if (metadata == null) {
			this.metadata = new HashMap<String,Object>();
		} else {
			this.metadata = metadata;
		}
	}

	public DashboardClient getDashboardClient() {
		return dashboardClient;
	}

}
