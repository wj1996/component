package com.wj.utils;

import com.wj.dao.GoodsInfoDao;
import com.wj.model.GoodsInfo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class LuceneUtils {

    private static IndexWriter indexWriter = null;
    private static Analyzer analyzer = null;
    private static Directory directory = null;

    public static void main(String[] args) {
//        test();
        try {
//            writeLucenIndex();
            testSearch();
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static {
        try {
            directory = FSDirectory.open(Paths.get("data"));
            analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory,iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLucenIndex() {
        GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
        int pageSize = 20000;
        long totalPageSize = goodsInfoDao.getTotalPageSize(pageSize);
        for (int i = 1 ; i <= totalPageSize ; i++) {
            handleData(goodsInfoDao.getList(i,pageSize));
        }
    }

    private static void handleData(List<GoodsInfo> list) {
        if (null == list || list.size() < 1) return;
        for (GoodsInfo goodsInfo : list) {
            Document doc = new Document();
            doc.add(new Field("goods_id",goodsInfo.getGoodsId(), TextField.TYPE_STORED));
            doc.add(new Field("goods_name",goodsInfo.getGoodsName(),TextField.TYPE_STORED));
            doc.add(new Field("goods_img_url",goodsInfo.getGoodsImgUrl(),TextField.TYPE_STORED));
            doc.add(new DoublePoint("goods_price",goodsInfo.getGoodsPrice()));
            try {
                indexWriter.addDocument(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static IndexWriter getIndexWrite() {
        IndexWriter indexWriter = null;
        return indexWriter;
    }

    public static void test() {
        GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
        List<GoodsInfo> list = goodsInfoDao.getList(1, 10);
        System.out.println("1");
    }

    public static void test2() {
        GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
        System.out.println();
    }


    public static void testSearch() {
        try {
            long start = System.currentTimeMillis();
            DirectoryReader ireader = DirectoryReader.open(directory);
            long start2 = System.currentTimeMillis();
            IndexSearcher isearcher = new IndexSearcher(ireader);
            QueryParser queryParser = new QueryParser("goods_name", analyzer);
            Query query = queryParser.parse("vivo iQOO U1x手机4G新品【购机6重礼】大屏大电池闪充千元机学生安卓智能游戏手机全网通 曜光黑 6GB+64GB 标配版");
            ScoreDoc[] scoreDocs = isearcher.search(query,20).scoreDocs;
            System.out.println("耗时为：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("耗时为：" + (System.currentTimeMillis() - start2) + "ms");
            if (scoreDocs.length < 1)
                throw new RuntimeException("not hits......");
            for (int i = 0 ; i < scoreDocs.length ; i++) {
                Document doc = isearcher.doc(scoreDocs[i].doc);
                System.out.println(doc.get("goods_name") + ",goods_id : " + doc.get("goods_id"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}
