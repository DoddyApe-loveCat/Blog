package com.liwei.service.impl;

import com.liwei.entity.Blog;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Liwei on 2016/8/22.
 */
@Service
public class BlogIndexService {
    private static final Logger logger = LoggerFactory.getLogger(BlogIndexService.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 存储 Lucene 索引文件的路径
    private static String indexDir;
    private Directory directory;

    static {
        // 参考资料: Spring 使用程序方式读取 properties 文件
        // http://outofmemory.cn/code-snippet/2770/Spring-usage-program-mode-duqu-properties-file
        if(indexDir == null){
            Resource resource = new ClassPathResource("config/config.properties");
            try {
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                indexDir = properties.getProperty("index.dir");
                logger.info("logger => " + indexDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得写索引的实例
     * @return
     */
    public IndexWriter getIndexWriter(){
        IndexWriter indexWriter = null;
        try {
            directory = FSDirectory.open(Paths.get(indexDir));
            // 使用中文分词器
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory,iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    /**
     * 添加博客索引
     * @param blog 一个博客对象
     * 说明：id 存储，但是不分词，id 没有必要分词，所以使用 StringField，
     * title 标题，标题数量不多，所以要存储，因为搜索要用到，所以也要分词，用 TextField
     * releaseDate 发布时间，发布时间要存储，但是不分词，分词没有意义，所以用 StringField
     * content 文章的内容，文章的内容存储，也要分词，所以使用 TextField
     */
    public void addIndex(Blog blog){
        IndexWriter indexWriter = getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title",String.valueOf(blog.getTitle()), Field.Store.YES));
        String dateStr = sdf.format(blog.getReleaseDate());
        document.add(new StringField("releaseDate",dateStr, Field.Store.YES));
        // 添加索引的时候，博客的内容没有必要把标签信息作为索引的内容
        document.add(new TextField("content",blog.getContentNoTag(), Field.Store.YES));
        try {
            // 使用 IndexWriter 添加索引
            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定博客的索引（和更新指定博客的索引一样，通过 Term 对象，指定 id 进行删除）
     * @param blogId
     */
    public void deleteIndex(String blogId){
        IndexWriter indexWriter = getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id",blogId));
            // 强制删除
            indexWriter.forceMergeDeletes();
            // 索引提交（只有删除的时候有索引的提交，添加和更新的时候并不调用 commit() 方法）
            indexWriter.commit();
            // 索引关闭
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新博客索引（更新索引的内容其实和添加索引是一样的）
     *
     * 通过 Term （id） 这个条件进行索引的更新
     *
     * @param blog
     */
    public void updateIndex(Blog blog){
        IndexWriter indexWriter = getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title",String.valueOf(blog.getTitle()), Field.Store.YES));
        String dateStr = sdf.format(blog.getReleaseDate());
        document.add(new StringField("releaseDate",dateStr, Field.Store.YES));
        document.add(new TextField("content",blog.getContentNoTag(), Field.Store.YES));
        try {
            // 使用 IndexWriter 更新索引
            indexWriter.updateDocument(new Term("id",String.valueOf(blog.getId())),document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 【重要方法】
     * 根据关键字查询索引
     * @param q
     * @return
     */
    public List<Blog> searchBlog(String q){
        try {
            directory = FSDirectory.open(Paths.get(indexDir));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            // 同时搜索“标题”和“内容”中含有 搜索关键字的内容
            // 所以我们使用 BooleanQuery
            // 中文分词器只要用一个 ，但是 QueryParser 要用 2 个
            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            QueryParser parser1 = new QueryParser("title",analyzer);
            Query query1 = parser1.parse(q);

            QueryParser parser2 = new QueryParser("content",analyzer);
            Query query2 = parser2.parse(q);

            booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
            booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

            // 查找前 100 条符合条件的记录
            TopDocs hits = indexSearcher.search(booleanQuery.build(),100);
            QueryScorer scorer = new QueryScorer(query1);

            // 高亮
            // 通过得分包装一个 Fragmenter 片段对象
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
            // 将搜索关键字进行高亮格式设置（前后加上 HTML 的标签）
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
            // 根据 格式对象 和 得分对象 创建高亮对象
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
            // 设置高亮对象的文本片段对象
            highlighter.setTextFragmenter(fragmenter);


            // 封装搜索结果返回的对象（就使用 Blog 的 List 对象 最合适）
            List<Blog> blogList = new ArrayList<>();
            Document doc = null;
            Blog blog = null;
            for(ScoreDoc scoreDoc:hits.scoreDocs){
                // 从 Document 对象中获得关于 Blog 对象的相关信息
                doc = indexSearcher.doc(scoreDoc.doc);
                blog = new Blog();
                blog.setId(Integer.parseInt(doc.get("id")));
                blog.setReleaseDateStr(doc.get("releaseDate"));
                String title = doc.get("title");

                // 为了使得一些特殊符合能够正常显示
                // 参考资料：利用StringEscapeUtils对字符串进行各种转义与反转义（Java）
                // http://blog.csdn.net/chenleixing/article/details/43456987
                String content = StringEscapeUtils.escapeXml(doc.get("content"));

                // title 和 content 都是我们搜索的关键字的范围，所以下面这两个代码片段很关键
                // 目的是为了获得含有搜索关键字的最佳片段

                // 拿到最佳片段
                if(title!=null){
                    TokenStream tokenStream = analyzer.tokenStream("title",new StringReader(title));
                    String hTitle = highlighter.getBestFragment(tokenStream,title);
                    if(StringUtils.isBlank(hTitle)){
                        blog.setTitle(title);
                    }else {
                        blog.setTitle(title);
                    }
                }

                // 拿到最佳片段
                if(content!=null){
                    TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(content));
                    String hContent = highlighter.getBestFragment(tokenStream,content);
                    if(StringUtils.isBlank(hContent)){
                        if(content.length()<=200){
                            blog.setContent(content);
                        }else {
                            blog.setContent(content.substring(0,200));
                        }
                    }else {
                        blog.setContent(hContent);
                    }
                }
                blogList.add(blog);
            }
            return blogList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有索引文件(以方便重新建立索引)
     */
    public void deleteAll() throws IOException{
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteAll();
        indexWriter.commit();
        indexWriter.close();
        System.out.println("索引目录下的所有索引文件清空完毕！");
    }
}
