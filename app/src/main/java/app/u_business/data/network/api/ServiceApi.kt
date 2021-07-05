package app.u_business.data.network.api

import EventIdBody
import app.u_business.data.network.query.cards.*
import app.u_business.data.network.query.events.EventBody
import app.u_business.data.network.query.events.UserEventBody
import app.u_business.data.network.query.library.FileBody
import app.u_business.data.network.query.library.UserFileBody
import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.tag_list.TagListResponse
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.data.network.response.events.fetch_event.FetchEventResponse
import app.u_business.data.network.response.library.fetch_last_files.FetchLastFiles
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.news.open_news.OpenNewsResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    // methods for business cards
    @POST("/api/fetchBusinessCard")
    fun getBusinessCard(@Body user: UserBody) : Call<BusinessCardResponse>

    @POST("/api/editBusinessCard")
    fun editBusinessCard(@Body card: BusinessCardBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @GET("/api/fetchBusinessCards")
    fun getActiveBusinessCards() : Call<FetchActivatedBusinessCards>

    @POST("/api/searchBusinessCards")
    fun searchBusinessCards(@Body word: SearchBody) : Call<FetchActivatedBusinessCards>

    @POST("/api/addFavoriteCard")
    fun addCardToFavorite(@Body card: FavoriteCardBody) : Call<MessageResponse>

    @POST("/api/deleteFavoriteCard")
    fun deleteCardFromFavorite(@Body card: FavoriteCardBody) : Call<MessageResponse>

    @POST("/api/fetchFavoritesCards")
    fun getFavoritesCards(@Body user: UserBody) : Call<FetchActivatedBusinessCards>

    @GET("/api/tagslist")
    fun getTop20Tags() : Call<TagListResponse>

    @POST("/api/tagsfilter")
    fun getBusinessCardsByTag(@Body tag: TagBody) : Call<FetchActivatedBusinessCards>

    @POST("/api/locationfilter50")
    fun getBusinessCardsByLocation50(@Body locationBody: LocationBody) : Call<FetchActivatedBusinessCards>

    @POST("/api/locationfilter100")
    fun getBusinessCardsByLocation100(@Body locationBody: LocationBody) : Call<FetchActivatedBusinessCards>


//    methods for events
    @POST("/api/createevent")
    fun createEvent(@Body event: EventBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/join")
    fun joinToEvent(@Body eventUser: UserEventBody) : Call<MessageResponse>

    @POST("/api/unjoin")
    fun unjoinFromEvent(@Body eventUser: UserEventBody) : Call<MessageResponse>

    @POST("/api/members")
    fun getMembersFromEvent(@Body even: EventIdBody) : Call<FetchActivatedBusinessCards>

    @POST("/api/editevents")
    fun editEvent(@Body event: EventBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/fetchevent")
    fun getEvent(@Body event: EventIdBody) : Call<FetchEventResponse>

    @POST("/api/searchevents")
    fun searchEventByWord(@Body event: SearchBody) : Call<FetchEventResponse>

    @GET("/api/fetchevents")
    fun getEvents() : Call<FetchEventResponse>


    // methods for library
    @POST("/api/addFile")
    fun addFile(@Body user: UserBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/fetchLastFiles")
    fun getLastFiles(@Body user: UserBody) : Call<List<FetchLastFiles>>

    @POST("/api/fetchImages")
    fun getImages(@Body user: UserBody) : Call<List<FetchLastFiles>>

    @POST("/api/fetchDocs")
    fun getDocs(@Body user: UserBody) : Call<List<FetchLastFiles>>

    @POST("/api/fetchAudio")
    fun getAudio(@Body user: UserBody) : Call<List<FetchLastFiles>>

    @POST("/api/fetchVideo")
    fun getVideo(@Body user: UserBody) : Call<List<FetchLastFiles>>

    @POST("/api/addFavoriteFile")
    fun addFavoriteFile(@Body user: UserFileBody) : Call<MessageResponse>

    @POST("/api/deleteFavorfiles")
    fun deleteFavoriteFiles(@Body user: FileBody) : Call<MessageResponse>

    @POST("/api/fetchFavorfiles")
    fun getFavoriteFiles(@Body user: FileBody) : Call<FetchLastFiles>

    @POST("/api/updateTimeAction")
    fun updateTimeAction(@Body user: FileBody) : Call<MessageResponse>

    @POST("/api/deleteFile")
    fun deleteFile(@Body user: FileBody) : Call<MessageResponse>

    // methods for news
    @POST("/api/createnews")
    fun createNews(@Body news: NewBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/editnews")
    fun editNews(@Body news: NewBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/opentnews")
    fun editNews(@Field("id_news") id: String) : Call<OpenNewsResponse>

    @GET("/api/fetchnewslist")
    fun getNewsList() : Call<FetchNewsResponse>

    @POST("/api/searchnews")
    fun searchNews(@Field("word") word: String) : Call<OpenNewsResponse>

}