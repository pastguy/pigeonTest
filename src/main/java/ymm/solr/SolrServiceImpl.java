package ymm.solr;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.BinaryResponseParser;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrServiceImpl implements ISolrjService {

	private static HttpSolrServer httpSolrServer = getHttpSolrServer();

	public static HttpSolrServer getHttpSolrServer(){

		httpSolrServer = new HttpSolrServer("http://192.168.199.92:8983/solr/ymmtest");
		httpSolrServer.setSoTimeout(120000); // socket read timeout
		httpSolrServer.setConnectionTimeout(3000);
		httpSolrServer.setDefaultMaxConnectionsPerHost(100);
		httpSolrServer.setMaxTotalConnections(200);
		httpSolrServer.setFollowRedirects(false); // defaults to false
		httpSolrServer.setAllowCompression(false);
		httpSolrServer.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		httpSolrServer.setRequestWriter(new BinaryRequestWriter());
		httpSolrServer.setParser(new BinaryResponseParser());
		return httpSolrServer;
	}

	public static void main(String[] args) throws MalformedURLException, SolrServerException {
		SolrServiceImpl service = new SolrServiceImpl();
		
		StudentDTO dto = new StudentDTO();
//		dto.setId(1l);
		dto.setName("张三*");
		List<StudentDTO> students = service.queryStudent(dto);
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));	
		}
	}
	
	public List<StudentDTO> queryStudent(StudentDTO studentDTO) throws SolrServerException{
		List<StudentDTO> students= new ArrayList<StudentDTO>();
		SolrQuery query = new SolrQuery();  
		query.setQuery(parseQuery(studentDTO));
//		query.setQuery("id:1");
		query.addSort(new SortClause("id", SolrQuery.ORDER.asc));
		
		QueryResponse queryResponse = httpSolrServer.query(query);
		SolrDocumentList list	=  queryResponse.getResults();
//		for (SolrDocument solrDocument : list) {
//			solrDocument.
//			students.add((StudentDTO)solrDocument);
//		}
		students = queryResponse.getBeans(StudentDTO.class);
		return students;
		
	}
	
	private String parseQuery(Object object) {
		StringBuilder sb = new StringBuilder();
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Method method;
			try {
				method = object.getClass().getMethod(  
				        "get" + UpperCaseFirstChar(field.getName()));
				Object o = method.invoke(object);
				if(o != null){
					sb.append(field.getName());
					sb.append(":");
					sb.append(o.toString());
					sb.append(" AND ");
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		
		}
		return sb.substring(0, sb.lastIndexOf("AND")).toString();
	}
	
	 // 转化字段首字母为大写  
    private  String UpperCaseFirstChar(String fieldName) {  
        fieldName = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName  
                .substring(0, 1).toUpperCase());  
        return fieldName;  
    }  
	
	
	
	@Override
	public List<Object> querySolrResult(Object propertyDO, Object compositorDO, Long startIndex, Long pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public Long querySolrResultCount(StudentDTO StudentDTO, Object compositorDO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
