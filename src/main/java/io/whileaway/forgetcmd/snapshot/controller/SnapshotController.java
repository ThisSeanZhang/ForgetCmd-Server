package io.whileaway.forgetcmd.snapshot.controller;

import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.task.SnapshotTask;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

@RestController("/snapshots")
public class SnapshotController {

    private final SnapshotTask task;

    public SnapshotController(SnapshotTask task) {
        this.task = task;
    }

    @PostMapping
    @AdminPermit
    public Result<Snapshot> createSnapshot(@RequestBody CreateSnapshotRequest request) {
        return ResultUtil.success(task.createSnapshot(request));
    }

    @GetMapping("/{cid}")
    @AdminPermit
    public Result<Snapshot> getSnapshotBySid(@PathVariable("cid") Long cid) {
        return ResultUtil.success(task.getSnapshotById(cid));
    }
}
