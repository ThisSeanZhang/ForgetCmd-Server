package io.whileaway.forgetcmd.commit.controller;

import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.request.ItemSearchRequest;
import io.whileaway.forgetcmd.commit.task.CommitItemTask;
import io.whileaway.forgetcmd.commit.task.CommitTask;
import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class CommitItemController {

    private final CommitItemTask task;

    public CommitItemController(CommitItemTask task) {
        this.task = task;
    }

//    @GetMapping("cmds/{cid}/versions/current")
//    public Result<List<CommitItem>> currentVersionCmdItems(@PathVariable(name = "cid")Long cid) {
//        return ResultUtil.success(task.getCommandCurrentVersion(cid));
//    }

//    @GetMapping("cmds/{cid}/version/{version}")
//    @AdminPermit
//    public Result commandCommitItem(@PathVariable(name = "cid")Long cid, @PathVariable(name = "version") String version) {
//        return ResultUtil.success();
//    }

    @GetMapping
    @AdminPermit
    public Result<List<CommitItem>> searchCommitItems(ItemSearchRequest request) {
        return ResultUtil.success(task.searchItems(request));
    }

    @GetMapping("commit/{ccid}")
    public Result<List<CommitItem>> getCommitAllItems(@PathVariable("ccid") Long ccid) {
        return ResultUtil.success(task.findItemsByCCID(ccid));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String temp = "UTF-8";
        for (int i = 0; i < 1_0000_0000 ; i++) {
            temp = MD2(temp);
        }
        System.out.println(temp);
    }
    public static String MD2 (String str) throws NoSuchAlgorithmException {
            // 获取MD2加密工具
            MessageDigest md = MessageDigest.getInstance("MD2");
            // 加密
            byte[] digest = md.digest(str.getBytes());
            // 获取二进制十六进制互转工具
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            // 将二进制数组转换为十六进制字符串
            String marshal = hexBinaryAdapter.marshal(digest);
            // 输出结果
            return marshal;
    }
}
