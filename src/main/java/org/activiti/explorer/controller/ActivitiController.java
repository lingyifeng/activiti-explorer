package org.activiti.explorer.controller;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.explorer.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lingzong on 2019/9/2.
 */
@RestController("ActivitiController")
@RequestMapping("/ActivitiController")
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/uploadBPMNFile", method = RequestMethod.POST, produces = {"application/json"})
    public Result uploadBPMNFile(@RequestParam("path") String path) throws IOException {
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        //创建部署构建器
        DeploymentBuilder deployment = repositoryService.createDeployment();
        //参数一作为资源名称，参数二为流程资源输入流
        Deployment deploy = deployment.addInputStream(file.getName(), stream).deploy();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", deploy.getId());
        return new Result(true, null, null, map);


    }

    /**
     * 获取流程实例列表
     *
     * @return
     */
    @RequestMapping(value = "/processDefinitionList", method = RequestMethod.POST)
    public Result processDefinitionList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().latestVersion().list();
        return new Result(true, null, null, list);
    }

    /**
     * 删除实例
     *
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value = "/deleteProcessDefinition", method = RequestMethod.POST)
    public Result deleteProcessDefinition(@RequestParam("processDefinitionId") String processDefinitionId) {
        repositoryService.deleteDeployment(processDefinitionId, true);
        return new Result(true);
    }

    /**
     * 获取开始事件的form表单key
     *
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value = "/verifyStartForm", method = RequestMethod.POST)
    public Result verifyStartForm(@RequestParam("processDefinitionId") String processDefinitionId) {
        StartFormData startFormData = formService.getStartFormData(processDefinitionId);
        ArrayList<String> keys = new ArrayList<String>();
        if (startFormData.getFormProperties() != null) {
            for (FormProperty formProperty : startFormData.getFormProperties()) {
                if (formProperty.getValue() == null) {
                    keys.add(formProperty.getId());
                }

            }
        }
        return new Result(true, null, null, keys);

    }

    /**
     * 启动流程
     *
     * @param map
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value = "/startProcess", method = RequestMethod.POST)
    public Result startProcess(@RequestBody Map<String, Object> map, @RequestParam("processDefinitionId") String processDefinitionId) {
        runtimeService.startProcessInstanceById(processDefinitionId, map);
        return new Result(true, null, null);

    }

    /**
     * 查询个人任务
     *
     * @param useraccount
     * @return
     */
    @RequestMapping(value = "/queryPersionTask", method = RequestMethod.POST)
    public Result queryPersionTask(@RequestParam("useraccount") String useraccount) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(useraccount).list();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
        for (Task task : list) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("assignee", task.getAssignee());
            List<Attachment> taskAttachments = taskService.getTaskAttachments(task.getId());
            if (taskAttachments != null && taskAttachments.size() > 0) {
                map.put("attachments", taskAttachments);
            }
            list1.add(map);
        }
        return new Result(true, null, null, list1);
    }

    /**
     * 获取组任务
     *
     * @param roleName 组名称
     * @return
     */
    @RequestMapping(value = "/queryGroupTask", method = RequestMethod.POST)
    public Result queryGroupTask(@RequestParam("roleName") String roleName) {
        List<Task> list = taskService.createTaskQuery().taskCandidateGroup(roleName).orderByTaskCreateTime().asc().list();
        return new Result(true, null, null, list);
    }

    /**
     * 组任务重新分配
     *
     * @param taskid   任务id
     * @param username 用户名称
     * @return
     */
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public Result distribution(@RequestParam("taskid") String taskid, @RequestParam("username") String username) {
        taskService.claim(taskid, username);
        return new Result(true);
    }

    /**
     * 查询任务的表单
     *
     * @param taskid
     * @return
     */
    @RequestMapping(value = "/queryTaskForm", method = RequestMethod.POST)
    public Result queryTaskForm(@RequestParam("taskId") String taskid) {
        List<FormProperty> list = formService.getTaskFormData(taskid).getFormProperties();
        ArrayList<String> keys = new ArrayList<String>();
        if (list != null) {
            for (FormProperty formProperty : list) {
                if (formProperty.getValue() != null) {
                    keys.add(formProperty.getId());
                }
            }
        }
        return new Result(true, null, null, keys);
    }

    /**
     * 完成任务
     *
     * @param taskId 任务id
     * @param map    任务form表单参数
     * @param path   文件路径
     * @return
     */
    @RequestMapping(value = "/finishTask", method = RequestMethod.POST)
    public Result finishTask(@RequestParam("taskId") String taskId, @RequestBody Map<String, Object> map,
                             @RequestParam(value = "path", required = false) String path) throws IOException {
        if (path != null) {
            File file = new File(path);
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            taskService.createAttachment(file.getName(), taskId, task.getProcessInstanceId(), null, null, path);
        }
        taskService.complete(taskId, map);

        return new Result(true);
    }

    /**
     * 查询历史
     * @param processInstanceId 实例id
     * @param processDefinitionId 流程id
     * @return
     */
    @RequestMapping(value = "historyList", method = RequestMethod.POST)
    public Result historyList(@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
                              @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId) {
        HistoricActivityInstanceQuery query = historyService
                .createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceStartTime();
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (processInstanceId != null) {
            query.processInstanceId(processDefinitionId);
        }
        List<HistoricActivityInstance> list = query.asc().list();
        if (list != null && list.size() > 0) {
            return new Result(true, null, null, list);
        }
        return new Result(true);
    }
}