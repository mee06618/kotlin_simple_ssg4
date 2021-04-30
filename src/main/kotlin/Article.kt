import java.io.Serializable
data class Article(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var title: String,
    var body: String,
    var memberId:Int=0,
    var boardId:Int

):Serializable

open class JsonArticle(){
    open val id: Int=0
    open val regDate: String=""
    open var updateDate: String=""
    open var title: String=""
    open var body: String=""
    open var memberId:Int=0
    open var boardId:Int = 0
}