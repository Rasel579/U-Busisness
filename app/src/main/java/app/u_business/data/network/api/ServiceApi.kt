package app.u_business.data.network.api


import app.u_business.data.network.query.cards.*
import app.u_business.data.network.query.events.EventBody
import app.u_business.data.network.query.events.EventIdBody
import app.u_business.data.network.query.events.UserEventBody
import app.u_business.data.network.query.library.FileBody
import app.u_business.data.network.query.library.UserFileBody
import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.query.offers.EditableOfferBody
import app.u_business.data.network.query.offers.OfferBody
import app.u_business.data.network.query.user.ChangePasswordBody
import app.u_business.data.network.query.user.LoginBody
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
import retrofit2.http.*
import app.u_business.presentation.ui.eventlist.response.EventItem

interface ServiceApi {

    // methods for business cards
    @POST("/api/fetchBusinessCard")
    fun getBusinessCard(@Body user: UserBody): BusinessCardResponse

    @POST("/api/editBusinessCard")
    fun editBusinessCard(
        @Body card: BusinessCardBody,
        @Part("files") file: RequestBody
    ): MessageResponse

    @GET("/api/fetchBusinessCards")
    fun getActiveBusinessCards(): FetchActivatedBusinessCards

    @POST("/api/searchBusinessCards")
    fun searchBusinessCards(@Body word: SearchBody): FetchActivatedBusinessCards

    @POST("/api/addFavoriteCard")
    fun addCardToFavorite(@Body card: FavoriteCardBody): MessageResponse

    @POST("/api/deleteFavoriteCard")
    fun deleteCardFromFavorite(@Body card: FavoriteCardBody): MessageResponse

    @POST("/api/fetchFavoritesCards")
    fun getFavoritesCards(@Body user: UserBody): FetchActivatedBusinessCards

    @GET("/api/tagslist")
    fun getTop20Tags(): TagListResponse

    @POST("/api/tagsfilter")
    fun getBusinessCardsByTag(@Body tag: TagBody): FetchActivatedBusinessCards

    @POST("/api/locationfilter50")
    fun getBusinessCardsByLocation50(@Body locationBody: LocationBody): FetchActivatedBusinessCards

    @POST("/api/locationfilter100")
    fun getBusinessCardsByLocation100(@Body locationBody: LocationBody): FetchActivatedBusinessCards


    //    methods for events
    @POST("/api/createevent")
    fun createEvent(@Body event: EventBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/join")
    fun joinToEvent(@Body eventUser: UserEventBody): MessageResponse

    @POST("/api/unjoin")
    fun unjoinFromEvent(@Body eventUser: UserEventBody): MessageResponse

    @POST("/api/members")
    fun getMembersFromEvent(@Body even: EventIdBody): FetchActivatedBusinessCards

    @POST("/api/editevents")
    fun editEvent(@Body event: EventBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/fetchevent")
    fun getEvent(@Body event: EventIdBody): FetchEventResponse

    @POST("/api/searchevents")
    fun searchEventByWord(@Body event: SearchBody): FetchEventResponse

    @GET("/api/fetchevents")
   suspend fun getEvents() : List<EventItem>


    // methods for library
    @POST("/api/addFile")
    fun addFile(@Body user: UserBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/fetchLastFiles")
    fun getLastFiles(@Body user: UserBody): FetchLastFiles

    @POST("/api/fetchImages")
    fun getImages(@Body user: UserBody): FetchLastFiles

    @POST("/api/fetchDocs")
    fun getDocs(@Body user: UserBody): FetchLastFiles

    @POST("/api/fetchAudio")
    fun getAudio(@Body user: UserBody): FetchLastFiles

    @POST("/api/fetchVideo")
    fun getVideo(@Body user: UserBody): FetchLastFiles

    @POST("/api/addFavoriteFile")
    fun addFavoriteFile(@Body user: UserFileBody): MessageResponse

    @POST("/api/deleteFavorfiles")
    fun deleteFavoriteFiles(@Body user: FileBody): MessageResponse

    @POST("/api/fetchFavorfiles")
    fun getFavoriteFiles(@Body user: FileBody): FetchLastFiles

    @POST("/api/updateTimeAction")
    fun updateTimeAction(@Body user: FileBody): MessageResponse

    @POST("/api/deleteFile")
    fun deleteFile(@Body user: FileBody): MessageResponse

    // methods for news
    @POST("/api/createnews")
    suspend fun createNews(@Body news: NewBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/editnews")
    suspend fun editNews(@Body news: NewBody, @Part("files") file: RequestBody): MessageResponse

    @GET("/api/opennews")
    suspend fun openNews(@Field("id_news") id: String): OpenNewsResponse

    @GET("/api/fetchnewslist")
    suspend fun getNewsList(): FetchNewsResponse

    @FormUrlEncoded
    @POST("/api/searchnews")
    suspend fun searchNews(@Field("word") word: String): FetchNewsResponse


    // methods for offers
    @POST("/api/createOffer")
    fun createOffer(@Body offer: OfferBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/fetchUserOffers")
    fun getUserOffers(@Field("iduser") id: String): FetchUserOffersResponse

    @GET("/api/fetchOffers")
    fun getOffers(): FetchUserOffersResponse

    @POST("/api/fetchHistoryOffers")
    fun getHistoryOffers(@Field("iduser") id: String): FetchUserOffersResponse

    @POST("/api/fetchOffer")
    fun getOfferById(@Field("idoffer") id: String): FetchUserOffersResponse

    @POST("/api/editOffers")
    fun editOffer(@Body offer: EditableOfferBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/updateTime")
    fun updateTime(@Field("idoffer") id: String): MessageResponse

    @POST("/api/searchoffers")
    fun searchOffers(@Field("word") id: String): SearchOffersResponse


    //methods for user
    @POST("/api/login")
    suspend fun loginUser(@Body user: LoginBody): LoginResponse

    @POST("/api/recovery")
    suspend fun recovery(@Body email: String): String

    @POST("/api/recovery")
    suspend fun recovery(@Body email: String) : String

    @GET("/api/facebook")
    fun loginUserWithFacebook(): LoginWithServiceResponse

    @GET("/api/google")
    fun loginUserWithGoogle(): LoginWithServiceResponse

    @POST("/api/reg")
    suspend fun registerUser(@Body user: RegistrationBody): LoginResponse

    @POST("/api/editProfile")
    fun editProfile(@Body profile: ProfileBody, @Part("files") file: RequestBody): MessageResponse

    @POST("/api/fetchProfile")
    fun getProfile(@Field("idUser") id: String): FetchProfileResponse

    @POST("/api/changePassword")
    fun changePasswordProfile(@Body newPassword: ChangePasswordBody): MessageResponse

    @POST("/api/locationupdate")
    fun locationUpdate(@Body newLocation: LocationBody): MessageResponse
}