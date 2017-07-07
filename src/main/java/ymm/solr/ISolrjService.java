package ymm.solr;

import java.util.List;

public interface ISolrjService {

	/** 
     * 获得搜索结果 
     *  
     * @param propertyDO 
     * @param compositorDO 
     * @param startIndex 
     * @param pageSize 
     * @return 
     * @throws Exception 
     */  
    public List<Object> querySolrResult(Object propertyDO,  
            Object compositorDO, Long startIndex, Long pageSize)  
            throws Exception;  
  
    /** 
     * 获得搜索结果条数 
     *  
     * @param propertyDO 
     * @param compositorDO 
     * @return 
     * @throws Exception 
     */  
    public Long querySolrResultCount(StudentDTO StudentDTO,  
            Object compositorDO) throws Exception; 
}
