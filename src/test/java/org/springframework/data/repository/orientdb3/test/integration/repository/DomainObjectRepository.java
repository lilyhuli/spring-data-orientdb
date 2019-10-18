package org.springframework.data.repository.orientdb3.test.integration.repository;

import org.springframework.data.repository.orientdb3.repository.OrientdbRepository;
import org.springframework.data.repository.orientdb3.test.DomainObject;
import org.springframework.data.repository.orientdb3.test.integration.IdParser.CustId;

public interface DomainObjectRepository extends OrientdbRepository<DomainObject, CustId> {
}
