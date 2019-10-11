package io.xxcxy.spring.data.orientdb.repository.support;

import com.orientechnologies.orient.core.id.ORID;

public interface OrientdbIdParser<T> {
    ORID parseJavaId(T t);

    T parseOrientdbId(ORID orid);

    Class<T> getIdClass();
}
