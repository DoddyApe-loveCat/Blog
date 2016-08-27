package com.liwei.lucene;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import com.sun.org.apache.regexp.internal.RE;
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
public class BlogIndex {

    private static String indexDir;

    private static final Logger logger = LoggerFactory.getLogger(BlogIndex.class);

    static {
        // 参考资料:http://outofmemory.cn/code-snippet/2770/Spring-usage-program-mode-duqu-properties-file
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

    private Directory directory;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public IndexWriter getWriter(){
        IndexWriter indexWriter = null;
        try {
            // TODO: 2016/8/22
            directory = FSDirectory.open(Paths.get(indexDir));
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory,iwc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public void addIndex(Blog blog){
        IndexWriter indexWriter = getWriter();
        Document document = new Document();
        document.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title",String.valueOf(blog.getTitle()), Field.Store.YES));
        String dateStr = sdf.format(blog.getReleaseDate());
        document.add(new StringField("releaseDate",dateStr, Field.Store.YES));
        // // TODO: 2016/8/22  blog.getContentNoTag()
        document.add(new TextField("content",blog.getContent(), Field.Store.YES));
        try {
            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteIndex(String blogId){
        IndexWriter indexWriter = getWriter();
        try {
            indexWriter.deleteDocuments(new Term("id",blogId));
            indexWriter.forceMergeDeletes();// 强制删除
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateIndex(Blog blog){
        IndexWriter indexWriter = getWriter();
        Document document = new Document();
        document.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title",String.valueOf(blog.getTitle()), Field.Store.YES));
        String dateStr = sdf.format(blog.getReleaseDate());
        document.add(new StringField("releaseDate",dateStr, Field.Store.YES));
        try {
            indexWriter.updateDocument(new Term("id",String.valueOf(blog.getId())),document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Blog> searchBlog(String q){
        try {
            directory = FSDirectory.open(Paths.get(indexDir));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            QueryParser parser1 = new QueryParser("title",analyzer);
            Query query1 = parser1.parse(q);

            QueryParser parser2 = new QueryParser("content",analyzer);
            Query query2 = parser2.parse(q);

            booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
            booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

            TopDocs hits = indexSearcher.search(booleanQuery.build(),100);
            QueryScorer scorer = new QueryScorer(query1);

            // 高亮
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
            highlighter.setTextFragmenter(fragmenter);

            List<Blog> blogList = new ArrayList<>();
            Document doc = null;
            Blog blog = null;
            for(ScoreDoc scoreDoc:hits.scoreDocs){
                doc = indexSearcher.doc(scoreDoc.doc);
                blog = new Blog();
                blog.setId(Integer.parseInt(doc.get("id")));
                blog.setReleaseDateStr(doc.get("releaseData"));
                String title = doc.get("title");
                String content = StringEscapeUtils.escapeXml(doc.get("content"));

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
                return blogList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return null;
    }

}
