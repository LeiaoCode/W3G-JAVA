package cn.iocoder.yudao.module.system.service.scripts;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.scripts.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.scripts.ScriptsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * Web3脚本 Service 接口
 *
 * @author Awren
 */
public interface ScriptsService {

    /**
     * 创建Web3脚本
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScripts(@Valid ScriptsSaveReqVO createReqVO);

    /**
     * 更新Web3脚本
     *
     * @param updateReqVO 更新信息
     */
    void updateScripts(@Valid ScriptsSaveReqVO updateReqVO);

    /**
     * 删除Web3脚本
     *
     * @param id 编号
     */
    void deleteScripts(Long id);

    /**
     * 获得Web3脚本
     *
     * @param id 编号
     * @return Web3脚本
     */
    ScriptsDO getScripts(Long id);

    /**
     * 获得Web3脚本分页
     *
     * @param pageReqVO 分页查询
     * @return Web3脚本分页
     */
    PageResult<ScriptsDO> getScriptsPage(ScriptsPageReqVO pageReqVO);

}