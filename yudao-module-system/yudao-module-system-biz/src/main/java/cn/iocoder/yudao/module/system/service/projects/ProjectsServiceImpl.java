package cn.iocoder.yudao.module.system.service.projects;

import cn.iocoder.yudao.module.system.dal.dataobject.projects.ProjectsDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.projects.ProjectsMapper;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.PROJECTS_NOT_EXISTS;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * Web3项目 Service 实现类
 *
 * @author Awren
 */
@Service
@Validated
public class ProjectsServiceImpl implements ProjectsService {

    @Resource
    private ProjectsMapper projectsMapper;

    @Override
    public Integer createProjects(ProjectsSaveReqVO createReqVO) {
        // 插入
        ProjectsDO projects = BeanUtils.toBean(createReqVO, ProjectsDO.class);
        projectsMapper.insert(projects);
        // 返回
        return projects.getId();
    }

    @Override
    public void updateProjects(ProjectsSaveReqVO updateReqVO) {
        // 校验存在
        validateProjectsExists(updateReqVO.getId());
        // 更新
        ProjectsDO updateObj = BeanUtils.toBean(updateReqVO, ProjectsDO.class);
        projectsMapper.updateById(updateObj);
    }

    @Override
    public void deleteProjects(Integer id) {
        // 校验存在
        validateProjectsExists(id);
        // 删除
        projectsMapper.deleteById(id);
    }

    private void validateProjectsExists(Integer id) {
        if (projectsMapper.selectById(id) == null) {
            throw exception(PROJECTS_NOT_EXISTS);
        }
    }

    @Override
    public ProjectsDO getProjects(Integer id) {
        return projectsMapper.selectById(id);
    }

    @Override
    public PageResult<ProjectsDO> getProjectsPage(ProjectsPageReqVO pageReqVO) {
        return projectsMapper.selectPage(pageReqVO);
    }

}