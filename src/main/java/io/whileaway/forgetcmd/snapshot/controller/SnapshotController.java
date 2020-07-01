package io.whileaway.forgetcmd.snapshot.controller;

import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.GetSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.task.SnapshotTask;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/snapshots")
@AllArgsConstructor
public class SnapshotController {

    private final SnapshotTask task;

    @PostMapping
    public Result<Snapshot> createSnapshot(@RequestBody CreateSnapshotRequest request) {
        return ResultUtil.success(task.createSnapshot(request));
    }

    @GetMapping("/{snapId}")
    public Result<Snapshot> getSnapshotBySid(@PathVariable("snapId") Long snapId,GetSnapshotRequest request) {
        request.setSnapId(snapId);
        return ResultUtil.success(task.getSnapshot(request));
    }

    @GetMapping("/search")
    public Result<List<Snapshot>> searchSnapshot(SearchSnapshotRequest request) {
        return ResultUtil.success(task.searchSnapshot(request));
    }

    @PutMapping("/{snapId}")
    public Result<Snapshot> searchSnapshot(@PathVariable("snapId") Long snapId, @RequestBody CreateSnapshotRequest request) {
        request.setSnapId(snapId);
        return ResultUtil.success(task.upgradeSnap(request));
    }

    @GetMapping("/{snapId}/shareCode")
    public Result<String> getSnapshotBySid(@PathVariable("snapId") Long snapId) {
        return ResultUtil.success(task.getSnapshotById(snapId).getShareCode());
    }
}
