package io.whileaway.forgetcmd.rbac.repository;

import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedRepository extends BaseRepository<ResourceRelated, Long> {

    List<ResourceRelated> findAllByDidAndResourceId(Long did, Long resourceId);

}
