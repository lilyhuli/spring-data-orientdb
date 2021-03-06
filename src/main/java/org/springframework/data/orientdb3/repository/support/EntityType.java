package org.springframework.data.orientdb3.repository.support;

import org.springframework.data.orientdb3.repository.EdgeEntity;
import org.springframework.data.orientdb3.repository.ElementEntity;
import org.springframework.data.orientdb3.repository.EmbeddedEntity;
import org.springframework.data.orientdb3.repository.Index;
import org.springframework.data.orientdb3.repository.VertexEntity;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Enumeration representing entity types.
 *
 * @author xxcxy
 */
public enum EntityType {
    /**
     * Represents elementEntity.
     */
    ELEMENT {
        /*
         * (non-Javadoc)
         * @see EntityType#getName
         */
        @Override
        protected <T> Optional<String> getName(final Class<T> clazz) {
            return Optional.of(clazz.getAnnotation(ElementEntity.class).name())
                    .filter(s -> !StringUtils.isEmpty(s));
        }

        /*
         * (non-Javadoc)
         * @see EntityType#getIndex
         */
        @Override
        public <T> Index[] getIndex(final Class<T> clazz) {
            return clazz.getAnnotation(ElementEntity.class).indexes();
        }
    },
    /**
     * Represents vertexEntity.
     */
    VERTEX {
        /*
         * (non-Javadoc)
         * @see EntityType#getName
         */
        @Override
        protected <T> Optional<String> getName(final Class<T> clazz) {
            return Optional.of(clazz.getAnnotation(VertexEntity.class).name())
                    .filter(s -> !StringUtils.isEmpty(s));
        }

        /*
         * (non-Javadoc)
         * @see EntityType#getIndex
         */
        @Override
        public <T> Index[] getIndex(final Class<T> clazz) {
            return clazz.getAnnotation(VertexEntity.class).indexes();
        }
    },
    /**
     * Represents edgeEntity.
     */
    EDGE {
        /*
         * (non-Javadoc)
         * @see EntityType#getName
         */
        @Override
        protected <T> Optional<String> getName(final Class<T> clazz) {
            return Optional.of(clazz.getAnnotation(EdgeEntity.class).name())
                    .filter(s -> !StringUtils.isEmpty(s));
        }

        /*
         * (non-Javadoc)
         * @see EntityType#getIndex
         */
        @Override
        public <T> Index[] getIndex(final Class<T> clazz) {
            return clazz.getAnnotation(EdgeEntity.class).indexes();
        }
    },
    /**
     * Represents embeddedElementEntity.
     */
    EMBEDDED {
        /*
         * (non-Javadoc)
         * @see EntityType#getName
         */
        @Override
        protected <T> Optional<String> getName(final Class<T> clazz) {
            return Optional.of(clazz.getAnnotation(EmbeddedEntity.class).name())
                    .filter(s -> !StringUtils.isEmpty(s));
        }

        /*
         * (non-Javadoc)
         * @see EntityType#getIndex
         */
        @Override
        public <T> Index[] getIndex(final Class<T> clazz) {
            return new Index[0];
        }
    };

    /**
     * Gets the orientdb's class name corresponding to this java class.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    protected abstract <T> Optional<String> getName(final Class<T> clazz);

    /**
     * Gets the orientdb's class name corresponding to this java class.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> String getEntityName(final Class<T> clazz) {
        return getName(clazz).orElse(clazz.getSimpleName());
    }

    /**
     * Gets the entity's index config.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T> Index[] getIndex(final Class<T> clazz);

    /**
     * Gets a entity's type.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Optional<EntityType> getEntityType(final Class<T> clazz) {
        if (clazz.getAnnotation(ElementEntity.class) != null) {
            return Optional.of(ELEMENT);
        }
        if (clazz.getAnnotation(VertexEntity.class) != null) {
            return Optional.of(VERTEX);
        }
        if (clazz.getAnnotation(EdgeEntity.class) != null) {
            return Optional.of(EDGE);
        }
        if (clazz.getAnnotation(EmbeddedEntity.class) != null) {
            return Optional.of(EMBEDDED);
        }
        return Optional.empty();
    }
}
