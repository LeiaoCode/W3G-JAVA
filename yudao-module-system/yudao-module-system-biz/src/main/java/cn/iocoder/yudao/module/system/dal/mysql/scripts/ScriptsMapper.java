package cn.iocoder.yudao.module.system.dal.mysql.scripts;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.scripts.ScriptsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.scripts.vo.*;

/**
 * Web3脚本 Mapper
 *
 * @author Awren
 */
@Mapper
public interface ScriptsMapper extends BaseMapperX<ScriptsDO> {

    default PageResult<ScriptsDO> selectPage(ScriptsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScriptsDO>()
                .likeIfPresent(ScriptsDO::getName, reqVO.getName())
                .eqIfPresent(ScriptsDO::getId, reqVO.getId())
                .eqIfPresent(ScriptsDO::getType, reqVO.getType())
                .eqIfPresent(ScriptsDO::getProjectId, reqVO.getProjectId())
                .eqIfPresent(ScriptsDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ScriptsDO::getIsLocalExecution, reqVO.getIsLocalExecution())
                .orderByDesc(ScriptsDO::getId));
    }

}