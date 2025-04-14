package cn.iocoder.yudao.module.system.service.projects;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.ProjectsPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.ProjectsSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.projects.ProjectsDO;

/**
 * Web3项目 Service 接口
 *
 * @author Awren
 */
public interface ProjectsService {

    /**
     * 创建Web3项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createProjects(@Valid ProjectsSaveReqVO createReqVO);

    /**
     * 更新Web3项目
     *
     * @param updateReqVO 更新信息
     */
    void updateProjects(@Valid ProjectsSaveReqVO updateReqVO);

    /**
     * 删除Web3项目
     *
     * @param id 编号
     */
    void deleteProjects(Integer id);

    /**
     * 获得Web3项目
     *
     * @param id 编号
     * @return Web3项目
     */
    ProjectsDO getProjects(Integer id);

    /**
     * 获得Web3项目分页
     *
     * @param pageReqVO 分页查询
     * @return Web3项目分页
     */
    PageResult<ProjectsDO> getProjectsPage(ProjectsPageReqVO pageReqVO);

}