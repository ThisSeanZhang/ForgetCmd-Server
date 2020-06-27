package io.whileaway.forgetcmd.snapshot.repository;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnapshotRepository extends BaseRepository<Snapshot, Long> {

//    @Query("SELECT Snapshot " +
//            "FROM Snapshot snap " +
//            "where snap.commandName LIKE %#{#request.commandName}% " +
//            "and snap.did")
//    List<Snapshot> searchSnapshot(SearchSnapshotRequest request);
}
