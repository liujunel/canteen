package com.canteen.bus.cache;

import com.canteen.bus.domain.Material;
import com.canteen.bus.domain.MaterialCategory;
import com.canteen.bus.domain.RecipeCategory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 前端缓存
 *
 * @author:junle
 * @create:2020/2/23-20:19
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class BusCache {

    // 缓存容器
    private Map<String, Object> cacheMap = new HashMap<>();

    //----------------------------------对食材类别进行缓存-------------------------------------
    // 对系别进行CRUD的缓存处理
    public static final String POINTCUT_MATERIAL_CATEGORY_ADD = "execution(* com.canteen.bus.service.impl.MaterialCategoryServiceImpl.save(..))";
    public static final String POINTCUT_MATERIAL_CATEGORY_GET = "execution(* com.canteen.bus.service.impl.MaterialCategoryServiceImpl.getById(..))";
    public static final String POINTCUT_MATERIAL_CATEGORY_DELETE = "execution(* com.canteen.bus.service.impl.MaterialCategoryServiceImpl.removeById(..))";
    public static final String POINTCUT_MATERIAL_CATEGORY_UPDATE = "execution(* com.canteen.bus.service.impl.MaterialCategoryServiceImpl.updateById(..))";
    // 定义系别缓存的前缀
    public static final String CACHE_MATERIAL_CATEGORY_PREFIX = "materialCategory:";


    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_CATEGORY_ADD)
    public Object materialCategoryAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        MaterialCategory section = (MaterialCategory) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_MATERIAL_CATEGORY_PREFIX + section.getId(), section);
            log.info("【食材类别对象】，缓存已添加" + CACHE_MATERIAL_CATEGORY_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_CATEGORY_GET)
    public Object materialCategoryGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        MaterialCategory section1 = (MaterialCategory) cacheMap.get(CACHE_MATERIAL_CATEGORY_PREFIX + sectionId);
        if (null != section1) {
            log.info("已从缓存中找到【食材类别对象】" + CACHE_MATERIAL_CATEGORY_PREFIX + sectionId);
            return section1;
        } else {
            // 执行目标方法
            MaterialCategory section2 = (MaterialCategory) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_MATERIAL_CATEGORY_PREFIX + section2.getId(), section2);
            log.info("未从缓存中找到【食材类别对象】，去数据库查询" + CACHE_MATERIAL_CATEGORY_PREFIX + section2.getId());
            return section2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_CATEGORY_UPDATE)
    public Object materialCategoryUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        MaterialCategory section = (MaterialCategory) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_MATERIAL_CATEGORY_PREFIX + section.getId());
            cacheMap.put(CACHE_MATERIAL_CATEGORY_PREFIX + section.getId(), section);
            log.info("【食材类别对象】，缓存已更新" + CACHE_MATERIAL_CATEGORY_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_CATEGORY_DELETE)
    public Object materialCategoryDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_MATERIAL_CATEGORY_PREFIX + sectionId);
            log.info("【食材类别对象】，缓存已删除" + CACHE_MATERIAL_CATEGORY_PREFIX + sectionId);
        }
        return flag;
    }

    //----------------------------------对食材进行缓存-------------------------------------
    // 对系别进行CRUD的缓存处理
    public static final String POINTCUT_MATERIAL_ADD = "execution(* com.canteen.bus.service.impl.MaterialServiceImpl.save(..))";
    public static final String POINTCUT_MATERIAL_GET = "execution(* com.canteen.bus.service.impl.MaterialServiceImpl.getById(..))";
    public static final String POINTCUT_MATERIAL_DELETE = "execution(* com.canteen.bus.service.impl.MaterialServiceImpl.removeById(..))";
    public static final String POINTCUT_MATERIAL_UPDATE = "execution(* com.canteen.bus.service.impl.MaterialServiceImpl.updateById(..))";
    // 定义系别缓存的前缀
    public static final String CACHE_MATERIAL_PREFIX = "material:";


    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_ADD)
    public Object materialAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Material section = (Material) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_MATERIAL_PREFIX + section.getId(), section);
            log.info("【食材对象】，缓存已添加" + CACHE_MATERIAL_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_GET)
    public Object materialGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        Material section1 = (Material) cacheMap.get(CACHE_MATERIAL_PREFIX + sectionId);
        if (null != section1) {
            log.info("已从缓存中找到【食材对象】" + CACHE_MATERIAL_PREFIX + sectionId);
            return section1;
        } else {
            // 执行目标方法
            Material section2 = (Material) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_MATERIAL_PREFIX + section2.getId(), section2);
            log.info("未从缓存中找到【食材对象】，去数据库查询" + CACHE_MATERIAL_PREFIX + section2.getId());
            return section2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_UPDATE)
    public Object materialUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Material section = (Material) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_MATERIAL_PREFIX + section.getId());
            cacheMap.put(CACHE_MATERIAL_PREFIX + section.getId(), section);
            log.info("【食材对象】，缓存已更新" + CACHE_MATERIAL_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_MATERIAL_DELETE)
    public Object materialDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_MATERIAL_PREFIX + sectionId);
            log.info("【食材对象】，缓存已删除" + CACHE_MATERIAL_PREFIX + sectionId);
        }
        return flag;
    }

    //----------------------------------对食材进行缓存-------------------------------------
    // 对系别进行CRUD的缓存处理
    public static final String POINTCUT_RecipeCategory_ADD = "execution(* com.canteen.bus.service.impl.RecipeCategoryServiceImpl.save(..))";
    public static final String POINTCUT_RecipeCategory_GET = "execution(* com.canteen.bus.service.impl.RecipeCategoryServiceImpl.getById(..))";
    public static final String POINTCUT_RecipeCategory_DELETE = "execution(* com.canteen.bus.service.impl.RecipeCategoryServiceImpl.removeById(..))";
    public static final String POINTCUT_RecipeCategory_UPDATE = "execution(* com.canteen.bus.service.impl.RecipeCategoryServiceImpl.updateById(..))";
    // 定义系别缓存的前缀
    public static final String CACHE_RecipeCategory_PREFIX = "recipeCategory:";


    /**
     * 添加
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_RecipeCategory_ADD)
    public Object recipeCategoryAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        RecipeCategory section = (RecipeCategory) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 添加成功往缓存中放入数据
        if (flag) {
            cacheMap.put(CACHE_RecipeCategory_PREFIX + section.getId(), section);
            log.info("【菜谱类别对象】，缓存已添加" + CACHE_RecipeCategory_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 查询
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_RecipeCategory_GET)
    public Object recipeCategoryGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        // 从缓存中查询数据
        RecipeCategory section1 = (RecipeCategory) cacheMap.get(CACHE_RecipeCategory_PREFIX + sectionId);
        if (null != section1) {
            log.info("已从缓存中找到【菜谱类别对象】" + CACHE_RecipeCategory_PREFIX + sectionId);
            return section1;
        } else {
            // 执行目标方法
            RecipeCategory section2 = (RecipeCategory) joinPoint.proceed();
            // 查询成功往缓存中放入数据
            cacheMap.put(CACHE_RecipeCategory_PREFIX + section2.getId(), section2);
            log.info("未从缓存中找到【菜谱类别对象】，去数据库查询" + CACHE_RecipeCategory_PREFIX + section2.getId());
            return section2;

        }
    }

    /**
     * 更新
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_RecipeCategory_UPDATE)
    public Object recipeCategoryUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        RecipeCategory section = (RecipeCategory) joinPoint.getArgs()[0];
        // 执行目标方法
        Boolean flag = (Boolean) joinPoint.proceed();
        // 执行成功后，从缓存中删除数据再添加
        if (flag) {
            cacheMap.remove(CACHE_RecipeCategory_PREFIX + section.getId());
            cacheMap.put(CACHE_RecipeCategory_PREFIX + section.getId(), section);
            log.info("【菜谱类别对象】，缓存已更新" + CACHE_RecipeCategory_PREFIX + section.getId());
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_RecipeCategory_DELETE)
    public Object recipeCategoryDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer sectionId = (Integer) joinPoint.getArgs()[0];
        Boolean flag = (Boolean) joinPoint.proceed();
        if (flag) {
            cacheMap.remove(CACHE_RecipeCategory_PREFIX + sectionId);
            log.info("【菜谱类别对象】，缓存已删除" + CACHE_RecipeCategory_PREFIX + sectionId);
        }
        return flag;
    }
}
