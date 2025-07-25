package cn.iocoder.yudao.module.system.controller.admin.orders;

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

import cn.iocoder.yudao.module.system.controller.admin.orders.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import cn.iocoder.yudao.module.system.service.orders.OrdersService;

@Tag(name = "管理后台 - 收款订单")
@RestController
@RequestMapping("/payment/orders")
@Validated
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/create")
    @Operation(summary = "创建收款订单")
    @PreAuthorize("@ss.hasPermission('payment:orders:create')")
    public CommonResult<String> createOrders(@Valid @RequestBody OrdersSaveReqVO createReqVO) {
        return success(ordersService.createOrders(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收款订单")
    @PreAuthorize("@ss.hasPermission('payment:orders:update')")
    public CommonResult<Boolean> updateOrders(@Valid @RequestBody OrdersSaveReqVO updateReqVO) {
        ordersService.updateOrders(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收款订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('payment:orders:delete')")
    public CommonResult<Boolean> deleteOrders(@RequestParam("id") String id) {
        ordersService.deleteOrders(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收款订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('payment:orders:query')")
    public CommonResult<OrdersRespVO> getOrders(@RequestParam("id") String id) {
        OrdersDO orders = ordersService.getOrders(id);
        return success(BeanUtils.toBean(orders, OrdersRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收款订单分页")
    @PreAuthorize("@ss.hasPermission('payment:orders:query')")
    public CommonResult<PageResult<OrdersRespVO>> getOrdersPage(@Valid OrdersPageReqVO pageReqVO) {
        PageResult<OrdersDO> pageResult = ordersService.getOrdersPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrdersRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收款订单 Excel")
    @PreAuthorize("@ss.hasPermission('payment:orders:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrdersExcel(@Valid OrdersPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrdersDO> list = ordersService.getOrdersPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收款订单.xls", "数据", OrdersRespVO.class,
                        BeanUtils.toBean(list, OrdersRespVO.class));
    }

}