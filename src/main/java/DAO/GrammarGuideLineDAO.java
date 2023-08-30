package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import BEAN.GrammarGuideLine;



public class GrammarGuideLineDAO {
	public static void InsertGrammarGuideLineName(HttpServletRequest request,HttpServletResponse response,Connection conn, GrammarGuideLine gr) {
		String sql = "INSERT INTO grammarguideline (grammarname) VALUES (?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String grammarname=gr.getGrammarname();
			
			statement.setString(1, grammarname);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		 
}
	public static List<GrammarGuideLine> DisplayGrammarGuideLine(Connection conn) {
		List<GrammarGuideLine> list = new ArrayList<>();
		String sql = "Select * from GrammarGuideLine";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				GrammarGuideLine gr=new GrammarGuideLine();
				int grammarguidelineid = resultSet.getInt("grammarguidelineid");
			    String grammarname = resultSet.getString("grammarname");
			    String grammarimage = resultSet.getString("grammarimage");
			    String content = resultSet.getString("content");
			    
			    gr.setGrammarguidelineid(grammarguidelineid);
			    gr.setGrammarname(grammarname);
			    gr.setGrammarimage(grammarimage);
			    gr.setContent(content);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static List<GrammarGuideLine> DisplayGrammarGuideLineAdminPage(int start,int count,Connection conn) {
		List<GrammarGuideLine> list = new ArrayList<>();
		String sql = "SELECT * FROM grammarguideline LIMIT "+count+" OFFSET "+(start-1);
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				GrammarGuideLine gr=new GrammarGuideLine();
				int grammarguidelineid = resultSet.getInt("grammarguidelineid");
			    String grammarname = resultSet.getString("grammarname");
			    String grammarimage = resultSet.getString("grammarimage");
			    String content = resultSet.getString("content");
			    
			    gr.setGrammarguidelineid(grammarguidelineid);
			    gr.setGrammarname(grammarname);
			    gr.setGrammarimage(grammarimage);
			    gr.setContent(content);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	
	
	public static void UploadSingleFile(HttpServletRequest request, HttpServletResponse response,Connection conn, int grammarguidelineid) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/imageUpload/");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadedFilePath = null;
		if(!isMultipart) {
			request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
		}
		 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
//        File uploadDir = new File(UPLOAD_DIRECTORY);
//        uploadDir.mkdir();
		  if (ServletFileUpload.isMultipartContent(request)) {
	            try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Đặt ngưỡng kích thước tối đa của file upload (nếu muốn)
        factory.setSizeThreshold(1024 * 1024 * 3); // 3MB

        // Đặt thư mục lưu trữ file upload tạm thời
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
     // Tạo một ServletFileUpload để xử lý file upload
        ServletFileUpload upload = new ServletFileUpload(factory);
        
     // Set overall request size constraint
        upload.setSizeMax(1024 * 1024 * 50); //50MB
        
     // Xử lý file upload
        List<FileItem> formItems = upload.parseRequest(request);
        
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // Kiểm tra xem item có phải là file upload hay không
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    
                    File storeFile = new File(filePath);

                    // Lưu file upload vào đường dẫn cụ thể
                    item.write(storeFile);
                    DAO.GrammarGuideLineDAO.InsertGrammarguidelineImage(request, response, conn, grammarguidelineid, fileName);
                    
                }
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
        request.setAttribute("message", "Upload thất bại: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
    }
	} else {
    request.setAttribute("message", "Yêu cầu không chứa dữ liệu file upload");
	}

	
}
	public static void InsertGrammarguidelineImage(HttpServletRequest request, HttpServletResponse response,Connection conn,int grammarguidelineid, String image) {
		String sql = "update grammarguideline set grammarimage=? where grammarguidelineid="+grammarguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			
			
			statement.setString(1, image);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		
	}
	public static void InsertGrammarguidelineContent(HttpServletRequest request, GrammarGuideLine gr,Connection conn,int grammarguidelineid) {
		String sql = "update grammarguideline set content=? where grammarguidelineid="+grammarguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String content=gr.getContent();
			
			statement.setString(1, content);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		
	}
	
	public static List<GrammarGuideLine> DisplayGrammarGuideLinePage(int start,int count,Connection conn) {
		List<GrammarGuideLine> list = new ArrayList<>();
		String sql = "SELECT * FROM grammarguideline LIMIT "+count+" OFFSET "+(start-1);

		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			
			
			while (resultSet.next()) {
				GrammarGuideLine gr=new GrammarGuideLine();
				int grammarguidelineid = resultSet.getInt("grammarguidelineid");
			    String grammarname = resultSet.getString("grammarname");
			    String grammarimage = resultSet.getString("grammarimage");
			    String content = resultSet.getString("content");
			    
			    gr.setGrammarguidelineid(grammarguidelineid);
			    gr.setGrammarname(grammarname);
			    gr.setGrammarimage(grammarimage);
			    gr.setContent(content);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static int countRow(Connection conn) {
		String sql = "SELECT count(*) FROM grammarguideline";
		int count=0;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			count=resultSet.getInt(1);
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return count;
	}
	
	public static List<GrammarGuideLine> DisplayGrammarGuideLineContent(Connection conn,int grammarguidelineid) {
		List<GrammarGuideLine> list = new ArrayList<>();
		String sql = "Select * from GrammarGuideLine where grammarguidelineid="+grammarguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				GrammarGuideLine gr=new GrammarGuideLine();
				int id = resultSet.getInt("grammarguidelineid");
			    String grammarname = resultSet.getString("grammarname");
			    String grammarimage = resultSet.getString("grammarimage");
			    String content = resultSet.getString("content");
			    
			    gr.setGrammarguidelineid(id);
			    gr.setGrammarname(grammarname);
			    gr.setGrammarimage(grammarimage);
			    gr.setContent(content);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	
	public static void DeleteGrammarGuideLine(HttpServletRequest request, HttpServletResponse response,Connection conn, int grammarguidelineid) {
		
		String sql = "DELETE FROM grammarguideline WHERE grammarguidelineid = "+grammarguidelineid;
	    PreparedStatement statement = null;

	    try {
	        statement = conn.prepareStatement(sql);
	        statement.executeUpdate();
	        request.setAttribute("message", "Xóa thành công " );
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	        	 request.setAttribute("message", "Xóa thất bại: " + e.getMessage());
	        }
	    }
	}
	
}
