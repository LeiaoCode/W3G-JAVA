package cn.iocoder.yudao.module.system.service.scripts;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.scripts.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.scripts.ScriptsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.scripts.ScriptsMapper;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.SCRIPTS_NOT_EXISTS;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * Web3脚本 Service 实现类
 *
 * @author Awren
 */
@Service
@Validated
public class ScriptsServiceImpl implements ScriptsService {

    @Resource
    private ScriptsMapper scriptsMapper;

    @Override
    public Long createScripts(ScriptsSaveReqVO createReqVO) {
        // 插入
        ScriptsDO scripts = BeanUtils.toBean(createReqVO, ScriptsDO.class);
        scriptsMapper.insert(scripts);
        // 返回
        return scripts.getId();
    }

    @Override
    public void updateScripts(ScriptsSaveReqVO updateReqVO) {
        // 校验存在
        validateScriptsExists(updateReqVO.getId());
        // 更新
        ScriptsDO updateObj = BeanUtils.toBean(updateReqVO, ScriptsDO.class);
        scriptsMapper.updateById(updateObj);
    }

    @Override
    public void deleteScripts(Long id) {
        // 校验存在
        validateScriptsExists(id);
        // 删除
        scriptsMapper.deleteById(id);
    }

    private void validateScriptsExists(Long id) {
        if (scriptsMapper.selectById(id) == null) {
            throw exception(SCRIPTS_NOT_EXISTS);
        }
    }

    @Override
    public ScriptsDO getScripts(Long id) {
        return scriptsMapper.selectById(id);
    }

    @Override
    public PageResult<ScriptsDO> getScriptsPage(ScriptsPageReqVO pageReqVO) {
        return scriptsMapper.selectPage(pageReqVO);
    }

}