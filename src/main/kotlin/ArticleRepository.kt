


import java.io.*
import java.util.*


object ArticleRepository {
    private val articles = mutableListOf<Article>()
    private var lastId = 0

    fun deleteArticle(article: Article) {
        articles.remove(article)
    }

    fun getArticleById(id: Int): Article? {
        for (article in articles) {
            if (article.id == id) {
                return article
            }
        }

        return null
    }


    fun addArticle(title: String, body: String) {
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()
        val random = Random()
        articles.add(Article(id, regDate, updateDate, title, body,1,random.nextInt(2)+1))
    }


    fun modifyArticle(id: Int, title: String, body: String) {
        val article = getArticleById(id)!!

        article.title = title
        article.body = body
        article.updateDate = Util.getNowDateStr()
    }

    fun getFilteredArticles(searchKeyword: String, page: Int, itemsCountInAPage: Int,boardId:Int): List<Article> {
        val filtered1Articles = getSearchKeywordFilteredArticles(articles, searchKeyword,boardId)
        val filtered2Articles = getPageFilteredArticles(filtered1Articles, page, itemsCountInAPage)


        return filtered2Articles
    }

    private fun getSearchKeywordFilteredArticles(articles: List<Article>, searchKeyword: String,boardId:Int): List<Article> {
        val filteredArticles = mutableListOf<Article>()

        for (article in articles) {
            if ( article.boardId==boardId) {  //article.title.contains(searchKeyword) ||
                filteredArticles.add(article)
            }
        }

        return filteredArticles
    }
    private fun getIdFilteredArticles(boardId: String){

    }
    private fun getPageFilteredArticles(articles: List<Article>, page: Int, itemsCountInAPage: Int): List<Article> {
        val filteredArticles = mutableListOf<Article>()

        val offsetCount = (page - 1) * itemsCountInAPage

        val startIndex = articles.lastIndex - offsetCount
        var endIndex = startIndex - (itemsCountInAPage - 1)

        if (endIndex < 0) {
            endIndex = 0
        }

        for (i in startIndex downTo endIndex) {
            filteredArticles.add(articles[i])
        }

        return filteredArticles
    }

    fun writeArticle(login: Int) {

        if (login != 0) {
            print("제목 : ")
            val title = readLineTrim()
            print("내용 : ")
            val body = readLineTrim()
            print("게시판 : ")
            val boardId= readLineTrim().toInt()
            val id = ++lastId
            val regDate = Util.getNowDateStr()
            val updateDate = Util.getNowDateStr()

            articles.add(Article(id, regDate, updateDate, title, body, login,boardId))
            val temp = Article(id, regDate, updateDate, title, body, login,boardId)



            File("C:\\Users\\SBS-\\IdeaProjects\\kotlin_simple_ssg3\\src\\datas\\article\\1.json").writeText("$temp")
        } else {
            println("로그인 해주세요")
        }
    }




}