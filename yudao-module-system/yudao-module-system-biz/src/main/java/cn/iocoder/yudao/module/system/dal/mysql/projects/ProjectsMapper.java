package cn.iocoder.yudao.module.system.dal.mysql.projects;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.ProjectsPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.projects.ProjectsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Web3项目 Mapper
 *
 * @author Awren
 */
@Mapper
public interface ProjectsMapper extends BaseMapperX<ProjectsDO> {

    default PageResult<ProjectsDO> selectPage(ProjectsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProjectsDO>()
                .eqIfPresent(ProjectsDO::getId, reqVO.getId())
                .eqIfPresent(ProjectsDO::getScriptType, reqVO.getScriptType())
                .likeIfPresent(ProjectsDO::getName, reqVO.getName())
                .eqIfPresent(ProjectsDO::getUrl, reqVO.getUrl())
                .eqIfPresent(ProjectsDO::getPlatformType, reqVO.getPlatformType())
                .orderByDesc(ProjectsDO::getId));
    }

}