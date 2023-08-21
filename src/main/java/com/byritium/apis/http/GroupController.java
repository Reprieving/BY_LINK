package com.byritium.apis.http;

import com.byritium.application.GroupAppService;
import com.byritium.application.command.GroupCommand;
import com.byritium.application.command.GroupMemberCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupAppService groupAppService;

    @RequestMapping("create")
    public void create(GroupCommand groupCommand) {
        groupAppService.createGroup(groupCommand);
    }

    @RequestMapping("join")
    @ResponseBody
    public void join(GroupMemberCommand groupMemberCommand) {
        groupAppService.createGroupMember(groupMemberCommand);
    }

    @RequestMapping("leave")
    public void leave() {

    }

}
