package com.byritium.apis.http;

import com.byritium.application.GroupAppService;
import com.byritium.application.command.GroupCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupAppService groupAppService;

    @RequestMapping("create")
    public void create(@RequestHeader String token, GroupCommand groupCommand) {
        groupAppService.createGroup(groupCommand);
    }

    @RequestMapping("join")
    @ResponseBody
    public void join(@RequestHeader String token, GroupCommand groupCommand) {
        groupAppService.createGroupMember(groupCommand);
    }

    @RequestMapping("leave")
    public void leave() {

    }

}
