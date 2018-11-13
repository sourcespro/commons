#### 1、use the left and right tree structure
> create an Entity extends BaseTree
```
public class Tree extends BaseTree {
    private String name;
    ……
}
```
> use the tree plug-in
```
@Bean
public LRTreeInterceptor treeInterceptor(){
    logger.info("Initialize the left and right tree structure mybatis plug-in");
    return new LRTreeInterceptor();
}
```
> The TreeMapper.java is probably like this.  
> insert method, This plug-in intercepts the Entity extends of BaseTree.
> delete method, This plug-in intercepts the @LRTreeDelete.
```
@Mapper
public interface TreeMapper {

    int insert(Tree record);

    int updateById(Tree record);

    Tree findById(Long id);

    List<Tree> findByPid(Long pid);

    List<Tree> findByLftAndRgt(@Param("lft") Integer lft, @Param("rgt") Integer rgt);

    @LRTreeDelete
    int deleteById(Long id);
}
```