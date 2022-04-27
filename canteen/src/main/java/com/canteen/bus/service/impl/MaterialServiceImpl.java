package com.canteen.bus.service.impl;

import com.canteen.sys.controller.FileUploadController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.canteen.bus.mapper.MaterialMapper;
import com.canteen.bus.domain.Material;
import com.canteen.bus.service.MaterialService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:junlejunle
 * @create:2020/2/22-13:27
 */
@Service
@Transactional
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Override
    public boolean removeById(Serializable id) {
        // 删除图片
        FileUploadController.removeFileByPath(this.getBaseMapper().selectById(id).getMaterialImgPath());
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Material entity) {
        // 处理图片（将临时图片更为名字 去掉_temp）
        String imgPath = entity.getMaterialImgPath();
        if (!(StringUtils.isNotBlank(imgPath) && imgPath.equals("default.jpg"))) {
            if (imgPath.endsWith("_temp")) {
                // 改名
                imgPath = FileUploadController.renameFile(imgPath);
                entity.setMaterialImgPath(imgPath);
                // 删除原来的图片
                String oldImgPathName = this.getBaseMapper().selectById(entity.getId()).getMaterialImgPath();
                FileUploadController.removeFileByPath(oldImgPathName);
            }
        }
        return super.updateById(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Material getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Material entity) {
        // 处理图片（将临时图片更为名字 去掉_temp）
        String imgPath = entity.getMaterialImgPath();
        if (StringUtils.isNotBlank(imgPath) && imgPath.endsWith("_temp")) {
            imgPath = FileUploadController.renameFile(imgPath);
            entity.setMaterialImgPath(imgPath);
        }
        return super.save(entity);
    }

    /**
     * 当日食材库存统计
     * @return
     */
    @Override
    public List<Map<String, Object>> todayStatistics() {
        return this.getBaseMapper().todayStatistics();
    }

    /**
     * 周食材出库统计
     * @return
     */
    @Override
    public List<Map<String, Object>> weekOutstorageStatistics(Date startTime, Date endTime) {
        return this.getBaseMapper().weekOutstorageStatistics(startTime, endTime);
    }
}


