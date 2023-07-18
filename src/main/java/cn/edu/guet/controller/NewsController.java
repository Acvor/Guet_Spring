package cn.edu.guet.controller;

import cn.edu.guet.bean.News;
import cn.edu.guet.bean.NewsExample;
import cn.edu.guet.common.ResponseData;
import cn.edu.guet.mapper.NewsMapper;
import cn.edu.guet.mvc.annotation.Controller;
import cn.edu.guet.mvc.annotation.RequestMapping;
import cn.edu.guet.util.DBConnection;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class NewsController {

    @RequestMapping("/viewNews")
    public ResponseData viewNews(Integer id){
        SqlSession sqlSession = DBConnection.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);

        return ResponseData.ok(newsMapper.selectByPrimaryKey(id));
    }

    @RequestMapping("/listImportantNews")
    public ResponseData listImportantNews(){
        SqlSession sqlSession = DBConnection.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria().andTypeEqualTo(1);
        List<News> importantNews = newsMapper.selectByExample(newsExample);
        //循环取
        for (int i=0;i<importantNews.size();i++) {
            String title = importantNews.get(i).getTitle();
            Matcher matcher = Pattern.compile(">(.*?)<").matcher(title);
            while (matcher.find()) {
                if(!matcher.group(1).equals("")) importantNews.get(i).setTitle(matcher.group(1));
            }
        }
        return ResponseData.ok(importantNews);
    }

}
