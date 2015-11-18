package org.cloudfoundry.community.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * A service offered by this broker.
 *
 * @author sgreenberg@gopivotal.com
 */
@Entity
public class ServiceDefinition implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private boolean bindable;
    @JsonProperty("plan_updateable")
    private boolean planUpdateable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Plan> plans = new HashSet<>();
    private HashSet<String> tags;

    @Transient
    private Map<String, Object> metadata = null;

    private HashSet<String> requires;

    @JsonProperty("dashboard_client")
    private DashboardClient dashboardClient;

    public ServiceDefinition() {
        this.id = UUID.randomUUID().toString();
    }

    public ServiceDefinition(String id, String name, String description, boolean bindable, Set<Plan> plans) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.bindable = bindable;
        this.plans = plans;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }

    public Collection<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Collection<Plan> plans) {
        this.plans = plans;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public HashSet<String> getRequires() {
        return requires;
    }

    public void setRequires(HashSet<String> requires) {
        this.requires = requires;
    }

    public DashboardClient getDashboardClient() {
        return dashboardClient;
    }

    public void setDashboardClient(DashboardClient dashboardClient) {
        this.dashboardClient = dashboardClient;
    }
}
