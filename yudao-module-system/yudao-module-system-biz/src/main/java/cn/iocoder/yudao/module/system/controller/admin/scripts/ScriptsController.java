package cn.iocoder.yudao.module.system.controller.admin.scripts;

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

import cn.iocoder.yudao.module.system.controller.admin.scripts.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.scripts.ScriptsDO;
import cn.iocoder.yudao.module.system.service.scripts.ScriptsService;

@Tag(name = "管理后台 - Web3脚本")
@RestController
@RequestMapping("/system/scripts")
@Validated
public class ScriptsController {

    @Resource
    private ScriptsService scriptsService;

    @PostMapping("/create")
    @Operation(summary = "创建Web3脚本")
    @PreAuthorize("@ss.hasPermission('web3:scripts:create')")
    public CommonResult<Long> createScripts(@Valid @RequestBody ScriptsSaveReqVO createReqVO) {
        return success(scriptsService.createScripts(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Web3脚本")
    @PreAuthorize("@ss.hasPermission('web3:scripts:update')")
    public CommonResult<Boolean> updateScripts(@Valid @RequestBody ScriptsSaveReqVO updateReqVO) {
        scriptsService.updateScripts(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Web3脚本")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('web3:scripts:delete')")
    public CommonResult<Boolean> deleteScripts(@RequestParam("id") Long id) {
        scriptsService.deleteScripts(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Web3脚本")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('web3:scripts:query')")
    public CommonResult<ScriptsRespVO> getScripts(@RequestParam("id") Long id) {
        ScriptsDO scripts = scriptsService.getScripts(id);
        return success(BeanUtils.toBean(scripts, ScriptsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Web3脚本分页")
    @PreAuthorize("@ss.hasPermission('web3:scripts:query')")
    public CommonResult<PageResult<ScriptsRespVO>> getScriptsPage(@Valid ScriptsPageReqVO pageReqVO) {
        PageResult<ScriptsDO> pageResult = scriptsService.getScriptsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ScriptsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Web3脚本 Excel")
    @PreAuthorize("@ss.hasPermission('web3:scripts:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScriptsExcel(@Valid ScriptsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScriptsDO> list = scriptsService.getScriptsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Web3脚本.xls", "数据", ScriptsRespVO.class,
                        BeanUtils.toBean(list, ScriptsRespVO.class));
    }

}