package org.activiti.explorer.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.rest.diagram.services.BaseProcessDefinitionDiagramLayoutResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lingzong on 2019/8/14.
 */
@RestController("ProcessInstanceDiagramLayoutResourc")
@RequestMapping("/diagram-viewer/service")
public class ProcessInstanceDiagramLayoutResource extends BaseProcessDefinitionDiagramLayoutResource {


    @RequestMapping(
            value = {"/process-instance/{processInstanceId}/diagram-layout"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public ObjectNode getDiagram(@PathVariable String processInstanceId) {
        return this.getDiagramNode(processInstanceId, (String)null);
    }
}
