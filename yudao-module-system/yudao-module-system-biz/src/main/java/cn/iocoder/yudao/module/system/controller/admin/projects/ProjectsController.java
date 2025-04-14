package cn.iocoder.yudao.module.system.controller.admin.projects;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.projects.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.projects.ProjectsDO;
import cn.iocoder.yudao.module.system.service.projects.ProjectsService;

@Tag(name = "管理后台 - Web3项目")
@RestController
@RequestMapping("/web3/projects")
@Validated
public class ProjectsController {

    @Resource
    private ProjectsService projectsService;

    @PostMapping("/create")
    @Operation(summary = "创建Web3项目")
    @PreAuthorize("@ss.hasPermission('web3:projects:create')")
    public CommonResult<Integer> createProjects(@Valid @RequestBody ProjectsSaveReqVO createReqVO) {
        return success(projectsService.createProjects(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Web3项目")
    @PreAuthorize("@ss.hasPermission('web3:projects:update')")
    public CommonResult<Boolean> updateProjects(@Valid @RequestBody ProjectsSaveReqVO updateReqVO) {
        projectsService.updateProjects(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Web3项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('web3:projects:delete')")
    public CommonResult<Boolean> deleteProjects(@RequestParam("id") Integer id) {
        projectsService.deleteProjects(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Web3项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('web3:projects:query')")
    public CommonResult<ProjectsRespVO> getProjects(@RequestParam("id") Integer id) {
        ProjectsDO projects = projectsService.getProjects(id);
        return success(BeanUtils.toBean(projects, ProjectsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Web3项目分页")
    @PreAuthorize("@ss.hasPermission('web3:projects:query')")
    public CommonResult<PageResult<ProjectsRespVO>> getProjectsPage(@Valid ProjectsPageReqVO pageReqVO) {
        PageResult<ProjectsDO> pageResult = projectsService.getProjectsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProjectsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Web3项目 Excel")
    @PreAuthorize("@ss.hasPermission('web3:projects:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProjectsExcel(@Valid ProjectsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProjectsDO> list = projectsService.getProjectsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Web3项目.xls", "数据", ProjectsRespVO.class,
                        BeanUtils.toBean(list, ProjectsRespVO.class));
    }

}