package org.cloudfoundry.community.servicebroker.catalog;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Map;

/**
 * A service plan available for a ServiceDefinition
 *
 * @author sgreenberg@gopivotal.com
 */
@Entity
public class Plan {

    @Id
    private String id;
    private String name;
    private String description;
    @Transient
    private Map<String, Object> metadata;
    private boolean free;

    public Plan() {
    }

    public Plan(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Plan(String id, String name, String description, Map<String, Object> metadata) {
        this(id, name, description);
        setMetadata(metadata);
    }

    public Plan(String id, String name, String description, Map<String, Object> metadata, boolean free) {
        this(id, name, description, metadata);
        this.free = free;
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

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    private void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public boolean isFree() {
        return free;
    }
}
