package io.whileaway.forgetcmd.commit.request;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.commit.enums.CommitError;
import io.whileaway.forgetcmd.util.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Data
public class ConfirmCommitRequest {

    private String ccids;
    private List<Integer> ciids;
    private Map<Long, List<Long>> usedIdMap = new HashMap<>();
    private Long version;
    private Long cid;
    private String commandName;
    private ConfirmCommitRequest() {}
    private ConfirmCommitRequest(String ccids, List<Integer> ciids, Map<Long, List<Long>> usedIdMap, Command cmd) {
        this.ccids = ccids;
        this.ciids = ciids;
        // 因为更新后的版本高了
        this.version = cmd.getVersion() - 1;
        this.cid = cmd.getCid();
        this.usedIdMap = usedIdMap;
        this.commandName = cmd.getCommandName();
    }

    public static ConfirmCommitRequest create(
            Supplier<String> ccids,
            Supplier<List<Integer>> ciids,
            Supplier<Map<Long, List<Long>>> usedIdMap,
            Supplier<Command> cmd
    ) {
        return new ConfirmCommitRequest(ccids.get(), ciids.get(),usedIdMap.get(), cmd.get());
    }

    public List<Long> getCcids() {
        return StringUtils.decodeIDs(ccids);
    }

    public boolean isInitialCommit() {
        if (Objects.nonNull(cid) && Objects.isNull(version)) {
            CommitError.VERSION_IMPOSSIBLE_BE_NULL.throwThis();
        }
        return Objects.isNull(cid);
    }

    public static void main(String[] args) {
        ConfirmCommitRequest request = new ConfirmCommitRequest();

        System.out.println(request.isInitialCommit());
        request.setCid(1L);
        request.setVersion(1L);
        System.out.println(request.isInitialCommit());
    }
}
