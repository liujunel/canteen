package com.canteen.sys.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.sys.mapper.LoginInfoMapper;
import com.canteen.sys.domain.LoginInfo;
import com.canteen.sys.service.LoginInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author:junle
 * @create:2020/2/20-13:22
 */
@Service
@Transactional
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService{

}
