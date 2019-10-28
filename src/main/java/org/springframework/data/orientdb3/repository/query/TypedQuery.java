package org.springframework.data.orientdb3.repository.query;

import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.springframework.data.orientdb3.repository.support.OrientdbEntityInformation;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds a query result.
 *
 * @author xxcxy
 */
public class TypedQuery<T, ID> {

    private final OResultSet oResultSet;
    private final OrientdbEntityInformation<T, ID> entityInformation;

    /**
     * Creates a new {@link TypedQuery}
     *
     * @param oResultSet
     * @param entityInformation
     */
    public TypedQuery(final OResultSet oResultSet, final OrientdbEntityInformation<T, ID> entityInformation) {
        this.oResultSet = oResultSet;
        this.entityInformation = entityInformation;
    }

    /**
     * Gets the query result.
     *
     * @return
     */
    public List<T> getResultList() {
        HashMap<OElement, Object> converted = new HashMap<>();
        return oResultSet.elementStream()
                .map(e -> entityInformation.getEntityProxy(e, converted))
                .collect(Collectors.toList());
    }

}
