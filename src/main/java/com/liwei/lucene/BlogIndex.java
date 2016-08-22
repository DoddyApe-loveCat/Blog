package com.liwei.lucene;

import com.liwei.entity.Blog;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Liwei on 2016/8/22.
 */
public class BlogIndex {

    private Directory directory;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public IndexWriter getWriter(){
        IndexWriter indexWriter = null;
        try {
            // TODO: 2016/8/22
            directory = FSDirectory.open(Paths.get("/lucene"));
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
        return null;
    }


}
