package cn.sourcespro.commons.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 分类 Mapper
 *
 * @author 张浩伟
 * @since 2018/07/09
 */
@Mapper
public interface BaseTreeMapper {

    @Select("SELECT MAX(rgt) FROM ${table} WHERE pid = 0;")
    Integer maxLv1Val(String table);

    @Select("SELECT MAX(rgt) FROM ${table} WHERE LEVEL = #{level} + 1 and lft > ${lft} and rgt < #{rgt};")
    Integer maxLvChildVal(@Param("table") String table,
                          @Param("level") Integer level,
                          @Param("lft") Integer lft,
                          @Param("rgt") Integer rgt);

    @Select("SELECT MIN(lft) FROM ${table} WHERE LEVEL = #{level} + 1 and lft > ${lft} and rgt < #{rgt} and id != #{id};")
    Integer minBrotherLftVal(@Param("table") String table,
                             @Param("level") Integer level,
                             @Param("lft") Integer lft,
                             @Param("rgt") Integer rgt,
                             @Param("id") Long id);

    @Update("UPDATE ${table} SET lft = lft + 2 WHERE lft > #{minVal};")
    int updateLftVal(@Param("table") String table,
                     @Param("minVal") Integer minVal);

    @Update("UPDATE ${table} SET rgt = rgt + 2 WHERE rgt > #{minVal};")
    int updateRgtVal(@Param("table") String table,
                     @Param("minVal") Integer minVal);

    @Select("SELECT * FROM ${table} where id = #{id};")
    Map<String, Object> findById(@Param("table") String table,
                                 @Param("id") Long id);

    @Select("SELECT * FROM ${table} where uuid = #{uuid};")
    Map<String,Object> findByUuid(@Param("table") String table,
                                  @Param("uuid") String uuid);

    @Update("UPDATE ${table} SET lft = lft - #{num} WHERE lft > #{minVal};")
    int updateLftSubVal(@Param("table") String table,
                        @Param("minVal") Integer minVal,
                        @Param("num") int num);

    @Update("UPDATE ${table} SET rgt = rgt - #{num} WHERE rgt > #{minVal};")
    int updateRgtSubVal(@Param("table") String table,
                        @Param("minVal") Integer minVal,
                        @Param("num") int num);

    @Update("UPDATE ${table} SET leaf = false WHERE id = #{id};")
    int updateParentNotLeaf(@Param("table") String table,
                            @Param("id") Long id);

    @Select("SELECT id FROM ${table} WHERE lft > ${lft} and rgt < #{rgt};")
    List<Integer> findChildIds(@Param("table") String table,
                               @Param("lft") Integer lft,
                               @Param("rgt") Integer rgt);
}