package io.whileaway.forgetcmd.commit.repository;

import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommandCommitRepository extends BaseRepository<CommandCommit, Long> {

    /**
     * 获取待确认的组
     * @param status 状态
     * @return 符合条件的列表
     */
    // TODO 如果当前有同命令不同版本的，那么都会展示  但是实际上是需要去掉过时的
    @Query(value = "SELECT " +
            " group_concat(ccid) AS ccids ," +
            " command_name AS 'commandName'," +
            " cid," +
            " MAX(create_time) as 'createTime'," +
            " MAX(version) as 'version'" +
            " from command_commit " +
            " where status = :#{#status.code} " +
            " GROUP BY command_name, cid", nativeQuery = true)
    List<Map<String, Object>> groupCommandName(@Param("status")CommitStatus status);

    List<CommandCommit> findByCidAndStatus(Long cid, CommitStatus status);

    List<CommandCommit> findByCommandNameAndVersionAndStatus(String commandName, Long version, CommitStatus status);

    @Query(value = "select m from CommandCommit m where commandName = :commandName and version = 0 and status = :#{#status.code} ")
    List<CommandCommit> initialCommitByNameAndStatus(@Param("commandName") String commandName,@Param("status") CommitStatus needReview);

    @Query(value = "select m from CommandCommit m where commandName = :commandName and version > :version")
    List<CommandCommit> updatedVersionCommit(@Param("commandName") String commandName, @Param("version")Long version);

    List<CommandCommit> findByDid(Long did);
}
