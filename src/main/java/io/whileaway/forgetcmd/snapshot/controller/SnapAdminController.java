package io.whileaway.forgetcmd.snapshot.controller;

import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.task.SnapshotTask;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/snaps")
@AllArgsConstructor
public class SnapAdminController {

    private final SnapshotTask task;

    @GetMapping
    @AdminPermit
    public Result<List<Snapshot>> allSnap() {
        return ResultUtil.success(task.getAllSnaps());
    }

    @DeleteMapping("{snapId}")
    @AdminPermit
    public Result deleteSnap(@PathVariable("snapId") Long snapId) {
        task.deleteSnap(snapId);

        return ResultUtil.success();
    }

//    @PutMapping("{snapId}")
//    @AdminPermit
//    public Result updateSnap(@PathVariable("snapId") Long snapId) {
//        task.updateSnap(snapId);
//        return ResultUtil.success();
//    }
}
