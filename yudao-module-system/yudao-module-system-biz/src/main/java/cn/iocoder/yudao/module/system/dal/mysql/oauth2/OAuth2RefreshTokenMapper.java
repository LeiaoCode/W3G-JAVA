package cn.iocoder.yudao.module.system.dal.mysql.oauth2;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapperX<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

    @TenantIgnore // 获取 token 的时候，需要忽略租户编号。原因是：一些场景下，可能不会传递 tenant-id 请求头，例如说文件上传、积木报表等等
    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
        return selectOne(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
    }

    default List<OAuth2RefreshTokenDO> getByUserId(Long userId) {
        QueryWrapper<OAuth2RefreshTokenDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.selectList(queryWrapper);
    }

}
