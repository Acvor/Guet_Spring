package cn.edu.guet.controller;

import cn.edu.guet.bean.News;
import cn.edu.guet.mapper.NewsMapper;
import cn.edu.guet.mvc.annotation.Controller;
import cn.edu.guet.mvc.annotation.RequestMapping;
import cn.edu.guet.util.DBConnection;
import cn.edu.guet.util.WangEditor;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author liwei
 * @Date 2023/7/14 11:19
 * @Version 1.0
 */
@Controller
public class UploadController {
    @RequestMapping("/upload")
    public WangEditor upload(HttpServletRequest request, HttpServletResponse response) {
        String dir = System.getProperty("user.dir");
        dir = dir.substring(0, dir.lastIndexOf("\\"));
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("\\");
        uri = uri.substring(index + 1);
        Gson gson = new Gson();
        String realPath = dir + "\\webapps\\upload";
        System.out.println("实际路径：" + realPath);
        // 检查输入请求是否为multipart表单数据。
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart == true) {
            // 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator<FileItem> itr = items.iterator();
            // String filePath=System.getProperty("user.dir")+ File.separator;
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                // 检查当前项目是普通表单项目还是上传文件。
                // 如果是普通表单项目，显示表单内容。
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    if (fieldName.equals("id")) {
                    } else if (fieldName.equals("title")) {
                    }
                } else {// 如果是上传文件，显示文件名。
                    File fullFile = new File(item.getName());
                    File savedFile = new File(realPath + "\\", fullFile.getName());
                    try {
                        item.write(savedFile);

                        response.setContentType("application/json;utf-8");
                        PrintWriter out = response.getWriter();
                        String url = "http:\\upload\\" + fullFile.getName();
                        System.out.println("图片URL：" + url);
                        //把图片url存入数据库
                        String[] strs = {url};
                        WangEditor editor = new WangEditor(strs);
                        System.out.println(gson.toJson(editor));
                        out.print(gson.toJson(editor));
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.print("the enctype must be multipart/form-data");
        }
        return null;
    }

    @RequestMapping("/saveNews")
    public void saveNews(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String newsContent = request.getParameter("mytxtIntro");
            Integer tpye = Integer.parseInt(request.getParameter("type"));
            System.out.println(">>>>>>>>>>>>>>>>>" + newsContent);
            //保存新闻
            News news = new News();
            news.setType(tpye);
            String newsTitle = "";
            //正则表达式拿到标题（带格式）
            Matcher matcher = Pattern.compile("<h1(.*)</h1>").matcher(newsContent);
            while (matcher.find()){
                newsTitle= matcher.group();
                //拿到不带格式的标题
                System.out.println(matcher.group(1).substring(matcher.group(1).lastIndexOf(">")+1));
            }
                /* 不带格式
                    Matcher matcher = Pattern.compile("<h1\\s(.*)>(.*)</h1>").matcher(newsContent);
                        while (matcher.find()){
                            newsTitle= matcher.group();
                        }
                 * */
            //设置时间、标题
            news.setCreateTime(new Date(System.currentTimeMillis()));
            news.setTitle(newsTitle);
            //去掉标题后的内容
            newsContent = newsContent.replaceAll(newsTitle,"");
            news.setContent(newsContent);

            SqlSession sqlSession = DBConnection.getSqlSession();
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            newsMapper.insert(news);
            sqlSession.commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
