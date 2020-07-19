package io.whileaway.forgetcmd.commit.repository;

import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitItemRepository extends BaseRepository<CommitItem, Long> {

    List<CommitItem> findByCcidIn(List<Long> ccids);

    List<CommitItem> findByCcid(Long ccid);

    @Modifying
    @Query(value="UPDATE CommitItem set cid = :cid where ccid in :ccids")
    void updateCidByCcids(@Param("cid") Long cid,@Param("ccids") Iterable<Long> ccids);
}
