package app.u_business.data.network.api

import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.offers.OfferBody
import app.u_business.data.network.query.events.EventIdBody
import app.u_business.data.network.query.cards.*
import app.u_business.data.network.query.events.EventBody
import app.u_business.data.network.query.events.UserEventBody
import app.u_business.data.network.query.library.FileBody
import app.u_business.data.network.query.library.UserFileBody
import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.query.offers.EditableOfferBody
import app.u_business.data.network.query.user.ChangePasswordBody
import app.u_business.data.network.query.user.ProfileBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.tag_list.TagListResponse
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.data.network.response.events.fetch_event.FetchEventResponse
import app.u_business.data.network.response.library.fetch_last_files.FetchLastFiles
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.news.open_news.OpenNewsResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponse
import app.u_business.data.network.response.offers.search_offers.SearchOffersResponse
import app.u_business.data.network.response.user.fetch.FetchProfileResponse
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.data.network.response.user.login.LoginWithServiceResponse
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


    // methods for offers
    @POST("/api/createOffer")
    fun createOffer(@Body offer: OfferBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/fetchUserOffers")
    fun getUserOffers(@Field("iduser") id: String) : Call<FetchUserOffersResponse>

    @GET("/api/fetchOffers")
    fun getOffers() : Call<FetchUserOffersResponse>

    @POST("/api/fetchHistoryOffers")
    fun getHistoryOffers(@Field("iduser") id: String) : Call<FetchUserOffersResponse>

    @POST("/api/fetchOffer")
    fun getOfferById(@Field("idoffer") id: String) : Call<FetchUserOffersResponse>

    @POST("/api/editOffers")
    fun editOffer(@Body offer: EditableOfferBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/updateTime")
    fun updateTime(@Field("idoffer") id: String) : Call<MessageResponse>

    @POST("/api/searchoffers")
    fun searchOffers(@Field("word") id: String) : Call<SearchOffersResponse>


    //methods for user
    @POST("/api/login")
    fun loginUser(@Body user: LoginBody, @Part("files") file: RequestBody) : Call<LoginResponse>

    @GET("/api/facebook")
    fun loginUserWithFacebook() : Call<LoginWithServiceResponse>

    @GET("/api/google")
    fun loginUserWithGoogle() : Call<LoginWithServiceResponse>

    @POST("/api/reg")
    fun registerUser(@Body user: RegistrationBody) : Call<LoginResponse>

    @POST("/api/editProfile")
    fun editProfile(@Body profile: ProfileBody, @Part("files") file: RequestBody) : Call<MessageResponse>

    @POST("/api/fetchProfile")
    fun getProfile(@Field("idUser") id: String) : Call<FetchProfileResponse>

    @POST("/api/changePassword")
    fun changePasswordProfile(@Body newPassword: ChangePasswordBody) : Call<MessageResponse>

    @POST("/api/locationupdate")
    fun locationUpdate(@Body newLocation: LocationBody) : Call<MessageResponse>
}